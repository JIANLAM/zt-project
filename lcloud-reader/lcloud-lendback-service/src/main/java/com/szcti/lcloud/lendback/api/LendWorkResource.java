package com.szcti.lcloud.lendback.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.lendback.config.JwtYmlConfig;
import com.szcti.lcloud.lendback.entity.vo.LendBackVO;
import com.szcti.lcloud.lendback.service.LendBackService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 借还信息
 * @Description: 借还信息
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("lendWork")
public class LendWorkResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LendBackService lendBackService;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBackVO lendBackVO = new LendBackVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBackVO = (LendBackVO)JSONUtil.json2Object(jsonStr,LendBackVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBackVO.setReaderId(readerId);
            }

            PageHelper.startPage(lendBackVO.getPageNum(),lendBackVO.getPageSize());

            List<LendBackVO> lendBackVOList = lendBackService.findList(lendBackVO);

            PageInfo p = new PageInfo(lendBackVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/reLend/{lendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R reLend(@PathParam("lendId") Long lendId){
        try {
            String dueBackTime = lendBackService.reLend(lendId);
            return R.ok().put("dueBackTime",dueBackTime);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据条件查找用户的借还信息            微信端
     * @param jsonStr
     * @return List<LendBackVO>
     */
    @Path("/weChatList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R weChatList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBackVO lendBackVO = new LendBackVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBackVO = (LendBackVO)JSONUtil.json2Object(jsonStr,LendBackVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBackVO.setReaderId(readerId);
            }

            PageHelper.startPage(lendBackVO.getPageNum(),lendBackVO.getPageSize());

            List<LendBackVO> lendBackVOList = lendBackService.weChatList(lendBackVO);

            PageInfo p = new PageInfo(lendBackVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
    /**
     * 根据lendId查找用户的借还 每本书的详情信息            微信端
     * @param lendId
     * @return LendBackVO
     */

    @Path("/weChatInfo/{lendId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R weChatInfo(@PathParam("lendId") Long lendId){
        try {
            LendBackVO lendBackVOObj = lendBackService.weChatInfo(lendId);
            return R.ok().put("lendBackVOObj", lendBackVOObj);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
}
