package com.szcti.lcloud.helplendback.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.helplendback.config.JwtYmlConfig;
import com.szcti.lcloud.helplendback.entity.vo.HelpLendBackVO;
import com.szcti.lcloud.helplendback.repository.HelpLendBackDao;
import com.szcti.lcloud.helplendback.service.HelpLendBackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 处理代借信息的API
 * @Description: 处理代借信息的API
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("lendWork")
public class LendWorkResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private HelpLendBackService helpLendBackService;

    @Autowired
    private HelpLendBackDao helpLendBackDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            HelpLendBackVO helpLendBackVO = new HelpLendBackVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                helpLendBackVO = (HelpLendBackVO)JSONUtil.json2Object(jsonStr,HelpLendBackVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                helpLendBackVO.setReaderId(readerId);
            }

            //type为0指代借
            helpLendBackVO.setType(0);
            PageHelper.startPage(helpLendBackVO.getPageNum(),helpLendBackVO.getPageSize());

            List<HelpLendBackVO> helpLendBackVOList = helpLendBackDao.findList(helpLendBackVO);

            PageInfo p = new PageInfo(helpLendBackVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/cancel/{helpLendBackId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R cancel(@PathParam("helpLendBackId") Long helpLendBackId){
        try {
            //status改为4为取消代借
            String res = helpLendBackService.cancelHelpLend(helpLendBackId);
            if("success".equals(res)){
                return R.ok();
            }else{
                return R.error(406,"无法取消");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /************************************微信端   代借******************************************/
    @Path("/weChatlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R weChatlist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            HelpLendBackVO helpLendBackVO = new HelpLendBackVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                helpLendBackVO = (HelpLendBackVO)JSONUtil.json2Object(jsonStr,HelpLendBackVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                helpLendBackVO.setReaderId(readerId);
            }

            //type为0指代借
            helpLendBackVO.setType(0);
            PageHelper.startPage(helpLendBackVO.getPageNum(),helpLendBackVO.getPageSize());

            List<HelpLendBackVO> helpLendBackVOList = helpLendBackDao.weChatfindList(helpLendBackVO);

            PageInfo p = new PageInfo(helpLendBackVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
}
