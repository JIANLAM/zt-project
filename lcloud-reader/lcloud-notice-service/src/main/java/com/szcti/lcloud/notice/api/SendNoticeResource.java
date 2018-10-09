package com.szcti.lcloud.notice.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.notice.config.JwtYmlConfig;
import com.szcti.lcloud.notice.entity.vo.NoticeVO;
import com.szcti.lcloud.notice.repository.NoticeDao;
import com.szcti.lcloud.notice.service.NoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 发送通知信息API
 * @Description: 发送通知信息
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("sendNotice")
public class SendNoticeResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeDao noticeDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            NoticeVO noticeVO = new NoticeVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                noticeVO = (NoticeVO) JSONUtil.json2Object(jsonStr,NoticeVO.class);
            }
            if(userType!=null&&userType==0){
                Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
                noticeVO.setCreateBy(userId);
            }
            PageHelper.startPage(noticeVO.getPageNum(),noticeVO.getPageSize());

            List<NoticeVO> noticeVOList =  noticeService.findSendNotices(noticeVO);

            PageInfo p = new PageInfo(noticeVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/count/{noticeInfoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R count(@PathParam("noticeInfoId") Long noticeInfoId){
        try {
            Integer sendedCount = noticeDao.getReadCount(noticeInfoId,null);
            Integer readedCount = noticeDao.getReadCount(noticeInfoId,1);
            Map map = new HashMap(16);
            map.put("sendedCount",sendedCount);
            map.put("readedCount",readedCount);
            return R.ok().put("count", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/users/{noticeInfoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R users(@PathParam("noticeInfoId") Long noticeInfoId){
        try {
            List<HashMap> list = noticeDao.findSendUsers(noticeInfoId);
            return R.ok().put("users", list);
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
            NoticeVO noticeVO = new NoticeVO();
            if(StringUtils.isNotEmpty(data)){
                noticeVO = (NoticeVO)JSONUtil.json2Object(data,NoticeVO.class);
            }
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            noticeVO.setCreateBy(userId);
            noticeService.save(noticeVO);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/send/{noticeInfoId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R send(@PathParam("noticeInfoId") Long noticeInfoId){
        try {
            noticeService.sendNotice(noticeInfoId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("id") Long id){
        try {
            noticeService.deleteSendNotice(id);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
