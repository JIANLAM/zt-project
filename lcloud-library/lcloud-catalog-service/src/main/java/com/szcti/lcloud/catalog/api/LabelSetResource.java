package com.szcti.lcloud.catalog.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.catalog.config.JwtYmlConfig;
import com.szcti.lcloud.catalog.entity.LabelSet;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LabelSetVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;
import com.szcti.lcloud.catalog.service.HoldingService;
import com.szcti.lcloud.catalog.service.LabelSetService;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.HttpServletRequestUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("labelset")
public class LabelSetResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    @Autowired
    private LabelSetService labelSetService;
    @Autowired
    private HoldingService holdingService;
    /**
     * 列表查询
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            LabelSetVO vo = new LabelSetVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                vo =(LabelSetVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), LabelSetVO.class);
            }if(vo.getLibraryId()==null||(vo.getLibraryId()>0)){
                vo.setLibraryId(libraryId);
            }
            PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
            List<LabelSetVO> LabelSetVOList = labelSetService.queryPage(vo);
            PageInfo p = new PageInfo(LabelSetVOList);
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
        LabelSet labelSet = labelSetService.selectByPrimaryKey(id);
        return R.ok().put("LabelSet", labelSet);
    }
    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R add(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> res = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            LabelSetVO o= new LabelSetVO();
            o.setPageNum(null);
            o.setPageSize(null);
            o.setCreator(userid);
            o.setLibraryId(libraryId);
            o.setCreateTime(DateUtils.getDateTime());
            return R.ok().put("LabelSet",o);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 保存:
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken){
        try {
            String ip= request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            LabelSet labelSet = new LabelSet();
            if(StringUtils.isNotEmpty(data)){
                labelSet =(LabelSet)JSONObject.toBean(JSONObject.fromObject(data), LabelSet.class);
            }
            labelSet.setCreator(userid);
            labelSet.setCreateTime(DateUtils.formatDateTime(new Date()));
            labelSet.setLibraryId(libraryId);
            int count= labelSetService.insert(labelSet);
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 修改
     */
    @Path("/edit/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R edit(@PathParam("id") Long id ,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            LabelSet o = labelSetService.selectByPrimaryKey(id);
            return R.ok().put("LabelSet",o);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 修改保存
     */
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            LabelSet labelSet = new LabelSet();
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(data)){
                labelSet =(LabelSet)JSONObject.toBean(JSONObject.fromObject(data), LabelSet.class);
                params = objectMapper.readValue(data, Map.class);
            }
            LabelSet p = labelSetService.selectByPrimaryKey(labelSet.getId());
            labelSet.setId(p.getId());
            labelSet.setCreator(p.getCreator());
            labelSet.setCreateTime(p.getCreateTime());
            int count= labelSetService.updateByPrimaryKey(labelSet);

            return R.ok().put("count",count);
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
    public R delete(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
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
                     count = labelSetService.deleteByPrimaryKey(Long.parseLong(id));
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 获取打印书标列表
     */
    @Path("/getprintlabel")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R getPrintLabel(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            LableVO vo = new LableVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                vo =(LableVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), LableVO.class);
            }if(vo.getLibraryId()==null||(vo.getLibraryId()>0)){
                vo.setLibraryId(libraryId);
            }
            PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
            List<LableVO> list =labelSetService.getPrintLabel(vo);
            PageInfo p = new PageInfo(list);
            LabelSet labelSet=labelSetService.selectByPrimaryKey(vo.getLabelSetId());
            R r=R.ok();
            r.put("LabelSet",labelSet);
            r.put("page", p);
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
