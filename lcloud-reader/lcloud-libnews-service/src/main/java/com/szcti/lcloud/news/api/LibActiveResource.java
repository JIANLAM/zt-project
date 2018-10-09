package com.szcti.lcloud.news.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.news.config.JwtYmlConfig;
import com.szcti.lcloud.news.entity.LibactiveEnrollEntity;
import com.szcti.lcloud.news.entity.vo.LibActiveVo;
import com.szcti.lcloud.news.service.LibActiveService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 最新资讯
 * @Description: 最新资讯
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Component
@Path("active")
public class LibActiveResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LibActiveService libActiveService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            LibActiveVo libActiveVo = new LibActiveVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                libActiveVo = (LibActiveVo)JSONUtil.json2Object(jsonStr,LibActiveVo.class);
            }
            PageHelper.startPage(libActiveVo.getPageNum(),libActiveVo.getPageSize());

            List<LibActiveVo> libActiveVoList = libActiveService.findList();

            PageInfo p = new PageInfo(libActiveVoList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/activeInfo/{activeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R activeInfo(@PathParam("activeId") Long activeId){
        try {
            LibActiveVo libActiveVoObj = libActiveService.get(activeId);
            return R.ok().put("libNewsVoObj", libActiveVoObj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            LibActiveVo libActiveVoObj = new LibActiveVo();
            if(StringUtils.isNotEmpty(data)){
                libActiveVoObj = (LibActiveVo)JSONUtil.json2Object(data,LibActiveVo.class);
            }
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            libActiveVoObj.setCreateBy(userId);
            libActiveService.save(libActiveVoObj);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{libActiveIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("libActiveIds") String libActiveIds){
        try {
            libActiveService.delLibActiveById(libActiveIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveLibActiveEnroll")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R saveLibActiveEnroll(String data){
        try {
            LibactiveEnrollEntity libactiveEnrollEntity = new LibactiveEnrollEntity();
            if(StringUtils.isNotEmpty(data)){
                libactiveEnrollEntity = (LibactiveEnrollEntity)JSONUtil.json2Object(data,LibactiveEnrollEntity.class);
            }
            if(libActiveService.saveLibActiveEnroll(libactiveEnrollEntity)){
                return R.ok();
            }else{
                return R.error(406,"您已报过名了");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
