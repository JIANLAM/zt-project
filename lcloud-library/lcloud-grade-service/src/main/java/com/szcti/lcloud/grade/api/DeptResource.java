package com.szcti.lcloud.grade.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.grade.config.JwtYmlConfig;
import com.szcti.lcloud.grade.entity.DeptEntity;
import com.szcti.lcloud.grade.repository.DeptDao;
import com.szcti.lcloud.grade.service.DeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 部门管理API
 * @Description: 部门管理API
 * @author: fengda
 * @date: 2018/8/29 9:02
 */
@Component
@Path("dept")
public class DeptResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private DeptService deptService;

    @Autowired
    private DeptDao deptDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            DeptEntity dept = new DeptEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                dept = (DeptEntity)JSONUtil.json2Object(jsonStr,DeptEntity.class);
            }
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            dept.setLibId(libId);

            PageHelper.startPage(dept.getPageNum(),dept.getPageSize());

            List<DeptEntity> deptList = deptDao.findList(dept);

            PageInfo p = new PageInfo(deptList);

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
            DeptEntity dept = deptDao.get(id);
            return R.ok().put("dept", dept);
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
            deptService.delete(id);
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
            DeptEntity dept = new DeptEntity();
            if(StringUtils.isNotEmpty(data)){
                dept = (DeptEntity)JSONUtil.json2Object(data,DeptEntity.class);
            }
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            dept.setLibId(libId);
            dept.setCreateBy(userId);
            dept.setCreateTime(DateUtils.getDateTime());
            boolean flag = deptService.save(dept);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"部门代码已经存在，请更改");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
