package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.CustomTypeEntity;
import com.szcti.lcloud.parameter.entity.vo.CustomVO;
import com.szcti.lcloud.parameter.repository.CustomDao;
import com.szcti.lcloud.parameter.service.CustomService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 自定义字段设置 API
 * @Description: 自定义字段设置 API
 * @author: wangsiyi
 * @date: 2018/7/31 17:02
 */
@Component
@Path("field")
public class CustomResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private CustomDao customDao;

    @Autowired
    private CustomService customService;

    /**
     * 查询自定义字段      字段一    设置List集合
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/cusOnelist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R cusOnelist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                customVO = (CustomVO) JSONUtil.json2Object(jsonStr,CustomVO.class);
            }
            PageHelper.startPage(customVO.getPageNum(),customVO.getPageSize());
            customVO.setType("cusOne");        //代表自定义字段一
            customVO.setLibraryId(libraryId);     //图书馆id
            List<CustomVO> customVOList = customDao.findList(customVO);

            PageInfo p = new PageInfo(customVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段      字段二    设置List集合
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/cusTwolist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R cusTwolist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                customVO = (CustomVO) JSONUtil.json2Object(jsonStr,CustomVO.class);
            }
            PageHelper.startPage(customVO.getPageNum(),customVO.getPageSize());
            customVO.setType("cusTwo");         //代表自定义字段二
            customVO.setLibraryId(libraryId);     //图书馆id
            List<CustomVO> customVOList = customDao.findList(customVO);

            PageInfo p = new PageInfo(customVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段      字段三    设置List集合
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/cusThreelist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R cusThreelist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
           Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                customVO = (CustomVO) JSONUtil.json2Object(jsonStr,CustomVO.class);
            }
            PageHelper.startPage(customVO.getPageNum(),customVO.getPageSize());
            customVO.setType("cusThree");         //代表自定义字段三
            customVO.setLibraryId(libraryId);     //图书馆id
            List<CustomVO> customVOList = customDao.findList(customVO);

            PageInfo p = new PageInfo(customVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 一
     * @param
     * @return String
     */
    @Path("/descriptionOneType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R descriptionOneType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusOne",libraryId);
            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 二
     * @param
     * @return String
     */
    @Path("/descriptionTwoType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionTwoType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusTwo",libraryId);

            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 三
     * @param
     * @return String
     */
    @Path("/descriptionThreeType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionThreeType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusThree",libraryId);

            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 四
     * @param
     * @return String
     */
    @Path("/descriptionFourType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionFourType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusFour",libraryId);

            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 五
     * @param
     * @return String
     */
    @Path("/descriptionFiveType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionFiveType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusFive",libraryId);

            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询自定义字段  描述 六
     * @param
     * @return String
     */
    @Path("/descriptionSixType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionSixType(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity description = customDao.findTypeList("cusSix",libraryId);

            return R.ok().put("description", description);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 自定义字段设置记录
     * @param customId
     */
    @Path("/delete/{customId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delCustom(@PathParam("customId") String customId){
        try {
            customService.delCustom(customId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条自定义字段一   设置 数据记录
     * @param   data
     * @return
     */
    @Path("/cusOneSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R cusOneSave(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if (StringUtils.isNotEmpty(data)) {
                customVO = (CustomVO) JSONUtil.json2Object(data, CustomVO.class);
            }
            customVO.setType("cusOne");

            customVO.setCreateBy(userid);   //创建者id
            customVO.setLibraryId(libraryId);   //创建学校
            Integer res = customService.save(customVO);
            if(res == 0){
                return R.error(406, "代码已存在");
            }else if(res == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条自定义字段二   设置 数据记录
     * @param   data
     * @return
     */
    @Path("/cusTowSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R cusTowSave(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if (StringUtils.isNotEmpty(data)) {
                customVO = (CustomVO) JSONUtil.json2Object(data, CustomVO.class);
            }
            customVO.setType("cusTwo");

            customVO.setCreateBy(userid);   //创建者id
            customVO.setLibraryId(libraryId);   //创建学校
            Integer res = customService.save(customVO);
            if(res == 0){
                return R.error(406, "代码已存在");
            }else if(res == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条自定义字段三   设置 数据记录
     * @param   data
     * @return
     */
    @Path("/cusThreeSave")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R cusThreeSave(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomVO customVO = new CustomVO();
            if (StringUtils.isNotEmpty(data)) {
                customVO = (CustomVO) JSONUtil.json2Object(data, CustomVO.class);
            }
            customVO.setType("cusThree");

            customVO.setCreateBy(userid);   //创建者id
            customVO.setLibraryId(libraryId);   //创建学校
            Integer res = customService.save(customVO);
            if(res == 0){
                return R.error(406, "代码已存在");
            }else if(res == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述一   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionOne/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionOne(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusOne");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);

            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述二   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionTwo/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionTwo(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusTwo");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述三   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionThree/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionThree(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusThree");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述四   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionFour/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionFour(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusFour");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述五   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionFive/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionFive(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusFive");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增或修改 一条自定义字段描述六   设置 数据记录
     * @param   description
     * @return
     */
    @Path("/descriptionSix/{description}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R descriptionSix(@PathParam("description") String description,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            CustomTypeEntity customTypeEntity = new CustomTypeEntity();
            customTypeEntity.setDescription(description);
            customTypeEntity.setType("cusSix");
            customTypeEntity.setLibraryId(libraryId);     //图书馆ID
            customService.saveSetup(customTypeEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
