package com.szcti.lcloud.share.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.share.config.JwtYmlConfig;
import com.szcti.lcloud.share.entity.vo.CommentVO;
import com.szcti.lcloud.share.entity.vo.ShareVO;
import com.szcti.lcloud.share.repository.FocusDao;
import com.szcti.lcloud.share.repository.ShareDao;
import com.szcti.lcloud.share.service.FocusService;
import com.szcti.lcloud.share.service.ShareService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 粉丝关注api
 * @Description: 粉丝关注api
 * @author: fengda
 * @date: 2018/6/29
 */
@Component
@Path("/focus")
public class FocusResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private FocusService focusService;

    @Autowired
    private FocusDao focusDao;

    @Path("/fanslist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R fansList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Map map;
            Integer pageNum = 1,pageSize = 20;
            if(StringUtils.isNotEmpty(jsonStr)){
                map = JSONUtil.json2Map(jsonStr);
                pageNum = Integer.valueOf(map.get("pageNum").toString());
                pageSize = Integer.valueOf(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum,pageSize);

            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            List<HashMap> list = focusDao.findMyFans(readerId);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/focuslist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R focusList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Map map;
            Integer pageNum = 1,pageSize = 20;
            if(StringUtils.isNotEmpty(jsonStr)){
                map = JSONUtil.json2Map(jsonStr);
                pageNum = Integer.valueOf(map.get("pageNum").toString());
                pageSize = Integer.valueOf(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum,pageSize);
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            List<HashMap> list = focusDao.findMyFocus(readerId);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/save/{readerId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(@PathParam("readerId") Long readerId,@HeaderParam("Authorization") String authToken){
        try {
            Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            boolean flag = focusService.save(currentReaderId,readerId);
            if(flag){
                return R.ok();
            }else{
                return R.error(406,"已关注过TA了！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{readerId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("readerId") Long readerId,@HeaderParam("Authorization") String authToken){
        try {
            Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            focusDao.delete(currentReaderId,readerId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/count/{readerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R count(@PathParam("readerId") Long readerId,@HeaderParam("Authorization") String authToken){
        try {
            Long currentReaderId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            HashMap map = focusDao.getCount(readerId,currentReaderId);
            if(currentReaderId.equals(readerId)){
                map.put("isSelf",true);
            }else{
                map.put("isSelf",false);
            }
            return R.ok().put("data", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
