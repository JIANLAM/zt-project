package com.szcti.lcloud.top.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.top.config.JwtYmlConfig;
import com.szcti.lcloud.top.entity.vo.TopBookVO;
import com.szcti.lcloud.top.service.TopService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * 排名表
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("top")
public class TopResource {
    @Autowired
    private TopService topService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
/**
 * @Author liujunliang
 * @Description 图书借阅排行
 * @Date  2018/7/16
 * @Param TopBookVO中参数封装
 * @return
 **/
    @Path("/toplendbook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topLendBook(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topLendBook(topBookVO);
            topService.setBookInfo(topBookVOList);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topLendBookExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 图书推荐排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toprecommbook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topRecommBook(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topRecommBook(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topRecommBookExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 图书荐购排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toprecommbuybook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topRecommBuyBook(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topRecommBuyBook(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topRecommBuyBookExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 读者借阅排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toplendreader")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topLendReader(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topLendReader(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topLendReaderExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 读者荐购排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toprecommbuyreader")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topRecommBuyReader(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topRecommBuyReader(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topRecommBuyReaderExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 图书借购排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toplendbuybook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topLendBuyBook(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topLendBuyBook(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topLendBuyBookExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * @Author liujunliang
     * @Description 读者借购排行
     * @Date  2018/7/16
     * @Param TopBookVO中参数封装
     * @return
     **/
    @Path("/toplendbuyreader")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R topLendBuyReader(@Context HttpServletRequest request , @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            TopBookVO topBookVO = new TopBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                topBookVO = (TopBookVO)JSONUtil.json2Object(jsonStr,TopBookVO.class);
            }
            Long readerid=JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            if(readerid!=null&&readerid>0){
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(topBookVO.getIsLocal()!=null&&topBookVO.getIsLocal().equals("1")){
                    topBookVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(topBookVO.getPageNum(), topBookVO.getPageSize());
            List<TopBookVO> topBookVOList = topService.topLendBuyReader(topBookVO);
            PageInfo p = new PageInfo(topBookVOList);
            if(topBookVO.getExport()!=null&&topBookVO.getExport().equals("1")){
                fileName=topService.topLendBuyReaderExport(topBookVOList);
                xlsurl=serverUrl+fileName;
                return R.ok().put("xlsurl",xlsurl);
            }
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
