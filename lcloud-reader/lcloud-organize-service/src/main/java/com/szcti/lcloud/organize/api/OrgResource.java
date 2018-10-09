package com.szcti.lcloud.organize.api;

import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.organize.config.JwtYmlConfig;
import com.szcti.lcloud.organize.entity.OrgEntity;
import com.szcti.lcloud.organize.repository.OrgDao;
import com.szcti.lcloud.organize.service.OrgService;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 组织机构api
 * @Description: 组织机构api
 * @author: fengda
 * @date: 2018/6/6 9:02
 */
@Component
@Path("org")
public class OrgResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private OrgService orgService;

    @Autowired
    private OrgDao orgDao;

    /**
     * 查询当前登录的图书馆下的 所有分馆
     * @return
     */
    @Path("/list/forOrgAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R forOrgAllList(@HeaderParam("Authorization") String authToken){
        try {
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            OrgEntity org = new OrgEntity();
            //当前登录的图书馆ID
            org.setId(libId);
            List orgList = orgService.orgTree(org);

            return R.ok().put("list",JSONArray.fromObject(orgList));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/list/school")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R schoolList(@QueryParam("jsonStr") String jsonStr){
        try {
            OrgEntity org = new OrgEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                org = (OrgEntity)JSONUtil.json2Object(jsonStr,OrgEntity.class);
            }
            //3标识学校的组织机构
            org.setType(3);

            List orgList = orgService.orgTree(org);

            return R.ok().put("list",JSONArray.fromObject(orgList));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/list/xh")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R xhList(@QueryParam("jsonStr") String jsonStr){
        try {
            OrgEntity org = new OrgEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                org = (OrgEntity)JSONUtil.json2Object(jsonStr,OrgEntity.class);
            }
            //2标识新华的组织机构
            org.setType(2);

            List orgList = orgService.orgTree(org);

            return R.ok().put("list",JSONArray.fromObject(orgList));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/list/gov")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R govList(@QueryParam("jsonStr") String jsonStr){
        try {
            OrgEntity org = new OrgEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                org = (OrgEntity)JSONUtil.json2Object(jsonStr,OrgEntity.class);
            }
            //0标识行政机关的组织机构
            org.setType(0);

            List orgList = orgService.orgTree(org);

            return R.ok().put("list",JSONArray.fromObject(orgList));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/list/otr")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R otrList(@QueryParam("jsonStr") String jsonStr){
        try {
            OrgEntity org = new OrgEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                org = (OrgEntity)JSONUtil.json2Object(jsonStr,OrgEntity.class);
            }
            //1标识其他的组织机构
            org.setType(1);

            List orgList = orgService.orgTree(org);

            return R.ok().put("list",JSONArray.fromObject(orgList));
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{orgId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@PathParam("orgId") Long orgId){
        try {
            OrgEntity org = orgDao.get(orgId);
            return R.ok().put("org",org);
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
            OrgEntity org = new OrgEntity();
            if(StringUtils.isNotEmpty(data)){
                org = (OrgEntity)JSONUtil.json2Object(data,OrgEntity.class);
            }
            orgService.save(org);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{orgId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("orgId") Long orgId){
        try {
            orgService.delete(orgId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
