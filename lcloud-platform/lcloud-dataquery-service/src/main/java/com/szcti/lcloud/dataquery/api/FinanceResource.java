package com.szcti.lcloud.dataquery.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.dataquery.config.JwtYmlConfig;
import com.szcti.lcloud.dataquery.entity.vo.*;
import com.szcti.lcloud.dataquery.repository.FinanceDao;
import com.szcti.lcloud.dataquery.repository.LendDao;
import com.szcti.lcloud.dataquery.service.FinanceService;
import com.szcti.lcloud.dataquery.service.LendService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: 财经记录信息
 * @Description: 财经记录信息
 * @author: fengda
 * @date: 2018/8/8 9:02
 */
@Component
@Path("finance")
public class FinanceResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private FinanceDao financeDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            FinanceVO finance = new FinanceVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                finance = (FinanceVO)JSONUtil.json2Object(jsonStr,FinanceVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                finance.setLibId(libId);
            }

            PageHelper.startPage(finance.getPageNum(),finance.getPageSize());

            List<FinanceVO> financeList = financeDao.findList(finance);

            PageInfo p = new PageInfo(financeList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportList(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            FinanceVO finance = new FinanceVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                finance = (FinanceVO)JSONUtil.json2Object(jsonStr,FinanceVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                finance.setLibId(libId);
            }

            String fileName = financeService.exportExcel(finance);

            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/broken")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R brokenList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            ForfeitBookVO bean = new ForfeitBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (ForfeitBookVO)JSONUtil.json2Object(jsonStr,ForfeitBookVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            //202为污损
            bean.setType(202);
            PageHelper.startPage(bean.getPageNum(),bean.getPageSize());

            List<ForfeitBookVO> list = financeDao.findForfeitList(bean);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/broken/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportBrokenList(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            ForfeitBookVO bean = new ForfeitBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (ForfeitBookVO)JSONUtil.json2Object(jsonStr,ForfeitBookVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            //202为污损
            bean.setType(202);

            String fileName = financeService.exportUnusualDataExcel(bean);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/lost")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R lostList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            ForfeitBookVO bean = new ForfeitBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (ForfeitBookVO)JSONUtil.json2Object(jsonStr,ForfeitBookVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            //203为丢失
            bean.setType(203);
            PageHelper.startPage(bean.getPageNum(),bean.getPageSize());

            List<ForfeitBookVO> list = financeDao.findForfeitList(bean);

            PageInfo p = new PageInfo(list);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/list/lost/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportLostList(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            ForfeitBookVO bean = new ForfeitBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bean = (ForfeitBookVO)JSONUtil.json2Object(jsonStr,ForfeitBookVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                bean.setLibId(libId);
            }
            //203为丢失
            bean.setType(203);

            String fileName = financeService.exportUnusualDataExcel(bean);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }


    
}
