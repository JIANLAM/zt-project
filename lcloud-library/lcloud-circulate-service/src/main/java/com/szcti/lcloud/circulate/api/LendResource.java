package com.szcti.lcloud.circulate.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.circulate.config.JwtYmlConfig;
import com.szcti.lcloud.circulate.entity.SysBasicparam;
import com.szcti.lcloud.circulate.entity.TBook;
import com.szcti.lcloud.circulate.entity.TCredit;
import com.szcti.lcloud.circulate.entity.THelpLendback;
import com.szcti.lcloud.circulate.entity.THolding;
import com.szcti.lcloud.circulate.entity.TLend;
import com.szcti.lcloud.circulate.entity.TLendRule;
import com.szcti.lcloud.circulate.entity.TLendbuyBook;
import com.szcti.lcloud.circulate.entity.TNoticeInfo;
import com.szcti.lcloud.circulate.entity.TNoticeRead;
import com.szcti.lcloud.circulate.entity.TOperationLog;
import com.szcti.lcloud.circulate.entity.TOrganization;
import com.szcti.lcloud.circulate.entity.TPeople;
import com.szcti.lcloud.circulate.entity.TPrebook;
import com.szcti.lcloud.circulate.entity.TPrelend;
import com.szcti.lcloud.circulate.entity.TReader;
import com.szcti.lcloud.circulate.entity.TReaderFinance;
import com.szcti.lcloud.circulate.entity.TReaderType;
import com.szcti.lcloud.circulate.entity.TRelend;
import com.szcti.lcloud.circulate.entity.TUser;
import com.szcti.lcloud.circulate.entity.vo.HelpLendVO;
import com.szcti.lcloud.circulate.entity.vo.HoldBookVO;
import com.szcti.lcloud.circulate.entity.vo.LendBuyVO;
import com.szcti.lcloud.circulate.entity.vo.ReaderLendVO;
import com.szcti.lcloud.circulate.entity.vo.ReaderVO;
import com.szcti.lcloud.circulate.mapper.SysBasicparamMapper;
import com.szcti.lcloud.circulate.mapper.TBookMapper;
import com.szcti.lcloud.circulate.mapper.TCreditMapper;
import com.szcti.lcloud.circulate.mapper.THelpLendbackMapper;
import com.szcti.lcloud.circulate.mapper.THoldingMapper;
import com.szcti.lcloud.circulate.mapper.TLendMapper;
import com.szcti.lcloud.circulate.mapper.TLendRuleMapper;
import com.szcti.lcloud.circulate.mapper.TLendbuyBookMapper;
import com.szcti.lcloud.circulate.mapper.TNoticeInfoMapper;
import com.szcti.lcloud.circulate.mapper.TNoticeReadMapper;
import com.szcti.lcloud.circulate.mapper.TOperationLogMapper;
import com.szcti.lcloud.circulate.mapper.TOrganizationMapper;
import com.szcti.lcloud.circulate.mapper.TPeopleMapper;
import com.szcti.lcloud.circulate.mapper.TPrebookMapper;
import com.szcti.lcloud.circulate.mapper.TPrelendMapper;
import com.szcti.lcloud.circulate.mapper.TReaderFinanceMapper;
import com.szcti.lcloud.circulate.mapper.TReaderMapper;
import com.szcti.lcloud.circulate.mapper.TReaderTypeMapper;
import com.szcti.lcloud.circulate.mapper.TRelendMapper;
import com.szcti.lcloud.circulate.mapper.TUserMapper;
import com.szcti.lcloud.circulate.service.TPeopleService;
import com.szcti.lcloud.circulate.service.TReaderService;
import com.szcti.lcloud.circulate.util.CsvWriter;
import com.szcti.lcloud.common.utils.FileUploader;
import com.szcti.lcloud.common.utils.HttpServletRequestUtil;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;

@Component
@Path("lend")
public class LendResource {

	private static final Logger log = LoggerFactory.getLogger(LendResource.class);

	@Autowired
	private JwtYmlConfig jwtYmlConfig;

	@Autowired
	private TReaderService readerService;
	@Autowired
	private TPeopleService peopleService;
	@Autowired
	private TOrganizationMapper organizationMapper;
	@Autowired
	private TLendMapper lendMapper;
	@Autowired
	private TPrelendMapper prelendMapper;

	@Autowired
	private TUserMapper userMapper;

	@Autowired
	private TPeopleMapper peopleMapper;
	@Autowired
	private TReaderTypeMapper readerTypeMapper;
	@Autowired
	private TReaderMapper readerMapper;
	@Autowired
	private TCreditMapper creditMapper;
	@Autowired
	private THoldingMapper holdingMapper;
	@Autowired
	private TBookMapper bookMapper;
	@Autowired
	private TOperationLogMapper operationLogMapper;
	@Autowired
	private TRelendMapper relendMapper;
	@Autowired
	private TReaderFinanceMapper readerFinanceMapper;
	@Autowired
	private TNoticeInfoMapper noticeInfoMapper;
	@Autowired
	private TNoticeReadMapper noticeReadMapper;
	@Autowired
	private THelpLendbackMapper helpLendbackMapper;
	@Autowired
	private TLendbuyBookMapper lendbuyBookMapper;
	@Autowired
	private TPrebookMapper prebookMapper;
	@Autowired
	private TLendRuleMapper lendRuleMapper;
	@Autowired
	private SysBasicparamMapper sysBasicparamMapper;

	@Autowired
	private FileUploader fileUploader;

	@Path("/lendbook")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendbook(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("readerId") Long readerId, @QueryParam("holdingId") Long holdingId) {

		if (readerId == null || holdingId == null) {
			return R.error("参数错误！");
		}

		THolding h = holdingMapper.selectById(holdingId);
		if (h.getStatus().intValue() != 0) {
			R r = R.error("此书不外借！");
			switch (h.getStatus().intValue()) {
			case 1:
				r = R.error("此书已借出！");
				break;
			case 2:
				r = R.error("此书只能管内阅读，不外借！");
				break;
			case 3:
				r = R.error("此书污损，不外借！");
				break;
			case 4:
				r = R.error("此书遗失，不外借！");
				break;
			case 5:
				r = R.error("此书剔除，不外借！");
				break;

			default:
				r = R.error("此书不外借！");
				break;
			}
			return r;

		}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);
		// if (h.getOwnlib().longValue() != u.getOrgId().longValue()) {
		// return R.error("此书不属于本馆，不能外借！");
		// }
		TBook b = bookMapper.selectById(h.getBookId());

		TReader reader = readerMapper.selectById(readerId);
		
		if(reader==null) {
			return R.error("未查询到读者信息！");
		}
		
		if(reader.getEndDate()==null || reader.getEndDate().before(new Date())) {
			return R.error("读者证已过期！");
		}
		if(reader.getStatus()==null || reader.getStatus()!=1) {
			return R.error("证件状态不正常！");
		};
		
		List<Integer> ls = new ArrayList<>();
		ls.add(0);
		ls.add(2);
		ls.add(3);
		int borrow = lendMapper
				.selectCount(new EntityWrapper<TLend>().eq("reader_id", readerId).and().in("lend_status", ls));

		TReaderType trt = readerTypeMapper.selectById(reader.getReaderType());
		if (trt == null) {
			return R.error("用户类型不存在！");
		}
		Long inlibrule = trt.getInLibLendrule();

		TLendRule inrule = lendRuleMapper.selectById(inlibrule);
		if (inrule == null) {
			return R.error("借阅规则不存在！");
		}
		if (inrule.getStatus() != 1) {
			return R.error("借阅规则未启用！");
		}

		int lendnum = 5;
		int maxday = 30;

		lendnum = inrule.getLendQty();
		maxday = inrule.getLendDays();

		if (borrow >= lendnum) {
			return R.error("已达最大借书量！");
		}
		Integer cre = inrule.getCredit();
		if (cre != null) {
			try {
				TPeople peop = peopleMapper.selectById(reader.getPeopleId());
				List<TCredit> crls = creditMapper
						.selectList(new EntityWrapper<TCredit>().eq("user_id", peop.getUserId()));
				if (crls.size() > 0) {

					TCredit c = crls.get(0);
					if (c.getOwnValue() < cre) {
						return R.error("信用不足！读者信用" + c.getOwnValue() + "，借书所需信用" + cre);
					}

				} else {
					TCredit c = new TCredit();
					c.setCardStatus(reader.getStatus());
					c.setDefaultValue(100);
					c.setIslendBuy(1);
					c.setOwnValue(100);
					c.setStatus(1);
					c.setSummary("");
					c.setUserId(peop.getUserId());
					creditMapper.insert(c);

					if (c.getOwnValue() < cre) {
						return R.error("信用不足！读者信用" + c.getOwnValue() + "，借书所需信用" + cre);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<TPrelend> prls = prelendMapper
				.selectList(new EntityWrapper<TPrelend>().eq("holding_id", h.getId()).and().eq("prelend_status", 0));
		if (prls.size() > 0) {
			TPrelend rl = prls.get(0);
			if (rl.getReaderId().longValue() != reader.getId().longValue()) {
				return R.error("此书已预借！");
			} else {
				rl.setPrelendStatus(1);
				prelendMapper.updateById(rl);
			}
		}
		TLend entity = new TLend();

		entity.setDueBackTime(DateUtils.addDays(new Date(), maxday));
		entity.setHoldingId(h.getId());
		entity.setLendStatus(0);
		entity.setLendTime(new Date());
		entity.setReaderId(readerId);
		entity.setRenew(0);
		entity.setUserId(u.getId());
		entity.setLendLibId(u.getOrgId());//借书图书管ID
		lendMapper.insert(entity);

		h.setStatus(1);
		holdingMapper.updateById(h);

		String libraryName = "";
		try {

			String ip = HttpServletRequestUtil.getIpAddr(request);

			TOrganization org = organizationMapper.selectById(u.getOrgId());
			libraryName = org.getName();
			TOperationLog oprlog = new TOperationLog();
			oprlog.setLibraryId(u.getOrgId());
			oprlog.setLoginName(u.getLoginName());
			oprlog.setLibraryName(libraryName);
			oprlog.setModuleId(10);
			oprlog.setModuleName("流通管理");
			oprlog.setOpContent(reader.getReaderCardNumber() + " 借书 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
			oprlog.setOperationType("借出处理");
			oprlog.setIp(ip);
			oprlog.setUserId(u.getId());
			oprlog.setUserName(u.getLoginName());

			operationLogMapper.insert(oprlog);

		} catch (Exception e) {
			e.printStackTrace();
		}

		HoldBookVO v = new HoldBookVO();
		BeanUtils.copyProperties(b, v);
		BeanUtils.copyProperties(h, v);
		v.setDueBackTime(entity.getDueBackTime());
		v.setLibraryName(libraryName);
		v.setLendTime(entity.getLendTime());
		List<HoldBookVO> l = new ArrayList<>();
		l.add(v);
		return R.ok().put("lend", l);
	}

	@Path("/relendbookBarcode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R relendbookBarcode(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("barcode") String barcode, @QueryParam("rfid") String rfid) {

		if (barcode == null && rfid == null) {
			return R.error("参数错误！");
		}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		EntityWrapper<THolding> ew = new EntityWrapper<THolding>();
		if (StringUtils.hasLength(barcode)) {
			ew.eq("barcode", barcode);
		}
		if (StringUtils.hasLength(rfid)) {
			ew.eq("barcode", rfid);
		}
		HoldBookVO v = new HoldBookVO();
		List<THolding> hls = holdingMapper.selectList(ew);
		List<Integer> ls = new ArrayList<>();
		ls.add(0);
		ls.add(2);
		ls.add(3);
		if (hls.size() > 0) {
			THolding h = hls.get(0);
			TBook b = bookMapper.selectById(h.getBookId());
			List<TLend> lls = lendMapper
					.selectList(new EntityWrapper<TLend>().eq("holding_id", h.getId()).and().in("lend_status", ls));
			if (lls.size() > 0) {

				TLend lend = lls.get(0);

				TReader reader = readerMapper.selectById(lend.getReaderId());

				// TReaderType trt = readerTypeMapper.selectById(reader.getReaderType());
				List<TLendRule> rls = lendRuleMapper
						.selectList(new EntityWrapper<TLendRule>().eq("status", 1).and().eq("library_id", u.getOrgId()));
				int renewqty = 1;
				int renewdays = 30;
				if (rls.size() > 0) {
					TLendRule rule = rls.get(0);

					renewqty = rule.getRenewQty();
					renewdays = rule.getRenewDays();
				}
				int c = relendMapper.selectCount(new EntityWrapper<TRelend>().eq("lend_id", lend.getId()));
				if (c >= renewqty) {
					return R.error("不能续借，已达最大续借次数" + c + "次!");
				}

				Date primaryBackTime = lend.getDueBackTime();
				lend.setRenew(1);
				Date relendBackTime = DateUtils.addDays(new Date(), renewdays);
				lend.setDueBackTime(relendBackTime);
				lendMapper.updateById(lend);

				TRelend relend = new TRelend();
				relend.setLendId(lend.getId());
				relend.setPrimaryBackTime(primaryBackTime);
				relend.setRelendBackTime(relendBackTime);
				relend.setRelendTime(new Date());
				relendMapper.insert(relend);

				TOrganization org = organizationMapper.selectById(h.getOwnlib());
				String libraryName = org.getName();

				BeanUtils.copyProperties(b, v);
				BeanUtils.copyProperties(h, v);
				v.setDueBackTime(lend.getDueBackTime());
				v.setLibraryName(libraryName);
				v.setLendTime(lend.getLendTime());
				v.setRelendNum(c + 1);

				try {

					String ip = HttpServletRequestUtil.getIpAddr(request);

					TOperationLog oprlog = new TOperationLog();
					oprlog.setLibraryId(u.getOrgId());
					oprlog.setLoginName(u.getLoginName());
					oprlog.setLibraryName(libraryName);
					oprlog.setModuleId(10);
					oprlog.setModuleName("流通管理");
					oprlog.setOpContent(
							reader.getReaderCardNumber() + " 续借 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
					oprlog.setOperationType("续借处理");
					oprlog.setIp(ip);
					oprlog.setUserId(u.getId());
					oprlog.setUserName(u.getLoginName());

					operationLogMapper.insert(oprlog);

				} catch (Exception e) {
					e.printStackTrace();
				}

				return R.ok().put("relendbook", v);

			} else {
				return R.error("未查询到书籍借阅信息！");
			}
		} else {
			return R.error("未查询到书籍信息！");
		}
	}

	@Path("/relendbook")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R relendbook(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			String data) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		JSONArray ja = new JSONArray();
		try {
			ja = JSON.parseArray(data);
			if (ja == null || ja.size() == 0) {
				return R.error("参数不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("参数错误！");
		}

		List<HoldBookVO> l = new ArrayList<>();
		R r = new R();
		for (int i = 0; i < ja.size(); i++) {

			HoldBookVO v = new HoldBookVO();

			Long lendid = ja.getLong(i);

			v.setLendId(lendid);

			TLend lend = lendMapper.selectById(lendid);
			if (lend == null) {
				v.setRelendMsg("未查询到借阅信息");
				continue;
			}
			THolding h = holdingMapper.selectById(lend.getHoldingId());
			if (h == null) {
				v.setRelendMsg("未查询到馆藏信息");
				continue;
			}
			TBook b = bookMapper.selectById(h.getBookId());
			if (b == null) {
				v.setRelendMsg("未查询到书籍信息");
				continue;
			}
			TReader reader = readerMapper.selectById(lend.getReaderId());

			if (reader == null) {
				v.setRelendMsg("未查询到读者信息");
				continue;
			}
			
			if(reader.getEndDate()==null || reader.getEndDate().before(new Date())) {
				v.setRelendMsg("读者证已过期！");
				continue;
			}
			if(reader.getStatus()==null || reader.getStatus()!=1) {
				v.setRelendMsg("证件状态不正常！");
				continue;
			}
			
			TReaderType trt = readerTypeMapper.selectById(reader.getReaderType());
			if (trt == null) {
				return R.error("用户类型不存在！");
			}
			Long inlibrule = trt.getInLibLendrule();

			TLendRule inrule = lendRuleMapper.selectById(inlibrule);
			if (inrule == null) {
				return R.error("借阅规则不存在！");
			}
			if (inrule.getStatus() != 1) {
				return R.error("借阅规则未启用！");
			}

			int renewqty = 1;
			int renewdays = 30;

			renewqty = inrule.getRenewQty();
			renewdays = inrule.getRenewDays();

			int c = relendMapper.selectCount(new EntityWrapper<TRelend>().eq("lend_id", lend.getId()));
			if (c >= renewqty) {

				v.setRelendNum(c);
				v.setRelendMsg("不能续借，已达最大续借次数" + renewqty + "次!");

			} else {

				Date primaryBackTime = lend.getDueBackTime();
				lend.setRenew(1);
				Date relendBackTime = DateUtils.addDays(new Date(), renewdays);
				lend.setDueBackTime(relendBackTime);
				lendMapper.updateById(lend);

				TRelend relend = new TRelend();
				relend.setId(IdGen.randomLong());
				relend.setLendId(lend.getId());
				relend.setPrimaryBackTime(primaryBackTime);
				relend.setRelendBackTime(relendBackTime);
				relend.setRelendTime(new Date());
				relendMapper.insert(relend);

				v.setRelendNum(c + 1);

				v.setRelendMsg("续借成功!");

				String libraryName = "";
				try {

					String ip = HttpServletRequestUtil.getIpAddr(request);

					TOrganization org = organizationMapper.selectById(u.getOrgId());
					libraryName = org.getName();
					TOperationLog oprlog = new TOperationLog();
					oprlog.setLibraryId(u.getOrgId());
					oprlog.setLoginName(u.getLoginName());
					oprlog.setLibraryName(libraryName);
					oprlog.setModuleId(10);
					oprlog.setModuleName("流通管理");
					oprlog.setOpContent(
							reader.getReaderCardNumber() + " 续借 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
					oprlog.setOperationType("续借处理");
					oprlog.setIp(ip);
					oprlog.setUserId(u.getId());
					oprlog.setUserName(u.getLoginName());

					operationLogMapper.insert(oprlog);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			TOrganization org = organizationMapper.selectById(h.getOwnlib());
			String libraryName = org.getName();

			BeanUtils.copyProperties(b, v);
			BeanUtils.copyProperties(h, v);
			v.setDueBackTime(lend.getDueBackTime());
			v.setLibraryName(libraryName);
			v.setLendTime(lend.getLendTime());

			l.add(v);
		}
		r.put("relendbook", l);
		return r;
	}

	@Path("/lendbookInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendbookInfo(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("barcode") String barcode, @QueryParam("rfid") String rfid) {

		if (barcode == null && rfid == null) {
			return R.error("参数错误！");
		}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		EntityWrapper<THolding> ew = new EntityWrapper<THolding>();
		if (StringUtils.hasLength(barcode)) {
			ew.eq("barcode", barcode);
		}
		if (StringUtils.hasLength(rfid)) {
			ew.eq("barcode", rfid);
		}
		HoldBookVO v = new HoldBookVO();
		List<THolding> hls = holdingMapper.selectList(ew);
		if (hls.size() > 0) {
			THolding h = hls.get(0);
			TBook b = bookMapper.selectById(h.getBookId());
			List<TLend> lls = lendMapper
					.selectList(new EntityWrapper<TLend>().eq("holding_id", h.getId()).and().eq("lend_status", 0));
			if (lls.size() > 0) {

				TLend lend = lls.get(0);

				int c = relendMapper.selectCount(new EntityWrapper<TRelend>().eq("lend_id", lend.getId()));

				TOrganization org = organizationMapper.selectById(h.getOwnlib());
				String libraryName = org.getName();

				BeanUtils.copyProperties(b, v);
				BeanUtils.copyProperties(h, v);
				v.setDueBackTime(lend.getDueBackTime());
				v.setLibraryName(libraryName);
				v.setLendTime(lend.getLendTime());
				v.setRelendNum(c);

				return R.ok().put("lendbookInfo", v);

			} else {
				return R.error("未查询到书籍借阅信息！");
			}
		} else {
			return R.error("未查询到书籍信息！");
		}
	}

	@Path("/readerlendbookInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R readerlendbookInfo(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName// 读者姓名
			, @QueryParam("IDCard") String IDCard// 身份证号
	) {

		if (StringUtils.isEmpty(IDCard) && StringUtils.isEmpty(readerName) && StringUtils.isEmpty(readerCardNumber)) {
			return R.error("查询条件不能为空！");
		}
		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
		}

		if (StringUtils.hasLength(readerName)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("username", readerName));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}
		if (StringUtils.hasLength(IDCard)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("card_number", IDCard));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}

		List<HoldBookVO> hbvls = new ArrayList<>();
		List<TReader> tls = readerService.selectList(ew);
		List<Integer> ls = new ArrayList<>();
		ls.add(0);
		ls.add(2);
		ls.add(3);

		for (TReader tReader : tls) {
			List<TLend> lls = lendMapper.selectList(
					new EntityWrapper<TLend>().eq("reader_id", tReader.getId()).and().in("lend_status", ls));
			for (TLend tLend : lls) {
				HoldBookVO v = new HoldBookVO();
				Long hid = tLend.getHoldingId();
				THolding h = holdingMapper.selectById(hid);
				if(h==null) {
					continue;
				}
				TBook b = bookMapper.selectById(h.getBookId());
				if(b==null) {
					continue;
				}
				int c = relendMapper.selectCount(new EntityWrapper<TRelend>().eq("lend_id", tLend.getId()));
				TOrganization org = organizationMapper.selectById(h.getOwnlib());
				String libraryName = org.getName();
				BeanUtils.copyProperties(b, v);
				BeanUtils.copyProperties(h, v);
				v.setDueBackTime(tLend.getDueBackTime());
				v.setLibraryName(libraryName);
				v.setLendTime(tLend.getLendTime());
				v.setRelendNum(c);
				v.setLendId(tLend.getId());
				hbvls.add(v);
			}
		}

		return R.ok().put("readerlendbookInfo", hbvls);

	}

	@Path("/backbook")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R backbook(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("barcode") String barcode, @QueryParam("rfid") String rfid) {

		if (barcode == null && rfid == null) {
			return R.error("参数错误！");
		}
		
		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		EntityWrapper<THolding> ew = new EntityWrapper<THolding>();
		if (StringUtils.hasLength(barcode)) {
			ew.eq("barcode", barcode);
		}
		if (StringUtils.hasLength(rfid)) {
			ew.eq("barcode", rfid);
		}
		HoldBookVO v = new HoldBookVO();
		List<THolding> hls = holdingMapper.selectList(ew);
		if (hls.size() > 0) {
			THolding h = hls.get(0);
			TBook b = bookMapper.selectById(h.getBookId());
			List<TLend> lls = lendMapper
					.selectList(new EntityWrapper<TLend>().eq("holding_id", h.getId()).and().eq("lend_status", 0));
			if (lls.size() > 0) {
				TLend lend = lls.get(0);

				lend.setBackTime(new Date());
				lend.setLendStatus(1);
				lend.setBackLibId(u.getOrgId());//还书图书管ID
				lendMapper.updateById(lend);
				
				h.setStatus(0);
				holdingMapper.updateById(h);

				TOrganization org = organizationMapper.selectById(h.getOwnlib());
				String libraryName = org.getName();

				BeanUtils.copyProperties(b, v);
				BeanUtils.copyProperties(h, v);
				v.setDueBackTime(lend.getDueBackTime());
				v.setLibraryName(libraryName);
				v.setLendTime(lend.getLendTime());

				try {

				
					String ip = HttpServletRequestUtil.getIpAddr(request);

					TOperationLog oprlog = new TOperationLog();
					oprlog.setLibraryId(u.getOrgId());
					oprlog.setLoginName(u.getLoginName());
					oprlog.setLibraryName(libraryName);
					oprlog.setModuleId(10);
					oprlog.setModuleName("流通管理");
					oprlog.setOpContent(" 还书 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
					oprlog.setOperationType("还回处理");
					oprlog.setIp(ip);
					oprlog.setUserId(u.getId());
					oprlog.setUserName(u.getLoginName());

					operationLogMapper.insert(oprlog);

				} catch (Exception e) {
					e.printStackTrace();
				}

				return R.ok().put("backbook", v);

			} else {
				return R.error("未查询到书籍借阅信息！");
			}
		} else {
			return R.error("未查询到书籍信息！");
		}

	}

	@Path("/stained")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R stained(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("lendId") String lendId, @QueryParam("forfeit") Double forfeit,
			@QueryParam("payMethod") String payMethod, @QueryParam("status") String status) {

		if (lendId == null || forfeit == null) {
			return R.error("参数错误！");
		}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		TLend lend = lendMapper.selectById(lendId);
		if (lend == null) {
			return R.error("未查询到借阅信息！");
		}

		lend.setBackTime(new Date());
		lend.setLendStatus(1);
		lendMapper.updateById(lend);

		THolding h = holdingMapper.selectById(lend.getHoldingId());
		TBook b = bookMapper.selectById(h.getBookId());
		TReader r = readerMapper.selectById(lend.getReaderId());

		if (h.getStatus() == 1) {
			h.setStatus(3);
			holdingMapper.updateById(h);
		}

		TReaderFinance rf = new TReaderFinance();
		rf.setBarcode(h.getBarcode());
		rf.setBookId(b.getId());
		rf.setBookName(b.getTitle());
		rf.setCreateDate(new Date());
		rf.setFinanceDatetime(new Date());
		rf.setForfeit(forfeit);
		rf.setReaderCardNumber(r.getReaderCardNumber());
		rf.setReaderId(r.getId());
		rf.setStatus(NumberUtils.toInt(status, 1));
		rf.setType(202);
		rf.setTypeName("污损罚款");
		rf.setOperatorId(u.getId());
		rf.setPayMethod(NumberUtils.toInt(payMethod, 0));

		readerFinanceMapper.insert(rf);

		String libraryName = "";
		try {

			String ip = HttpServletRequestUtil.getIpAddr(request);

			TOrganization org = organizationMapper.selectById(u.getOrgId());
			libraryName = org.getName();
			TOperationLog oprlog = new TOperationLog();
			oprlog.setLibraryId(u.getOrgId());
			oprlog.setLoginName(u.getLoginName());
			oprlog.setLibraryName(libraryName);
			oprlog.setModuleId(10);
			oprlog.setModuleName("流通管理");
			oprlog.setOpContent(" 污损 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
			oprlog.setOperationType("污损处理");
			oprlog.setIp(ip);
			oprlog.setUserId(u.getId());
			oprlog.setRemark(h.getId() + "");
			oprlog.setUserName(u.getLoginName());

			operationLogMapper.insert(oprlog);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();

	}

	@Path("/lose")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lose(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("lendId") String lendId, @QueryParam("forfeit") Double forfeit,
			@QueryParam("payMethod") String payMethod, @QueryParam("status") String status) {

		if (lendId == null || forfeit == null) {
			return R.error("参数错误！");
		}
		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		TLend lend = lendMapper.selectById(lendId);
		if (lend == null) {
			return R.error("未查询到借阅信息！");
		}

		lend.setBackTime(new Date());
		lend.setLendStatus(1);
		lendMapper.updateById(lend);

		THolding h = holdingMapper.selectById(lend.getHoldingId());
		TBook b = bookMapper.selectById(h.getBookId());
		TReader r = readerMapper.selectById(lend.getReaderId());

		if (h.getStatus() == 1) {
			h.setStatus(4);
			holdingMapper.updateById(h);
		}

		TReaderFinance rf = new TReaderFinance();
		rf.setBarcode(h.getBarcode());
		rf.setBookId(b.getId());
		rf.setBookName(b.getTitle());
		rf.setCreateDate(new Date());
		rf.setFinanceDatetime(new Date());
		rf.setForfeit(forfeit);
		rf.setReaderCardNumber(r.getReaderCardNumber());
		rf.setReaderId(r.getId());
		rf.setStatus(NumberUtils.toInt(status, 1));
		rf.setType(203);
		rf.setTypeName("丢失罚款");
		rf.setOperatorId(u.getId());
		rf.setPayMethod(NumberUtils.toInt(payMethod, 0));

		readerFinanceMapper.insert(rf);

		String libraryName = "";
		try {

			String ip = HttpServletRequestUtil.getIpAddr(request);

			TOrganization org = organizationMapper.selectById(u.getOrgId());
			libraryName = org.getName();
			TOperationLog oprlog = new TOperationLog();
			oprlog.setLibraryId(u.getOrgId());
			oprlog.setLoginName(u.getLoginName());
			oprlog.setLibraryName(libraryName);
			oprlog.setModuleId(10);
			oprlog.setModuleName("流通管理");
			oprlog.setOpContent(" 丢失 " + h.getBarcode() + "<<" + b.getTitle() + ">>");
			oprlog.setOperationType("丢失处理");
			oprlog.setIp(ip);
			oprlog.setUserId(u.getId());
			oprlog.setRemark(h.getId() + "");
			oprlog.setUserName(u.getLoginName());

			operationLogMapper.insert(oprlog);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();

	}

	@Path("/reader")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R reader(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName// 读者姓名
			, @QueryParam("IDCard") String IDCard// 身份证号
	) {
		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		if (StringUtils.isEmpty(IDCard) && StringUtils.isEmpty(readerName) && StringUtils.isEmpty(readerCardNumber)) {
			return R.error("查询条件不能为空！");
		}

		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
		}

		if (StringUtils.hasLength(readerName)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("username", readerName));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}
		if (StringUtils.hasLength(IDCard)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("card_number", IDCard));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<TReader> tls = readerService.selectList(ew);
		List<ReaderVO> rls = new ArrayList<>();
		for (TReader tReader : tls) {
			ReaderVO rv = new ReaderVO();
			BeanUtils.copyProperties(tReader, rv);

			Long lid = tReader.getLibraryId();
			TOrganization org = organizationMapper.selectById(lid);
			if (org != null) {
				rv.setLibraryName(org.getName());
			}
			TPeople people = peopleService.selectById(tReader.getPeopleId());
			if (people != null) {
				rv.setReaderName(people.getUsername());
				Integer s = people.getSex();
				if (s == 1) {
					rv.setSex("男");
				} else {
					rv.setSex("女");
				}
				rv.setIDCard(people.getCardNumber());
			} else {
				log.error("数据错误，reader ID ={} 没有对应的people数据", tReader.getId());
				continue;
			}

			TReaderType trt = readerTypeMapper.selectById(tReader.getReaderType());
			List<TLendRule> rulels = lendRuleMapper
					.selectList(new EntityWrapper<TLendRule>().eq("status", 1).and().eq("library_id", u.getOrgId()));
			int renewqty = 1;
			int renewdays = 30;
			int lendnum = 0;
			if (rulels.size() > 0) {
				TLendRule rule = rulels.get(0);

				renewqty = rule.getRenewQty();
				renewdays = rule.getRenewDays();
				lendnum = rule.getLendQty();
			}
			if (trt != null) {
				rv.setReaderTypeName(trt.getName());
				rv.setLendNum(lendnum);
			} else {
				rv.setReaderTypeName("默认用户");
				rv.setLendNum(5);
			}

			List<Integer> ls = new ArrayList<>();
			ls.add(0);
			ls.add(2);
			ls.add(3);
			Integer borrow = lendMapper.selectCount(
					new EntityWrapper<TLend>().eq("reader_id", tReader.getId()).and().in("lend_status", ls));
			rv.setBorrow(borrow);

			List<TCredit> crels = creditMapper
					.selectList(new EntityWrapper<TCredit>().eq("user_id", people.getUserId()));
			if (crels.size() > 0) {
				TCredit c = crels.get(0);
				rv.setCredit(c.getOwnValue());
			} else {
				TCredit c = new TCredit();
				c.setCardStatus(tReader.getStatus());
				c.setDefaultValue(100);
				c.setIslendBuy(1);
				c.setOwnValue(100);
				c.setStatus(1);
				c.setSummary("");
				c.setUserId(people.getUserId());
				creditMapper.insert(c);

				rv.setCredit(c.getOwnValue());
			}

			rls.add(rv);
		}
		PageInfo<ReaderVO> p = new PageInfo<>(rls);

		return R.ok().put("page", p);
	}

	@Path("/book")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R book(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("barcode") String barcode, @QueryParam("rfid") String rfid) {
		if (StringUtils.isEmpty(barcode) && StringUtils.isEmpty(rfid)) {
			return R.error("查询参数不能为空！");
		}
		EntityWrapper<THolding> ew = new EntityWrapper<THolding>();
		if (StringUtils.hasLength(barcode)) {
			ew.eq("barcode", barcode);
		}
		if (StringUtils.hasLength(rfid)) {
			ew.eq("barcode", rfid);
		}
		HoldBookVO v = new HoldBookVO();
		List<THolding> hls = holdingMapper.selectList(ew);
		if (hls.size() > 0) {
			THolding h = hls.get(0);
			TBook b = bookMapper.selectById(h.getBookId());
			if(b==null) {
				return R.error("未查询到书籍信息!");
			}
			List<TLend> lls =lendMapper.selectList(new EntityWrapper<TLend>().eq("holding_id", h.getId()).and().eq("lend_status", 0));
			if(lls.size()>0) {
				TLend lend = lls.get(0);
				v.setLendId(lend.getId());
			}
			BeanUtils.copyProperties(b, v);
			BeanUtils.copyProperties(h, v);

			return R.ok().put("holding", v);
		}
		return R.error("未查询到书籍!");
	}

	@Path("/testSmtp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R testSmtp(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("username") String username, @QueryParam("password") String password,
			@QueryParam("smtphost") String smtphost) {

		Properties properties = new Properties();
		String st[] = smtphost.split(":");
		String smtp = smtphost;
		String sslport = "25";
		if (st.length > 1) {
			smtp = st[0];
			sslport = st[1];

			properties.setProperty("mail.smtp.auth", "true");// 开启认证
			properties.setProperty("mail.debug", "true");// 启用调试
			properties.setProperty("mail.smtp.timeout", "5000");// 设置链接超时
			properties.setProperty("mail.smtp.port", "" + NumberUtils.toInt(sslport, 465));// 设置端口
			properties.setProperty("mail.smtp.socketFactory.port", "" + NumberUtils.toInt(sslport, 465));// 设置ssl端口
			properties.setProperty("mail.smtp.socketFactory.fallback", "false");
			properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(smtp);// 指定用来发送Email的邮件服务器主机名
		mailSender.setPort(NumberUtils.toInt(sslport, 25));// 默认端口，标准的SMTP端口
		mailSender.setUsername(username);// 用户名
		mailSender.setPassword(password);// 密码

		mailSender.setJavaMailProperties(properties);

		try {
			mailSender.testConnection();
		} catch (MessagingException e) {
			e.printStackTrace();
			return R.error("验证失败，请重新填写！");
		}

		return R.ok("成功！");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Path("/noteicList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R noteicList(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@QueryParam("readerCardNumberStart") String readerCardNumberStart, //
			@QueryParam("readerCardNumberEnd") String readerCardNumberEnd, //
			@QueryParam("bookStatus") String bookStatus, // 0，在借；1，已逾期； 2，将逾期； 3，所有
			@QueryParam("lendtimeStart") String lendtimeStart // yyy-MM-dd
			, @QueryParam("lendtimeEnd") String lendtimeEnd, // yyy-MM-dd
			@QueryParam("duebacktimeStart") String duebacktimeStart, // yyy-MM-dd
			@QueryParam("duebacktimeEnd") String duebacktimeEnd) // yyy-MM-dd
	{

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		EntityWrapper<TReader> rew = new EntityWrapper<>();
		if (StringUtils.hasLength(readerCardNumberStart)) {
			rew.ge("reader_card_number", readerCardNumberStart);
		}
		if (StringUtils.hasLength(readerCardNumberEnd)) {
			rew.le("reader_card_number", readerCardNumberEnd);
		}
		List<Long> ridls = new ArrayList<>();
		if (StringUtils.hasLength(readerCardNumberStart) || StringUtils.hasLength(readerCardNumberEnd)) {
			ridls.add(-1L);
			List<TReader> rls = readerMapper.selectList(rew);
			for (TReader tReader : rls) {
				ridls.add(tReader.getId());
			}
		}
		EntityWrapper<TLend> lew = new EntityWrapper<>();
		if ("0".equals(bookStatus)) {// 在借

			lew.in("lend_status", "0,2,3");

		} else if ("1".equals(bookStatus)) {// 已逾期

			lew.le("due_back_time", new Date()).and().in("lend_status", "0,2,3");

		} else if ("2".equals(bookStatus)) {// 将逾期 ,3天内

			lew.between("due_back_time", DateUtils.addDays(new Date(), -3), new Date()).and().in("lend_status", "0,2");

		} else if ("3".equals(bookStatus)) {// 所有
			lew.in("lend_status", "0,2,3");
		} else {
			lew.in("lend_status", "0,2,3");
		}

		if (ridls.size() > 0) {
			lew.and().in("reader_id", ridls);
		}

		if (StringUtils.hasLength(lendtimeStart)) {
			try {
				Date d = DateUtils.parseDate(lendtimeStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				lew.ge("lend_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.hasLength(lendtimeEnd)) {
			try {
				Date d = DateUtils.parseDate(lendtimeEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				lew.le("lend_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.hasLength(duebacktimeStart)) {
			try {
				Date d = DateUtils.parseDate(duebacktimeStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				lew.ge("due_back_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.hasLength(duebacktimeEnd)) {
			try {
				Date d = DateUtils.parseDate(duebacktimeEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				lew.le("due_back_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		PageHelper.startPage(pageNum, pageSize);
		List<TLend> ls = lendMapper.selectList(lew);
		PageInfo page = new PageInfo<>(ls);
		List<ReaderLendVO> rlls = new ArrayList<>();
		for (TLend tLend : ls) {
			try {

				ReaderLendVO rlv = new ReaderLendVO();
				BeanUtils.copyProperties(tLend, rlv);

				THolding h = holdingMapper.selectById(tLend.getHoldingId());
				TBook b = bookMapper.selectById(h.getBookId());
				TReader r = readerMapper.selectById(tLend.getReaderId());
				TPeople p = peopleMapper.selectById(r.getPeopleId());
				TOrganization org = organizationMapper.selectById(h.getOwnlib());

				SysBasicparam sb1 = sysBasicparamMapper.selectById(h.getColladdressId());
				SysBasicparam sb2 = sysBasicparamMapper.selectById(h.getActType());
				if (sb1 != null) {
					rlv.setCollAddress(sb1.getLabel());
				} else {
					rlv.setCollAddress("");
				}
				if (sb2 != null) {
					rlv.setActType(sb2.getLabel());
				} else {
					rlv.setActType("");
				}

				String libraryName = org.getName();

				TReaderType trt = readerTypeMapper.selectById(r.getReaderType());
				rlv.setReaderType(r.getReaderType());
				if (trt != null) {
					rlv.setReaderTypeName(trt.getName());
				} else {
					rlv.setReaderTypeName("默认用户");
				}
				rlv.setBarcode(h.getBarcode());
				rlv.setStatus(r.getStatus());
				rlv.setCallNo(h.getCallNo());
				rlv.setEmail(p.getEmail());
				rlv.setOwnlibName(libraryName);
				rlv.setPhone(p.getPhone());
				rlv.setReaderCardNumber(r.getReaderCardNumber());
				rlv.setTitle(b.getTitle());
				rlv.setUsername(p.getUsername());
				rlls.add(rlv);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page.setList(rlls);
		return R.ok().put("page", page);
	}

	@Path("/sendNotice")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R sendNotice(String data, @HeaderParam("Authorization") String authToken,
			@Context HttpServletRequest request) {
		// {"sendEmail":1,"sendNotice":1,"username":"username","password":"password","smtphost":"smtphost","lendId":[1,2,3]}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		JSONObject obj = JSON.parseObject(data);
		int sendEmail = obj.getIntValue("sendEmail");
		int sendNotice = obj.getIntValue("sendNotice");

		if (sendEmail == 0 && sendNotice == 0) {
			return R.error("请选择提醒方式！");
		}
		String username = obj.getString("username");
		String password = obj.getString("password");
		String smtphost = obj.getString("smtphost");

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		if (sendEmail == 1) {
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(smtphost)) {
				return R.error("请填写发送邮箱信息！");
			}
			Properties properties = new Properties();
			String st[] = smtphost.split(":");
			String smtp = smtphost;
			String sslport = "25";
			if (st.length > 1) {
				smtp = st[0];
				sslport = st[1];

				properties.setProperty("mail.smtp.auth", "true");// 开启认证
				properties.setProperty("mail.debug", "true");// 启用调试
				properties.setProperty("mail.smtp.timeout", "5000");// 设置链接超时
				properties.setProperty("mail.smtp.port", "" + NumberUtils.toInt(sslport, 465));// 设置端口
				properties.setProperty("mail.smtp.socketFactory.port", "" + NumberUtils.toInt(sslport, 465));// 设置ssl端口
				properties.setProperty("mail.smtp.socketFactory.fallback", "false");
				properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
			//JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(smtp);// 指定用来发送Email的邮件服务器主机名
			mailSender.setPort(NumberUtils.toInt(sslport, 25));// 默认端口，标准的SMTP端口
			mailSender.setUsername(username);// 用户名
			mailSender.setPassword(password);// 密码

			mailSender.setJavaMailProperties(properties);

			try {
				mailSender.testConnection();
			} catch (MessagingException e) {
				e.printStackTrace();
				return R.error("发送邮箱验证失败，请重新填写！");
			}
		}
		JSONArray ja = obj.getJSONArray("lendId");

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (ja.size() > 0) {
					for (int i = 0; i < ja.size(); i++) {

						try {
							TLend lend = lendMapper.selectById(ja.getLong(i));
							TReader reader = readerMapper.selectById(lend.getReaderId());
							TPeople people = peopleMapper.selectById(reader.getPeopleId());
							THolding h = holdingMapper.selectById(lend.getHoldingId());
							TBook b = bookMapper.selectById(h.getBookId());

							if (sendEmail == 1) {
								try {

									String email = people.getEmail();
									if (StringUtils.hasLength(email)) {

										SimpleMailMessage message = new SimpleMailMessage();
										message.setFrom(username);
										message.setTo(email);
										message.setSubject("系统提示：还书提醒！");
										message.setText("您借阅的图书 <<" + b.getTitle() + ">>, 将于"
												+ DateFormatUtils.format(lend.getDueBackTime(), "yyyy-MM-dd")
												+ "到期,请于过期之日前归还至图书馆！");
										// 发送邮件
										mailSender.send(message);
										log.debug("send Email to {}", email);
									} else {
										log.debug("error email :{}", email);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}

							if (sendNotice == 1) {
								long id = IdGen.randomLong();
								TNoticeInfo n = new TNoticeInfo();
								n.setId(id);
								n.setContent("您借阅的图书 <<" + b.getTitle() + ">>, 将于"
										+ DateFormatUtils.format(lend.getDueBackTime(), "yyyy-MM-dd")
										+ "到期,请于过期之日前归还至图书馆！");
								n.setCreateBy(u.getId());
								n.setCreateTime(new Date());
								n.setSendTime(new Date());
								n.setStatus(1);
								n.setTitle("系统提示：还书提醒！");
								n.setType(0);
								noticeInfoMapper.insert(n);

								TNoticeRead r = new TNoticeRead();
								r.setId(id);
								r.setNoticeInfoId(id);
								r.setReadStatus(0);
								r.setUserId(people.getUserId());
								noticeReadMapper.insert(r);
								log.debug("send notice to {}", people.getUserId());
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			}
		}).start();

		String libraryName = "";
		try {

			String ip = HttpServletRequestUtil.getIpAddr(request);

			TOrganization org = organizationMapper.selectById(u.getOrgId());
			libraryName = org.getName();
			TOperationLog oprlog = new TOperationLog();
			oprlog.setLibraryId(u.getOrgId());
			oprlog.setLoginName(u.getLoginName());
			oprlog.setLibraryName(libraryName);
			oprlog.setModuleId(10);
			oprlog.setModuleName("流通管理");
			oprlog.setOpContent("发送催还通知");
			oprlog.setOperationType("自动催还");
			oprlog.setIp(ip);
			oprlog.setUserId(u.getId());
			oprlog.setUserName(u.getLoginName());

			operationLogMapper.insert(oprlog);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();
	}

	@Path("/helpLendList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public R helpLendList(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerLib") String readerLib//
			, @QueryParam("bookLib") String bookLib//
			, @QueryParam("bookName") String bookName//
			, @QueryParam("isbn") String isbn//
			, @QueryParam("barcode") String barcode//
			, @QueryParam("callno") String callno//
			, @QueryParam("status") String status// 状态 0待处理，1待领取，2已领取，3已还书，4已取消，5 拒绝
			, @QueryParam("duetimeStart") String duetimeStart // yyy-MM-dd
			, @QueryParam("duetimeEnd") String duetimeEnd // yyy-MM-dd
	) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		EntityWrapper<THelpLendback> ew = new EntityWrapper<>();
		ew.eq("type", 0);

		if (StringUtils.hasText(status)) {
			ew.eq("status", NumberUtils.toInt(status, -1));
		} else {
			ew.in("status", "0,1,5");
		}

		if (StringUtils.hasLength(duetimeStart)) {
			try {
				Date d = DateUtils.parseDate(duetimeStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				ew.ge("due_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.hasLength(duetimeEnd)) {
			try {
				Date d = DateUtils.parseDate(duetimeEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				ew.le("due_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.hasLength(readerCardNumber)) {
			List<TReader> ls = readerMapper
					.selectList(new EntityWrapper<TReader>().eq("reader_card_number", readerCardNumber));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TReader tReader : ls) {
				ridls.add(tReader.getId());
			}
			ew.in("reader_id", ridls);
		}
		if (StringUtils.hasLength(readerLib)) {
			List<TOrganization> ls = organizationMapper
					.selectList(new EntityWrapper<TOrganization>().like("name", readerLib));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TOrganization tOrganization : ls) {
				ridls.add(tOrganization.getId());
			}
			ew.in("reader_lib_id", ridls);
		}
		if (StringUtils.hasLength(bookLib)) {
			List<TOrganization> ls = organizationMapper
					.selectList(new EntityWrapper<TOrganization>().like("name", bookLib));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TOrganization tOrganization : ls) {
				ridls.add(tOrganization.getId());
			}
			ew.in("holding_lib_id", ridls);
		}
		if (StringUtils.hasLength(bookName)) {
			List<TBook> ls = bookMapper.selectList(new EntityWrapper<TBook>().like("title", bookName));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TBook tBook : ls) {
				ridls.add(tBook.getId());
			}
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().in("book_id", ridls));
			ridls = new ArrayList<>();
			ridls.add(-1L);
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(isbn)) {
			List<TBook> ls = bookMapper.selectList(new EntityWrapper<TBook>().like("ISBN", isbn));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TBook tBook : ls) {
				ridls.add(tBook.getId());
			}
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().in("book_id", ridls));
			ridls = new ArrayList<>();
			ridls.add(-1L);
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(barcode)) {
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().eq("barcode", barcode));
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(callno)) {
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().eq("call_no", callno));
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<THelpLendback> hls = helpLendbackMapper.selectList(ew);
		PageInfo page = new PageInfo(hls);
		List<HelpLendVO> hlvls = new ArrayList<>();
		for (THelpLendback tHelpLendback : hls) {
			try {

				HelpLendVO hl = new HelpLendVO();
				BeanUtils.copyProperties(tHelpLendback, hl);

				TReader r = readerMapper.selectById(tHelpLendback.getReaderId());
				TOrganization ro = organizationMapper.selectById(r.getLibraryId());
				if (ro != null) {
					hl.setReaderLibName(ro.getName());
				} else {
					hl.setReaderLibName("-");
				}
				hl.setReaderCardNumber(r.getReaderCardNumber());
				THolding h = holdingMapper.selectById(tHelpLendback.getHoldingId());
				TBook b = bookMapper.selectById(h.getBookId());
				TOrganization ro1 = organizationMapper.selectById(h.getOwnlib());
				if (ro1 != null) {
					hl.setBookLibName(ro1.getName());
				} else {
					hl.setBookLibName("-");
				}
				
				hl.setBarcode(h.getBarcode());
				hl.setBookName(b.getTitle());
				hl.setCallNo(h.getCallNo());

				hlvls.add(hl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page.setList(hlvls);

		return R.ok().put("page", page);
	}

	@Path("/helpLendListExport")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "unchecked" })
	public R helpLendListExport(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@Context HttpServletResponse respose, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerLib") String readerLib//
			, @QueryParam("bookLib") String bookLib//
			, @QueryParam("bookName") String bookName//
			, @QueryParam("isbn") String isbn//
			, @QueryParam("barcode") String barcode//
			, @QueryParam("callno") String callno//
			, @QueryParam("status") String status// 状态 0待处理，1待领取，2已领取，3已还书，4已取消，5 拒绝
			, @QueryParam("duetimeStart") String duetimeStart // yyy-MM-dd
			, @QueryParam("duetimeEnd") String duetimeEnd // yyy-MM-dd
	) throws IOException {

		R r = helpLendList(authToken, request, 1, Integer.MAX_VALUE, readerCardNumber, readerLib, bookLib, bookName,
				isbn, barcode, callno, status, duetimeStart, duetimeEnd);

		PageInfo<HelpLendVO> p = (PageInfo<HelpLendVO>) r.get("page");
		List<HelpLendVO> ls = p.getList();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CsvWriter writer = new CsvWriter(stream, ',', Charset.forName("GBK"));
		writer.writeRecord(new String[] { "序号", "读者证号", "读者所属馆", "条码号", "图书名称", "索书号", "申请时间", "状态" });
		int i = 1;

		for (HelpLendVO rv : ls) {
			try {
				writer.write("" + i);
				writer.write("" + rv.getReaderCardNumber());
				writer.write("" + rv.getReaderLibName());
				writer.write("" + rv.getBarcode());
				writer.write("" + rv.getBookName());
				writer.write("" + rv.getCallNo());
				writer.write("" + DateFormatUtils.format(rv.getDueTime(), "yyyy-MM-dd"));
				if (rv.getStatus() == 0) {// 0 申请中； 1 待领取； 5 已拒绝；

					writer.write("申请中");
				} else if (rv.getStatus() == 1) {
					writer.write("待领取");
				} else if (rv.getStatus() == 5) {
					writer.write("已拒绝");
				} else {
					writer.write("-");
				}

				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}

			writer.endRecord();
		}

		writer.close();

		String fileName = "helpLendListExport-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".csv";
		InputStream inputStream = new ByteArrayInputStream(stream.toString("GBK").getBytes("GBK"));
		String url = fileUploader.uploadFile(inputStream, fileName);

		return R.ok().put("url", url);
	}

	@Path("/helpLendProcess")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R helpLendProcess(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			String data) {
		// {"isAgree":1,"ids":[1,2,3]}
		JSONObject obj = null;
		try {
			obj = JSON.parseObject(data);
			Integer isAgree = obj.getInteger("isAgree");
			if (isAgree == null) {
				return R.error("参数错误！");
			}
			JSONArray ja = obj.getJSONArray("ids");
			if (ja == null || ja.size() == 0) {
				return R.error("参数错误！");
			}

			for (int i = 0; i < ja.size(); i++) {
				if (isAgree == 1) {
					THelpLendback entity = new THelpLendback();
					entity.setId(ja.getLong(i));
					entity.setStatus(1);
					helpLendbackMapper.updateById(entity);
				}

				if (isAgree == 0) {
					THelpLendback entity = new THelpLendback();
					entity.setId(ja.getLong(i));
					entity.setStatus(5);// 状态 0待处理，1待领取，2已领取，3已还书，4已取消，5 拒绝
					helpLendbackMapper.updateById(entity);
				}
			}

			String libraryName = "";
			try {

				Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
				TUser u = userMapper.selectById(loginuserid);
				String ip = HttpServletRequestUtil.getIpAddr(request);

				TOrganization org = organizationMapper.selectById(u.getOrgId());
				libraryName = org.getName();
				TOperationLog oprlog = new TOperationLog();
				oprlog.setLibraryId(u.getOrgId());
				oprlog.setLoginName(u.getLoginName());
				oprlog.setLibraryName(libraryName);
				oprlog.setModuleId(10);
				oprlog.setModuleName("流通管理");
				if (isAgree == 0) {

					oprlog.setOpContent(" 拒绝代借 " + ja.toJSONString());
				} else {
					oprlog.setOpContent(" 同意代借 " + ja.toJSONString());

				}
				oprlog.setOperationType("代借处理");
				oprlog.setIp(ip);
				oprlog.setUserId(u.getId());
				oprlog.setUserName(u.getLoginName());

				operationLogMapper.insert(oprlog);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return R.error("参数错误！");
		}

		return R.ok();
	}

	@Path("/helpBackList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public R helpBackList(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerLib") String readerLib//
			, @QueryParam("bookLib") String bookLib//
			, @QueryParam("bookName") String bookName//
			, @QueryParam("isbn") String isbn//
			, @QueryParam("barcode") String barcode//
			, @QueryParam("callno") String callno//
			// , @QueryParam("status") Integer status// 状态 0待处理，1待领取，2已领取，3已还书，4已取消，5 拒绝
			, @QueryParam("duetimeStart") String duetimeStart // yyy-MM-dd
			, @QueryParam("duetimeEnd") String duetimeEnd // yyy-MM-dd
	) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		EntityWrapper<THelpLendback> ew = new EntityWrapper<>();
		ew.eq("type", 1);

		// if(status!=null) {
		// ew.eq("status", status);
		// }else {
		ew.in("status", "0,1,5");
		// }

		if (StringUtils.hasLength(duetimeStart)) {
			try {
				Date d = DateUtils.parseDate(duetimeStart + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				ew.ge("due_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.hasLength(duetimeEnd)) {
			try {
				Date d = DateUtils.parseDate(duetimeEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
				ew.le("due_time", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.hasLength(readerCardNumber)) {
			List<TReader> ls = readerMapper
					.selectList(new EntityWrapper<TReader>().eq("reader_card_number", readerCardNumber));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TReader tReader : ls) {
				ridls.add(tReader.getId());
			}
			ew.in("reader_id", ridls);
		}
		if (StringUtils.hasLength(readerLib)) {
			List<TOrganization> ls = organizationMapper
					.selectList(new EntityWrapper<TOrganization>().like("name", readerLib));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TOrganization tOrganization : ls) {
				ridls.add(tOrganization.getId());
			}
			ew.in("reader_lib_id", ridls);
		}
		if (StringUtils.hasLength(bookLib)) {
			List<TOrganization> ls = organizationMapper
					.selectList(new EntityWrapper<TOrganization>().like("name", bookLib));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TOrganization tOrganization : ls) {
				ridls.add(tOrganization.getId());
			}
			ew.in("holding_lib_id", ridls);
		}
		if (StringUtils.hasLength(bookName)) {
			List<TBook> ls = bookMapper.selectList(new EntityWrapper<TBook>().like("title", bookName));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TBook tBook : ls) {
				ridls.add(tBook.getId());
			}
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().in("book_id", ridls));
			ridls = new ArrayList<>();
			ridls.add(-1L);
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(isbn)) {
			List<TBook> ls = bookMapper.selectList(new EntityWrapper<TBook>().like("ISBN", isbn));
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			for (TBook tBook : ls) {
				ridls.add(tBook.getId());
			}
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().in("book_id", ridls));
			ridls = new ArrayList<>();
			ridls.add(-1L);
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(barcode)) {
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().eq("barcode", barcode));
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		if (StringUtils.hasLength(callno)) {
			List<Long> ridls = new ArrayList<>();
			ridls.add(-1L);
			List<THolding> hls = holdingMapper.selectList(new EntityWrapper<THolding>().eq("call_no", callno));
			for (THolding tHolding : hls) {
				ridls.add(tHolding.getId());
			}
			ew.in("holding_id", ridls);
		}
		PageHelper.startPage(pageNum, pageSize);
		List<THelpLendback> hls = helpLendbackMapper.selectList(ew);
		PageInfo page = new PageInfo(hls);
		List<HelpLendVO> hlvls = new ArrayList<>();
		for (THelpLendback tHelpLendback : hls) {
			try {

				HelpLendVO hl = new HelpLendVO();
				BeanUtils.copyProperties(tHelpLendback, hl);

				TReader r = readerMapper.selectById(tHelpLendback.getReaderId());
				TOrganization ro = organizationMapper.selectById(r.getLibraryId());
				if (ro != null) {
					hl.setReaderLibName(ro.getName());
				} else {
					hl.setReaderLibName("-");
				}
				hl.setReaderCardNumber(r.getReaderCardNumber());
				THolding h = holdingMapper.selectById(tHelpLendback.getHoldingId());
				TBook b = bookMapper.selectById(h.getBookId());

				hl.setBarcode(h.getBarcode());
				hl.setBookName(b.getTitle());
				hl.setCallNo(h.getCallNo());

				TOrganization rog = organizationMapper.selectById(h.getOwnlib());
				if (rog != null) {
					hl.setBookLibName(rog.getName());
				} else {
					hl.setBookLibName("-");
				}

				hlvls.add(hl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		page.setList(hlvls);

		return R.ok().put("page", page);
	}

	@Path("/helpBackListExport")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "unchecked" })
	public R helpBackListExport(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@Context HttpServletResponse respose, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize, //
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerLib") String readerLib//
			, @QueryParam("bookLib") String bookLib//
			, @QueryParam("bookName") String bookName//
			, @QueryParam("isbn") String isbn//
			, @QueryParam("barcode") String barcode//
			, @QueryParam("callno") String callno//
			// , @QueryParam("status") Integer status// 状态 0待处理，1待领取，2已领取，3已还书，4已取消，5 拒绝
			, @QueryParam("duetimeStart") String duetimeStart // yyy-MM-dd
			, @QueryParam("duetimeEnd") String duetimeEnd // yyy-MM-dd
	) throws IOException {

		R r = helpBackList(authToken, request, 1, Integer.MAX_VALUE, readerCardNumber, readerLib, bookLib, bookName,
				isbn, barcode, callno, duetimeStart, duetimeEnd);

		PageInfo<HelpLendVO> p = (PageInfo<HelpLendVO>) r.get("page");
		List<HelpLendVO> ls = p.getList();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CsvWriter writer = new CsvWriter(stream, ',', Charset.forName("GBK"));
		writer.writeRecord(new String[] { "序号", "读者证号", "读者所属馆", "条码号", "图书名称", "索书号", "申请时间", "状态" });
		int i = 1;

		for (HelpLendVO rv : ls) {
			try {
				writer.write("" + i);
				writer.write("" + rv.getReaderCardNumber());
				writer.write("" + rv.getReaderLibName());
				writer.write("" + rv.getBarcode());
				writer.write("" + rv.getBookName());
				writer.write("" + rv.getCallNo());
				writer.write("" + DateFormatUtils.format(rv.getDueTime(), "yyyy-MM-dd"));
				if (rv.getStatus() == 0) {// 0 申请中； 1 待领取； 5 已拒绝；

					writer.write("申请中");
				} else if (rv.getStatus() == 1) {
					writer.write("待领取");
				} else if (rv.getStatus() == 5) {
					writer.write("已拒绝");
				} else {
					writer.write("-");
				}

				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}

			writer.endRecord();
		}

		writer.close();

		String fileName = "helpLendListExport-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".csv";
		InputStream inputStream = new ByteArrayInputStream(stream.toString("GBK").getBytes("GBK"));
		String url = fileUploader.uploadFile(inputStream, fileName);

		return R.ok().put("url", url);
	}

	@Path("/lendBuyReader")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendBuyReader(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName// 读者姓名
			, @QueryParam("IDCard") String IDCard// 身份证号
	) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		if (StringUtils.isEmpty(IDCard) && StringUtils.isEmpty(readerName) && StringUtils.isEmpty(readerCardNumber)) {
			return R.error("查询条件不能为空！");
		}

		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
		}

		if (StringUtils.hasLength(readerName)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("username", readerName));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}
		if (StringUtils.hasLength(IDCard)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("card_number", IDCard));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<TReader> tls = readerService.selectList(ew);
		List<ReaderVO> rls = new ArrayList<>();
		for (TReader tReader : tls) {
			ReaderVO rv = new ReaderVO();
			BeanUtils.copyProperties(tReader, rv);

			Long lid = tReader.getLibraryId();
			TOrganization org = organizationMapper.selectById(lid);
			if (org != null) {
				rv.setLibraryName(org.getName());
			}
			TPeople people = peopleService.selectById(tReader.getPeopleId());
			if (people != null) {
				rv.setReaderName(people.getUsername());
				Integer s = people.getSex();
				if (s == 1) {
					rv.setSex("男");
				} else {
					rv.setSex("女");
				}
				rv.setIDCard(people.getCardNumber());
			} else {
				log.error("数据错误，reader ID ={} 没有对应的people数据", tReader.getId());
				continue;
			}

			TReaderType trt = readerTypeMapper.selectById(tReader.getReaderType());
			List<TLendRule> rulels = lendRuleMapper
					.selectList(new EntityWrapper<TLendRule>().eq("status", 1).and().eq("library_id", u.getOrgId()));
			int renewqty = 1;
			int renewdays = 30;
			int lendnum = 0;
			if (rulels.size() > 0) {
				TLendRule rule = rulels.get(0);

				renewqty = rule.getRenewQty();
				renewdays = rule.getRenewDays();
				lendnum = rule.getLendQty();
			}

			if (trt != null) {
				rv.setReaderTypeName(trt.getName());
				rv.setLendNum(lendnum);
			} else {
				rv.setReaderTypeName("默认用户");
				rv.setLendNum(5);
			}

			List<Integer> ls = new ArrayList<>();
			ls.add(0);
			ls.add(2);
			ls.add(3);
			Integer borrow = lendMapper.selectCount(
					new EntityWrapper<TLend>().eq("reader_id", tReader.getId()).and().in("lend_status", ls));
			rv.setBorrow(borrow);

			List<TCredit> crels = creditMapper
					.selectList(new EntityWrapper<TCredit>().eq("user_id", people.getUserId()));
			if (crels.size() > 0) {
				TCredit c = crels.get(0);
				rv.setCredit(c.getOwnValue());
			} else {
				TCredit c = new TCredit();
				c.setCardStatus(tReader.getStatus());
				c.setDefaultValue(100);
				c.setIslendBuy(1);
				c.setOwnValue(100);
				c.setStatus(1);
				c.setSummary("");
				c.setUserId(people.getUserId());
				creditMapper.insert(c);

				rv.setCredit(c.getOwnValue());
			}

			List<TLendbuyBook> lbb = lendbuyBookMapper
					.selectList(new EntityWrapper<TLendbuyBook>().eq("reader_id", rv.getId()));
			List<LendBuyVO> lbvs = new ArrayList<>();
			for (TLendbuyBook tLendbuyBook : lbb) {
				TPrebook pb = prebookMapper.selectById(tLendbuyBook.getPrebookId());
				LendBuyVO lbv = new LendBuyVO();
				BeanUtils.copyProperties(pb, lbv);
				lbv.setBackTime(tLendbuyBook.getBackTime());
				lbv.setDueBackTime(tLendbuyBook.getDueBackTime());
				lbv.setSummary("");
				lbv.setCreateTime(tLendbuyBook.getCreateTime());

				lbvs.add(lbv);
			}

			rv.setLendbuyls(lbvs);

			rls.add(rv);
		}
		PageInfo<ReaderVO> p = new PageInfo<>(rls);

		return R.ok().put("page", p);
	}

	@Path("/lendBuyBookInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendBuyBookInfo(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request,
			@QueryParam("isbn") String isbn) {

		if (StringUtils.isEmpty(isbn)) {
			return R.error("参数错误！");
		}

		List<TPrebook> ls = prebookMapper.selectList(new EntityWrapper<TPrebook>().eq("ISBN", isbn));

		return R.ok().put("lendBuyBookInfo", ls);
	}

	@Path("/lendBuyBookProcess")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendBuyBookProcess(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("preBookId") Long preBookId, @QueryParam("readerId") Long readerId) {

		if (preBookId == null || readerId == null) {
			return R.error("参数错误！");
		}

		List<TLendbuyBook> ls = lendbuyBookMapper.selectList(
				new EntityWrapper<TLendbuyBook>().eq("prebook_id", preBookId).and().eq("reader_id", readerId));
		if (ls.size() > 0) {
			TLendbuyBook tLendbuyBook = ls.get(0);
			tLendbuyBook.setBackTime(new Date());
			lendbuyBookMapper.updateById(tLendbuyBook);

			TPrebook b = prebookMapper.selectById(tLendbuyBook.getPrebookId());

			String libraryName = "";
			try {

				Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
				TUser u = userMapper.selectById(loginuserid);
				String ip = HttpServletRequestUtil.getIpAddr(request);

				TOrganization org = organizationMapper.selectById(u.getOrgId());
				libraryName = org.getName();
				TOperationLog oprlog = new TOperationLog();
				oprlog.setLibraryId(u.getOrgId());
				oprlog.setLoginName(u.getLoginName());
				oprlog.setLibraryName(libraryName);
				oprlog.setModuleId(10);
				oprlog.setModuleName("流通管理");
				oprlog.setOpContent(" 借购还回 " + "<<" + b.getTitle() + ">>");
				oprlog.setOperationType("借购还回");
				oprlog.setIp(ip);
				oprlog.setUserId(u.getId());
				oprlog.setUserName(u.getLoginName());

				operationLogMapper.insert(oprlog);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return R.ok().put("bookName", b.getTitle());
		}

		return R.error("未查询到借购信息！");

	}

	@Path("/operatorList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R operatorList(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request//

	) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);
		List<TUser> ulss = userMapper
				.selectList(new EntityWrapper<TUser>().eq("type", 1).and().eq("org_id", u.getOrgId()));
		for (TUser tUser : ulss) {
			tUser.setPassword("***");
		}
		return R.ok().put("oprlist", ulss);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Path("/logs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R logs(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request, //
			@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize,
			@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate,
			@QueryParam("logType") String logType, @QueryParam("operatorId") String operatorId) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		EntityWrapper<TOperationLog> ew = new EntityWrapper<>();
		ew.eq("library_id", u.getOrgId()).and().eq("module_id", 10);
		if (StringUtils.hasText(logType)) {
			ew.eq("operation_type", logType);
		}
		if (StringUtils.hasText(startDate)) {
			try {
				ew.ge("create_time", DateUtils.parseDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.hasText(endDate)) {
			try {
				ew.le("create_time", DateUtils.parseDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (NumberUtils.isNumber(operatorId)) {
			ew.eq("user_id", operatorId);
		}
		ew.orderBy("create_time", false);
		PageHelper.startPage(pageNum, pageSize);
		List<TOperationLog> ls = operationLogMapper.selectList(ew);
		PageInfo page = new PageInfo(ls);
		return R.ok().put("page", page);

	}

	@Path("/readerTypeList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R readerTypeList() {

		List<TReaderType> rtls = readerTypeMapper.selectList(null);

		return R.ok().put("readerTypeList", rtls);
	}

}
