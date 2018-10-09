package com.szcti.lcloud.purchase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.OrderBatch;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;
import com.szcti.lcloud.purchase.service.OrderBatchService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;


/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("orderbatch")
public class OrderBatchResource {
    @Autowired
    private OrderBatchService orderBatchService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 列表 data为OrderBatchVO的json格式
     * Ajax中的data: {"jsonStr":JSON.stringify({ "purchaseCode": "D0001","orderCode": "2018051025",
     *      * "expressCode":"AD58652122","creatorName":"西湾管理"，"supplierName":"新华集团"，"acceptStatus":"1"
     *      * "startTime":"2017-06-25","endTime":"2018-06-25"})}
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            OrderBatchVO orderBatchVO = new OrderBatchVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                orderBatchVO =(OrderBatchVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), OrderBatchVO.class);
            }
            if(orderBatchVO.getLibraryId()==null||(orderBatchVO.getLibraryId()>0)){
                orderBatchVO.setLibraryId(libraryId);
            }
            PageHelper.startPage(orderBatchVO.getPageNum(), orderBatchVO.getPageSize());
            List<OrderBatchVO> orderBatchVOList = orderBatchService.queryPage(orderBatchVO);
            PageInfo p = new PageInfo(orderBatchVOList);
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
        OrderBatch orderBatch = orderBatchService.selectByPrimaryKey(id);
        return R.ok().put("orderBuy", orderBatch);
    }
    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R add(@QueryParam("jsonStr") String jsonStr) {
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                OrderBatchVO o= new OrderBatchVO();
                o.setPageNum(null);
                o.setPageSize(null);
                if(params.get("userId")!=null&&StringUtils.isNotEmpty(params.get("userId"))){
                    o.setCreator(Long.parseLong(params.get("userId")));
                }
                //o.setPurchaseCode("D"+IdGen.getDateUUId());
                o.setCreateTime(DateUtils.formatDateTime(new Date()));
                return R.ok().put("OrderBatch",o);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error();
            }
    }
    /**
     * 保存:
     * var jsonStr=JSON.stringify({"purchaseCode":'D20180523113722578569',"creatorName":'管理员',"createTime":'2018-05-23 11:37:22',"summary":'请填写备注:',"creator":'1',"id":'1'});
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        try {
            String purchaseCode="";
        OrderBatch orderBatch = new OrderBatch();
        if(StringUtils.isNotEmpty(data)){
            orderBatch =(OrderBatch)JSONObject.toBean(JSONObject.fromObject(data), OrderBatch.class);
        }
        if(orderBatch.getPurchaseCode()==null|| orderBatch.getPurchaseCode().equals("")){
            return R.error().put("error","缺少订购号！");
        }
        int count= orderBatchService.insert(orderBatch);
        return R.ok().put("count",count).put("purchaseCode",purchaseCode);
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
                OrderBatch o = orderBatchService.selectByPrimaryKey(id);
                return R.ok().put("OrderBatch",o);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error();
            }
    }
    /**
     * 修改保存
     *      * var jsonStr=JSON.stringify({"id":'3',"purchaseCode":'D30180',"creatorName":'管理员11',"createTime":'2018-05-23 11:37:22',"summary":'请填写备注:11',"creator":'1',"id":'1'});
     *      * ajax中: type:'post'
     *      *         data: jsonStr
     */
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(String data){
        OrderBatch orderBatch = new OrderBatch();
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        if(StringUtils.isNotEmpty(data)){
            orderBatch =(OrderBatch)JSONObject.toBean(JSONObject.fromObject(data), OrderBatch.class);
            params = objectMapper.readValue(data, Map.class);
            if(params.get("createTime")!=null&&!params.get("createTime").equals(""))
            {
                orderBatch.setCreateTime(DateUtils.parseDate(params.get("createTime")));
            }
        }
        if(orderBatch.getPurchaseCode()==null|| orderBatch.getPurchaseCode().equals("")){
            return R.error().put("error","缺少订购号！");
        }
        OrderBatch p = orderBatchService.selectByPrimaryKey(orderBatch.getId());
        int count= orderBatchService.updateByPrimaryKey(p);
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
        //UserUtil.getUserId();
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
                    int c = orderBatchService.deleteByPrimaryKey(Long.parseLong(id));
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
                    fileName=orderBatchService.exportExcel(list);
                    xlsurl=serverUrl+fileName;
                }
            }
            return R.ok().put("xlsurl",xlsurl);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 提交验收单状态 var jsonStr=JSON.stringify({"ids":'1,2,3,6',"checkStatus":'1'})
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/checked")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R checked(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String)params.get("ids");
            String[] array =ids.split(",");
            params.put("checker",userid);
            params.put("checkTime",DateUtils.getDateTime());
            int count=0;
            for(String id:array){
                if(id!=null&&!id.equals("")){
                    params.put("id",Long.parseLong(id));
                    OrderBatch pb=orderBatchService.selectByPrimaryKey(Long.parseLong(id));
                    pb.setAcceptStatus("1");
                    int re= orderBatchService.updateByPrimaryKey(pb);
                    if(re>0){count++;}
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}