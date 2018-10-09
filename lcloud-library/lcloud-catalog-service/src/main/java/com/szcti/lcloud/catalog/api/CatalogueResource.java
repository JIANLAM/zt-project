package com.szcti.lcloud.catalog.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.github.pagehelper.PageHelper;
import com.szcti.lcloud.catalog.repository.BookRepository;
import com.szcti.lcloud.common.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.catalog.config.JwtYmlConfig;
import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.PrebookVO;
import com.szcti.lcloud.catalog.service.BookService;
import com.szcti.lcloud.catalog.service.CatalogService;
import com.szcti.lcloud.catalog.service.HoldingService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * @描述: 编目管理API处理层 
 * @版权: Copyright (c) 2018 
 * @公司: 深圳市中图信息技术有限公司
 * @作者: tianbw
 * @版本: 1.0 
 * @创建日期: 2018年8月25日 
 * @创建时间: 上午8:43:45
 */
@Component
@Path("catalogue")
public class CatalogueResource
{
    private Logger         logger     = Logger.getLogger(CatalogueResource.class);
    
    @Autowired
    private JwtYmlConfig   jwtymlconfig;
    
    @Autowired
    private CatalogService catalogService;
    
    @Autowired
    private HoldingService holdingService;
    
    @Autowired
    private BookService    bookService;

    @Autowired
    private BookRepository bookRepository;
    
    private String         errorKey   = "msg";
    
    private String         errorValue = "无权操作！请联系管理员。";

    /**
     *
     * @描述：书目导入导出 初始化数据列表   采访库   MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @return
     */
    @Path("/preBook/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R preBookList(@QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            PageHelper.startPage(catalogVO.getPageNum(),catalogVO.getPageSize());
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            List<PrebookVO> preBookList = bookService.queryPagePreBook(catalogVO);

            PageInfo p = new PageInfo(preBookList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     *
     * @描述：书目导入导出 初始化数据列表   中央库   MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @return
     */
    @Path("/book/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R bookList(@QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            PageHelper.startPage(catalogVO.getPageNum(),catalogVO.getPageSize());
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            List<Book> bookList = bookService.getBookInfo(catalogVO);

            PageInfo p = new PageInfo(bookList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     *
     * @描述：书目导入导出 查看一条内容详细marc信息    采访库      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param preBookId
     * @return
     */
    @Path("/infopre/{preBookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getpreBook(@PathParam("preBookId") Long preBookId){
        try {
            PrebookVO prebookVO = bookRepository.getPreBook(preBookId);
            String marcpreBookString = MarcUtil.obj2Rows(prebookVO);
            return R.ok().put("marcpreBookString", marcpreBookString.replace("\n","<br>"));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：书目导入导出 查看一条内容详细marc信息    中央库      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param bookId
     * @return
     */
    @Path("/info/{bookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getBook(@PathParam("bookId") Long bookId){
        try {
            Book book = bookRepository.selectByPrimaryKey(bookId);
            String marcBookString = MarcUtil.obj2Rows(book);
            return R.ok().put("marcBookString", marcBookString.replace("\n","<br>"));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：导出采访库书目数据列表      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/exportPrebookMARC")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportPrebookMARC(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            String fileName = bookService.exportPrebookMARC(catalogVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：导出中央库书目数据列表      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/exportBookMarc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportBookMarc(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            String fileName = bookService.exportBookMARC(catalogVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     *
     * @描述：批量导入 采访书库书目 数据记录     MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @return
     */
    @Path("/importPrebookMarc")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importPrebookMarc(@Context HttpServletRequest request, MultipartFormDataInput multipartFormDataInput , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist=  MarcUtil.getInfoByISO(filePathName);
                String result = bookService.importPrebooklist(maplist , libraryId , userid , "marc");
                //String result = bookService.importPrebooklist(maplist , new Long(2) , new Long(4) , "marc");
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据存在异常！  错误可能原因：1、录入数据不规范").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }



    /**
     *
     * @描述：批量导入 中央书库书目 数据记录     MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @return
     */
    @Path("/importBookMarc")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importBookMarc(@Context HttpServletRequest request, MultipartFormDataInput multipartFormDataInput , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist = MarcUtil.getInfoByISO(filePathName);
                String result = bookService.importBooklist(maplist , libraryId , userid , "marc");
                //String result = bookService.importBooklist(maplist , new Long(2) , new Long(4) , "marc");
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据存在异常！  错误可能原因：1、录入数据不规范").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }


    /**
     *
     * @描述：批量导入 采访书库书目 数据记录     Excel
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @return
     */
    @Path("/importPrebook")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importPrebooklist(@Context HttpServletRequest request, MultipartFormDataInput multipartFormDataInput , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            new FileUploadUtil().getFileType(filePathName);
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist= POITool.importXls(filePathName);
                String result = bookService.importPrebooklist(maplist , libraryId , userid , "excel");
                //String result = bookService.importPrebooklist(maplist , new Long("293109093374") , new Long(4) , "excel");
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据存在异常！  错误可能原因：1、录入数据不规范").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }


    /**
     *
     * @描述：批量导入 中央书库书目 数据记录     Excel
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @return
     */
    @Path("/importBook")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importBooklist(@Context HttpServletRequest request, MultipartFormDataInput multipartFormDataInput , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            new FileUploadUtil().getFileType(filePathName);
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist= POITool.importXls(filePathName);
                String result = bookService.importBooklist(maplist , libraryId , userid , "excel");
                //String result = bookService.importBooklist(maplist , new Long(1) , new Long(4) , "excel");
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据存在异常！  错误可能原因：1、录入数据不规范").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }

    /**
     *
     * @描述：下载导入书目 模板        Excel
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @return
     */
    @Path("/exportMould")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMouldExcel(@Context HttpServletRequest request){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            String fileName = bookService.exportMouldExcel();
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：导出采访库书目数据列表      Excel
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/exportPreBook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportPreBookExcel(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            String fileName = bookService.exportPreBookExcel(catalogVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：导出中央库书目数据列表      Excel
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/exportBook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportBookExcel(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            CatalogVO catalogVO = new CatalogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                catalogVO = (CatalogVO)JSONUtil.json2Object(jsonStr,CatalogVO.class);
            }
            catalogVO.setLibraryId(libraryId);        //图书馆ID
            String fileName = bookService.exportBookExcel(catalogVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    
    /**
     * 
     * @描述：普通编目 字段检索以及指定库查询
     * @作者：tianbw
     * @时间：2018年8月25日 上午10:02:48
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R booklist(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
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
            
            CatalogVO vo = null;
            PageInfo p = null;
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                vo = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
                /**
                 * 检索字段值为:isbn|id|title|author|book_type
                 */
                if ( "isbn".equals(vo.getSearchKey()) )
                {
                    vo.setIsbn(vo.getSearchValue());
                }
                if ( "id".equals(vo.getSearchKey()) )
                {
                    vo.setAuthor(vo.getSearchValue());
                }
                if ( "title".equals(vo.getSearchKey()) )
                {
                    vo.setTitle(vo.getSearchValue());
                }
                if ( "author".equals(vo.getSearchKey()) )
                {
                    vo.setAuthor(vo.getSearchValue());
                }
                if ( "bookType".equals(vo.getSearchKey()) )
                {
                    vo.setBookType(vo.getSearchValue());
                }
                
            }
            /**
             * 中央库查询
             */
            if ( vo.getType().equals("0") )
            {
                List<Book> bookList = bookService.getBookInfo(vo);
                p = new PageInfo(bookList);
            }
            /**
             * 采访库查询
             */
            if ( vo.getType().equals("1") )
            {
                PrebookVO b = new PrebookVO();
                List<PrebookVO> bookList = bookService.queryPagePreBook(vo);
                p = new PageInfo(bookList);
            }
            /**
             * 多库查询
             */
            if ( vo.getType().length() > 1 )
            {
                List result=new ArrayList();
                if ( vo.getType().contains("0") )
                {
                    List<Book> bookList = bookService.getBookInfo(vo);
                  
                    result.add(bookList);
                }
                if ( vo.getType().contains("1") )
                {
                    PrebookVO b = new PrebookVO();
                    List<PrebookVO> bookList = bookService.queryPagePreBook(vo);
                    result.add(bookList);
                }
                p = new PageInfo(result);
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
    
    /**
     * 
     * @描述：普通编目 图书馆书籍信息详细查询(带馆藏信息)
     * @作者：tianbw
     * @时间：2018年8月25日 上午10:02:48
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/cataloginfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R catalogInfo(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        CatalogVO vo = null;
        try
        {
            /**
               * 校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            
            CatalogVO catalogVO = new CatalogVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                catalogVO = (CatalogVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), CatalogVO.class);
            }
            if ( catalogVO.getLibraryId() == null || (catalogVO.getLibraryId() > 0) )
            {
                catalogVO.setLibraryId(libraryId);
            }
            if ( catalogVO.getBookId() == null )
            {
                return R.error( -1, "bookId不能为空");
            }
            vo = catalogService.queryCatalogInfo(catalogVO);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return R.ok().put("CatalogVO", vo);
    }
    
    /**
     *  普通编目数据提交保存,修改
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R gneralSave(@Context HttpServletRequest request, String data,
            @HeaderParam("Authorization") String authToken)
    {
        int count = 0;
        CatalogVO catalogVO = new CatalogVO();
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtymlconfig.getSecret());
            
            /**
             * 校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());//图书馆id
            
            if ( StringUtils.isNotEmpty(data) )
            {
                JSONObject catalog = JSONObject.fromObject(data);
                catalogVO = (CatalogVO) JSONObject.toBean(catalog, CatalogVO.class);
                catalogVO.setBook((Book) JSONObject.toBean(JSONObject.fromObject(catalog.get("book")), Book.class));
                JSONArray hold = JSONArray.fromObject(catalog.get("holdingList"));
                catalogVO.setHoldingList(JSONArray.toList(hold, new HoldingVO(), new JsonConfig()));
            }
            catalogVO.setIp(ip);
            catalogVO.setCreator(userid);
            catalogVO.setLibraryId(libraryId);
            //书id为null则此次请求表示新增操作，反之修改操作
            if ( catalogVO.getBook().getId() == null )
            {
                if ( catalogVO.getHoldingList() != null && catalogVO.getHoldingList().size() > 0 )
                {
                    for (Object obj : catalogVO.getHoldingList())
                    {
                        HoldingVO hvo = (HoldingVO) JSONObject
                                .toBean(JSONObject.fromObject(JSONUtil.object2String(obj)), HoldingVO.class);
                        hvo.setOwnlib(libraryId);
                        //判断馆藏数据是否有id，有则表示老数据，无则表示新数据需要校验条码是否唯一
                        if ( hvo.getId() == null )
                        {
                            List<HoldingVO> hlist = holdingService.queryHolding(hvo);
                            if ( hlist != null && hlist.size() > 0 )
                            {
                                return R.error().put("msg", hvo.getBarcode() + "条码号已存在。");
                            }
                        }
                    }
                }
                count = catalogService.insertCatalogInfo(catalogVO);
            }
            else
            {
                count = catalogService.updateCatalog(catalogVO);
                if ( count < 0 )
                {
                    return R.error( -1, "存在不可删除的馆藏状态信息!");
                    
                }
            }
            
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if ( count > 0 )
        {
            return R.ok().put("count", count).put("isbn", catalogVO.getIsbn());
        }
        return R.ok().put("count", count);
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
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtymlconfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            
            /**
             * 校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            CatalogVO catalogVO = new CatalogVO();
            if ( StringUtils.isNotEmpty(data) )
            {
                JSONObject catalog = JSONObject.fromObject(data);
                catalogVO = (CatalogVO) JSONObject.toBean(catalog, CatalogVO.class);
                catalogVO.setBook((Book) JSONObject.toBean(JSONObject.fromObject(catalog.get("book")), Book.class));
                JSONArray hold = JSONArray.fromObject(catalog.get("holdingList"));
                catalogVO.setHoldingList(JSONArray.toList(hold, new HoldingVO(), new JsonConfig()));
            }
            catalogVO.setIp(ip);
            catalogVO.setCreator(userid);
            catalogVO.setLibraryId(libraryId);
            
            int count = catalogService.DeleteCatalog(catalogVO);
            if ( count > 0 )
            {
                return R.ok().put("count", count).put("isbn", catalogVO.getIsbn());
            }
            return R.error( -1, "馆藏数据馆藏状态为不可删除!");
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
        
        Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
        if ( libraryId == null || !(libraryId > 0) )
        {
            return false;
        }
        return true;
        
    }
}
