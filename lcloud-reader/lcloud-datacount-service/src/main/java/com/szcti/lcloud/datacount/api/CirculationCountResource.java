package com.szcti.lcloud.datacount.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.datacount.config.JwtYmlConfig;
import com.szcti.lcloud.datacount.entity.vo.*;
import com.szcti.lcloud.datacount.repository.CirculationCountDao;
import com.szcti.lcloud.datacount.repository.InterviewCountDao;
import com.szcti.lcloud.datacount.service.CirculationCountService;
import com.szcti.lcloud.datacount.service.InterviewCountService;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 流通统计
 * @Description: 各种统计
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Component
@Path("circulation")
public class CirculationCountResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private CirculationCountDao circulationCountDao;

    @Autowired
    private CirculationCountService circulationCountService;
    //selectType = 1 图书流通类型 selectType = 2 图书分类 selectType = 3 馆藏地 selectType = 4 读者证类型 selectType = 5 学生证年级 selectType = 6 学生证班级
    private Integer selectType = null;
    //choiceCount = 1 读者证类型 choiceCount = 2 性别   choiceCount = 3 年级   choiceCount = 4 班级
    private Integer choiceCount = null;
    // type = 202 代表污损      type = 203  代表丢失
    private Integer type = null;

    /**
     * 流通统计 导出数据
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/exports")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exports(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            circulationVO.setLibraryId(libraryId);           //图书馆ID
            circulationVO.setType(type);
            String fileName = circulationCountService.bookLendExport(circulationVO ,selectType , choiceCount);


            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /******************************************************      图书借阅统计     ********************************************************* */

    /**
     * 图书借阅统计       图书流通类型
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/bookCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R bookCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 1;     //selectType = 1 图书流通类型
            choiceCount = null;
            type = null;
            List<Object> map = circulationCountService.bookLendCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书借阅统计       图书分类
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/bookTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R bookTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 2;         //selectType = 2 图书分类
            choiceCount = null;
            type = null;
            List<Object> map = circulationCountService.bookLendCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书借阅统计       馆藏地点
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/bookcollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R bookcollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 3;             //selectType = 3 馆藏地
            choiceCount = null;
            type = null;
            List<Object> map = circulationCountService.bookLendCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /******************************************************      读者借阅统计     ********************************************************* */

    /**
     * 读者借阅统计       图书流通类型      根据读者证
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/readerCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R readerCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 1; choiceCount = 1;        //selectType = 1 图书流通类型 choiceCount = 1 读者证类型
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       图书分类        根据读者证
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/readerTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R readerTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 2; choiceCount = 1;        //selectType = 2 图书分类      choiceCount = 1 读者证类型
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       馆藏地             根据读者证
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/readercollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R readercollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 3; choiceCount = 1;            //selectType = 3 馆藏地      choiceCount = 1 读者证类型
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       图书流通类型      根据性别统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/sexCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R sexCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 1; choiceCount = 2;        //selectType = 1 图书流通类型 choiceCount = 2 性别
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       图书分类        根据性别统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/sexTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R sexTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 2; choiceCount = 2;                //selectType = 2 图书分类  choiceCount = 2 性别
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       馆藏地       根据性别统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/sexcollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R sexcollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 3; choiceCount = 2;            // selectType = 3 馆藏地 choiceCount = 2 性别
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       图书流通类型          根据年级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/gradeCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R gradeCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 1; choiceCount = 3;            //selectType = 1 图书流通类型      choiceCount = 3 年级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       图书分类          根据年级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/gradeTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R gradeTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 2; choiceCount = 3;             //selectType = 2 图书分类      choiceCount = 3 年级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计       馆藏地          根据年级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/gradecollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R gradecollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);               //图书馆ID
            selectType = 3; choiceCount = 3;                //selectType = 3 馆藏地      choiceCount = 3 年级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计           图书流通类型        根据班级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/classesCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R classesCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            selectType = 1; choiceCount = 4;         //selectType = 1 图书流通类型      choiceCount = 4 班级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 读者借阅统计           图书分类        根据班级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/classesTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R classesTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            selectType = 2; choiceCount = 4;            //selectType = 2 图书分类      choiceCount = 4 班级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 读者借阅统计           馆藏地        根据班级统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/classescollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R classescollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            selectType = 3; choiceCount = 4;        //selectType = 3 馆藏地      choiceCount = 4 班级
            type = null;
            List<Object> map = circulationCountService.readerLendCount(circulationVO,selectType,choiceCount);
            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /******************************************************      图书污损统计     ********************************************************* */

    /**
     * 图书污损统计       图书流通类型
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defileCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defileCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 1;     //selectType = 1 图书流通类型
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书污损统计       图书分类号
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defileTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defileTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 2;             //selectType = 2 图书分类
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书污损统计       馆藏地
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defilecollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defilecollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 3;         //selectType = 3 馆藏地
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书污损统计       读者证类型
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defileReaderLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defileReaderLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 4;             // selectType = 4 读者证类型
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书污损统计       年级
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defileGradeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defileGradeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 5;              //selectType = 5 学生证年级
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书污损统计       班级
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/defileClassesLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R defileClassesLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 202; //代表污损
            circulationVO.setType(type);
            selectType = 6;         // selectType = 6 学生证班级
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

  /******************************************************      图书丢失统计     ********************************************************* */

    /**
     * 图书丢失统计       图书流通类型
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/loseCirculLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R loseCirculLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 1;         //selectType = 1 图书流通类型
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书丢失统计       图书分类号
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/loseTypeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R loseTypeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 2;     //selectType = 2 图书分类
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书丢失统计       馆藏地
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/losecollAdsLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R losecollAdsLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 3;     //selectType = 3 馆藏地
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书丢失统计       读者证类型
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/loseReaderLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R loseReaderLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 4;             //selectType = 4 读者证类型
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书丢失统计       年级
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/loseGradeLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R loseGradeLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 5;         //selectType = 5 学生证年级
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书丢失统计       班级
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/loseClassesLend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R loseClassesLend(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            type = 203; //代表丢失
            circulationVO.setType(type);
            selectType = 6;         //selectType = 6 学生证班级
            choiceCount = null;
            List<Object> map = circulationCountService.financeCount(circulationVO,selectType);

            return R.ok().put("ListMap", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


}
