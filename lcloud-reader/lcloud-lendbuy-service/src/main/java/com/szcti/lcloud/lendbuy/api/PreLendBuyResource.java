package com.szcti.lcloud.lendbuy.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.lendbuy.config.JwtYmlConfig;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyOrderVO;
import com.szcti.lcloud.lendbuy.repository.LendBuyDao;
import com.szcti.lcloud.lendbuy.service.LendBuyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 借购书车api
 * @Description: 借购书车api
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("preBook")
public class PreLendBuyResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LendBuyService lendBuyService;

    @Autowired
    private LendBuyDao lendBuyDao;

    @Path("/listonline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookListOnline(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyBookVO = (LendBuyBookVO)JSONUtil.json2Object(jsonStr,LendBuyBookVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setLibId(libId);
            }
            lendBuyBookVO.setIsSubmit(0);
            lendBuyBookVO.setOnline(1);
            PageHelper.startPage(lendBuyBookVO.getPageNum(),lendBuyBookVO.getPageSize());

            List<LendBuyBookVO> lendBuyBookVOList = lendBuyDao.findBooks(lendBuyBookVO);

            PageInfo p = new PageInfo(lendBuyBookVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/listoffline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookListOffline(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyBookVO = (LendBuyBookVO)JSONUtil.json2Object(jsonStr,LendBuyBookVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setLibId(libId);
            }
            lendBuyBookVO.setIsSubmit(0);
            lendBuyBookVO.setOnline(0);

            PageHelper.startPage(lendBuyBookVO.getPageNum(),lendBuyBookVO.getPageSize());

            List<LendBuyBookVO> lendBuyBookVOList = lendBuyDao.findBooks(lendBuyBookVO);

            PageInfo p = new PageInfo(lendBuyBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveonline")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveOnline(String data,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
            if(StringUtils.isNotEmpty(data)){
                lendBuyBookVO = (LendBuyBookVO)JSONUtil.json2Object(data,LendBuyBookVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setReaderId(readerId);
            }
            lendBuyBookVO.setOnline(1);
            //0为未到馆，线上借购
            lendBuyBookVO.setStatus(0);
            lendBuyBookVO.setIsSubmit(0);
            boolean res = lendBuyService.saveBooks(lendBuyBookVO);
            if(res){
                return R.ok();
            }else{
                return R.error(406,"你已借购过这本书");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/saveoffline")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R saveOffline(String data,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
            if(StringUtils.isNotEmpty(data)){
                lendBuyBookVO = (LendBuyBookVO)JSONUtil.json2Object(data,LendBuyBookVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyBookVO.setReaderId(readerId);
            }
            lendBuyBookVO.setOnline(0);
            //status=1为到馆，线下借购都取1
            lendBuyBookVO.setStatus(1);
            lendBuyBookVO.setIsSubmit(0);
            boolean res = lendBuyService.saveBooks(lendBuyBookVO);
            if(res){
                return R.ok();
            }else{
                return R.error(406,"你已借购过这本书");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/delete/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("ids") String ids){
        try {
            lendBuyService.deleteBooks(ids);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
