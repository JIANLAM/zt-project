package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.MarcMouldEntity;
import com.szcti.lcloud.parameter.repository.MarcTempletDao;
import com.szcti.lcloud.parameter.service.MarcTempletService;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Title: mrc模板 API
 * @Description: mrc模板 API
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */
@Component
@Path("marc")
public class MarcTempletResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private MarcTempletDao marcTempletDao;

    @Autowired
    private MarcTempletService marcTempletService;

    /*************************************************    MARC字段       ***************************************************/

    /**
     * 查询mrc字段 List集合
     * @param
     * @return List<TitleNumberEntity>
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
            MarcFieldEntity marcFieldEntity = new MarcFieldEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                marcFieldEntity = (MarcFieldEntity) JSONUtil.json2Object(jsonStr,MarcFieldEntity.class);
            }
            PageHelper.startPage(marcFieldEntity.getPageNum(),marcFieldEntity.getPageSize());

            List<MarcFieldEntity> marcFieldEntityList = marcTempletDao.findList(libraryId);       //图书馆ID

            PageInfo p = new PageInfo(marcFieldEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条mrc字段  记录
     * @param   marcId
     * @return MarcFieldEntity
     */
    @Path("/info/{marcId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R get(@PathParam("marcId") Long marcId){
        try {
            MarcFieldEntity marcFieldEntity = marcTempletDao.get(marcId);
            return R.ok().put("marcFieldEntity", marcFieldEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 mrc字段 数据记录
     * @param marcIds
     */
    @Path("/delete/{marcIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delMarc(@PathParam("marcIds") String marcIds){
        try {
            marcTempletService.delMarc(marcIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条mrc字段  数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            MarcFieldEntity marcFieldEntity = new MarcFieldEntity();
            if (StringUtils.isNotEmpty(data)) {
                marcFieldEntity = (MarcFieldEntity) JSONUtil.json2Object(data, MarcFieldEntity.class);
            }
            marcFieldEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            marcFieldEntity.setLibraryId(libraryId);        //图书馆ID
            marcTempletService.save(marcFieldEntity);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *导出 marc字段  数据记录
     * @param
     * @return
     */
    @Path("/exportMarc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMarcExcel(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            MarcFieldEntity marcFieldEntity = new MarcFieldEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                marcFieldEntity = (MarcFieldEntity)JSONUtil.json2Object(jsonStr,MarcFieldEntity.class);
            }
            marcFieldEntity.setLibraryId(libraryId);        //图书馆ID
            String fileName = marcTempletService.exportMarcExcel(marcFieldEntity);

            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *下载导出 marc字段 模板 数据记录
     * @param
     * @return
     */
    @Path("/exportMould")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMouldExcel(@Context HttpServletRequest request){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            String fileName = marcTempletService.exportMouldExcel();
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *导入 marc字段  数据记录
     * @param
     * @return
     */
    @Path("/importMarc")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importMarc(@Context HttpServletRequest request,MultipartFormDataInput multipartFormDataInput ,@HeaderParam("Authorization") String authToken){
        try {

            Long userid= JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }


            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            new FileUploadUtil().getFileType(filePathName);
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist= POITool.getData(filePathName);
                String result = marcTempletService.importMarc(maplist , libraryId , userid);
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据错误可能原因：1、字段名称已存在 2、必填项为空").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }


    /*************************************************    MARC模版       ***************************************************/

    /**
     * 查询mrc模板List集合
     * @param
     * @return List<MarcMouldEntity>
     */
    @Path("/findMouldList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R findMouldList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            MarcMouldEntity marcMouldEntity = new MarcMouldEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                marcMouldEntity = (MarcMouldEntity) JSONUtil.json2Object(jsonStr,MarcMouldEntity.class);
            }
            PageHelper.startPage(marcMouldEntity.getPageNum(),marcMouldEntity.getPageSize());

            List<MarcMouldEntity> marcMouldEntityList = marcTempletDao.findMouldList(libraryId);       //图书馆ID

            PageInfo p = new PageInfo(marcMouldEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条mrc模板 记录
     * @param   marcMouldId
     * @return MarcMouldEntity
     */
    @Path("/infoMould/{marcMouldId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getMould(@PathParam("marcMouldId") Long marcMouldId){
        try {
            MarcMouldEntity marcMouldEntity = marcTempletDao.getMould(marcMouldId);
            return R.ok().put("marcFieldEntity", marcMouldEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 mrc模板数据记录
     * @param marcMouldId
     */
    @Path("/deleteMould/{marcMouldId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delMarcMould(@PathParam("marcMouldId") String marcMouldId){
        try {
            marcTempletService.delMarcMould(marcMouldId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条mrc模板 数据记录
     * @param   data
     * @return
     */
    @Path("/saveMould")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveMould(String data,@HeaderParam("Authorization") String authToken){
        try {

           Long userid= JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            MarcMouldEntity marcMouldEntity = new MarcMouldEntity();
            if (StringUtils.isNotEmpty(data)) {
                marcMouldEntity = (MarcMouldEntity) JSONUtil.json2Object(data, MarcMouldEntity.class);
            }
            marcMouldEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            marcMouldEntity.setLibraryId(libraryId);        //图书馆ID
            Integer res = marcTempletService.saveMould(marcMouldEntity);
            if (res == 1) {
                return R.error(406, "该模版代码已存在");
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
     * MARC模板     启用一个模版 设为默认
     * @param   mouldId
     * @return
     */
    @Path("/open/{mouldId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R openMould(@PathParam("mouldId") Long mouldId,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            marcTempletService.openMould(mouldId , libraryId);

            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 禁用一个模版 设为禁用
     * @param   mouldId
     * @return
     */
    @Path("/colse/{mouldId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R colseMould(@PathParam("mouldId") Long mouldId,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            marcTempletDao.colseMould(mouldId);

            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
