package com.szcti.lcloud.purchase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.*;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.service.*;
import com.szcti.lcloud.purchase.service.impl.FileUploadUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 
 * @描述: 订购单操作服务类 
 * @版权: Copyright (c) 2018 
 * @公司: 深圳市中图信息技术有限公司
 * @作者: tianbw
 * @版本: 1.0 
 * @创建日期: 2018年9月6日 
 * @创建时间: 下午7:04:07
 */
@Component
@Path("purchasebook")
public class PurchaseBookResource
{
    
    private static final Logger  logger     = Logger.getLogger(PurchaseBookResource.class);
    
    @Autowired
    private PurchaseBookService  purchaseBookService;
    
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    
    @Autowired
    PurchaseRuleService          purchaseRuleService;
    
    @Autowired
    PrebookService               prebookService;
    
    @Autowired
    BookService                  bookService;
    
    @Autowired
    private OperationLogService  operationLogService;
    
    @Autowired
    private JwtYmlConfig         jwtYmlConfig;
    
    private String               errorKey   = "msg";
    
    private String               errorValue = "无权操作！请联系管理员。";
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            /**
             *   校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            PurchaseBookVO prechasebookvo = null;
            PageInfo p = null;
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                prechasebookvo = (PurchaseBookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseBookVO.class);
                
                if ( prechasebookvo.getOrderId() == null || prechasebookvo.getOrderId() <= 0 )
                {
                    return R.error( -1, "orderId 订购号不能为空！");
                }
                
                //                if ( prebookVO.getRuleId() == null || prebookVO.getRuleId() <= 0 )
                //                {
                //                    return R.error( -1, "RuleId 订购规则id不能为空！");
                //                }
                
                if ( prechasebookvo.getSource() == null || prechasebookvo.getSource().equals("") )
                {
                    return R.error( -1, "Source 数据源不能为空！");
                }
                
                /**
                 * 检索字段值为:isbn|id|title|author|book_type
                 */
                if ( "isbn".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setIsbn(prechasebookvo.getSearchValue());
                }
                if ( "id".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setAuthor(prechasebookvo.getSearchValue());
                }
                if ( "title".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setTitle(prechasebookvo.getSearchValue());
                }
                if ( "author".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setAuthor(prechasebookvo.getSearchValue());
                }
                if ( "bookType".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setBookType(prechasebookvo.getSearchValue());
                }
                if ( "publisher".equals(prechasebookvo.getSearchKey()) )
                {
                    prechasebookvo.setPublisher(prechasebookvo.getSearchValue());
                }
                
            }
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            prechasebookvo.setLibraryId(libraryId);
            /**
             * 中央库查询
             */
            if ( prechasebookvo.getSource().equals("0") )
            {
                List<PurchaseBookVO> bookList = purchaseBookService.getPageBook(prechasebookvo);
                p = new PageInfo(bookList);
            }
            /**
             * 采访库查询
             */
            if ( prechasebookvo.getSource().equals("1") )
            {
                List<PurchaseBookVO> bookList = purchaseBookService.queryPagePreBook(prechasebookvo);
                p = new PageInfo(bookList);
            }
            /**
             * 新华库查询
             */
            if ( prechasebookvo.getSource().equals("2") )
            {
                List<PurchaseBookVO> bookList = purchaseBookService.queryPageBookXH(prechasebookvo);
                
                p = new PageInfo(bookList);
            }
            
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        
        return R.error();
        
    }
    
    //    /**
    //     * 设置订购数
    //     */
    //    @Path("/setbookqty")
    //    @POST
    //    @Produces(MediaType.APPLICATION_JSON)
    //    public R setbookqty(@Context HttpServletRequest request, String data,
    //            @HeaderParam("Authorization") String authToken)
    //    {
    //        try
    //        {
    //            String ip = HttpServletRequestUtil.getIpAddr(request);
    //            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
    //            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
    //            if ( libraryId == null || !(libraryId > 0) )
    //            {
    //                return R.error().put("msg", "无权操作！请联系管理员。");
    //            }
    //            Map<String, Object> params = new HashMap<String, Object>();
    //            ObjectMapper objectMapper = new ObjectMapper();
    //            params = objectMapper.readValue(data, Map.class);
    //            Long orderId = null;
    //            if ( params.get("orderId") != null && !params.get("orderId").equals("") )
    //            {
    //                orderId = Long.parseLong(params.get("orderId").toString());
    //            }
    //            String ids = (String) params.get("ids");
    //            String[] array = ids.split(",");
    //            int count = 0;
    //            if ( orderId > 0 && array != null && array.length > 0 )
    //            {
    //                String purchaseCode = purchaseOrderService.selectByPrimaryKey(orderId).getPurchaseCode();
    //                count = purchaseBookService.setBookQt(array, orderId, params);
    //                if ( count > 0 )
    //                {
    //                    OperationLog o = operationLogService.getUserInfo(userid);
    //                    o.setLibraryId(libraryId);
    //                    o.setModuleId(1);
    //                    o.setModuleName("征订目录");
    //                    o.setOperationType("设置订购数");
    //                    o.setUserId(userid);
    //                    o.setOpContent(purchaseCode + "设置订购数");
    //                    o.setIp(ip);
    //                    operationLogService.insert(o);
    //                }
    //            }
    //            return R.ok().put("count", count);
    //        }
    //        catch (Exception e)
    //        {
    //            e.printStackTrace();
    //            return R.error();
    //        }
    //    }
    
    /**
     * 
     * @描述：删除订购单中的已选书
     * @作者：tianbw
     * @时间：2018年9月6日 下午7:05:26
     * @param request
     * @param data
     * @param authToken
     * @return
     */
    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R delete(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            if ( data == null || data.isEmpty() )
            {
                return R.error( -1, "data 不能为空！");
            }
            
            /**
             *   校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            String ip = HttpServletRequestUtil.getIpAddr(request);
            
            PurchaseBookVO purchaseBookVo = null;
            
            if ( StringUtils.isNotEmpty(data) )
            {
                JSONObject catalog = JSONObject.fromObject(data);
                purchaseBookVo = (PurchaseBookVO) JSONObject.toBean(catalog, PurchaseBookVO.class);
                JSONArray purchasebook = JSONArray.fromObject(catalog.get("purchasebooklist"));
                purchaseBookVo
                        .setPurchasebooklist(JSONArray.toList(purchasebook, new PurchaseBook(), new JsonConfig()));
                
                if ( purchaseBookVo.getOrderId() == null || purchaseBookVo.getOrderId() <= 0 )
                {
                    return R.error( -1, "orderId 订购号不能为空！");
                }
                if ( purchaseBookVo.getRuleId() == null || purchaseBookVo.getRuleId() <= 0 )
                {
                    return R.error( -1, "RuleId 订购规则id不能为空！");
                }
            }
            
            List<PurchaseBook> purchaseBook = purchaseBookVo.getPurchasebooklist();
            int count = 0;
            for (int i = 0; i < purchaseBook.size(); i++)
            {
                if ( purchaseBook.get(i).getSource() == null || purchaseBook.get(i).getSource() <= 0 )
                {
                    return R.error( -1, "source 数据源不能为空！");
                }
                if ( purchaseBook.get(i).getBookId() == null || purchaseBook.get(i).getBookId() <= 0 )
                {
                    return R.error( -1, "bookId不能为空！");
                }
                purchaseBook.get(i).setLibraryId(libraryId.toString());
                purchaseBook.get(i).setOrderId(purchaseBookVo.getOrderId());
                
                if ( purchaseOrderService.queryPurchaseBook(purchaseBook.get(i)) != null )
                {
                    count = purchaseOrderService.deletePurchaseBook(purchaseBook.get(i));
                }
            }
            if ( count > 0 )
            {
                return R.ok();
            }
            return R.error( -1, "非法操作,执行失败");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 采访图书查重 入参：orderId订购单id，ids：订购单书记录id
     */
    @Path("/dupsearch")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R dupSearch(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            String ids = params.get("ids");
            Long orderId = null;
            String[] array = null;
            if ( ids != null && !ids.equals("") )
            {
                array = ids.split(",");
            }
            
            if ( params.get("orderId") != null && !params.get("orderId").equals("") )
            {
                orderId = Long.parseLong(params.get("orderId").toString());
                libraryId = purchaseOrderService.selectByPrimaryKey(orderId).getLibraryId();
            }
            Map<String, String> m = new HashMap<String, String>();
            if ( array != null && array.length > 0 )
            {
                for (String id : array)
                {
                    Long idl = Long.parseLong(id);
                    Map resMap = purchaseBookService.dupCheckPrebookRes(idl, libraryId, orderId);
                    String res = "";
                    int checked = (Integer) resMap.get("checked");
                    int commit = (Integer) resMap.get("commit");
                    int library = (Integer) resMap.get("library");
                    res = "审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                    if ( (checked + commit + library) == 0 )
                    {
                        res = "无重复数.";
                    }
                    else
                    {
                        if ( resMap.get("rule") != null && !resMap.get("rule").equals("") )
                        {
                            int rule = (Integer) resMap.get("rule");
                            res = "还可设置:" + rule + ",审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                            
                        }
                        if ( resMap != null && resMap.get("remain") != null && !resMap.get("remain").equals("") )
                        {
                            int remain = (Integer) resMap.get("remain");
                            res = "还可设置:" + remain + ",审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                        }
                    }
                    m.put(idl + "", res);
                }
            }
            return R.ok().put("res", m);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 已选图书查重
     */
    @Path("/dupcheck")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R dupcheck(@QueryParam("jsonStr") String jsonStr)
    {
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            String ids = params.get("ids");
            String[] array = null;
            if ( ids != null && !ids.equals("") )
            {
                array = ids.split(",");
            }
            else
            {
                Long orderId = null;
                if ( params.get("orderId") != null && !params.get("orderId").equals("") )
                {
                    orderId = Long.parseLong(params.get("orderId").toString());
                    BookInfo bi = new BookInfo();
                    bi.setOrderId(orderId);
                    List<Long> bookIdList = purchaseBookService.getBooks(bi);
                    array = new String[bookIdList.size()];
                    bookIdList.toArray(array);
                }
            }
            Map<String, String> m = new HashMap<String, String>();
            if ( array != null && array.length > 0 )
            {
                for (String id : array)
                {
                    Long idl = Long.parseLong(id);
                    Map resMap = purchaseBookService.dupCheckBookRes(idl);
                    String res = "";
                    int checked = (Integer) resMap.get("checked");
                    int commit = (Integer) resMap.get("commit");
                    int library = (Integer) resMap.get("library");
                    if ( (checked + commit + library) == 0 )
                    {
                        res = "无重复数.";
                    }
                    else
                    {
                        if ( resMap.get("rule") != null && !resMap.get("rule").equals("") )
                        {
                            int rule = (Integer) resMap.get("rule");
                            res = "还可设置:" + rule + ",审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                            
                        }
                        else if ( resMap != null && resMap.get("remain") != null && !resMap.get("remain").equals("") )
                        {
                            int remain = (Integer) resMap.get("remain");
                            res = "还可设置:" + remain + ",审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                        }
                        else if ( resMap == null || (resMap.get("rule") == null || resMap.get("rule").equals("")) )
                        {
                            res = "还可设置:任意数量.审核已通过的书:" + checked + ",采购中的书:" + commit + ",馆藏已有的书:" + library;
                        }
                    }
                    m.put(idl + "", res);
                }
            }
            return R.ok().put("res", m);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 获取解析上传的文件
     *
     * @param multipartFormDataInput
     * @return
     * @throws Exception
     */
    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA) //接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importBooks(@Context HttpServletRequest request, MultipartFormDataInput multipartFormDataInput,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            String orderId = multipartFormDataInput.getFormDataMap().get("orderId").get(0).getBodyAsString();
            String filePathName = new FileUploadUtil().uploadStoreFile(multipartFormDataInput, "fileName");
            R r = importOrder(filePathName, orderId);
            if ( orderId != null && !orderId.equals("") && (int) r.get("count") > 0 )
            {
                String purchaseCode = purchaseOrderService.selectByPrimaryKey(Long.parseLong(orderId))
                        .getPurchaseCode();
                OperationLog o = operationLogService.getUserInfo(userid);
                o.setLibraryId(libraryId);
                o.setModuleId(1);
                o.setModuleName("征订目录");
                o.setOperationType("导入");
                o.setUserId(userid);
                o.setIp(ip);
                o.setOpContent("导入书:" + purchaseCode);
                operationLogService.insert(o);
            }
            return r;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    public R importOrder(String excelUrl, String orderId)
    {
        try
        {
            List<PurchaseBook> list = new ArrayList<PurchaseBook>();
            List<Map> maplist = POITool.getData(excelUrl);
            Map<String, String> resMap = new HashMap();
            for (Map m : maplist)
            {
                PurchaseBook b = getBook(m);
                b.setOrderId(Long.parseLong(orderId));
                if ( (b.getPrebookId() != null && b.getPrebookId() > 0)
                        || (b.getBookId() != null && b.getBookId() > 0) )
                {
                    list.add(b);
                }
                else
                {
                    resMap.put(b.getIsbn(), b.getIsbn() + "不存在书库中;");
                }
            }
            int count = 0;
            if ( list.size() > 0 )
            {
                count = purchaseBookService.importExcel(list, Long.parseLong(orderId));
                resMap.put("count", count + "");
            }
            return R.ok().put("res", resMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    private PurchaseBook getBook(Map m)
    {
        PurchaseBook b = new PurchaseBook();
        String isbn = m.get("isbn") == null ? "" : (String) m.get("isbn");
        String price = m.get("price") == null ? "0" : (String) m.get("price");
        String bookQty = m.get("bookQty") == null ? "0" : (String) m.get("bookQty");
        b.setIsbn(isbn);
        if ( StringUtils.isNotBlank(isbn) )
        {
            PrebookCriteria pc = new PrebookCriteria();
            PrebookCriteria.Criteria criteria = pc.createCriteria();
            criteria.andIsbnEqualTo(isbn);
            List<Prebook> list = prebookService.selectByExample(pc);
            if ( list != null && list.size() > 0 )
            {
                b = new PurchaseBook();
                Prebook pre = list.get(0);
                b.setIsbn(pre.getIsbn());
                b.setStatus(new Integer("0"));
                b.setBookQty(Integer.parseInt(bookQty));
                b.setGoodsCode(pre.getGoodsCode());
                b.setPrebookId(pre.getId());
                b.setTotalPrice(b.getBookQty() * pre.getPrice());
            }
            else
            {
                BookCriteria bc = new BookCriteria();
                bc.createCriteria().andIsbnEqualTo(isbn);
                List<Book> bookList = bookService.selectByCriteria(bc);
                if ( bookList != null && bookList.size() > 0 )
                {
                    b = new PurchaseBook();
                    Book pre = bookList.get(0);
                    b.setIsbn(pre.getIsbn());
                    b.setStatus(new Integer("0"));
                    b.setBookQty(Integer.parseInt(bookQty));
                    //b.setGoodsCode(pre.getGoodsCode());
                    b.setBookId(pre.getId());
                    b.setTotalPrice(b.getBookQty() * pre.getPrice());
                }
            }
        }
        return b;
    }
    
    /**
     * 导出
     */
    @Path("/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportOrder(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr)
    {
        try
        {
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            String serverUrl = "http://" + request.getServerName() + ":" + new POITool().getExportPort();
            String fileName = "";
            String xlsurl = "";
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
                if ( params.get("orderId") != null && !"".equals(params.get("orderId").toString()) )
                {
                    String orderId = params.get("orderId") + "";
                    fileName = purchaseBookService.exportExcel(Long.parseLong(orderId), new ArrayList());
                    xlsurl = serverUrl + fileName;
                }
                else if ( params.get("ids") != null && !"".equals(params.get("ids").toString()) )
                {
                    String ids = (String) params.get("ids");
                    String[] array = ids.split(",");
                    fileName = purchaseBookService.exportExcel(null, Arrays.asList(array));
                    xlsurl = serverUrl + fileName;
                }
            }
            return R.ok().put("xlsurl", xlsurl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 自动审核 var jsonStr=JSON.stringify({"ids":'1,2,3,6'})
     * ajax中: type:'post'
     *         data:jsonStr
     */
    @Path("/autocheck")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R autoCheck(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String) params.get("ids");
            String[] array = ids.split(",");
            params.put("checker", userid);
            params.put("checkTime", DateUtils.getDateTime());
            int count = 0;
            Map m = new HashMap();
            Map returnMap = new HashMap();
            for (String id : array)
            {
                StringBuilder msg = new StringBuilder("");
                if ( id != null && !id.equals("") )
                {
                    params.put("id", Long.parseLong(id));
                    PurchaseBook pb = purchaseBookService.selectByPrimaryKey(Long.parseLong(id));
                    if ( pb == null )
                    {
                        return R.ok().put("count", count);
                    }
                    Boolean re = purchaseBookService.autoCheck(Long.parseLong(id), m);
                    if ( re )
                    {
                        pb.setStatus(new Integer("1"));
                        pb.setCheckedmsg("通过");
                        int c = purchaseBookService.updateByPrimaryKey(pb);
                        purchaseOrderService.refleshCheckStatus(userid, pb.getOrderId());
                        if ( c > 0 )
                        {
                            count++;
                            returnMap.put(id, re);
                        }
                    }
                    else
                    {
                        pb.setStatus(new Integer("2"));
                        msg = new StringBuilder("不通过");
                        Map resmap = (Map) m.get("map");
                        for (Object key : resmap.keySet())
                        {
                            if ( !((String) resmap.get(key).toString()).equals("true") )
                            {
                                msg.append("," + (String) resmap.get(key));
                            }
                        }
                        pb.setCheckedmsg(msg.toString());
                        int c = purchaseBookService.updateByPrimaryKey(pb);
                        if ( c > 0 )
                        {
                            count++;
                            returnMap.put(id, msg);
                        }
                    }
                }
            }
            if ( params.get("orderId") != null && !"".equals(params.get("orderId") + "") )
            {
                Long oid = Long.parseLong(params.get("orderId") + "");
                purchaseOrderService.refleshCheckStatus(userid, oid);
                String purchaseCode = purchaseOrderService.selectByPrimaryKey(oid).getPurchaseCode();
                OperationLog o = operationLogService.getUserInfo(userid);
                o.setLibraryId(libraryId);
                o.setModuleId(1);
                o.setModuleName("征订目录");
                o.setOperationType("自动审核");
                o.setUserId(userid);
                o.setOpContent(purchaseCode + "执行自动审核");
                o.setIp(ip);
                operationLogService.insert(o);
            }
            return R.ok().put("count", count).put("res", returnMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 强行审核 var jsonStr=JSON.stringify({"ids":'1,2,3,6',"checkStatus":'1'})
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/checked")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R checked(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String) params.get("ids");
            String[] array = ids.split(",");
            params.put("checker", userid);
            params.put("checkTime", DateUtils.getDateTime());
            int count = 0;
            for (String id : array)
            {
                if ( id != null && !id.equals("") )
                {
                    params.put("id", Long.parseLong(id));
                    PurchaseBook pb = purchaseBookService.selectByPrimaryKey(Long.parseLong(id));
                    String msg = pb.getCheckedmsg();
                    pb.setStatus(new Integer(1));
                    pb.setCheckedmsg("(强行通过)" + msg);
                    int re = purchaseBookService.updateByPrimaryKey(pb);
                    if ( re > 0 )
                    {
                        String purchaseCode = purchaseOrderService.selectByPrimaryKey(pb.getOrderId())
                                .getPurchaseCode();
                        OperationLog o = operationLogService.getUserInfo(userid);
                        o.setLibraryId(libraryId);
                        o.setModuleId(1);
                        o.setModuleName("征订目录");
                        o.setOperationType("强行审核");
                        o.setUserId(userid);
                        o.setOpContent(purchaseCode + "强行审核通过已选书isbn" + pb.getIsbn());
                        o.setIp(ip);
                        operationLogService.insert(o);
                        count++;
                    }
                }
            }
            return R.ok().put("count", count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 图书馆端订购验收详情
     */
    @Path("/getaccepted")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R getAccepted(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            PurchaseBookVO purchaseBookVO = new PurchaseBookVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                System.out.println("jsonStr===" + jsonStr);
                purchaseBookVO = (PurchaseBookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseBookVO.class);
            }
            if ( purchaseBookVO.getLibraryId() == null || (purchaseBookVO.getLibraryId() > 0) )
            {
                purchaseBookVO.setLibraryId(libraryId);
            }
            PageHelper.startPage(purchaseBookVO.getPageNum(), purchaseBookVO.getPageSize());
            List<PurchaseBookVO> purchaseBookVOList = purchaseBookService.getAcceptBook(purchaseBookVO);
            PageInfo p = new PageInfo(purchaseBookVOList);
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 导出催缺单 入参orderId：订购单id
     */
    @Path("/exportlack")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportLack(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr)
    {
        try
        {
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            String serverUrl = "http://" + request.getServerName() + ":" + new POITool().getExportPort();
            String fileName = "";
            String xlsurl = "";
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
                if ( StringUtils.isNotEmpty(params.get("orderId") + "") )
                {
                    String orderId = params.get("orderId") + "";
                    fileName = purchaseBookService.exportLack(Long.parseLong(orderId));
                    xlsurl = serverUrl + fileName;
                }
            }
            return R.ok().put("xlsurl", xlsurl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 
     * @描述：通用校验权限方法
     * @作者：tianbw
     * @时间：2018年8月25日 上午9:03:48
     * @param authToken
     * @return
     */
    public Boolean checkAuthorization(String authToken)
    {
        
        Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
        if ( libraryId == null || !(libraryId > 0) )
        {
            return false;
        }
        return true;
        
    }
}
