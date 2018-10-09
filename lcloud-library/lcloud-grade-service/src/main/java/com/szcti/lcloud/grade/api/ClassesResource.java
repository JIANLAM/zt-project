package com.szcti.lcloud.grade.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.grade.config.JwtYmlConfig;
import com.szcti.lcloud.grade.entity.ClassesEntity;
import com.szcti.lcloud.grade.repository.ClassesDao;
import com.szcti.lcloud.grade.service.ClassesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 班级管理API
 * @Description: 班级管理API
 * @author: fengda
 * @date: 2018/7/31 9:02
 */
@Component
@Path("classes")
public class ClassesResource {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private ClassesDao classesDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            ClassesEntity classes = new ClassesEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                classes = (ClassesEntity)JSONUtil.json2Object(jsonStr,ClassesEntity.class);
            }

            PageHelper.startPage(classes.getPageNum(),classes.getPageSize());

            List<ClassesEntity> classesList = classesDao.findList(classes);

            PageInfo p = new PageInfo(classesList);

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
            ClassesEntity classes = classesDao.get(id);
            return R.ok().put("classes", classes);
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
            classesService.delete(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        try {
            ClassesEntity classes = new ClassesEntity();
            if(StringUtils.isNotEmpty(data)){
                classes = (ClassesEntity)JSONUtil.json2Object(data,ClassesEntity.class);
            }
            boolean flag = classesService.save(classes);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"班级代码已经存在，请更改");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
