package com.szcti.lcloud.grade.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.grade.entity.GradeEntity;
import com.szcti.lcloud.grade.repository.GradeDao;
import com.szcti.lcloud.grade.config.JwtYmlConfig;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.grade.service.GradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 年级管理API
 * @Description: 年级管理API
 * @author: fengda
 * @date: 2018/7/30 9:02
 */
@Component
@Path("/")
public class GradeResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeDao gradeDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            GradeEntity grade = new GradeEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                grade = (GradeEntity)JSONUtil.json2Object(jsonStr,GradeEntity.class);
            }
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            grade.setLibId(libId);

            PageHelper.startPage(grade.getPageNum(),grade.getPageSize());

            List<GradeEntity> gradeList = gradeDao.findList(grade);

            PageInfo p = new PageInfo(gradeList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    @Path("/info/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@PathParam("id") Long id){
        try {
            GradeEntity grade = gradeDao.get(id);
            return R.ok().put("grade", grade);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("id") Long id){
        try {
            gradeService.delete(id);
            return R.ok();
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
            GradeEntity grade = new GradeEntity();
            if(StringUtils.isNotEmpty(data)){
                grade = (GradeEntity)JSONUtil.json2Object(data,GradeEntity.class);
            }
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            grade.setLibId(libId);
            boolean flag = gradeService.save(grade);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"年级代码已经存在，请更改");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/upgrade/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R upgrade(@PathParam("id") Long id){
        try {
            String flag = gradeService.upgrade(id);
            if("ok".equals(flag)){
                return R.ok();
            }else if("higherFirst".equals(flag)){
                return R.error(406,"请先升级高年级！");
            }else if("noHigher".equals(flag)){
                return R.error(407,"没有更高年级了！");
            }else{
                return R.error();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/graduate/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R graduate(@PathParam("id") Long id){
        try {
            boolean flag = gradeService.graduate(id);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"最高年级才能毕业！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
