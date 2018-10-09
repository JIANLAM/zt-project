package com.szcti.lcloud.purchase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.*;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;
import com.szcti.lcloud.purchase.entity.vo.OrderbatchBookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.service.*;
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
 * @描述: 
 * @版权: Copyright (c) 2018 
 * @公司: 深圳市中图信息技术有限公司
 * @作者: tianbw
 * @版本: 1.0 
 * @创建日期: 2018年8月29日 
 * @创建时间: 下午6:02:38
 */
@Component
@Path("orderbatchbook")
public class OrderbatchBookResource {
    @Autowired
    private OrderbatchBookService orderbatchBookService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private PurchaseBookService purchaseBookService;
    @Autowired
    OrderBatchService orderBatchService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 列表 data为OrderbatchBookVO的json格式
     * Ajax中的data: {"jsonStr":JSON.stringify({ "purchaseCode": "D0001","orderCode": "2018051025",
     *      * "expressCode":"AD58652122","creatorName":"西湾管理"，"supplierName":"新华集团"，"acceptStatus":"1"
     *      * "startTime":"2017-06-25","endTime":"2018-06-25"})}
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            OrderbatchBookVO orderbatchBookVO = new OrderbatchBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                orderbatchBookVO =(OrderbatchBookVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), OrderbatchBookVO.class);
            }
            orderbatchBookVO.setIsHandIn("0");
            PageHelper.startPage(orderbatchBookVO.getPageNum(), orderbatchBookVO.getPageSize());
            List<OrderbatchBookVO> orderbatchBookVOList = orderbatchBookService.queryPage(orderbatchBookVO);
            PageInfo p = new PageInfo(orderbatchBookVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    @Path("/waitacceptlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R waitAcceptList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long orgId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Integer usertype=JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            PurchaseBookVO purchaseBookVO = new PurchaseBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                System.out.println("jsonStr==="+jsonStr);
                purchaseBookVO =(PurchaseBookVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), PurchaseBookVO.class);
            }
            if(purchaseBookVO.getLibraryId()==null||(purchaseBookVO.getLibraryId()>0)){
                if(usertype==1){
                    purchaseBookVO.setLibraryId(orgId);
                }
            }
            if(usertype==2){
                purchaseBookVO.setOrderCodeHave("1");
            }
            PurchaseOrder p=purchaseOrderService.selectByPrimaryKey(purchaseBookVO.getOrderId());
            PurchaseBookCriteria pc=new PurchaseBookCriteria();
            pc.createCriteria().andOrderIdEqualTo(purchaseBookVO.getOrderId());
            List<PurchaseBook> purchaseBookList = purchaseBookService.selectByExample(pc);
            List<OrderbatchBookVO> list=new ArrayList<OrderbatchBookVO>();
            for(PurchaseBook pb:purchaseBookList){
                OrderbatchBookVO ob=new OrderbatchBookVO();
                ob.setPurchaseBookId(pb.getId());
                ob.setIsbn(pb.getIsbn());
                ob.setTitle(pb.getTitle());
                ob.setAuthor(pb.getAuthor());
                ob.setPrice(pb.getPrice());
                ob.setPubdate(pb.getPubdate());
                ob.setPublisher(pb.getPublisher());
                ob.setBookType(pb.getBookType());
                ob.setOrderQty(pb.getBookQty());
                ob.setBookQty(pb.getBookQty());
                ob.setCheckedQty(0);
                ob.setStatus("1");
                list.add(ob);
            }
            OrderBatchVO oba=new OrderBatchVO();
            oba.setBooklist(list);
            oba.setLibraryId(orgId);
            oba.setPurchaseCode(p.getPurchaseCode());
            oba.setBookTypeQty(p.getBookTypeQty());
            oba.setButgetId(p.getBudgetId());
            //oba.setSupplierId();
            return R.ok().put("OrderBatchVO", oba);
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
        OrderbatchBook orderbatchBook = orderbatchBookService.selectByPrimaryKey(id);
        return R.ok().put("orderBuy", orderbatchBook);
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
            OrderbatchBookVO o= new OrderbatchBookVO();
            o.setPageNum(null);
            o.setPageSize(null);
            //o.setPurchaseCode("D"+IdGen.getDateUUId());
            o.setCreateTime(DateUtils.formatDateTime(new Date()));
            return R.ok().put("OrderbatchBook",o);
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
            OrderbatchBook orderbatchBook = new OrderbatchBook();
            if(StringUtils.isNotEmpty(data)){
                orderbatchBook =(OrderbatchBook)JSONObject.toBean(JSONObject.fromObject(data), OrderbatchBook.class);
            }
            int count= orderbatchBookService.insert(orderbatchBook);
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
            OrderbatchBook o = orderbatchBookService.selectByPrimaryKey(id);
            return R.ok().put("OrderbatchBook",o);
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
        OrderbatchBook orderbatchBook = new OrderbatchBook();
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(StringUtils.isNotEmpty(data)){
                orderbatchBook =(OrderbatchBook)JSONObject.toBean(JSONObject.fromObject(data), OrderbatchBook.class);
                params = objectMapper.readValue(data, Map.class);
            }
            OrderbatchBook p = orderbatchBookService.selectByPrimaryKey(orderbatchBook.getId());
            int count= orderbatchBookService.updateByPrimaryKey(p);
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
                    int c = orderbatchBookService.deleteByPrimaryKey(Long.parseLong(id));
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
     * 提交验收单 var jsonStr=JSON.stringify({"ids":'1-10,2-22,3-15,6-2',"orderBatchId":'2'})
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/accepted")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R accepted(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String)params.get("ids");
            String[] array =ids.split(",");
            params.put("checker",userid+"");
            params.put("checkTime",DateUtils.getDateTime());
            Long orderBatchId=null;
            if(params.get("orderBatchId")!=null&&!params.get("orderBatchId").equals(""))
            {orderBatchId=Long.parseLong(params.get("orderBatchId").toString());}
            int count=0;
            if(orderBatchId>0&&array!=null&&array.length>0){
                Long orderId=null;
                PurchaseOrder po=purchaseOrderService.getByPurchaseCode(orderBatchService.selectByPrimaryKey(orderBatchId).getPurchaseCode());
                if(po!=null&&po.getId()>0){
                    orderId=po.getId();
                }
                count= orderbatchBookService.acceptQty(array,orderId,params);
                if(count>0){
                    purchaseOrderService.refleshAcceptStatus(orderId);
                    OperationLog o=operationLogService.getUserInfo(userid);
                    o.setLibraryId(libraryId);
                    o.setModuleId(4);
                    o.setModuleName("订购验收");
                    o.setOperationType("验收提交");
                    o.setUserId(userid);
                    o.setOpContent(po.getPurchaseCode()+"提交验结果");
                    o.setIp(ip);
                    operationLogService.insert(o);
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 提交验收单 var jsonStr=JSON.stringify(
     * {
     * 格式如：OrderBatchVO中成员:
     * }
     * );
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/bookaccepted")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R bookAccepted(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            OrderBatchVO vo=(OrderBatchVO)JSONObject.toBean(JSONObject.fromObject(data), OrderBatchVO.class);
            int count=0;
            if(vo!=null&&vo.getBooklist()!=null&&vo.getBooklist().size()>0){
                    vo.setLibraryId(libraryId);
                    vo.setCreator(userid);
                    vo.setCreateTime(DateUtils.getDateTime());
                    vo.setChecker(userid);
                    vo.setCheckTime(DateUtils.getDateTime());
                    Long orderId=null;
                    PurchaseOrder po=purchaseOrderService.getByPurchaseCode(vo.getPurchaseCode());
                    if(po!=null&&po.getId()>0){
                        orderId=po.getId();
                    }
                    count= orderbatchBookService.acceptBookQty(vo);
                    if(count>0&&orderId!=null&&orderId>0){
                        purchaseOrderService.refleshAcceptStatus(orderId);
                        OperationLog o=operationLogService.getUserInfo(userid);
                        o.setLibraryId(libraryId);
                        o.setModuleId(4);
                        o.setModuleName("订购验收");
                        o.setOperationType("验收提交");
                        o.setUserId(userid);
                        o.setOpContent(po.getPurchaseCode()+"提交验结果");
                        o.setIp(ip);
                        operationLogService.insert(o);
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
    public R export(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr){
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
                    fileName=orderbatchBookService.exportExcel(list);
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