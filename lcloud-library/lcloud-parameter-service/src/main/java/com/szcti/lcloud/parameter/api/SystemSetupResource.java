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
@Path("sys")
public class SystemSetupResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private BasicParamDao basicParamDao;

    @Autowired
    private BasicParamService basicParamService;

    /**
     * 查询数据字典   文献来源    List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/literatureList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R literatureList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(jsonStr,BasicParamEntity.class);
            }
            PageHelper.startPage(basicParamEntity.getPageNum(),basicParamEntity.getPageSize());
            basicParamEntity.setType("literature");         //代表文献来源类型
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            List<BasicParamEntity> BasicParamEntityList = basicParamDao.findList(basicParamEntity);

            PageInfo p = new PageInfo(BasicParamEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询数据字典   装帧类型    List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/bindingList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bindingList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(jsonStr,BasicParamEntity.class);
            }
            PageHelper.startPage(basicParamEntity.getPageNum(),basicParamEntity.getPageSize());
            basicParamEntity.setType("binding");         //代表装帧类型
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            List<BasicParamEntity> basicParamEntityList = basicParamDao.findList(basicParamEntity);

            PageInfo p = new PageInfo(basicParamEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询数据字典   载体类型    List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/carrierList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R carrierList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(jsonStr,BasicParamEntity.class);
            }
            PageHelper.startPage(basicParamEntity.getPageNum(),basicParamEntity.getPageSize());
            basicParamEntity.setType("carrier");         //代表载体类型
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            List<BasicParamEntity> basicParamEntityList = basicParamDao.findList(basicParamEntity);

            PageInfo p = new PageInfo(basicParamEntityList);

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
     * 新增   或修改 一条字典 文献来源 数据记录
     * @param   data
     * @return
     */
    @Path("/literatureSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R literatureSave(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }


            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if (StringUtils.isNotEmpty(data)) {
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(data, BasicParamEntity.class);
            }
            basicParamEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            basicParamEntity.setType("literature");         //代表文献来源
            basicParamEntity.setDescription("文献来源");
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            Integer result = basicParamService.save(basicParamEntity);
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
     * 新增   或修改 一条字典 装帧类型 数据记录
     * @param   data
     * @return
     */
    @Path("/bindingSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R bindingSave(String data,@HeaderParam("Authorization") String authToken){
        try {

            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if (StringUtils.isNotEmpty(data)) {
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(data, BasicParamEntity.class);
            }
            basicParamEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            basicParamEntity.setType("binding");         //代表装帧类型
            basicParamEntity.setDescription("装帧类型");
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            Integer result = basicParamService.save(basicParamEntity);
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
     * 新增   或修改 一条字典 载体类型 数据记录
     * @param   data
     * @return
     */
    @Path("/carrierSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R carrierSave(String data,@HeaderParam("Authorization") String authToken){
        try {

            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            BasicParamEntity basicParamEntity = new BasicParamEntity();
            if (StringUtils.isNotEmpty(data)) {
                basicParamEntity = (BasicParamEntity) JSONUtil.json2Object(data, BasicParamEntity.class);
            }
            basicParamEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            basicParamEntity.setType("carrier");         //代表载体类型
            basicParamEntity.setDescription("载体类型");
            basicParamEntity.setLibraryId(libraryId);     //图书馆ID
            Integer result = basicParamService.save(basicParamEntity);
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

}
