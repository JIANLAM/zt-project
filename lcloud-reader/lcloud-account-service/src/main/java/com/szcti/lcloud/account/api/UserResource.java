package com.szcti.lcloud.account.api;

import com.szcti.lcloud.account.entity.RoleEntity;
import com.szcti.lcloud.account.entity.vo.ReaderVO;
import com.szcti.lcloud.account.entity.vo.StaffVO;
import com.szcti.lcloud.account.entity.vo.UserInfoVO;
import com.szcti.lcloud.account.repository.UserDao;
import com.szcti.lcloud.account.service.UserService;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 我的账户信息api
 * @Description: 我的账户信息pai
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("user")
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Path("/info/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R userInfo(@PathParam("userId") Long userId){
        try {
            UserInfoVO user = userService.getUserInfo(userId);
            return R.ok().put("user", user);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{userId}/reader")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R readerInfo(@PathParam("userId") Long userId){
        try {
            Map map = userService.getReaderInfo(userId);
            return R.ok().put("user", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 获取用户 角色集合 一对多
     * @param userId
     * @return
     */
    @Path("/{userId}/getRoleInfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getRoleInfo(@PathParam("userId") Long userId){
        try {
            List<RoleEntity> roleList = userDao.getRoleInfo(userId);
            return R.ok().put("roles", roleList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{userId}/staff")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R staffInfo(@PathParam("userId") Long userId){
        try {
            Map map = userService.getStaffInfo(userId);
            return R.ok().put("user", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/modifyPwd")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R modifyPwd(String data){
        try {
            Map<String,String> map = JSONUtil.json2Map(data);
            String flag = userService.modifyPwd(Long.parseLong(map.get("userId")),map.get("oldPassword"),map.get("newPassword"));
            if("success".equals(flag)){
                return R.ok();
            }else{
                return R.error(406,"密码错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveUserInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveUserInfo(String data){
        try {
            UserInfoVO user = new UserInfoVO();
            if(StringUtils.isNotEmpty(data)){
                user = (UserInfoVO)JSONUtil.json2Object(data,UserInfoVO.class);
            }
            userService.saveUserInfo(user);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveReader")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveReader(String data){
        try {
            userService.saveReader(data);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveStaff")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveStaff(String data){
        try {
            StaffVO staff = new StaffVO();
            if(StringUtils.isNotEmpty(data)){
                staff = (StaffVO)JSONUtil.json2Object(data,StaffVO.class);
            }
            userService.saveStaff(staff);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /************************      修改读者资料         读者微信端     ******************************/
    @Path("/updateReadInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R updateReadInfo(String data){
        try {
            UserInfoVO user = new UserInfoVO();
            if(StringUtils.isNotEmpty(data)){
                user = (UserInfoVO)JSONUtil.json2Object(data,UserInfoVO.class);
            }
            userDao.updateReadInfo(user);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
