package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;
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
@Path("clt")
public class CirculationResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private BasicParamDao basicParamDao;

    @Autowired
    private BasicParamService basicParamService;

    /**
     * 查询数据字典   文献流通类型 List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/circulationList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R circulationList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
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
            BasicParamEntity.setType("circulation");         //代表文献流通类型
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
     * 根据ID 查询一条文献流通类型 记录
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
     * 根据主键删除一条 或多条 文献流通类型记录
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
     * 新增   或修改 一条文献流通类型 数据记录
     * @param   data
     * @return
     */
    @Path("/circulationSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R circulationSave(String data,@HeaderParam("Authorization") String authToken){
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
            BasicParamEntity.setType("circulation");         //代表文献流通类型
            BasicParamEntity.setDescription("文献流通类型");
            BasicParamEntity.setLibraryId(libraryId);     //图书馆ID
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


     /*  **************************************************        读者证类型         ***************************************************  */

    /**
     * 查询数据字典   读者证类型 List集合
     * @param
     * @return List<BasicParamEntity>
     */
    @Path("/readerCardList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R readerCardList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
           Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ReaderCardEntity readerCardEntity = new ReaderCardEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                readerCardEntity = (ReaderCardEntity) JSONUtil.json2Object(jsonStr,ReaderCardEntity.class);
            }
            PageHelper.startPage(readerCardEntity.getPageNum(),readerCardEntity.getPageSize());
            readerCardEntity.setLibraryId(libraryId);     //图书馆id
            List<ReaderCardEntity> BasicParamEntityList = basicParamDao.readerCardList(readerCardEntity);

            PageInfo p = new PageInfo(BasicParamEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询读者证类型 一条数据
     * @param   resderCardIds
     * @return BasicParamEntity
     */
    @Path("/infoReaderCard/{resderCardIds}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R infoReaderCard(@PathParam("resderCardIds") Long resderCardIds){
        try {
            ReaderCardEntity readerCardEntity = basicParamDao.readerCardGet(resderCardIds);
            return R.ok().put("readerCardEntity", readerCardEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 新增   或修改 一条读者证类型 数据记录
     * @param   data
     * @return
     */
    @Path("/readerCardSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R readerCardSave(String data,@HeaderParam("Authorization") String authToken){
        try {

           Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            ReaderCardEntity readerCardEntity = new ReaderCardEntity();
            if(StringUtils.isNotEmpty(data)){
                readerCardEntity = (ReaderCardEntity) JSONUtil.json2Object(data,ReaderCardEntity.class);
            }
            readerCardEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            readerCardEntity.setLibraryId(libraryId);     //图书馆ID
            Integer result = basicParamService.savereaderCard(readerCardEntity);
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
     * 删除 一条或多条 读者证类型
     * @param resderCardIds
     */
    @Path("/delReaderCard/{resderCardIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delReaderCard(@PathParam("resderCardIds") String resderCardIds){
        try {
            basicParamService.delReaderCard(resderCardIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }



}
