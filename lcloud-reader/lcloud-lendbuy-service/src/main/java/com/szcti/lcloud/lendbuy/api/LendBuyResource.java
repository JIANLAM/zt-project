package com.szcti.lcloud.lendbuy.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.lendbuy.config.JwtYmlConfig;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyOrderVO;
import com.szcti.lcloud.lendbuy.repository.LendBuyDao;
import com.szcti.lcloud.lendbuy.service.LendBuyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 借购api
 * @Description: 借购api
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("order")
public class LendBuyResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LendBuyService lendBuyService;

    @Autowired
    private LendBuyDao lendBuyDao;

    @Path("/saveonline/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R saveOnline(@PathParam("ids") String ids,@HeaderParam("Authorization") String authToken){
        try {
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            List<Long> idList = lendBuyService.saveOrder(ids,1,userId,readerId,libId);
            if(idList!=null&&idList.size()>0){
                return R.error(406,"不满足规则").put("idList",idList);
            }else{
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/saveoffline/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R saveOffline(@PathParam("ids") String ids,@HeaderParam("Authorization") String authToken){
        try {
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            List<Long> idList = lendBuyService.saveOrder(ids,0,userId,readerId,libId);
            if(idList!=null&&idList.size()>0){
                return R.error(406,"不满足规则").put("idList",idList);
            }else{
                return R.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/checkbook/{isbn}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R checkBook(@PathParam("isbn") String isbn,@HeaderParam("Authorization") String authToken){
        try {
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            boolean res = lendBuyService.checkBook(isbn,readerId);
            return R.ok().put("exist",res);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/bookList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
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
            lendBuyBookVO.setIsSubmit(1);
            PageHelper.startPage(lendBuyBookVO.getPageNum(),lendBuyBookVO.getPageSize());

            List<LendBuyBookVO> lendBuyBookVOList = lendBuyDao.findBooksInOrder(lendBuyBookVO);

            PageInfo p = new PageInfo(lendBuyBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/exportbooks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportBooks(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
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
            lendBuyBookVO.setIsSubmit(1);
            String fileName = lendBuyService.exportBookExcel(lendBuyBookVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/listonline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R listOnline(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBuyOrderVO lendBuyOrderVO = new LendBuyOrderVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyOrderVO = (LendBuyOrderVO)JSONUtil.json2Object(jsonStr,LendBuyOrderVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setLibId(libId);
            }
            lendBuyOrderVO.setOnline(1);
            PageHelper.startPage(lendBuyOrderVO.getPageNum(),lendBuyOrderVO.getPageSize());

            List<LendBuyOrderVO> lendBuyOrderVOList = lendBuyDao.findOrders(lendBuyOrderVO);

            PageInfo p = new PageInfo(lendBuyOrderVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/exportonline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportOrderOnline(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            LendBuyOrderVO lendBuyOrderVO = new LendBuyOrderVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyOrderVO = (LendBuyOrderVO)JSONUtil.json2Object(jsonStr,LendBuyOrderVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setLibId(libId);
            }
            lendBuyOrderVO.setOnline(1);
            String fileName = lendBuyService.exportOrderExcel(lendBuyOrderVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/listoffline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R listOffline(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendBuyOrderVO lendBuyOrderVO = new LendBuyOrderVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyOrderVO = (LendBuyOrderVO)JSONUtil.json2Object(jsonStr,LendBuyOrderVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setLibId(libId);
            }
            lendBuyOrderVO.setOnline(0);
            PageHelper.startPage(lendBuyOrderVO.getPageNum(),lendBuyOrderVO.getPageSize());

            List<LendBuyOrderVO> lendBuyOrderVOList = lendBuyDao.findOrders(lendBuyOrderVO);

            PageInfo p = new PageInfo(lendBuyOrderVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/exportoffline")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportOrderOffline(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            LendBuyOrderVO lendBuyOrderVO = new LendBuyOrderVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyOrderVO = (LendBuyOrderVO)JSONUtil.json2Object(jsonStr,LendBuyOrderVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setReaderId(readerId);
            }else if(userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                lendBuyOrderVO.setLibId(libId);
            }
            lendBuyOrderVO.setOnline(0);
            String fileName = lendBuyService.exportOrderExcel(lendBuyOrderVO);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/cancel/{lendBuyOrderId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R cancel(@PathParam("lendBuyOrderId") Long lendBuyOrderId){
        try {
            String res = lendBuyService.cancelOrder(lendBuyOrderId);
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

    @Path("/finish/{lendBuyOrderId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R finish(@PathParam("lendBuyOrderId") Long lendBuyOrderId){
        try {
          //  lendBuyService.finishOrder(lendBuyOrderId);
            return R.ok().put("待修改","登录token中没有职工ID");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{lendBuyOrderId}/delete/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("lendBuyOrderId") Long lendBuyOrderId,@PathParam("ids") String ids){
        try {
            lendBuyService.deleteBooksInOrder(lendBuyOrderId,ids);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
