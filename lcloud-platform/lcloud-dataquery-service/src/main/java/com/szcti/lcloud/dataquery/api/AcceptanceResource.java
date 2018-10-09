package com.szcti.lcloud.dataquery.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.dataquery.config.JwtYmlConfig;
import com.szcti.lcloud.dataquery.entity.vo.AcceptanceVO;
import com.szcti.lcloud.dataquery.entity.vo.AcceptDetailVO;
import com.szcti.lcloud.dataquery.entity.vo.BackDetailVO;
import com.szcti.lcloud.dataquery.entity.vo.BackPurchaseVO;
import com.szcti.lcloud.dataquery.repository.AcceptanceDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 验收记录信息
 * @Description: 验收记录信息
 * @author: fengda
 * @date: 2018/8/9 9:02
 */
@Component
@Path("acceptance")
public class AcceptanceResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private AcceptanceDao acceptanceDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            AcceptanceVO acceptanceVO = new AcceptanceVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                acceptanceVO = (AcceptanceVO)JSONUtil.json2Object(jsonStr,AcceptanceVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                acceptanceVO.setLibId(libId);
            }

            PageHelper.startPage(acceptanceVO.getPageNum(),acceptanceVO.getPageSize());

            List<AcceptanceVO> acceptanceVOList = acceptanceDao.findList(acceptanceVO);

            PageInfo p = new PageInfo(acceptanceVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/detail")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R listDetail(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            AcceptDetailVO bean = new AcceptDetailVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (AcceptDetailVO)JSONUtil.json2Object(jsonStr,AcceptDetailVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            PageHelper.startPage(bean.getPageNum(),bean.getPageSize());

            List<AcceptDetailVO> list = acceptanceDao.findDetail(bean);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/back/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R backList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            BackPurchaseVO backPurchaseVO = new BackPurchaseVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                backPurchaseVO = (BackPurchaseVO)JSONUtil.json2Object(jsonStr,BackPurchaseVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                backPurchaseVO.setLibId(libId);
            }

            PageHelper.startPage(backPurchaseVO.getPageNum(),backPurchaseVO.getPageSize());

            List<BackPurchaseVO> backPurchaseVOList = acceptanceDao.findBackList(backPurchaseVO);

            PageInfo p = new PageInfo(backPurchaseVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/back/list/detail")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R backListDetail(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            BackDetailVO bean = new BackDetailVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (BackDetailVO)JSONUtil.json2Object(jsonStr,BackDetailVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            PageHelper.startPage(bean.getPageNum(),bean.getPageSize());

            List<BackDetailVO> list = acceptanceDao.findBackDetail(bean);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
    
}
