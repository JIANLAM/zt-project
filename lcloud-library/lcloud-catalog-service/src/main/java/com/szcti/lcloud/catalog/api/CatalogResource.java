package com.szcti.lcloud.catalog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.catalog.config.JwtYmlConfig;
import com.szcti.lcloud.catalog.entity.*;
import com.szcti.lcloud.catalog.entity.vo.*;
import com.szcti.lcloud.catalog.service.*;
import com.szcti.lcloud.common.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("catalog")
public class CatalogResource {
	@Autowired
	private CatalogService catalogService;
	@Autowired
	private JwtYmlConfig jwtYmlConfig;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AcceptanceService acceptanceService;
	@Autowired
	private HoldingService holdingService;
	@Autowired
	private BarcodeService BarcodeServiceImpl;

	/**
	 * 查询编目列表有编目记录号的书才显示 data为CatalogVO的json格式
	 */
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R list(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			CatalogVO vo = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			List<CatalogVO> CatalogVOList = catalogService.queryPage(vo);

			PageInfo p = new PageInfo(CatalogVOList);
			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 普通编目
	 */
	@Path("/general")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R general(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			System.out.print("进入general");
			CatalogVO vo = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}
			return R.ok().put("page", vo);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 验收编目(空页面)
	 */
	@Path("/acceptcatalog")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R acceptCatalog(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			CatalogVO vo = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}
			return R.ok().put("page", vo);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 普通编目：查询功能 isbn对应的书列表(不一定有编目号) data为CatalogVO的json格式
	 */
	@Path("/booklist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R bookList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			CatalogVO vo = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}

			List<CatalogVO> CatalogVOList = null;
			PageInfo p = new PageInfo(CatalogVOList);
			/********************************* 1中央库查询t_book *****************************/
			if (vo.getType().equals("1")) {
				PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
				CatalogVOList = catalogService.queryPage(vo);
				p = new PageInfo(CatalogVOList);
				if (CatalogVOList == null || CatalogVOList.size() == 0) {
					CatalogVO bookv = new CatalogVO();
					PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
					if ("isbn".equals(vo.getSearchKey())) {
						bookv.setIsbn(vo.getSearchValue());
					}
					if ("author".equals(vo.getSearchKey())) {
						bookv.setAuthor(vo.getSearchValue());
					}
					if ("bookType".equals(vo.getSearchKey())) {
						bookv.setBookType(vo.getSearchValue());
					}
					if ("title".equals(vo.getSearchKey())) {
						bookv.setTitle(vo.getSearchValue());
					}
					CatalogVOList = getBookInfo(bookv);
					p = new PageInfo(CatalogVOList);
					if (CatalogVOList != null || CatalogVOList.size() > 0) {
						for (CatalogVO v : CatalogVOList) {
							CatalogVO q = new CatalogVO();
							q.setBookId(v.getBookId());
							q.setLibraryId(libraryId);
							q.setIsbn(v.getIsbn());
							List<CatalogVO> li = catalogService.queryPage(q);
							if (li != null && li.size() > 0) {
								v.setId(li.get(0).getId());
								v.setRecount(li.get(0).getRecount());
							}
						}
					}
				}
			}
			/*********************************
			 * 0采访库库查询t_prebook
			 *****************************/
			if (vo.getType().equals("0") && (CatalogVOList == null || CatalogVOList.size() == 0)) {
				BookVO bvo = new BookVO();
				if (StringUtils.isNotEmpty(jsonStr)) {
					bvo = (BookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
				}
				PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
				List<BookVO> BookVOList = bookService.foreignBookPage(bvo);
				p = new PageInfo(BookVOList);
//                CatalogVOList=new ArrayList<CatalogVO>();
//                for (BookVO b : BookVOList) {
//                    CatalogVO c = new CatalogVO();
//                    c.setBookId(b.getId());
//                    c.setIsbn(b.getIsbn());
//                    c.setAuthor(b.getAuthor());
//                    c.setTitle(b.getTitle());
//                    c.setBookType(b.getBookType());
//                    c.setPublisher(b.getPublisher());
//                    c.setPubdate(b.getPubdate());
//                    c.setRecount(0);
//                    CatalogVOList.add(c);
//                }
//                p = new PageInfo(CatalogVOList);
			}
			/*********************************
			 * 0和1采访库和中央库查询t_prebook
			 *****************************/
			if (vo.getType().contains("0") && vo.getType().contains("1")) {
				BookVO bvo = new BookVO();
				if (StringUtils.isNotEmpty(jsonStr)) {
					bvo = (BookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
				}
				PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
				List<BookVO> BookVOList = bookService.foreignBookPage(bvo);
				p = new PageInfo(BookVOList);
				CatalogVOList = new ArrayList<CatalogVO>();
				for (BookVO b : BookVOList) {
					CatalogVO c = new CatalogVO();
					c.setBookId(b.getId());
					c.setIsbn(b.getIsbn());
					c.setAuthor(b.getAuthor());
					c.setTitle(b.getTitle());
					c.setBookType(b.getBookType());
					c.setPublisher(b.getPublisher());
					c.setPubdate(b.getPubdate());
					c.setRecount(0);
					CatalogVOList.add(c);
				}
				p = new PageInfo(CatalogVOList);
			}

			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 普通编目：查询功能 isbn对应的书列表(不一定有编目号) data为CatalogVO的json格式
	 */
	@Path("/foreignbooklist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R foreignBookList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			BookVO vo = new BookVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (BookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			List<BookVO> BookVOList = bookService.foreignBookPage(vo);

			PageInfo p = new PageInfo(BookVOList);
			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 普通编目：查询功能 isbn对应的书列表(不一定有编目号) data为CatalogVO的json格式
	 */
	@Path("/foreignbookinfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R foreignBookInfo(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			BookVO vo = new BookVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (BookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			List<BookVO> BookVOList = bookService.foreignBookPage(vo);
			BookVO b = null;
			if (BookVOList != null && BookVOList.size() > 0) {
				b = BookVOList.get(0);
			}
			return R.ok().put("Book", b);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 下载到中央库 data为BookVO的json格式
	 */
	@Path("/downloadbook")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R downLoadBook(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			BookVO vo = new BookVO();
			Book book = new Book();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (BookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
				book = (Book) JSONObject.toBean(JSONObject.fromObject(jsonStr), Book.class);
				book.setPubdate(vo.getPubdate());
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			Book b = new Book();
			b.setIsbn(vo.getIsbn());
			List<Book> BookVOList = bookService.queryPage(b);
			if (BookVOList != null && BookVOList.size() > 0) {
				return R.ok().put("msg", vo.getIsbn() + "已存在.");
			} else {
				bookService.insert(book);
				return R.ok().put("msg", vo.getIsbn() + "已下载成功到采访库.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 下载到采访库 data为BookVO的json格式
	 */
	@Path("/downloadprebook")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R downLoadPreBook(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			PrebookVO vo = new PrebookVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (PrebookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), PrebookVO.class);
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			PrebookVO b = new PrebookVO();
			b.setIsbn(vo.getIsbn());
			List<Book> BookVOList =new ArrayList(); //bookService.queryPagePreBook(b);
			if (BookVOList != null && BookVOList.size() > 0) {
				return R.ok().put("msg", vo.getIsbn() + "已存在.");
			} else {
				bookService.insertPreBook(vo);
				return R.ok().put("msg", vo.getIsbn() + "已下载成功到采访库.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 借购编目查询列表
	 */
	@Path("/lendbuylist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R lendBuyList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			// Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			LendBuyBookVO vo = new LendBuyBookVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (LendBuyBookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), LendBuyBookVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}
			PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
			List<LendBuyBookVO> list = catalogService.queryLendBuyPage(vo);
			PageInfo p = new PageInfo(list);
			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	private List<CatalogVO> getBookInfo(CatalogVO vo) {
		List<CatalogVO> list = new ArrayList<CatalogVO>();
		Book bvo = new Book();
		bvo.setId(vo.getBookId());
		bvo.setIsbn(vo.getIsbn());
		bvo.setTitle(vo.getTitle());
		bvo.setAuthor(vo.getAuthor());
		bvo.setBookType(vo.getBookType());
		List<Book> blist = bookService.queryPage(bvo);
		for (Book b : blist) {
			CatalogVO c = new CatalogVO();
			c.setBookId(b.getId());
			c.setIsbn(b.getIsbn());
			c.setAuthor(b.getAuthor());
			c.setTitle(b.getTitle());
			c.setBookType(b.getBookType());
			c.setPublisher(b.getPublisher());
			if (b.getPubdate() != null) {
				c.setPubdate(b.getPubdate());
			}
			c.setRecount(0);
			list.add(c);
		}
		return list;
	}

	/**
	 * 信息
	 */
	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R info(@PathParam("id") Long id) {
		CatalogVO vo = new CatalogVO();
		vo.setId(id);
		List<CatalogVO> list = catalogService.queryPage(vo);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return R.ok().put("catalog", vo);
	}

	/**
	 * 书目详情 查询isbn对应的 书+编目+馆藏 信息 参数：isbn：ISBN
	 */
	@Path("/cataloginfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R catalogInfo(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		CatalogVO vo = null;
		try {
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			CatalogVO catalogVO = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				catalogVO = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (catalogVO.getLibraryId() == null || (catalogVO.getLibraryId() > 0)) {
				catalogVO.setLibraryId(libraryId);
			}
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			vo = catalogService.getCatalogInfo(catalogVO);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.ok().put("CatalogVO", vo);
	}

	/**
	 * 书目详情 查询isbn的 书+编目+馆藏 信息 参数：isbn：ISBN
	 */
	@Path("/marccataloginfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R marcCatalogInfo(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		CatalogVO vo = null;
		try {
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			CatalogVO catalogVO = new CatalogVO();
			if (StringUtils.isNotEmpty(jsonStr)) {
				catalogVO = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
			}
			if (catalogVO.getLibraryId() == null || (catalogVO.getLibraryId() > 0)) {
				catalogVO.setLibraryId(libraryId);
			}
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			vo = catalogService.getMarcCatalogInfo(catalogVO);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.ok().put("CatalogVO", vo);
	}

	@Path("/add")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R add(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			Map<String, String> params = new HashMap<String, String>();
			ObjectMapper objectMapper = new ObjectMapper();
			if (StringUtils.isNotEmpty(jsonStr)) {
				params = objectMapper.readValue(jsonStr, Map.class);
			}
			CatalogVO o = new CatalogVO();
			o.setPageNum(null);
			o.setPageSize(null);
			o.setCreator(userid);
			o.setLibraryId(libraryId);
			return R.ok().put("CatalogVO", o);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 编目管理:普通、验收、借购 保存:
	 */
	@Path("/save")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R gneralSave(@Context HttpServletRequest request, String data,
			@HeaderParam("Authorization") String authToken) {
		try {
			String ip = HttpServletRequestUtil.getIpAddr(request);
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			CatalogVO catalogVO = new CatalogVO();
			if (StringUtils.isNotEmpty(data)) {
				JSONObject catalog = JSONObject.fromObject(data);
				catalogVO = (CatalogVO) JSONObject.toBean(catalog, CatalogVO.class);
				catalogVO.setBook((Book) JSONObject.toBean(JSONObject.fromObject(catalog.get("book")), Book.class));
				JSONArray hold = JSONArray.fromObject(catalog.get("holdingList"));
				catalogVO.setHoldingList(JSONArray.toList(hold, new HoldingVO(), new JsonConfig()));
			}
			catalogVO.setIp(ip);
			catalogVO.setCreator(userid);
			catalogVO.setLibraryId(libraryId);
			if (catalogVO.getHoldingList() != null && catalogVO.getHoldingList().size() > 0) {
				for (Object obj : catalogVO.getHoldingList()) {
					HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
							HoldingVO.class);
					List<HoldingVO> hlist = holdingService.queryPage(hvo);
					if (hvo.getId() == null && hlist != null && hlist.size() > 0) {
						return R.error().put("msg", hvo.getBarcode() + "条码号已存在。");
					}
				}
			}
			int count = catalogService.insertGeneralCatalog(catalogVO);

			if (count > 0) {
				return R.ok().put("count", count).put("isbn", catalogVO.getIsbn());
			}
			return R.ok().put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 编目管理:普通、验收、借购 删除:
	 * 
	 * @param request
	 * @param data
	 * @param authToken
	 * @return
	 */
	@Path("/singledelete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R gneralDelete(@Context HttpServletRequest request, String data,
			@HeaderParam("Authorization") String authToken) {
		try {
			String ip = HttpServletRequestUtil.getIpAddr(request);
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}

			CatalogVO catalogVO = new CatalogVO();
			if (StringUtils.isNotEmpty(data)) {
				JSONObject catalog = JSONObject.fromObject(data);
				catalogVO = (CatalogVO) JSONObject.toBean(catalog, CatalogVO.class);
				catalogVO.setBook((Book) JSONObject.toBean(JSONObject.fromObject(catalog.get("book")), Book.class));
				JSONArray hold = JSONArray.fromObject(catalog.get("holdingList"));
				catalogVO.setHoldingList(JSONArray.toList(hold, new HoldingVO(), new JsonConfig()));
			}
			catalogVO.setIp(ip);
			catalogVO.setCreator(userid);
			catalogVO.setLibraryId(libraryId);
			if (catalogVO.getHoldingList() != null && catalogVO.getHoldingList().size() > 0) {
				for (Object obj : catalogVO.getHoldingList()) {
					HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
							HoldingVO.class);
					List<HoldingVO> hlist = holdingService.queryPage(hvo);
					if (hvo.getId() == null && hlist != null && hlist.size() > 0) {
						return R.error().put("msg", hvo.getBarcode() + "条码号已存在。");
					}
				}
			}
			int count = catalogService.DeleteCatalog(catalogVO);
			if (count > 0) {
				return R.ok().put("count", count).put("isbn", catalogVO.getIsbn());
			}
			return R.error(-1, "馆藏数据馆藏状态为不可删除!");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * marc编目管理:普通、验收、借购 保存:
	 */
	@Path("/marcsave")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R marcSave(@Context HttpServletRequest request, String data,
			@HeaderParam("Authorization") String authToken) {
		try {
			String ip = HttpServletRequestUtil.getIpAddr(request);
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			CatalogVO catalogVO = new CatalogVO();
			if (StringUtils.isNotEmpty(data)) {
				catalogVO = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(data), CatalogVO.class);
			}
			catalogVO.setIp(ip);
			catalogVO.setCreator(userid);
			catalogVO.setLibraryId(libraryId);
			if (catalogVO.getHoldingList() != null && catalogVO.getHoldingList().size() > 0) {
				for (HoldingVO hvo : catalogVO.getHoldingList()) {
					HoldingCriteria hc = new HoldingCriteria();
					hc.createCriteria().andBarcodeEqualTo(hvo.getBarcode()).andOwnlibEqualTo(catalogVO.getLibraryId());
					List<Holding> hlist = holdingService.selectByExample(hc);
					if (hlist != null && hlist.size() > 0) {
						return R.error().put("msg", hvo.getBarcode() + "条码号已存在。");
					}
				}
			}
			int count = catalogService.insertMarcAcceptCatalog(catalogVO);
			if (count > 0) {
				return R.ok().put("count", count).put("isbn", catalogVO.getIsbn());
			}
			return R.ok().put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 修改
	 */
	@Path("/edit/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R edit(@PathParam("id") Long id, @QueryParam("jsonStr") String jsonStr,
			@HeaderParam("Authorization") String authToken) {
		try {
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			Map<String, String> params = new HashMap<String, String>();
			List<HashMap<String, Object>> rulelist = null;
			ObjectMapper objectMapper = new ObjectMapper();
			if (StringUtils.isNotEmpty(jsonStr)) {
				params = objectMapper.readValue(jsonStr, Map.class);
			}
			Catalog o = catalogService.selectByPrimaryKey(id);
			return R.ok().put("Catalog", o);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 修改保存
	 */
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R update(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken) {
		try {
			String ip = HttpServletRequestUtil.getIpAddr(request);
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			Catalog catalog = new Catalog();
			Map<String, String> params = new HashMap<String, String>();
			ObjectMapper objectMapper = new ObjectMapper();
			if (StringUtils.isNotEmpty(data)) {
				catalog = (Catalog) JSONObject.toBean(JSONObject.fromObject(data), Catalog.class);
				params = objectMapper.readValue(data, Map.class);
			}
			Catalog p = catalogService.selectByPrimaryKey(catalog.getId());

			int count = catalogService.updateByPrimaryKey(p);
			if (count > 0) {
				OperationLog o = operationLogService.getUserInfo(userid);
				o.setLibraryId(libraryId);
				o.setModuleId(9);
				o.setModuleName("编目管理");
				o.setOperationType("修改");
				o.setUserId(userid);
				o.setIp(ip);
				o.setOpContent("修改" + catalog.getIsbn());
				operationLogService.insert(o);
			}
			return R.ok().put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 删除
	 */
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R delete(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken) {
		try {
			String ip = request.getServerName();
			Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			if (libraryId == null || !(libraryId > 0)) {
				return R.error().put("msg", "无权操作！请联系管理员。");
			}
			Map<String, String> params = new HashMap<String, String>();
			ObjectMapper objectMapper = new ObjectMapper();
			params = objectMapper.readValue(data, Map.class);
			String ids = params.get("ids");
			String[] array = ids.split(",");
			int count = 0;
			for (String id : array) {
				Long idl = Long.parseLong(id);
				if (idl > 0) {
					String isbn = catalogService.selectByPrimaryKey(Long.parseLong(id)).getIsbn();
					int c = catalogService.deleteByPrimaryKey(Long.parseLong(id));
					if (c > 0) {
						count++;
						OperationLog o = operationLogService.getUserInfo(userid);
						o.setLibraryId(libraryId);
						o.setModuleId(9);
						o.setModuleName("编目管理");
						o.setOperationType("删除");
						o.setUserId(userid);
						o.setIp(ip);
						o.setOpContent("删除" + isbn);
						operationLogService.insert(o);
					}
				}
			}
			return R.ok().put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

//    /**
//     * 获取馆藏中最大的条码号
//     */
//    @Path("/getmaxbarcode")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public R getMaxBarcode(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
//        try {
//            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
//            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
//            LableVO vo = new LableVO();
//            if(StringUtils.isNotEmpty(jsonStr)){
//                vo =(LableVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), LableVO.class);
//            }if(vo.getLibraryId()==null||(vo.getLibraryId()>0)){
//                vo.setLibraryId(libraryId);
//            }
//            LableVO lableVO =holdingService.getMaxBarcode(vo);
//            return R.ok().put("LableVO", lableVO);
//        }catch (Exception e){
//            e.printStackTrace();
//            return R.error();
//        }
//    }
	/**
	 * 获取馆藏中最大的条码号
	 */
	@Path("/getmaxbarcode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public R getMaxBarcode(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
			LableVO vo = null;
			if (StringUtils.isNotEmpty(jsonStr)) {
				vo = (LableVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), LableVO.class);
			}
			if (vo.getLibraryId() == null || (vo.getLibraryId() > 0)) {
				vo.setLibraryId(libraryId);
			}
			LableVO lableVO = BarcodeServiceImpl.queryCurrBarcode(vo);

			if (lableVO != null) {
				long stopBarcode = lableVO.getStopBarcode();// 终止条码
				long barcode = lableVO.getCurrBarcode();// 当前条码
				// 启始条码数等于终止条码数，即当前条码前缀已经不能使用
				if (stopBarcode != barcode) {
					lableVO.setSurplusBarcode(stopBarcode - barcode);
				} else {
					lableVO.setSurplusBarcode(0);
				}
			}
			return R.ok().put("LableVO", lableVO);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
