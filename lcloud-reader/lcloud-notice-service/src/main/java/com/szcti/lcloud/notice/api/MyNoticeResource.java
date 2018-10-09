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
import java.util.List;

/**
 * @Title: 我的通知信息
 * @Description: 我的通知信息
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("myNotice")
public class MyNoticeResource {

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
                noticeVO.setUserId(userId);
            }
            PageHelper.startPage(noticeVO.getPageNum(),noticeVO.getPageSize());

            List<NoticeVO> noticeVOList = noticeDao.findMyNotices(noticeVO);

            PageInfo p = new PageInfo(noticeVOList);

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
            NoticeVO noticeVO = noticeDao.getNoticeById(id);
            return R.ok().put("notice", noticeVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/read/{noticeReadId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R readNotice(@PathParam("noticeReadId") Long noticeReadId){
        try {
            noticeService.readNotice(noticeReadId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{noticeReadId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("noticeReadId") String noticeReadId){
        try {
            noticeService.deleteMyNotice(noticeReadId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
