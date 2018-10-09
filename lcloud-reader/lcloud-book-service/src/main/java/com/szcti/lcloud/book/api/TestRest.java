package com.szcti.lcloud.book.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.book.entity.Test;
import com.szcti.lcloud.book.service.TestService;
import com.szcti.lcloud.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
        PageHelper.startPage(0,2);
        PageHelper.orderBy("id desc");

        List<Test> list = testService.findAll();

        PageInfo p = new PageInfo(list);

        return R.ok().put("page", p);
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R save(@MatrixParam("areaid") String areaid,@MatrixParam("area") String area,@MatrixParam("cityid") String cityid){


        return R.ok();
    }

}
