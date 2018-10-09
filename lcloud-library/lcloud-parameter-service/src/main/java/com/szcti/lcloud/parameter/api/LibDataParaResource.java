package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.LibdataParaEntity;
import com.szcti.lcloud.parameter.repository.LibDataParaDao;
import com.szcti.lcloud.parameter.service.LibDataParaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: Z39.50地址设置
 * @Description: 获取数据库信息
 * @author: wangsiyi
 * @date: 2018/7/25 11:04
 */
@Component
@Path("libDataBase")
public class LibDataParaResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LibDataParaDao libDataParaDao;

    @Autowired
    private LibDataParaService libDataParaService;

    /**
     * 新增   或修改 一条数据记录
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

            LibdataParaEntity libdataParaEntity = new LibdataParaEntity();
            if(StringUtils.isNotEmpty(data)){
                libdataParaEntity = (LibdataParaEntity) JSONUtil.json2Object(data,LibdataParaEntity.class);
            }
            libdataParaEntity.setLibraryId(libraryId);    //图书馆ID
            libdataParaEntity.setUserId(userid);   //创建者id暂时为
            libDataParaService.save(libdataParaEntity);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 数据记录
     * @param libDataBaseIds
     */
    @Path("/delete/{libDataBaseIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delLibDataBaseInfo(@PathParam("libDataBaseIds") String libDataBaseIds){
        try {
            libDataParaService.delLibDataBaseInfo(libDataBaseIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询List集合
     * @param
     * @return List<LibdataParaEntity>
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            LibdataParaEntity libdataParaEntity = new LibdataParaEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                libdataParaEntity = (LibdataParaEntity)JSONUtil.json2Object(jsonStr,LibdataParaEntity.class);
            }
            PageHelper.startPage(libdataParaEntity.getPageNum(),libdataParaEntity.getPageSize());

            List<LibdataParaEntity> libdataParaEntityList = libDataParaDao.findLibDataBaseList(libraryId);        //为图书馆ID

            PageInfo p = new PageInfo(libdataParaEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条数据记录
     * @param   libDataBaseIds
     * @return LibdataParaEntity
     */
    @Path("/getLibDataBase/{libDataBaseIds}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getLibDataBase(@PathParam("libDataBaseIds") Long libDataBaseIds){
        try {
            LibdataParaEntity libdataParaEntity = libDataParaDao.getLibDataBase(libDataBaseIds);
            return R.ok().put("libdataParaEntity", libdataParaEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
