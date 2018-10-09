package com.szcti.lcloud.exchange.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.szcti.lcloud.exchange.dcn.IdWork;
import com.szcti.lcloud.exchange.entity.TBook;
import com.szcti.lcloud.exchange.entity.THolding;
import com.szcti.lcloud.exchange.entity.TPeople;
import com.szcti.lcloud.exchange.entity.TReader;
import com.szcti.lcloud.exchange.entity.TUser;
import com.szcti.lcloud.exchange.entity.TUserRole;
import com.szcti.lcloud.exchange.mapper.TBookMapper;
import com.szcti.lcloud.exchange.mapper.THoldingMapper;
import com.szcti.lcloud.exchange.mapper.TPeopleMapper;
import com.szcti.lcloud.exchange.mapper.TReaderMapper;
import com.szcti.lcloud.exchange.mapper.TUserMapper;
import com.szcti.lcloud.exchange.mapper.TUserRoleMapper;
import com.szcti.lcloud.exchange.service.DcnSycnService;

@Service
public class DcnSycnServiceImpl implements DcnSycnService {

	@Autowired
	private TPeopleMapper peoRepo;
	@Autowired
	private TUserMapper userRepo;
	@Autowired
	private TUserRoleMapper userRoleRepo;
	@Autowired
	private TReaderMapper readerRepo;
	@Autowired
	private TBookMapper bookRepo;
	@Autowired
	private THoldingMapper holedingRepo;

	@Value("${default.libid}")
	private Long libid;
	
	@Value("${default.userroleid}")
	private Long userroleid;
	
	@Override
	public void handle(String topic, String jsonData,int type) {
		String[] tp = topic.split("/");
		JSONObject obj = JSON.parseObject(jsonData);

		if (tp != null && tp.length == 3) {

			if (tp[1].endsWith("READER")) {
				if (tp[2].equals("INSERT") || tp[2].equals("UPDATE")) {

					saveUser(obj,type);
				}
				if (tp[2].equals("DELETE")) {
					String rowid = obj.getString("ROWID");
					deleteUser(rowid);
				}
			}

			if (tp[1].endsWith("BIBLIOS")) {
				if (tp[2].equals("INSERT") || tp[2].equals("UPDATE")) {

					try {
						saveBook(obj,type);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (tp[2].equals("DELETE")) {
					String rowid = obj.getString("ROWID");
					deleteBook(rowid);
				}
			}
			if (tp[1].endsWith("HOLDING")) {
				if (tp[2].equals("INSERT") || tp[2].equals("UPDATE")) {

					saveHOLDING(obj,type);
				}
				if (tp[2].equals("DELETE")) {
					String rowid = obj.getString("ROWID");
					deleteHOLDING(rowid);
				}
			}
		}
	}

	public void saveBook(JSONObject obj,int type) {
		TBook b = new TBook();
		b.setAuthor(obj.getString("AUTHOR"));
		b.setBookType(obj.getString("TYPE") == null ? "1" : obj.getString("TYPE"));
		b.setBookrecno(obj.getInteger("BOOKRECNO"));
		b.setCreateBy(1L);
		try {
			b.setCreateTime(DateUtils.parseDate(obj.getString("CREATETIME"), "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			e.printStackTrace();
			b.setCreateTime(new Date());
		}
		// b.setId(id);
		b.setIsbn(obj.getString("ISBN"));
		int pages = 1;
		try {
			String p = obj.getString("PAGE");
			String pp = p.replace("页", "").replace(",", "");
			pages = Integer.parseInt(pp);
		} catch (Exception e) {

		}
		b.setPages(pages);
		//b.setPic("https://www.szlib.org.cn/Search/images/cover-default-s.gif");

		float price = 0;
		try {
			String pr = obj.getString("PRICE");
			String ppr = pr.replace("CNY", "").replace("(全套)", "");
			price = Float.parseFloat(ppr);
		} catch (Exception e) {

		}
		b.setPrice(price);
		Date pubdate=null;
		if (obj.getString("PUBDATE") != null) {
			try {
				b.setPubdate(DateUtils.parseDate(obj.getString("PUBDATE"), "yyyy","yyyy.m","yyyy年m月d日","yyyy.m.d","yyyy。MM","yyyy-MM","yyyy年","yyyy-","[yyyy]","(yyyy)"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			b.setPubdate(pubdate);
		}
		
		b.setPublisher(obj.getString("PUBLISHER"));
		b.setRowid(obj.getString("ROWID"));
		b.setSummary(" ");
		b.setTitle(obj.getString("TITLE"));
		b.setBookType(obj.getString("CLASSNO"));
		b.setSubject(obj.getString("SUBJECT"));
		
//		TBook entity=new TBook();
//		entity.setRowid(obj.getString("ROWID"));
		List<TBook> book = bookRepo.selectList(new EntityWrapper<TBook>().eq("rowid", obj.getString("ROWID")));
		if (book != null && book.size()>0) {
			b.setId(book.get(0).getId());
//			if(type == 0) {
				
				bookRepo.updateById(b);
//			}
		} else {
			b.setId(IdWork.getId());
			bookRepo.insert(b);
		}

	}

	public void deleteBook(String rowid) {
	
		bookRepo.delete(new EntityWrapper<TBook>().eq("rowid", rowid));
	}

	public void saveHOLDING(JSONObject obj,int type) {
		THolding h = new THolding();
		h.setBarcode(obj.getString("BARCODE"));
		int BOOKRECNO = obj.getInteger("BOOKRECNO");
		TBook entity=new TBook();
		entity.setBookrecno(BOOKRECNO);
		List<TBook> b = bookRepo.selectList(new EntityWrapper<TBook>().eq("bookrecno", BOOKRECNO));//馆藏对应的图书;
		if (b != null && b.size()>0) {
			h.setBookId(b.get(0).getId());
		} else {
			return;
		}
		
		h.setBookrecno(BOOKRECNO);
		h.setCallNo(obj.getString("CALLNO"));
		h.setCurlib(libid);
		// h.setId(id);
		try {
			h.setIndate(DateUtils.parseDate(obj.getString("INDATE"), "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			e.printStackTrace();
			h.setIndate(new Date());
		}
		h.setOwnlib(libid);
		h.setRecno(obj.getInteger("RECNO"));
		h.setRowid(obj.getString("ROWID"));
		h.setShelf(0L);
		h.setSingleprice(obj.getFloat("SINGLEPRICE"));
		h.setSource(0);
		h.setStatus(obj.getInteger("STATE"));
		h.setTotalprice(obj.getFloat("TOTALPRICE"));
		
		THolding entity1=new THolding();
		entity1.setRowid(obj.getString("ROWID"));
		List<THolding> holding = holedingRepo.selectList(new EntityWrapper<THolding>().eq("rowid", obj.getString("ROWID")));
		if (holding != null && holding.size()>0) {
			h.setId(holding.get(0).getId());
			if(type ==0 ) {
				holedingRepo.updateById(h);
			}
		} else {
			h.setId(IdWork.getId());
			holedingRepo.insert(h);
		}

	}

	public void deleteHOLDING(String rowid) {
		
		holedingRepo.delete(new EntityWrapper<THolding>().eq("rowid", rowid));
	}

	public void saveUser(JSONObject obj,int type) {

		TUser u = new TUser();
		TPeople p = new TPeople();
		TReader r = new TReader();

		u.setCreateAccount("admin");
		u.setCreateBy(1L);
		u.setCreateName("admin");
		u.setCreateTime(obj.getDate("RDSTARTDATE"));
		u.setEnabled(1);
		u.setLoginName(obj.getString("RDID"));
		u.setOrgId(libid);
		u.setPassword(obj.getString("RDPASSWD"));
		u.setRowid(obj.getString("ROWID"));
		u.setStatus(1);
		u.setType(0);
		p.setCardNumber("0");
		p.setCardType(0);
		p.setEmail(obj.getString("RDEMAIL"));
		p.setIcon("");
		p.setPhone(obj.getString("RDPHONE"));

		int s = obj.getIntValue("RDSEX");
		if (s == 0) {
			p.setSex(2);
		} else {
			p.setSex(1);
		}
		p.setUsername(obj.getString("RDNAME"));

		r.setGrade(1);
		r.setLibraryId(libid);
		r.setReaderCardNumber("");
		r.setReaderCardType(1);
		r.setStatus(1);
		
		TUser entity=new TUser();
		entity.setRowid(u.getRowid());
		List<TUser> user = userRepo.selectList(new EntityWrapper<TUser>().eq("rowid", obj.getString("ROWID")));
		if (user == null|| user.size()==0) {

			long id = IdWork.getId();
			u.setId(id);
			p.setId(id);
			p.setUserId(id);
			r.setPeopleId(id);
			r.setId(id);

			userRepo.insertAllColumn(u);
			peoRepo.insertAllColumn(p);
			readerRepo.insertAllColumn(r);
			
			TUserRole ur =new TUserRole();
			ur.setUserId(id);
			ur.setRoleId(userroleid);
			userRoleRepo.insertAllColumn(ur);
			

		} else {
			long id = user.get(0).getId();
			u.setId(id);
			p.setId(id);
			p.setUserId(id);
			r.setPeopleId(id);
			r.setId(id);
			
			if(type == 0) {
				userRepo.updateById(u);
				peoRepo.updateById(p);
				readerRepo.updateById(r);
			}
		}
	}

	public void deleteUser(String rowid) {
		TUser user =new TUser();
		user.setRowid(rowid);
		List<TUser> u = userRepo.selectList(new EntityWrapper<TUser>().eq("rowid", rowid));
		
		if(u!=null && u.size()>0) {
			for (TUser tUser : u) {
				
				userRepo.deleteById(tUser.getId());
				peoRepo.deleteById(tUser.getId());
				readerRepo.deleteById(tUser.getId());
				userRoleRepo.delete(new EntityWrapper<TUserRole>().eq("user_id", tUser.getId()));
			}
		}
	}
}
