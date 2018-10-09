package com.szcti.lcloud.datacount.api;

import com.github.pagehelper.PageHelper;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.datacount.config.JwtYmlConfig;
import com.szcti.lcloud.datacount.entity.vo.CirculationVO;
import com.szcti.lcloud.datacount.entity.vo.ConditionVO;
import com.szcti.lcloud.datacount.repository.CirculationCountDao;
import com.szcti.lcloud.datacount.repository.CollectionCountDao;
import com.szcti.lcloud.datacount.service.CirculationCountService;
import com.szcti.lcloud.datacount.service.CollectionCountService;
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
 * @Title: 馆藏统计
 * @Description: 各种统计
 * @author: wangsiyi
 * @date: 2018/8/9 9:27
 */
@Component
@Path("collection")
public class CollectionCountResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private CollectionCountDao collectionCountDao;

    @Autowired
    private CollectionCountService collectionCountService;

    /**
     * 图书馆藏分类统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/collectionTypeCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R collectionTypeCount(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
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
            Object obj = collectionCountService.collectionTypeCount(circulationVO);

            return R.ok().put("ListMap", obj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书馆藏分布统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/collDistriCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R collDistriCount(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());

            Object obj = collectionCountService.collectionTypeCount(circulationVO);

            return R.ok().put("ListMap", obj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书馆藏现存统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/collExistingCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R collExistingCount(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CirculationVO circulationVO = new CirculationVO();
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            PageHelper.startPage(circulationVO.getPageNum(),circulationVO.getPageSize());

            Object obj = collectionCountService.collectionTypeCount(circulationVO);

            return R.ok().put("ListMap", obj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 导出数据
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
            circulationVO.setLibraryId(libraryId);           //图书馆ID
            if(StringUtils.isNotEmpty(jsonStr)){
                circulationVO = (CirculationVO)JSONUtil.json2Object(jsonStr,CirculationVO.class);
            }

            String fileName = collectionCountService.exports(circulationVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 图书馆藏处理统计
     * @param jsonStr
     * @return  PageInfo
     */
    @Path("/collManageCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R collManageCount(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
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
            Object obj = collectionCountService.collectionTypeCount(circulationVO);

            return R.ok().put("ListMap", obj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询馆藏处理统计    操作人员
     * @param
     * @return  userMap
     */
    @Path("/operationStaff")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R operationStaff(@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

           List<Map<String,Object>> userMap = collectionCountDao.operationStaff(libraryId);  //图书馆ID
            return R.ok().put("userMap", userMap);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
