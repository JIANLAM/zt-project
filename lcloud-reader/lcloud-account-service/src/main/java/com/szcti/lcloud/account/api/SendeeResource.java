package com.szcti.lcloud.account.api;

import com.szcti.lcloud.account.entity.SendeeEntity;
import com.szcti.lcloud.account.repository.SendeeDao;
import com.szcti.lcloud.account.service.SendeeService;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.szcti.lcloud.account.config.JwtYmlConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 收件人信息api
 * @Description: 收件人信息api
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("sendee")
public class SendeeResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private SendeeDao sendeeDao;

    @Autowired
    private SendeeService sendeeService;

    @Path("/{peopleId}/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R list(@PathParam("peopleId") Long peopleId){
        try {
            List<SendeeEntity> sendeeList = sendeeDao.findList(peopleId);
            return R.ok().put("list",sendeeList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{sendeeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@PathParam("sendeeId") Long sendeeId){
        try {
            SendeeEntity sendeeEntity = sendeeDao.get(sendeeId);
            return R.ok().put("sendee", sendeeEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{sendeeId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("sendeeId") Long sendeeId){
        try {
            sendeeDao.delete(sendeeId);
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
            SendeeEntity sendee = new SendeeEntity();
            if(StringUtils.isNotEmpty(data)){
                sendee = (SendeeEntity)JSONUtil.json2Object(data,SendeeEntity.class);
            }
            Long peopleId = JwtUtil.getPeopleIdByToken(authToken,jwtYmlConfig.getSecret());
            sendee.setPeopleId(peopleId);
            sendeeService.save(sendee);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 设置默认地址
     * @param sendeeId
     */
    @Path("/setDefault/{sendeeId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R setDefault(@PathParam("sendeeId") Long sendeeId,@HeaderParam("Authorization") String authToken){
        try {
            Long peopleId = JwtUtil.getPeopleIdByToken(authToken,jwtYmlConfig.getSecret());
            sendeeService.setDefault(sendeeId,peopleId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
