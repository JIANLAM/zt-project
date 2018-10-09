package com.szcti.lcloud.lendback.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.lendback.config.JwtYmlConfig;
import com.szcti.lcloud.lendback.entity.vo.PreLendVO;
import com.szcti.lcloud.lendback.service.PreLendService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 预借信息
 * @Description: 预借信息
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("preLendWork")
public class PreLendWorkResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private PreLendService preLendService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            PreLendVO preLendVO = new PreLendVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                preLendVO = (PreLendVO)JSONUtil.json2Object(jsonStr,PreLendVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                preLendVO.setReaderId(readerId);
            }

            PageHelper.startPage(preLendVO.getPageNum(),preLendVO.getPageSize());

            List<PreLendVO> preLendVOList = preLendService.findList(preLendVO);

            PageInfo p = new PageInfo(preLendVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/finish/{preLendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R finish(@PathParam("preLendId") Long preLendId){
        try {
            preLendService.finishPreLend(preLendId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/cancel/{preLendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R cancel(@PathParam("preLendId") Long preLendId){
        try {
            preLendService.cancelPreLend(preLendId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/recover/{preLendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R recover(@PathParam("preLendId") Long preLendId){
        try {
            preLendService.recoverPreLend(preLendId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /*************************************     微信端     *****************************/
    @Path("/weChatList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R weChatList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            PreLendVO preLendVO = new PreLendVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                preLendVO = (PreLendVO)JSONUtil.json2Object(jsonStr,PreLendVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                preLendVO.setReaderId(readerId);
            }

            PageHelper.startPage(preLendVO.getPageNum(),preLendVO.getPageSize());

            List<PreLendVO> preLendVOList = preLendService.weChatFindList(preLendVO);

            PageInfo p = new PageInfo(preLendVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
}
