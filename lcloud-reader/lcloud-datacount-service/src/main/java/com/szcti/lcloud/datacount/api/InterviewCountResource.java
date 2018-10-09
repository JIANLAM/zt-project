package com.szcti.lcloud.datacount.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.datacount.config.JwtYmlConfig;
import com.szcti.lcloud.datacount.entity.vo.*;
import com.szcti.lcloud.datacount.repository.InterviewCountDao;
import com.szcti.lcloud.datacount.service.InterviewCountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 采访统计
 * @Description: 各种统计
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Component
@Path("interviewCount")
public class InterviewCountResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private InterviewCountDao interviewCountDao;

    @Autowired
    private InterviewCountService interviewCountService;

    /**
     * 根据条件 查询订购单统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/purchaseOrderList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R purchaseOrderList(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }

            PageHelper.startPage(conditionVO.getPageNum(),conditionVO.getPageSize());
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            List<PurchaseOrderVO> purchaseOrderVOList = interviewCountDao.purchaseOrderCount(conditionVO);

            PageInfo p = new PageInfo(purchaseOrderVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据条件 查询借购单统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/lendBuyList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R lendBuyList(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }

            PageHelper.startPage(conditionVO.getPageNum(),conditionVO.getPageSize());
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            List<LendBuyOrderVO> lendBuyOrderVOList = interviewCountDao.lendBuyOrderCount(conditionVO);

            PageInfo p = new PageInfo(lendBuyOrderVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据条件 查询图书借购统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/bookLendBuyList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookLendBuyList(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }

            PageHelper.startPage(conditionVO.getPageNum(),conditionVO.getPageSize());
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            List<LendBuyBookVO> lendBuyBookVOList = interviewCountDao.bookLendBuyCount(conditionVO);

            PageInfo p = new PageInfo(lendBuyBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据条件 查询图书荐购统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/recommBuyList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R recommBuyList(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }

            PageHelper.startPage(conditionVO.getPageNum(),conditionVO.getPageSize());
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            List<RecommBuyBookVO> recommBuyBookVOList = interviewCountDao.recommBuyCount(conditionVO);

            PageInfo p = new PageInfo(recommBuyBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 订购单统计导出数据
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/purOrderExport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R purOrderExport(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }
            conditionVO.setExportCount(1);      //订购单统计导出数据
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            String fileName = interviewCountService.exports(conditionVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 借购单统计导出数据
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/lendBuyExport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R lendBuyExport(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }
            conditionVO.setExportCount(2);      //订购单统计导出数据
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            String fileName = interviewCountService.exports(conditionVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书借购统计导出数据
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/bookLendExport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookLendExport(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }
            conditionVO.setExportCount(3);      //订购单统计导出数据
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            String fileName = interviewCountService.exports(conditionVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书荐购统计导出数据
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/recommBuyExport")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R recommBuyExport(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ConditionVO conditionVO = new ConditionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                conditionVO = (ConditionVO)JSONUtil.json2Object(jsonStr,ConditionVO.class);
            }
            conditionVO.setExportCount(4);      //订购单统计导出数据
            conditionVO.setLibraryId(libraryId);      //图书馆ID
            String fileName = interviewCountService.exports(conditionVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
