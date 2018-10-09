package com.szcti.lcloud.purchase.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.TBackPurchase;
import com.szcti.lcloud.purchase.entity.TBackPurchaseDetail;
import com.szcti.lcloud.purchase.entity.TPeople;
import com.szcti.lcloud.purchase.entity.TPurchaseBook;
import com.szcti.lcloud.purchase.entity.TPurchaseOrder;
import com.szcti.lcloud.purchase.entity.vo.BackPurchaseVO;
import com.szcti.lcloud.purchase.entity.vo.TBackPurchaseBookVO;
import com.szcti.lcloud.purchase.mapper.TBackPurchaseDetailMapper;
import com.szcti.lcloud.purchase.mapper.TBackPurchaseMapper;
import com.szcti.lcloud.purchase.mapper.TPeopleMapper;
import com.szcti.lcloud.purchase.mapper.TPurchaseBookMapper;
import com.szcti.lcloud.purchase.mapper.TPurchaseOrderMapper;
import com.szcti.lcloud.purchase.mapper.TUserMapper;

@Component
@Path("backPurchase")
public class BackPurchaseResource {

	@Autowired
	TBackPurchaseDetailMapper backPurchaseDetailMapper;
	@Autowired
	TBackPurchaseMapper backPurchaseMapper;
	@Autowired
	TPeopleMapper peopleMapper;
	@Autowired
	TPurchaseBookMapper purchaseBookMapper;
	@Autowired
	TPurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	TUserMapper userMapper;
	@Autowired
	private JwtYmlConfig jwtYmlConfig;

	@SuppressWarnings({ "unchecked","rawtypes" })
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R list(@QueryParam("purchaseCode") String purchaseCode, @QueryParam("operName") String operName,
			@QueryParam("timeStart") String timeStart, @QueryParam("timeEnd") String timeEnd,
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@HeaderParam("Authorization") String authToken) {
		try {
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 20;
			}
			
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long orgId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
			if (userid == null || !(userid > 0) || orgId ==null) {
				return R.error().put("msg", "无权登录此平台！请联系管理员。");
			}
			EntityWrapper<TBackPurchase> ew = new EntityWrapper<>();
			ew.orderBy("create_time", false);
			ew.eq("lib_id", orgId);
			
			if (StringUtils.hasText(purchaseCode)) {
				ew.eq("purchase_code", purchaseCode);
			}
			List<Long> ids = new ArrayList<>();
			if (StringUtils.hasLength(operName)) {
				ids.add(-1L);
				EntityWrapper<TPeople> ewp = new EntityWrapper<>();
				ewp.like("username", operName);
				List<TPeople> ls = peopleMapper.selectList(ewp);
				for (TPeople tPeople : ls) {
					ids.add(tPeople.getUserId());
				}
			}
			if (ids.size() > 0) {
				ew.in("operator_id", ids);
			}
			if (StringUtils.hasText(timeStart)) {
				ew.ge("create_time", DateUtils.parseDate(timeStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.hasLength(timeEnd)) {
				ew.le("create_time", DateUtils.parseDate(timeEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}
			PageHelper.startPage(pageNum, pageSize);
			List<TBackPurchase> tbpls = backPurchaseMapper.selectList(ew);
			PageInfo page = new PageInfo<>(tbpls);

			List<BackPurchaseVO> bpls = new ArrayList<>();
			for (TBackPurchase tBackPurchase : tbpls) {
				BackPurchaseVO vo = new BackPurchaseVO();
				BeanUtils.copyProperties(tBackPurchase, vo);
				
				List<TPeople> pls = peopleMapper.selectList(new EntityWrapper<TPeople>().eq("user_id", tBackPurchase.getOperatorId()));
				if(pls!=null && pls.size()>0) {
					TPeople p = pls.get(0);
					vo.setOperatorName(p.getUsername());
				}
				List<TPurchaseOrder> pols = purchaseOrderMapper.selectList(
						new EntityWrapper<TPurchaseOrder>().eq("purchase_code", tBackPurchase.getPurchaseCode()));
				if (pols.size() > 0) {
					TPurchaseOrder po = pols.get(0);
					vo.setTotalQuantity(po.getTotalQuantity());
					vo.setBookTypeQty(po.getBookTypeQty());

					List<TBackPurchaseBookVO> books = new ArrayList<>();

					List<TBackPurchaseDetail> bpdls = backPurchaseDetailMapper.selectList(
							new EntityWrapper<TBackPurchaseDetail>().eq("back_purchase_id", tBackPurchase.getId()));
					for (TBackPurchaseDetail tBackPurchaseDetail : bpdls) {
						TPurchaseBook b = purchaseBookMapper.selectById(tBackPurchaseDetail.getPurchaseBookId());
						TBackPurchaseBookVO bpb = new TBackPurchaseBookVO();
						BeanUtils.copyProperties(b, bpb);
						bpb.setBackQuantity(tBackPurchaseDetail.getBackQuantity());
						books.add(bpb);
					}

					vo.setBooks(books);
				}

				bpls.add(vo);
			}
			page.setList(bpls);

			return R.ok().put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
