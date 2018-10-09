package com.szcti.lcloud.share.api;

import com.szcti.lcloud.share.service.TestService;
import com.szcti.lcloud.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/4/24 16:39
 */
@Component
@Path("test")
public class TestRest {

    @Autowired
    private TestService testService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R list(){

        return R.ok();
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R save(){
        return R.ok();
    }

}
