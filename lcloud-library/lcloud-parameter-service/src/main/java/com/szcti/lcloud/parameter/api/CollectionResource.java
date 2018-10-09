package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.repository.BasicParamDao;
import com.szcti.lcloud.parameter.service.BasicParamService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 数据字典 API
 * @Description: 数据字典 API
 * @author: wangsiyi
 * @date: 2018/7/30 10:26
 */
@Component
@Path("colle")
public class CollectionResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private BasicParamDao basicParamDao;

    @Autowired
    private BasicParamService basicParamService;

    /**
     * 查询数据字典   馆藏状态设置 List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/collStateList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R collStateList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity BasicParamEntity = new BasicParamEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                BasicParamEntity = (BasicParamEntity) JSONUtil.json2Object(jsonStr,BasicParamEntity.class);
            }
            PageHelper.startPage(BasicParamEntity.getPageNum(),BasicParamEntity.getPageSize());
            BasicParamEntity.setType("collState");         //代表馆藏状态设置
            BasicParamEntity.setLibraryId(libraryId);     //图书馆id
            List<BasicParamEntity> BasicParamEntityList = basicParamDao.findList(BasicParamEntity);

            PageInfo p = new PageInfo(BasicParamEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询数据字典   馆藏地设置 List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/collAddressList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R collAddressList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity BasicParamEntity = new BasicParamEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                BasicParamEntity = (BasicParamEntity) JSONUtil.json2Object(jsonStr,BasicParamEntity.class);
            }
            PageHelper.startPage(BasicParamEntity.getPageNum(),BasicParamEntity.getPageSize());
            BasicParamEntity.setType("collAddress");         //代表馆藏地设置
            BasicParamEntity.setLibraryId(libraryId);     //图书馆id
            List<BasicParamEntity> BasicParamEntityList = basicParamDao.findList(BasicParamEntity);

            PageInfo p = new PageInfo(BasicParamEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条数据字典 记录
     * @param   dicId
     * @return BasicParamEntity
     */
    @Path("/info/{dicId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getTitleNumber(@PathParam("dicId") Long dicId){
        try {
            BasicParamEntity BasicParamEntity = basicParamDao.get(dicId);
            return R.ok().put("BasicParamEntity", BasicParamEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 字典数据记录
     * @param dicId
     */
    @Path("/delete/{dicId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delTitleNumber(@PathParam("dicId") String dicId){
        try {
            basicParamService.delCustom(dicId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条馆藏状态设置 数据记录
     * @param   data
     * @return
     */
    @Path("/collStateSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R collStateSave(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity BasicParamEntity = new BasicParamEntity();
            if (StringUtils.isNotEmpty(data)) {
                BasicParamEntity = (BasicParamEntity) JSONUtil.json2Object(data, BasicParamEntity.class);
            }
            BasicParamEntity.setCreateBy(userid);   //创建者id
            BasicParamEntity.setType("collState");         //代表读者证类型
            BasicParamEntity.setDescription("馆藏状态设置");
            BasicParamEntity.setLibraryId(libraryId);     //图书馆id
            Integer result = basicParamService.save(BasicParamEntity);
            if(result == 0){
                return R.error(406, "代码已存在");
            }else if(result == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条馆藏地设置 数据记录
     * @param   data
     * @return
     */
    @Path("/collAddressSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R collAddressSave(String data,@HeaderParam("Authorization") String authToken){
        try {

            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity BasicParamEntity = new BasicParamEntity();
            if (StringUtils.isNotEmpty(data)) {
                BasicParamEntity = (BasicParamEntity) JSONUtil.json2Object(data, BasicParamEntity.class);
            }
            BasicParamEntity.setCreateBy(userid);   //创建者id
            BasicParamEntity.setType("collAddress");         //代表馆藏地设置
            BasicParamEntity.setDescription("馆藏地设置");
            BasicParamEntity.setLibraryId(libraryId);     //图书馆id
            Integer result = basicParamService.save(BasicParamEntity);
            if(result == 0){
                return R.error(406, "此类型的键值已存在");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
