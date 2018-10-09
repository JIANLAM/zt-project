package com.szcti.lcloud.news.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.news.config.JwtYmlConfig;
import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.vo.LibNewsVo;
import com.szcti.lcloud.news.repository.LibNewsDao;
import com.szcti.lcloud.news.service.LibNewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.szcti.lcloud.common.utils.IdGen;
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
@Path("news")
public class LibNewsResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LibNewsService libNewsService;
    @Autowired
    private LibNewsDao libNewsDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            LibNewsVo libNewsVo = new LibNewsVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                libNewsVo = (LibNewsVo)JSONUtil.json2Object(jsonStr,LibNewsVo.class);
            }
            PageHelper.startPage(libNewsVo.getPageNum(),libNewsVo.getPageSize());
            List<LibNewsVo> libNewsVoList = libNewsDao.findList(libNewsVo.getStartTime(),libNewsVo.getClosureTime());

            PageInfo p = new PageInfo(libNewsVoList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/newsInfo/{newsId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R newsInfo(@PathParam("newsId") Long newsId){
        try {
            LibNewsVo libNewsVoObj = libNewsService.get(newsId);
            return R.ok().put("libNewsVoObj", libNewsVoObj);
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
            LibNewsVo libNewsVoObj = new LibNewsVo();
            if(StringUtils.isNotEmpty(data)){
                libNewsVoObj = (LibNewsVo)JSONUtil.json2Object(data,LibNewsVo.class);
            }
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            libNewsVoObj.setCreateBy(userId);
            libNewsService.save(libNewsVoObj);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{libNewsIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("libNewsIds") String libNewsIds){
        try {
            libNewsService.delLibNewsById(libNewsIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveLibNewsLook")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R saveLibNewsLook(String data){
        try {
            LibNewsLookEntity libNewsLookObj = new LibNewsLookEntity();
            if(StringUtils.isNotEmpty(data)){
                libNewsLookObj = (LibNewsLookEntity)JSONUtil.json2Object(data,LibNewsLookEntity.class);
            }
            libNewsService.saveLibNewsLook(libNewsLookObj);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
