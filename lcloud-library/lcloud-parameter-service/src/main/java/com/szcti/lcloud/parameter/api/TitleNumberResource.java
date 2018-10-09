package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.TitleNumberEntity;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import com.szcti.lcloud.parameter.repository.TitleNumberDao;
import com.szcti.lcloud.parameter.service.TitleNumberService;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 种次号维护 API
 * @Description: 种次号维护 API
 * @author: wangsiyi
 * @date: 2018/7/26 19:58
 */
@Component
@Path("defend")
public class TitleNumberResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private TitleNumberDao titleNumberDao;

    @Autowired
    private TitleNumberService titleNumberService;

    /**
     * 查询种次号维护List集合
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

            TitleNumberEntity titleNumberEntity = new TitleNumberEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                titleNumberEntity = (TitleNumberEntity) JSONUtil.json2Object(jsonStr,TitleNumberEntity.class);
            }
            PageHelper.startPage(titleNumberEntity.getPageNum(),titleNumberEntity.getPageSize());
            titleNumberEntity.setLibraryId(libraryId);        //图书馆ID
            List<TitleNumberEntity> titleNumberVOList = titleNumberDao.findTitleNumberList(titleNumberEntity);

            PageInfo p = new PageInfo(titleNumberVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条种次号维护 记录
     * @param   titleNumberId
     * @return TitleNumberEntity
     */
    @Path("/info/{titleNumberId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getTitleNumber(@PathParam("titleNumberId") Long titleNumberId){
        try {
            TitleNumberEntity titleNumberEntity = titleNumberDao.getTitleNumber(titleNumberId);
            return R.ok().put("titleNumberEntity", titleNumberEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 种次号维护数据记录
     * @param titleNumberIds
     */
    @Path("/delete/{titleNumberIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delTitleNumber(@PathParam("titleNumberIds") String titleNumberIds){
        try {
            titleNumberService.delTitleNumber(titleNumberIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条种次号维护 数据记录
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

            TitleNumberEntity titleNumberEntity = new TitleNumberEntity();
            if (StringUtils.isNotEmpty(data)) {
                titleNumberEntity = (TitleNumberEntity) JSONUtil.json2Object(data, TitleNumberEntity.class);
            }
            titleNumberEntity.setUserId(userid);   //创建者id
            titleNumberEntity.setLibraryId(libraryId);        //图书馆ID
            Integer res = titleNumberService.save(titleNumberEntity);
            if (res == 1) {
                return R.error(406, "分类号已存在");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增初始化    种次号维护 最大值加1 默认为1
     * @param
     */
    @Path("/maxCurrSeednumber/{classNumber}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R maxCurrSeednumber(@HeaderParam("Authorization") String authToken , @PathParam("classNumber") String classNumber){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            return R.ok().put("maxBarcodeNumber", titleNumberService.maxCurrSeednumber(libraryId , classNumber));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *导出 种次号维护 数据记录
     * @param
     * @return
     */
    @Path("/exportExcel")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportExcel(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            TitleNumberEntity titleNumberEntity = new TitleNumberEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                titleNumberEntity = (TitleNumberEntity)JSONUtil.json2Object(jsonStr,TitleNumberEntity.class);
            }
            titleNumberEntity.setLibraryId(libraryId);        //图书馆ID
            String fileName = titleNumberService.exportExcel(titleNumberEntity);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *下载  导入 种次号维护模版 数据记录
     * @param
     * @return
     */
    @Path("/exportMould")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMouldExcel(@Context HttpServletRequest request){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            String fileName = titleNumberService.exportMouldExcel();
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *批量导入 种次号维护 数据记录
     * @param
     * @return
     */
    @Path("/import")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importTitleNumber(@Context HttpServletRequest request,MultipartFormDataInput multipartFormDataInput , @HeaderParam("Authorization") String authToken){
        try {
            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            new FileUploadUtil().getFileType(filePathName);
            if(filePathName != null || filePathName.isEmpty()){
                List<Map>  maplist= POITool.getData(filePathName);
              Long userid= JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());  //创建者
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());   //图书馆ID
                if(libraryId==null||!(libraryId>0)){
                    return R.error().put("msg", "无权操作！请联系管理员。");
                }
                String result = titleNumberService.importTitleNumber(maplist , libraryId , userid);
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error(407,"部分数据错误可能原因：1、分类号已存在 2、当前种号必须大于最大种号 3、输入数据类型错误 4、必填项为空").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error(405,"导入失败，文件格式不规范！");
        }
    }

}
