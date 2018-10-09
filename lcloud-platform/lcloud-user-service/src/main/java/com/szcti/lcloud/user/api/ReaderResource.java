package com.szcti.lcloud.user.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szcti.lcloud.common.poi.ExcelLogs;
import com.szcti.lcloud.common.poi.ExcelUtil;
import com.szcti.lcloud.common.utils.FileUploader;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.config.JwtYmlConfig;
import com.szcti.lcloud.user.entity.TCredit;
import com.szcti.lcloud.user.entity.TLend;
import com.szcti.lcloud.user.entity.TLendRule;
import com.szcti.lcloud.user.entity.TOrganization;
import com.szcti.lcloud.user.entity.TPeople;
import com.szcti.lcloud.user.entity.TPrelend;
import com.szcti.lcloud.user.entity.TReader;
import com.szcti.lcloud.user.entity.TReaderFinance;
import com.szcti.lcloud.user.entity.TReaderType;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.CreditPeopleVO;
import com.szcti.lcloud.user.entity.vo.ReaderTypeVO;
import com.szcti.lcloud.user.entity.vo.ReaderVO;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;
import com.szcti.lcloud.user.mapper.TCreditMapper;
import com.szcti.lcloud.user.mapper.TLendMapper;
import com.szcti.lcloud.user.mapper.TLendRuleMapper;
import com.szcti.lcloud.user.mapper.TOrganizationMapper;
import com.szcti.lcloud.user.mapper.TPeopleMapper;
import com.szcti.lcloud.user.mapper.TPrelendMapper;
import com.szcti.lcloud.user.mapper.TReaderCreditMapper;
import com.szcti.lcloud.user.mapper.TReaderFinanceMapper;
import com.szcti.lcloud.user.mapper.TReaderTypeMapper;
import com.szcti.lcloud.user.mapper.TUserMapper;
import com.szcti.lcloud.user.mapper.TUserRoleMapper;
import com.szcti.lcloud.user.service.TPeopleService;
import com.szcti.lcloud.user.service.TReaderService;
import com.szcti.lcloud.user.service.UserService;
import com.szcti.lcloud.user.util.CsvWriter;

@Component
@Path("reader")
public class ReaderResource {
	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(ReaderResource.class);

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
	private UserService userService;
	@Autowired
	private TUserRoleMapper userRoleMapper;
	@Autowired
	private TReaderFinanceMapper readerFinanceMapper;

	@Autowired
	private TUserMapper userMapper;

	@Autowired
	private TPeopleMapper peopleMapper;
	@Autowired
	private TReaderTypeMapper readerTypeMapper;
	@Autowired
	private TCreditMapper creditMapper;
	@Autowired
	private TReaderCreditMapper readerCreditMapper;
	@Autowired
	private TLendRuleMapper lendRuleMapper;

	@Autowired
	private FileUploader fileUploader;
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R list(@HeaderParam("Authorization") String authToken, @QueryParam("pageNum") Integer pageNum,
			@QueryParam("pageSize") Integer pageSize, @QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName// 读者姓名
			, @QueryParam("IDCard") String IDCard// 身份证号
			, @QueryParam("grade") String grade// 年级
			, @QueryParam("classes") String classes// 班级
			, @QueryParam("readerCardNumberStart") String readerCardNumberStart// 卡号开始
			, @QueryParam("reareaderTypeListderCardNumberEnd") String readerCardNumberEnd// 卡号结束
			, @QueryParam("readerType") String readerType// 读者类型
			, @QueryParam("createDate") String createDate// 办证时间

	) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		ew.eq("library_id", u.getOrgId());

		if (StringUtils.hasText(readerCardNumberStart)) {
			ew.ge("reader_card_number", readerCardNumberStart);
		}
		if (StringUtils.hasText(readerCardNumberEnd)) {
			ew.le("reader_card_number", readerCardNumberStart);
		}
		if (StringUtils.hasText(createDate)) {
			try {
				ew.ge("create_date", DateUtils.parseDate(createDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				ew.le("create_date", DateUtils.parseDate(createDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!StringUtils.isEmpty(readerType)){
			ew.eq("reader_type",readerType);
		}
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
		}
		if (StringUtils.hasText(grade)) {
			ew.eq("grade", grade);
		}
		if (StringUtils.hasText(classes)) {
			ew.eq("classes", classes);
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
		//ew.orderBy("create_date", false);
		
		PageHelper.startPage(pageNum, pageSize);
		List<TReader> tls = readerService.selectList(ew);
		PageInfo p = new PageInfo(tls);
		
		List<ReaderVO> rls = new ArrayList<>();
		for (TReader tReader : tls) {
			ReaderVO rv = new ReaderVO();
			TPeople people = peopleService.selectById(tReader.getPeopleId());

			if (people != null) {
				BeanUtils.copyProperties(people, rv);
				rv.setReaderName(people.getUsername());
				Integer s = people.getSex();
				if (s == 1) {
					rv.setSex("男");
				} else {
					rv.setSex("女");
				}
				rv.setIDCard(people.getCardNumber());
				TUser tus = userMapper.selectById(people.getUserId());
//				if(tus ==null) {//用户不存在，跳过
//					continue;
//				}
			}
//			}else {//people不存在，跳过
//				continue;
//			}

			BeanUtils.copyProperties(tReader, rv);

			Integer rt = tReader.getReaderType();
			if (rt != null) {
				TReaderType trt = readerTypeMapper.selectById(rt);
				if (trt != null) {
					rv.setReaderTypeName(trt.getLabel());
				} else {
					rv.setReaderTypeName("普通读者");
				}
			}
			Long lid = tReader.getLibraryId();
			TOrganization org = organizationMapper.selectById(lid);
			if (org != null) {
				rv.setLibraryName(org.getName());
			}

			Integer borrowCount = lendMapper.selectCount(new EntityWrapper<TLend>().eq("reader_id", tReader.getId()));
			rv.setBorrowCount(borrowCount);
			Integer renewCount = lendMapper.selectCount(
					new EntityWrapper<TLend>().eq("reader_id", tReader.getId()).and().eq("lend_status", 2));
			rv.setRenewCount(renewCount);
			Integer reservationCount = prelendMapper
					.selectCount(new EntityWrapper<TPrelend>().eq("reader_id", tReader.getId()));
			rv.setReservationCount(reservationCount);

			rv.setStartDateStr(DateFormatUtils.format(rv.getStartDate(), "yyyy-MM-dd"));
			rv.setEndDateStr(DateFormatUtils.format(rv.getEndDate(), "yyyy-MM-dd"));
			rv.setCreateDateStr(DateFormatUtils.format(rv.getCreateDate(), "yyyy-MM-dd"));
			rv.setId(tReader.getId());
			rls.add(rv);
		}
		p.setList(rls);

		return R.ok().put("page", p);
	}

	@SuppressWarnings("unchecked")
	@Path("/listExport")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R listExport(@Context HttpServletRequest request, @Context HttpServletResponse respose,
			@HeaderParam("Authorization") String authToken, @QueryParam("pageNum") Integer pageNum,
			@QueryParam("pageSize") Integer pageSize, @QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName// 读者姓名
			, @QueryParam("IDCard") String IDCard// 身份证号
			, @QueryParam("grade") String grade// 年级
			, @QueryParam("classes") String classes// 班级
			, @QueryParam("readerCardNumberStart") String readerCardNumberStart// 卡号开始
			, @QueryParam("readerCardNumberEnd") String readerCardNumberEnd// 卡号结束
			, @QueryParam("readerType") String readerType// 读者类型
			, @QueryParam("createDate") String createDate// 办证时间

	) throws IOException {

		R r = list(authToken, 1, Integer.MAX_VALUE, readerCardNumber, readerName, IDCard, grade, classes,
				readerCardNumberStart, readerCardNumberEnd, readerType, createDate);
		
		PageInfo<ReaderVO> p = (PageInfo<ReaderVO>) r.get("page");
		List<ReaderVO> ls = p.getList();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CsvWriter writer = new CsvWriter(stream, ',', Charset.forName("GBK"));
		writer.writeRecord(new String[] { "序号", "读者证号", "姓名", "性别", "证状态", "所属馆", "启用日期","终止日期","借阅总次数","馆际互借次数","违约次数","预付款金额","欠款金额" });
		int i = 1;

		for (ReaderVO rv : ls) {
			try {
				writer.write(""+i);
				writer.write(""+rv.getReaderCardNumber());
				writer.write(""+rv.getReaderName());
				writer.write(""+rv.getSex());
				if(rv.getStatus()==0) {//状态0禁用1正常2注销3挂失
					writer.write("禁用");
				}else if(rv.getStatus()==1) {
					writer.write("正常");
				}else if(rv.getStatus()==2) {
					writer.write("注销");
				}else if(rv.getStatus()==3) {
					writer.write("挂失");
				}else {
					writer.write("未知");
				}
				writer.write(""+rv.getLibraryName());
				writer.write(""+rv.getStartDateStr());
				writer.write(""+rv.getEndDateStr());
				writer.write(""+rv.getBorrowCount());
				writer.write(""+rv.getInterLibBorrowCount());
				writer.write(""+rv.getViolateCount());
				writer.write(""+rv.getPrePayment());
				writer.write(""+rv.getArrear());
				
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}

			writer.endRecord();
		}

		writer.close();

		String fileName = "readerlistExport-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".csv";
		//CsvWriter.exportCsv(fileName, stream.toString("GBK"), respose);
		InputStream inputStream = new ByteArrayInputStream(stream.toString("GBK").getBytes("GBK"));
		String url = fileUploader.uploadFile(inputStream,fileName);
		
		return R.ok().put("url", url);
	}

	@Path("/finance")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R finance(@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("IDCard") String IDCard// 身份证号
	) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		if (StringUtils.isEmpty(readerCardNumber) && StringUtils.isEmpty(IDCard)) {
			return R.error("查询参数不能为空！");
		}

		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
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

		List<TReader> tls = readerService.selectList(ew);
		TReader tReader = null;
		if (tls.size() > 0) {
			tReader = tls.get(0);
		} else {
			return R.error("查无此人！");
		}

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
		}

		PageHelper.startPage(pageNum, pageSize);

		List<TReaderFinance> rls = readerFinanceMapper
				.selectList(new EntityWrapper<TReaderFinance>().eq("reader_id", tReader.getId()));

		PageInfo<TReaderFinance> p = new PageInfo<>(rls);

		return R.ok().put("page", p).put("reader", rv);
	}

	@SuppressWarnings("unchecked")
	@Path("/financeExport")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R financeExport(@Context HttpServletRequest request, @Context HttpServletResponse respose,
			// @QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer
			// pageSize,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("IDCard") String IDCard// 身份证号
	) throws IOException {

		R r = finance(1, Integer.MAX_VALUE, readerCardNumber, IDCard);

		PageInfo<TReaderFinance> p = (PageInfo<TReaderFinance>) r.get("page");
		List<TReaderFinance> ls = p.getList();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CsvWriter writer = new CsvWriter(stream, ',', Charset.forName("GBK"));
		writer.writeRecord(new String[] { "序号", "财经类别", "日期", "罚款金额", "状态", "书名", "条码号" });
		int i = 1;
		for (TReaderFinance rf : ls) {
			try {

				writer.write(i + "");
				writer.write("" + rf.getTypeName());

				writer.write(DateFormatUtils.format(rf.getFinanceDatetime(), "yyyy-MM-dd"));
				writer.write("" + rf.getForfeit());
				if (rf.getStatus() == 0) {
					writer.write("未缴纳");
				} else {
					writer.write("已缴纳");
				}
				writer.write("" + rf.getBookName());
				writer.write("" + rf.getBarcode());

				i++;

			} catch (Exception e) {
				e.printStackTrace();
			}
			writer.endRecord();
		}
		writer.close();

		String fileName = "financeExport-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + ".csv";
		InputStream inputStream = new ByteArrayInputStream(stream.toString("GBK").getBytes("GBK"));
		String url = fileUploader.uploadFile(inputStream,fileName);
		
		return R.ok().put("url", url);
	}

	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R addReader(String data, @HeaderParam("Authorization") String authToken) {
		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}
		try {

			ReaderVO rv = JSON.parseObject(data, ReaderVO.class);
			if (rv == null) {
				return R.error("参数错误！");
			}
			TUser tu = userService.getUserByName(rv.getReaderCardNumber());
			if (tu != null) {
				return R.error("读者证号已存在！");
			}

			int ic =readerService.selectCount(new EntityWrapper<TReader>().eq("reader_card_number", rv.getReaderCardNumber()));
			if(ic>0) {
				return R.error("读者证号已存在！");
			}
			
			UserInfoVO user = new UserInfoVO();
			user.setLoginName(rv.getReaderCardNumber());
			user.setUserName(rv.getReaderName());
			user.setPassword(rv.getPassword());
			user.setType(rv.getReaderType());
			user.setStatus(1);
			user.setOrgId(u.getOrgId());
			user.setRoleId(1L);
			user.setSex(NumberUtils.toInt(rv.getSex(), 1));
			user.setCardType(0);
			user.setCardNumber(rv.getIDCard());
			user.setPhone(rv.getPhone());
			user.setBirthday(rv.getBirthday());

			if (u != null) {
				user.setCreateAccount(u.getLoginName());
				user.setCreateBy(u.getId());
				user.setCreateName(u.getLoginName());
			}
			userService.saveUserInfo(user);

			TReader entity = new TReader();
			BeanUtils.copyProperties(rv, entity);

			entity.setId(user.getUserId());
			entity.setPeopleId(user.getPeopleId());
			entity.setReaderCardType(0);
			entity.setGrade(rv.getGrade());
			entity.setClasses(rv.getClasses());
			entity.setStuworkNumber(rv.getStuworkNumber());
			entity.setLibraryId(u.getOrgId());
			entity.setRemark(rv.getRemark());

			readerService.insert(entity);

			return R.ok();

		} catch (Exception e) {
			return R.error("参数错误！");
		}

	}

	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R updateReader(String data, @HeaderParam("Authorization") String authToken) {
		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}
		try {

			ReaderVO rv = JSON.parseObject(data, ReaderVO.class);
			if (rv == null) {
				return R.error("参数错误！");
			}
			Long readerid = rv.getId();
			if (readerid == null) {
				return R.error("读者ID不能为空！");
			}
			TReader reader = readerService.selectById(readerid);
			Long peopleid = reader.getPeopleId();
			TPeople people = peopleMapper.selectById(peopleid);

			TUser tuser = userMapper.selectById(people.getUserId());
			if(tuser==null) {
				tuser =new TUser();
				tuser.setId(people.getUserId());
				tuser.setEnabled(0);
				tuser.setLoginName(rv.getReaderCardNumber());
			}
			TUser tu = userService.getUserByName(rv.getReaderCardNumber());
			if (tu != null && tu.getId().longValue() != tuser.getId().longValue()) {
				return R.error("读者证号已存在！");
			}

			UserInfoVO user = new UserInfoVO();
			user.setUserId(tuser.getId());
			user.setPeopleId(peopleid);

			user.setUserId(tuser.getId());
			user.setLoginName(rv.getReaderCardNumber());
			user.setUserName(rv.getReaderName());
			user.setPassword(rv.getPassword());
			user.setType(0);
			user.setStatus(1);
			user.setOrgId(u.getOrgId());
			user.setRoleId(1L);
			user.setSex(NumberUtils.toInt(rv.getSex(), 1));
			user.setCardType(0);
			user.setCardNumber(rv.getIDCard());
			user.setPhone(rv.getPhone());
			user.setBirthday(rv.getBirthday());

			if (u != null) {
				user.setCreateAccount(u.getLoginName());
				user.setCreateBy(u.getId());
				user.setCreateName(u.getLoginName());
			}
			userService.saveUserInfo(user);

			TReader entity = new TReader();
			BeanUtils.copyProperties(rv, entity);

			entity.setId(reader.getId());
			entity.setPeopleId(user.getPeopleId());
			entity.setReaderCardType(0);
			entity.setGrade(rv.getGrade());
			entity.setClasses(rv.getClasses());
			entity.setStuworkNumber(rv.getStuworkNumber());
			entity.setLibraryId(u.getOrgId());
			entity.setRemark(rv.getRemark());

			readerService.updateById(entity);

			return R.ok();

		} catch (Exception e) {
			e.printStackTrace();
			return R.error("参数错误！");
		}

	}

	/**
	 * 
	 * @param data
	 *            [{id:2},{id:3},{id:1}]
	 * @return
	 */
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R delete(String data) {
		try {
			Gson gson = new Gson();
			List<TReader> rpvls = gson.fromJson(data, new TypeToken<List<TReader>>() {
			}.getType());
			for (TReader tReader : rpvls) {
				Long readerid = tReader.getId();
				TReader reader = readerService.selectById(readerid);
				TPeople peo = peopleService.selectById(reader.getPeopleId());
				long userid = peo.getUserId();

				// userMapper.deleteById(userid);
				// userRoleMapper.deleteById(userid);
				// peopleMapper.deleteById(userid);
				readerService.deleteById(readerid);

			}

			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Path("/credit")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R credit(@QueryParam("pageNum") Integer pageNum, @QueryParam("pageSize") Integer pageSize,
			@QueryParam("readerCardNumber") String readerCardNumber// 读者卡号
			, @QueryParam("readerName") String readerName//
			, @QueryParam("status") Integer status//
			, @QueryParam("sex") Integer sex//
			, @QueryParam("valStart") Integer valStart//
			, @QueryParam("valEnd") Integer valEnd//
	) {

		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}

		EntityWrapper<TReader> ew = new EntityWrapper<TReader>();
		if (StringUtils.hasLength(readerCardNumber)) {
			ew.eq("reader_card_number", readerCardNumber);
		}
		if (status != null) {
			ew.eq("status", status);
		}

		if (StringUtils.hasLength(readerName)) {
			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().like("readerName", readerName));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}

		if (valStart != null || valEnd != null) {

			EntityWrapper<TCredit> cew = new EntityWrapper<TCredit>();

			if (valStart != null) {
				cew.ge("own_value", valStart);
			}
			if (valEnd != null) {
				cew.le("own_value", valEnd);
			}
			List<TCredit> cls = creditMapper.selectList(cew);
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TCredit tCredit : cls) {
				inls.add(tCredit.getUserId());
			}
			List<TPeople> pls = peopleMapper.selectList(new EntityWrapper<TPeople>().in("user_id", inls));
			inls.clear();
			inls.add(0L);
			for (TPeople tPeople : pls) {
				inls.add(tPeople.getId());
			}

			ew.in("people_id", inls);

		}

		if (sex != null) {

			List<TPeople> ls = peopleService.selectList(new EntityWrapper<TPeople>().eq("sex", sex));
			List<Long> inls = new ArrayList<>();
			inls.add(0L);
			for (TPeople tPeople : ls) {
				inls.add(tPeople.getId());
			}
			ew.in("people_id", inls);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<TReader> tls = readerService.selectList(ew);

		PageInfo p = new PageInfo<>(tls);
		List<CreditPeopleVO> cpv = new ArrayList<>();

		for (TReader tReader : tls) {
			TPeople peo = peopleService.selectById(tReader.getPeopleId());
			if (peo != null) {
				CreditPeopleVO cp = new CreditPeopleVO();
				BeanUtils.copyProperties(peo, cp);
				cp.setReaderCardNumber(tReader.getReaderCardNumber());
				List<TCredit> ls = creditMapper.selectList(new EntityWrapper<TCredit>().eq("user_id", peo.getUserId()));
				if (ls.size() > 0) {
					TCredit credit = ls.get(0);
					cp.setOwnValue(credit.getOwnValue());
				} else {
					cp.setOwnValue(100);
				}

				cpv.add(cp);
			}

		}
		p.setList(cpv);

		return R.ok().put("page", p);
	}

	@Path("/readerTypeList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R readerTypeList() {

		List<TReaderType> rtls = readerTypeMapper.selectList(null);
		List<ReaderTypeVO> ls = new ArrayList<>();
		for (TReaderType tReaderType : rtls) {
			ReaderTypeVO vo = new ReaderTypeVO();
			BeanUtils.copyProperties(tReaderType, vo);
			Long r1 = vo.getMuseumRule();
			if (r1 != null) {

				TLendRule lr = lendRuleMapper.selectById(r1);
				if (lr != null) {
					vo.setInLibLendruleName(lr.getRuleName());
				} else {
					vo.setInLibLendruleName("-");
				}
			} else {
				vo.setInLibLendruleName("-");
			}

			Long r2 = vo.getInterlibraryRule();
			if (r2 != null) {
				TLendRule lr = lendRuleMapper.selectById(r2);
				if (lr != null) {
					vo.setOutLibLendruleName(lr.getRuleName());
				} else {
					vo.setOutLibLendruleName("-");
				}
			} else {
				vo.setOutLibLendruleName("-");
			}

			ls.add(vo);
		}
		return R.ok().put("readerTypeList", ls);
	}

	@Path("/readerTypeById")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R readerTypeById(@QueryParam("pageNum") Integer id) {

		TReaderType tReaderType = readerTypeMapper.selectById(id);

		if (tReaderType != null) {
			ReaderTypeVO vo = new ReaderTypeVO();
			BeanUtils.copyProperties(tReaderType, vo);
			Long r1 = vo.getMuseumRule();
			if (r1 != null) {

				TLendRule lr = lendRuleMapper.selectById(r1);
				if (lr != null) {
					vo.setInLibLendruleName(lr.getRuleName());
				} else {
					vo.setInLibLendruleName("-");
				}
			} else {
				vo.setInLibLendruleName("-");
			}

			Long r2 = vo.getInterlibraryRule();
			if (r2 != null) {
				TLendRule lr = lendRuleMapper.selectById(r2);
				if (lr != null) {
					vo.setOutLibLendruleName(lr.getRuleName());
				} else {
					vo.setOutLibLendruleName("-");
				}
			} else {
				vo.setOutLibLendruleName("-");
			}
		}

		return R.ok().put("readerType", tReaderType);
	}
	
	
	@Path("/readerImport")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json;charset=UTF-8")
	public R readerImport(@Context HttpServletRequest request, MultipartFormDataInput input,@HeaderParam("Authorization") String authToken) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);
		
		String url = "";
		Map<String, List<InputPart>> dataMaps = input.getFormDataMap();

		// 根据表单元素名称获取表单元素
		List<InputPart> fileParts = dataMaps.get("file");// 表单元素-文件

		// 解析获取表单文件的输入流
		InputStream inputStream;
		try {
			if (fileParts == null || fileParts.isEmpty()) {
				return R.error().put("msg", "未上传文件");
			}

			InputPart filePart = fileParts.get(0);

			inputStream = filePart.getBody(InputStream.class, null);

			ExcelLogs logs =new ExcelLogs();
			
			Collection<Map> c= ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd", logs , 0);
			for (Map map : c) {
				
				try {
					String loginname = map.get("读者证号").toString();
					
					TUser tu = userService.getUserByName(loginname);
					if (tu != null) {
						continue;
					}
					
					Long id = IdGen.randomLong();
					UserInfoVO user = new UserInfoVO();
					user.setUserId(id);
					user.setPeopleId(id);
					user.setLoginName(loginname);
					user.setUserName(map.get("姓名").toString());
					user.setPassword("000000");
					user.setType(0);
					user.setStatus(1);
					user.setOrgId(u.getOrgId());
					user.setRoleId(1L);
					user.setSex(map.get("性别").toString().equals("男")?1:2);
					user.setCardType(0);
					user.setCardNumber(map.get("读者证号").toString());
					user.setPhone("");
					user.setBirthday("");
					
					if (u != null) {
						user.setCreateAccount(u.getLoginName());
						user.setCreateBy(u.getId());
						user.setCreateName(u.getLoginName());
					}
					userService.saveUserInfo(user);
					
					TReader reader = new TReader();
					reader.setReaderCardNumber(map.get("读者证号").toString());
					reader.setPrePayment(NumberUtils.toDouble(map.get("预付款金额").toString()));
					reader.setArrear(NumberUtils.toDouble(map.get("欠款金额").toString()));
					reader.setId(user.getUserId());
					reader.setPeopleId(user.getPeopleId());
					reader.setReaderCardType(0);
					reader.setStatus("有效".equals(map.get("证状态").toString())?1:0);
					Date d1900=DateUtils.parseDate("1900/01/01", "yyyy/MM/dd");
					reader.setStartDate(DateUtils.addDays(d1900, NumberUtils.toInt(map.get("启用日期").toString())));
					reader.setEndDate(DateUtils.addDays(d1900, NumberUtils.toInt(map.get("终止日期").toString())));
					//entity.setGrade();
					//entity.setClasses(rv.getClasses());
					reader.setStuworkNumber(String.valueOf(map.get("学号工号")));
					reader.setLibraryId(u.getOrgId());
					reader.setRemark("");
					
					readerService.insert(reader);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok();
	}
}
