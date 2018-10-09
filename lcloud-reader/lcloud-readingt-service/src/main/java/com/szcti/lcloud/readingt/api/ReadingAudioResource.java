package com.szcti.lcloud.readingt.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.readingt.config.JwtYmlConfig;
import com.szcti.lcloud.readingt.service.ReadingAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by linzj on 2018/9/10
 */

@Component
@Path("readingt")
public class ReadingAudioResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private ReadingAudioService readingAudioService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R getReadingAudioList(   @HeaderParam("Authorization") String authToken,
                                    @QueryParam("pageNum") Integer pageNum,
                                    @QueryParam("pageSize") Integer pageSize){

        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }

        //获取读者ID
        Long readerIdByToken=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());

        List<Map<String,Object>> readingAudioList=readingAudioService.queryAudioList();

        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(readingAudioList);

        return R.ok().put("readingAudioList",pageInfo);

    }
}
