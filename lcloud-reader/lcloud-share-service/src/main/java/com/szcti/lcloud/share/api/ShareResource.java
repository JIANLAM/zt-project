package com.szcti.lcloud.share.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.share.config.JwtYmlConfig;
import com.szcti.lcloud.share.entity.vo.CommentVO;
import com.szcti.lcloud.share.entity.vo.ReportVO;
import com.szcti.lcloud.share.entity.vo.ShareVO;
import com.szcti.lcloud.share.repository.ShareDao;
import com.szcti.lcloud.share.service.ShareService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 阅读分享api
 * @Description: 阅读分享api
 * @author: fengda
 * @date: 2018/6/29
 */
@Component
@Path("/share")
public class ShareResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private ShareService shareService;

    @Autowired
    private ShareDao shareDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R shareList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            ShareVO shareVO = new ShareVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                shareVO = (ShareVO)JSONUtil.json2Object(jsonStr,ShareVO.class);
            }
            if(userType!=null&&userType==0){
                Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                shareVO.setReaderId(currentReaderId);
            }

            PageHelper.startPage(shareVO.getPageNum(),shareVO.getPageSize());

            List<ShareVO> shareVOList = shareDao.findList(shareVO);

            PageInfo p = new PageInfo(shareVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/commentlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R commentList(@QueryParam("jsonStr") String jsonStr){
        try {
            CommentVO commentVO = new CommentVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                commentVO = (CommentVO)JSONUtil.json2Object(jsonStr,CommentVO.class);
            }

            PageHelper.startPage(commentVO.getPageNum(),commentVO.getPageSize());

            List<CommentVO> commentVOList = shareDao.findCommentList(commentVO);

            PageInfo p = new PageInfo(commentVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{shareId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R shareInfo(@PathParam("shareId") Long shareId,@HeaderParam("Authorization") String authToken){
        try {
            Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            ShareVO share = shareDao.get(shareId,currentReaderId);
            return R.ok().put("share", share);
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
            Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            ShareVO shareVO = new ShareVO();
            if(StringUtils.isNotEmpty(data)){
                shareVO = (ShareVO)JSONUtil.json2Object(data,ShareVO.class);
            }
            shareVO.setId(IdGen.randomLong());
            shareVO.setReaderId(currentReaderId);
            shareVO.setCreateTime(DateUtils.getDateTime());
            shareDao.insertShare(shareVO);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{shareId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("shareId") Long shareId){
        try {
            shareService.delete(shareId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{shareId}/agree/{readerId}/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R agree(@PathParam("shareId") Long shareId,@PathParam("readerId") Long readerId){
        try {
            Integer count = shareService.saveAgree(shareId,readerId);
            if(count>=0){
                return R.ok().put("count",count);
            }else{
                return R.error(406,"不能重复点赞！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{shareId}/agree/{readerId}/cancel")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R cancelAgree(@PathParam("shareId") Long shareId,@PathParam("readerId") Long readerId){
        try {
            Integer count = shareService.deleteAgree(shareId,readerId);
            return R.ok().put("count",count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/comment/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R saveComment(String data){
        try {
            CommentVO comment = new CommentVO();
            if(StringUtils.isNotEmpty(data)){
                comment = (CommentVO)JSONUtil.json2Object(data,CommentVO.class);
            }
            comment.setId(IdGen.randomLong());
            comment.setCreateTime(DateUtils.getDateTime());
            shareDao.insertComment(comment);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{shareId}/comment/{readerId}/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R deleteComment(@PathParam("shareId") Long shareId,@PathParam("readerId") Long readerId){
        try {
            shareDao.deleteComment(shareId,readerId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/report")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R report(String data){
        try {
            ReportVO report = new ReportVO();
            if(StringUtils.isNotEmpty(data)){
                report = (ReportVO)JSONUtil.json2Object(data,ReportVO.class);
            }
            boolean flag = shareService.report(report);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"不能重复举报！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/reportlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R reportList(@QueryParam("jsonStr") String jsonStr){
        try {
            ReportVO reportVO = new ReportVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                reportVO = (ReportVO)JSONUtil.json2Object(jsonStr,ReportVO.class);
            }

            PageHelper.startPage(reportVO.getPageNum(),reportVO.getPageSize());

            List<ReportVO> reportVOList = shareDao.findReportList(reportVO);

            PageInfo p = new PageInfo(reportVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
