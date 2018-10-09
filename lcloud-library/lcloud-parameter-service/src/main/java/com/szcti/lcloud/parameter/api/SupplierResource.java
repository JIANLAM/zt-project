package com.szcti.lcloud.parameter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.Supplier;
import com.szcti.lcloud.parameter.entity.vo.SupplierVO;
import com.szcti.lcloud.parameter.service.SupplierService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("supplier")
public class SupplierResource {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 列表 data为supplierVO的json格式
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
            SupplierVO supplierVO = new SupplierVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                supplierVO =(SupplierVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), SupplierVO.class);
            }
            supplierVO.setLibraryId(libraryId);
            PageHelper.startPage(supplierVO.getPageNum(), supplierVO.getPageSize());
            List<SupplierVO> supplierVOList = supplierService.queryPage(supplierVO);
            PageInfo p = new PageInfo(supplierVOList);
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
        Supplier supplier = supplierService.selectByPrimaryKey(id);
        return R.ok().put("Supplier", supplier);
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
                SupplierVO o= new SupplierVO();
                o.setPageNum(null);
                o.setPageSize(null);
                return R.ok().put("Supplier",o);
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
            Supplier supplier = new Supplier();
            if(StringUtils.isNotEmpty(data)){
            supplier =(Supplier)JSONObject.toBean(JSONObject.fromObject(data), Supplier.class);
        }
        supplier.setLibraryId(libraryId);
        int count= supplierService.insert(supplier);
        if(count>0){
            return R.ok().put("count",count).put("coding",supplier.getCoding());
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
    public R edit(@PathParam("id") Long id ,@QueryParam("jsonStr") String jsonStr) {
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                Supplier o = supplierService.selectByPrimaryKey(id);
                return R.ok().put("Supplier",o);
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
        Supplier supplier = new Supplier();
       // Map<String, String> params = new HashMap<String, String>();
        //ObjectMapper objectMapper = new ObjectMapper();
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
        if(StringUtils.isNotEmpty(data)){
            supplier =(Supplier)JSONObject.toBean(JSONObject.fromObject(data), Supplier.class);
            //params = objectMapper.readValue(data, Map.class);
        }
            //Supplier p = supplierService.selectByPrimaryKey(supplier.getId());
            //p.setCoding(supplier.getCoding());
            //p.setName(supplier.getName());
            supplier.setLibraryId(libraryId);
        int count= supplierService.updateByPrimaryKey(supplier);
            if(count == 0){
                return R.error(407, "代码只能由数字或字母组成");
            }
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
                    int c = supplierService.deleteByPrimaryKey(Long.parseLong(id));
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
                    //fileName=supplierService.exportExcel(list);
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
