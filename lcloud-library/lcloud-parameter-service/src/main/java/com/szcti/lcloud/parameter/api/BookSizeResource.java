package com.szcti.lcloud.parameter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.entity.BookSize;
import com.szcti.lcloud.parameter.entity.vo.BookSizeVO;
import com.szcti.lcloud.parameter.service.BookSizeService;
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
@Path("booksize")
public class BookSizeResource {
    @Autowired
    private BookSizeService bookSizeService;
    /**
     * 列表 data为bookSizeVO的json格式
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            BookSizeVO bookSizeVO = new BookSizeVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bookSizeVO =(BookSizeVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), BookSizeVO.class);
            }
            PageHelper.startPage(bookSizeVO.getPageNum(), bookSizeVO.getPageSize());
            List<BookSizeVO> bookSizeVOList = bookSizeService.queryPage(bookSizeVO);
            PageInfo p = new PageInfo(bookSizeVOList);
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
        BookSize bookSize = bookSizeService.selectByPrimaryKey(id);
        return R.ok().put("BookSize", bookSize);
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
                BookSizeVO o= new BookSizeVO();
                o.setPageNum(null);
                o.setPageSize(null);
                return R.ok().put("BookSize",o);
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
        BookSize bookSize = new BookSize();
        if(StringUtils.isNotEmpty(data)){
            bookSize =(BookSize)JSONObject.toBean(JSONObject.fromObject(data), BookSize.class);
        }
        int count= bookSizeService.insert(bookSize);
        if(count>0){
            return R.ok().put("count",count).put("coding",bookSize.getCoding());
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
                BookSize o = bookSizeService.selectByPrimaryKey(id);
                return R.ok().put("BookSize",o);
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
        BookSize bookSize = new BookSize();
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        if(StringUtils.isNotEmpty(data)){
            bookSize =(BookSize)JSONObject.toBean(JSONObject.fromObject(data), BookSize.class);
            params = objectMapper.readValue(data, Map.class);
        }
        BookSize p = bookSizeService.selectByPrimaryKey(bookSize.getId());
        p.setCoding(bookSize.getCoding());
        p.setSize(bookSize.getSize());
        p.setName(bookSize.getName());
        p.setRemark(bookSize.getRemark());
        int count= bookSizeService.updateByPrimaryKey(p);
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
                    int c = bookSizeService.deleteByPrimaryKey(Integer.parseInt(id));
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
                    //fileName=bookSizeService.exportExcel(list);
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
