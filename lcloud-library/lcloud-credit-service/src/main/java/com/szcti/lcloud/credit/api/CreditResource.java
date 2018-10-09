package com.szcti.lcloud.credit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.credit.config.JwtYmlConfig;
import com.szcti.lcloud.credit.entity.Credit;
import com.szcti.lcloud.credit.entity.OperationLog;
import com.szcti.lcloud.credit.entity.vo.CreditVO;
import com.szcti.lcloud.credit.service.CreditService;
import com.szcti.lcloud.credit.service.OperationLogService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Path("credit")
public class CreditResource {
    @Autowired
    CreditService creditService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 用户信用列表
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            CreditVO creditVO = new CreditVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                creditVO =(CreditVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), CreditVO.class);
            }
            if(creditVO.getLibraryId()==null||!(creditVO.getLibraryId()>0)){
                creditVO.setLibraryId(libraryId);
            }
            PageHelper.startPage(creditVO.getPageNum(), creditVO.getPageSize());
            List<CreditVO> creditVOList = creditService.queryPage(creditVO);
            PageInfo p = new PageInfo(creditVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 信息
     */
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R info(@PathParam("id") Long id){
        Credit credit = creditService.selectByPrimaryKey(id);
        return R.ok().put("credit", credit);
    }
    /**
     * 修改
     */
    @Path("/edit/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R edit(@PathParam("id") Long id ,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
            try {
                //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                if(libraryId==null||!(libraryId>0)){
                    return R.error().put("msg", "无权操作！请联系管理员。");
                }
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            CreditVO vo =new CreditVO();
            vo.setId(id);
            List<CreditVO> list = creditService.queryPage(vo);
            if(list!=null&&list.size()>0){
                return R.ok().put("Credit",list.get(0));
            }
            return R.error();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 保存
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip = request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CreditVO creditVO = new CreditVO();
            Credit credit = new Credit();
            int count=0;
            if(StringUtils.isNotEmpty(data)){
                creditVO =(CreditVO)JSONObject.toBean(JSONObject.fromObject(data), CreditVO.class);
                creditService.getDataBean(credit, creditVO);
            }
            if(credit.getUserId()!=null&&credit.getUserId()>0)
            {
                count= creditService.insert(credit);
            }
            if(count>0){
                OperationLog o=operationLogService.getUserInfo(userid);
                OperationLog reader=operationLogService.getUserInfo(credit.getUserId());
                o.setLibraryId(libraryId);
                o.setModuleId(6);
                o.setModuleName("信用管理");
                o.setOperationType("添加");
                o.setUserId(userid);
                o.setIp(ip);
                o.setOpContent("添加信用"+reader.getUserName());
                operationLogService.insert(o);
                return R.ok().put("count",count);
            }else{
                return R.error().put("errormsg","保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 修改
     */
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip = request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CreditVO creditVO = new CreditVO();
            Credit credit = new Credit();
            int count=0;
            if(StringUtils.isNotEmpty(data)){
                creditVO =(CreditVO)JSONObject.toBean(JSONObject.fromObject(data), CreditVO.class);
                //creditService.getDataBean(credit, creditVO);
            }
            if(creditVO.getId()!=null&& creditVO.getId()>0)
            {
                credit=creditService.selectByPrimaryKey(creditVO.getId());
                if(creditVO.getUserId()!=null&&creditVO.getUserId()>0)
                {credit.setUserId(creditVO.getUserId());}
                if(creditVO.getDefaultValue()!=null&&creditVO.getDefaultValue()>0)
                {credit.setDefaultValue(creditVO.getDefaultValue());}
                if(creditVO.getOwnValue()!=null&&creditVO.getOwnValue()>0)
                {credit.setOwnValue(creditVO.getOwnValue());}
                if(creditVO.getIslendBuy()!=null&&creditVO.getIslendBuy()>0)
                {credit.setIslendBuy(creditVO.getIslendBuy());}
                if(creditVO.getCardStatus()!=null&&creditVO.getCardStatus()>0)
                {credit.setCardStatus(creditVO.getCardStatus());}
                if(creditVO.getStatus()!=null&&creditVO.getStatus()>0)
                {credit.setStatus(creditVO.getStatus());}
                if(creditVO.getSummary()!=null&&!"".equals(creditVO.getSummary()))
                {credit.setSummary(creditVO.getSummary());}
                count= creditService.updateByPrimaryKey(credit);
            }
            if(count>0){
                OperationLog o=operationLogService.getUserInfo(userid);
                OperationLog reader=operationLogService.getUserInfo(credit.getUserId());
                o.setLibraryId(libraryId);
                o.setModuleId(6);
                o.setModuleName("信用管理");
                o.setOperationType("修改");
                o.setUserId(userid);
                o.setIp(ip);
                o.setOpContent("修改信用"+reader.getUserName());
                operationLogService.insert(o);
                return R.ok().put("count",count);
            }else{
                return R.error().put("errormsg","保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 删除
     */
    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R delete(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken){
        try {
            String ip = request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String[] array = ids.split(",");
            int count=0;
            for(String id :array){
                Long idl=Long.parseLong(id);
                if(idl>0) {
                    Long readeruser=creditService.selectByPrimaryKey(Long.parseLong(id)).getUserId();
                    int c = creditService.deleteByPrimaryKey(Long.parseLong(id));
                    if(c>0){
                        OperationLog o=operationLogService.getUserInfo(userid);
                        OperationLog reader=operationLogService.getUserInfo(readeruser);
                        o.setLibraryId(libraryId);
                        o.setModuleId(6);
                        o.setModuleName("信用管理");
                        o.setOperationType("删除");
                        o.setUserId(userid);
                        o.setIp(ip);
                        o.setOpContent("删除信用"+reader.getUserName());
                        operationLogService.insert(o);
                        count++;
                    }
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 启用借购状态//islendBuy:1启用，0禁用
     */
    @Path("/setislendbuy")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R setIslendBuy(String data,@HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String islendBuy=params.get("islendBuy");
            String[] array = ids.split(",");
            int count=0;
            if(ids!=null&&!ids.equals("")&&islendBuy!=null&&!islendBuy.equals("")){
                count=creditService.setIslendBuy(array,islendBuy);
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    @Path("/setownvalue")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R setOwnValue(String data,@HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String ownValue=params.get("ownValue");
            String[] array = ids.split(",");
            int count=0;
            if(ids!=null&&!ids.equals("")&&ownValue!=null&&!ownValue.equals("")){
                count=creditService.setOwnValue(array,ownValue);
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
