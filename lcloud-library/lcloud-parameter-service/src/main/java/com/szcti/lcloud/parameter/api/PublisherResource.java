package com.szcti.lcloud.parameter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.entity.Publisher;
import com.szcti.lcloud.parameter.entity.vo.PublisherVO;
import com.szcti.lcloud.parameter.service.PublisherService;
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
@Path("publisher")
public class PublisherResource {
    @Autowired
    private PublisherService publisherService;
    /**
     * 列表 data为publisherVO的json格式
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            PublisherVO publisherVO = new PublisherVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                publisherVO =(PublisherVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), PublisherVO.class);
            }
            PageHelper.startPage(publisherVO.getPageNum(), publisherVO.getPageSize());
            List<PublisherVO> publisherVOList = publisherService.queryPage(publisherVO);
            PageInfo p = new PageInfo(publisherVOList);
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
    public R info(@PathParam("id") Integer id){
        Publisher publisher = publisherService.selectByPrimaryKey(id);
        return R.ok().put("Publisher", publisher);
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
                PublisherVO o= new PublisherVO();
                o.setPageNum(null);
                o.setPageSize(null);
                return R.ok().put("Publisher",o);
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
    public R save(String data){
        try {
        Publisher publisher = new Publisher();
        if(StringUtils.isNotEmpty(data)){
            publisher =(Publisher)JSONObject.toBean(JSONObject.fromObject(data), Publisher.class);
        }
        int count= publisherService.insert(publisher);
        if(count>0){
            return R.ok().put("count",count).put("coding",publisher.getName());
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
    public R edit(@PathParam("id") Integer id ,@QueryParam("jsonStr") String jsonStr) {
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                Publisher o = publisherService.selectByPrimaryKey(id);
                return R.ok().put("Publisher",o);
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
    public R update(String data){
        Publisher publisher = new Publisher();
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        if(StringUtils.isNotEmpty(data)){
            publisher =(Publisher)JSONObject.toBean(JSONObject.fromObject(data), Publisher.class);
            params = objectMapper.readValue(data, Map.class);
        }
        Publisher p = publisherService.selectByPrimaryKey(publisher.getId());
        p.setName(publisher.getName());
        p.setName(publisher.getName());
        int count= publisherService.updateByPrimaryKey(p);
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
                    int c = publisherService.deleteByPrimaryKey(Integer.parseInt(id));
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
                    //fileName=publisherService.exportExcel(list);
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
