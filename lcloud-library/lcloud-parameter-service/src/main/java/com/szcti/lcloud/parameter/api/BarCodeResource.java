package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import com.szcti.lcloud.parameter.repository.BarCodeDao;
import com.szcti.lcloud.parameter.service.BarCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 条码分区 API
 * @Description: 条码分区参数 API
 * @author: wangsiyi
 * @date: 2018/7/26 11:04
 */
@Component
@Path("partition")
public class BarCodeResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private BarCodeDao barCodeDao;

    @Autowired
    private BarCodeService barCodeService;

    /**
     * 查询条码分区参数List集合
     * @param
     * @return List<BarCodeDao>
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BarCodeVO barCodeVO = new BarCodeVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                barCodeVO = (BarCodeVO) JSONUtil.json2Object(jsonStr,BarCodeVO.class);
            }
            PageHelper.startPage(barCodeVO.getPageNum(),barCodeVO.getPageSize());

            List<BarCodeVO> BarCodeVOList = barCodeDao.findBarCodeList(libraryId , null);        //图书馆ID

            PageInfo p = new PageInfo(BarCodeVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询   当前登录 能使用的条码分区参数List集合
     * @param
     * @return List<BarCodeDao>
     */
    @Path("/list/currUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R listCurrUser(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BarCodeVO barCodeVO = new BarCodeVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                barCodeVO = (BarCodeVO) JSONUtil.json2Object(jsonStr,BarCodeVO.class);
            }
            PageHelper.startPage(barCodeVO.getPageNum(),barCodeVO.getPageSize());

            List<BarCodeVO> BarCodeVOList = barCodeDao.findBarCodeList(libraryId , userid);        //图书馆ID

            PageInfo p = new PageInfo(BarCodeVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条条码分区参数记录
     * @param   barCodeId
     * @return BarCodeVO
     */
    @Path("/info/{barCodeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getBarCode(@PathParam("barCodeId") Long barCodeId){
        try {
            BarCodeVO barCodeVO = barCodeDao.getBarCode(barCodeId);
            return R.ok().put("barCodeVO", barCodeVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 条码分区参数数据记录
     * @param barCodeIds
     */
    @Path("/delete/{barCodeIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delBarCodeInfo(@PathParam("barCodeIds") String barCodeIds){
        try {
            barCodeService.delBarCodeInfo(barCodeIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条条码分区参数 数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BarCodeVO barCodeVO = new BarCodeVO();
            if (StringUtils.isNotEmpty(data)) {
                barCodeVO = (BarCodeVO) JSONUtil.json2Object(data, BarCodeVO.class);
            }
            barCodeVO.setUserId(userid);   //创建者id
            barCodeVO.setLibraryId(libraryId);        //图书馆ID
            Integer res = barCodeService.save(barCodeVO);
            if (res == 1) {
                return R.error(406, "条码区号已存在");
            } else if (res == 2) {
                return R.error(407, "条码号不满足规范");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增初始化    条码区号 最大值加1 默认为1
     * @param
     */
    @Path("/maxBarcodeNumber")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R maxBarcodeNumber(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            return R.ok().put("maxBarcodeNumber", barCodeService.maxBarcodeNumber(libraryId));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 当前登录的图书馆 的用户
     * @param
     * @return Map
     */
    @Path("/list/cuurLibraryUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R cuurLibraryUser(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            List<Map> cuurLibraryUserMap = barCodeDao.cuurLibraryUser(libraryId);        //图书馆ID

            return R.ok().put("cuurLibraryUserMap",cuurLibraryUserMap);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
