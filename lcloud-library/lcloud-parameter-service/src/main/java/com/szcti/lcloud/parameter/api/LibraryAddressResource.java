package com.szcti.lcloud.parameter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.LibraryAddress;
import com.szcti.lcloud.parameter.entity.vo.LibraryAddressVO;
import com.szcti.lcloud.parameter.service.LibraryAddressService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * 
 *
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("libraryaddress")
public class LibraryAddressResource {
    @Autowired
    private LibraryAddressService libraryAddressService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 列表 data为libraryAddressVO的json格式
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            LibraryAddressVO libraryAddressVO = new LibraryAddressVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                libraryAddressVO =(LibraryAddressVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), LibraryAddressVO.class);
            }
            libraryAddressVO.setLibraryId(libraryId);
            PageHelper.startPage(libraryAddressVO.getPageNum(), libraryAddressVO.getPageSize());
            List<LibraryAddressVO> libraryAddressVOList = libraryAddressService.queryPage(libraryAddressVO);
            PageInfo p = new PageInfo(libraryAddressVOList);
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
        LibraryAddress libraryAddress = libraryAddressService.selectByPrimaryKey(id);
        return R.ok().put("LibraryAddress", libraryAddress);
    }
    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R add(@QueryParam("jsonStr") String jsonStr) {
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> res = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                LibraryAddressVO o= new LibraryAddressVO();
                o.setPageNum(null);
                o.setPageSize(null);
                return R.ok().put("LibraryAddress",o);
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
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
        LibraryAddress libraryAddress = new LibraryAddress();
        if(StringUtils.isNotEmpty(data)){
            libraryAddress =(LibraryAddress)JSONObject.toBean(JSONObject.fromObject(data), LibraryAddress.class);
        }
            libraryAddress.setLibraryId(libraryId);
            libraryAddress.setCreator(userid);
            libraryAddress.setCreateTime(DateUtils.parseDate(new Date()));
        int count= libraryAddressService.insert(libraryAddress);
        if(count>0){
            return R.ok().put("count",count).put("address",libraryAddress.getAddress());
        }
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
    public R edit(@PathParam("id") long id ,@QueryParam("jsonStr") String jsonStr) {
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                LibraryAddress o = libraryAddressService.selectByPrimaryKey(id);
                return R.ok().put("LibraryAddress",o);
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
    public R update(String data , @HeaderParam("Authorization") String authToken){
        LibraryAddress libraryAddress = new LibraryAddress();
        //Map<String, String> params = new HashMap<String, String>();
        //ObjectMapper objectMapper = new ObjectMapper();
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

        if(StringUtils.isNotEmpty(data)){
            libraryAddress =(LibraryAddress)JSONObject.toBean(JSONObject.fromObject(data), LibraryAddress.class);
            //params = objectMapper.readValue(data, Map.class);
        }
        //LibraryAddress p = libraryAddressService.selectByPrimaryKey(libraryAddress.getId());
            libraryAddress.setLibraryId(libraryId);
        int count= libraryAddressService.updateByPrimaryKey(libraryAddress);
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
    public R delete(String data){
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String[] array = ids.split(",");
            int count=0;
            for(String id :array){
                Long idl=Long.parseLong(id);
                if(idl>0) {
                    int c = libraryAddressService.deleteByPrimaryKey(Long.parseLong(id));
                    if(c>0){
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
     * 导出
     */
    @Path("/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportOrder(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr){
        try {
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            String serverUrl="http://"+request.getServerName()+":"+new POITool().getExportPort();
            String fileName="";
            String xlsurl="";
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
                String ids = params.get("ids");
                if(ids!=null&&!ids.equals("")){
                    String[] ar= ids.split(",");
                    List list =Arrays.asList(ar);
                    //fileName=libraryAddressService.exportExcel(list);
                    xlsurl=serverUrl+fileName;
                }
            }
            return R.ok().put("xlsurl",xlsurl);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
