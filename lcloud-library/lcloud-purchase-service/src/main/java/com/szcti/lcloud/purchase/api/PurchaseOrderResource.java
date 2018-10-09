package com.szcti.lcloud.purchase.api;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.OperationLog;
import com.szcti.lcloud.purchase.entity.PurchaseBook;
import com.szcti.lcloud.purchase.entity.PurchaseOrder;
import com.szcti.lcloud.purchase.entity.TOperationLog;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseOrderVO;
import com.szcti.lcloud.purchase.mapper.TOperationLogMapper;
import com.szcti.lcloud.purchase.service.OperationLogService;
import com.szcti.lcloud.purchase.service.PurchaseBookService;
import com.szcti.lcloud.purchase.service.PurchaseOrderService;
import com.szcti.lcloud.purchase.service.PurchaseRuleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 *
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("purchaseorder")
public class PurchaseOrderResource {
    private static final Logger logger = Logger.getLogger(PurchaseOrderResource.class);

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private PurchaseRuleService purchaseRuleService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private TOperationLogMapper operationLogMapper;
    @Autowired
    private PurchaseBookService  purchaseBookService;

    private String               errorKey   = "msg";

    private String               errorValue = "无权操作！请联系管理员。";

    /**
     * 列表 data为orderBuyVO的json格式
     * Ajax中的data: {"jsonStr":JSON.stringify({ "purchaseCode": "D0001","orderCode": "2018051025",
     *      * "expressCode":"AD58652122","creatorName":"西湾管理"，"bookName":"西游记1"，"author":"LLk"
     *      * "isbn":"MN34554454","出版者":"Mr.Li"})}
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryIdByToken=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Integer usertype=JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            if(usertype==1&&(libraryIdByToken==null||!(libraryIdByToken>0))){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();


            if(StringUtils.isNotEmpty(jsonStr)){
                purchaseOrderVO =(PurchaseOrderVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), PurchaseOrderVO.class);
            }
            if(purchaseOrderVO.getLibraryId()==null||!(purchaseOrderVO.getLibraryId()>0)){
                if(usertype==1){
                    purchaseOrderVO.setLibraryId(libraryIdByToken);
                }
            }
            if(usertype==2){
                purchaseOrderVO.setOrderCodeHave("1");
            }
           logger.info("jsonStr=="+jsonStr);

            //获取启用状态的订购规则集合
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String,String> params = objectMapper.readValue(jsonStr, Map.class);
            params.put("ruleStatus","0");
            params.put("libraryId",String.valueOf(libraryIdByToken));
            List<HashMap<String,Object>> rulelist= purchaseRuleService.queryMapList(params);

            //查询列表信息，若规则正在使用，则在订购规则中无法对其进行关闭开启、修改、删除（linzj 2018/9/5）
            purchaseOrderService.checkRulesIsUse(rulelist,purchaseOrderVO);

            PageHelper.startPage(purchaseOrderVO.getPageNum(),purchaseOrderVO.getPageSize());
             List<PurchaseOrderVO> purchaseOrderVOList = purchaseOrderService.queryPage(purchaseOrderVO);

            PageInfo p = new PageInfo(purchaseOrderVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("....error..."+e.getMessage());
            return R.error();
        }
    }

    //图书馆端-验收查询列表

    @Path("/acceptlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R acceptList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            Integer usertype = JwtUtil.getUserTypeByToken(authToken, jwtYmlConfig.getSecret());
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                purchaseOrderVO = (PurchaseOrderVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseOrderVO.class);
            }
            purchaseOrderVO.setOrderCodeHave("1");
            if ( purchaseOrderVO.getLibraryId() == null || !(purchaseOrderVO.getLibraryId() > 0) )
            {
                if ( usertype == 1 )
                {
                    purchaseOrderVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(purchaseOrderVO.getPageNum(), purchaseOrderVO.getPageSize());
            List<PurchaseOrderVO> purchaseOrderVOList = purchaseOrderService.queryAcceptPage(purchaseOrderVO);
            setDesc(purchaseOrderVOList);
            PageInfo p = new PageInfo(purchaseOrderVOList);
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    //平台运营端订购单待处理列表
    @Path("/waitinglist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R waitingList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( userid == null || !(userid > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                purchaseOrderVO = (PurchaseOrderVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseOrderVO.class);
            }
            purchaseOrderVO.setOrderCodeHave("1");
            //purchaseOrderVO.setOrderStatus(0);
            PageHelper.startPage(purchaseOrderVO.getPageNum(), purchaseOrderVO.getPageSize());
            List<PurchaseOrderVO> purchaseOrderVOList = purchaseOrderService.queryPage(purchaseOrderVO);
            setDesc(purchaseOrderVOList);
            PageInfo p = new PageInfo(purchaseOrderVOList);
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    //平台运营端订购单已完成订购列表
    @Path("/finishlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R finishList(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( userid == null || !(userid > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                purchaseOrderVO = (PurchaseOrderVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseOrderVO.class);
            }
            purchaseOrderVO.setOrderCodeHave("1");
            purchaseOrderVO.setOrderStatus("3");
            PageHelper.startPage(purchaseOrderVO.getPageNum(), purchaseOrderVO.getPageSize());
            List<PurchaseOrderVO> purchaseOrderVOList = purchaseOrderService.queryPage(purchaseOrderVO);
            setDesc(purchaseOrderVOList);
            PageInfo p = new PageInfo(purchaseOrderVOList);
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    private void setDesc(List<PurchaseOrderVO> list)
    {
        for (PurchaseOrderVO v : list)
        {
            /*if(v.getCheckStatus()!=null&&!v.getCheckStatus().equals("")) {
                String statusdesc = CommonStatus.getCommonStatus(v.getCheckStatus()).getMsg();
                v.setCheckStatus(statusdesc);
            }*/
        }
    }

    /**
     * 信息
     */
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R info(@PathParam("id") Long id)
    {
        PurchaseOrderVO purchaseOrderVo = purchaseOrderService.selectById(id);
        return R.ok().put("orderBuyVO", purchaseOrderVo);
    }

    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R add(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String, Object>> res = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            //只查询启用状态代码（0为启用状态，1为未启用）
            params.put("ruleStatus", "0");
            params.put("libraryId", String.valueOf(libraryId));
            //订购规则
            res = purchaseRuleService.queryMapList(params);
            PurchaseOrderVO o = new PurchaseOrderVO();
            o.setPageNum(null);
            o.setPageSize(null);
            o.setCreator(userid);
            o.setCreateTime(DateUtils.formatDateTime(new Date()));
            o.setSummary("请填写备注：");
            R r = R.ok();
            r.put("ruleList", res);
            r.put("budgetList", purchaseOrderService.queryBudgetMapList(params));
            r.put("addressList", purchaseOrderService.queryAddressMapList(params));
            r.put("PurchaseOrder", o);
            return r;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 保存:
     * var jsonStr=JSON.stringify({"summary":'请填写备注:',"creator":'1',"ruleId":'1'});
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            String purchaseCode = IdGen.getDateUUId();
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            if ( StringUtils.isNotEmpty(data) )
            {
                purchaseOrder = (PurchaseOrder) JSONObject.toBean(JSONObject.fromObject(data), PurchaseOrder.class);
            }
            purchaseOrder.setLibraryId(libraryId);
            purchaseOrder.setCreator(userid);
            if ( purchaseOrder.getRuleId() != null )
            {
                purchaseCode = purchaseCode(purchaseOrder.getRuleId());
                purchaseOrder.setPurchaseCode(purchaseCode);
            }
            if ( purchaseOrder.getPurchaseCode() == null || purchaseOrder.getPurchaseCode().equals("") )
            {
                return R.error().put("error", "缺少订购号！");
            }
            if ( purchaseOrder.getCreator() == null || purchaseOrder.getCreator() == 0 )
            {
                return R.error().put("error", "缺少创建者！");
            }
            purchaseOrder.setCheckStatus(new Integer("0"));//未审核
            purchaseOrder.setBookTypeQty(0);
            purchaseOrder.setTotalPrice(new Float(0));
            purchaseOrder.setTotalQuantity(0);
            int count = purchaseOrderService.insert(purchaseOrder);
            if ( count > 0 )
            {
                OperationLog o = operationLogService.getUserInfo(userid);
                o.setLibraryId(libraryId);
                o.setModuleId(1);
                o.setModuleName("征订目录");
                o.setOperationType("添加");
                o.setUserId(userid);
                o.setIp(ip);
                o.setOpContent("添加" + purchaseOrder.getPurchaseCode());
                operationLogService.insert(o);
            }
            return R.ok().put("count", count).put("purchaseCode", purchaseCode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    private String purchaseCode(Long ruleId)
    {
        return purchaseOrderService.createPurchaseCode(ruleId);
    }

    /**
     * 强行审核 var jsonStr=JSON.stringify({"ids":'1,2,3,6',"checkStatus":'1'})
     * ajax中: type:'post'
     *         data: jsonStr
     */
    @Path("/check")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R check(String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String) params.get("ids");
            String[] array = ids.split(",");
            params.put("checker", userid);
            params.put("checkTime", DateUtils.getDateTime());
            int count = 0;
            for (String id : array)
            {
                if ( id != null && !id.equals("") )
                {
                    params.put("id", Long.parseLong(id));
                    int re = purchaseOrderService.checkBatchIds(params);
                    if ( re > 0 )
                    {
                        count++;
                    }
                }
            }
            return R.ok().put("count", count);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 订购单提交
     * * 审核 jsonStr=JSON.stringify({"ids":'1,2,3,6',"orderStatus":'1'})
     *      * ajax中: type:'post'
     *      *         data: jsonStr
     */
    @Path("/commit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R commit(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = (String) params.get("ids");
            String[] array = ids.split(",");
            params.put("committer", userid);
            params.put("orderTime", DateUtils.getDateTime());
            params.put("orderStatus", '1');//订单状态：1申请中2已审批3已发货4部分验收5补发货6已完成7退回
            int count = 0;
            for (String id : array)
            {
                if ( id != null && !id.equals("") )
                {
                    params.put("id", Long.parseLong(id));
                    params.put("orderCode", IdGen.getDateUUId());
                    String purchaseCode = purchaseOrderService.selectByPrimaryKey(Long.parseLong(id)).getPurchaseCode();
                    int re = purchaseOrderService.commitBatchIds(params);
                    if ( re > 0 )
                    {
                        count++;
                        OperationLog o = operationLogService.getUserInfo(userid);
                        o.setLibraryId(libraryId);
                        o.setModuleId(1);
                        o.setModuleName("征订目录");
                        o.setOperationType("提交");
                        o.setUserId(userid);
                        o.setIp(ip);
                        o.setOpContent("添加" + purchaseCode);
                        operationLogService.insert(o);
                    }
                }
            }
            return R.ok().put("count", count);
        }
        catch (Exception e)
        {
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
    public R edit(@PathParam("id") Long id, @QueryParam("jsonStr") String jsonStr,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String, Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            params.put("libraryId", String.valueOf(libraryId));
            //只查询启用状态订购规则
            params.put("ruleStatus", "启用");
            PurchaseOrderVO o = purchaseOrderService.selectById(id);
            o.setPageSize(null);
            o.setPageNum(null);
            rulelist = purchaseRuleService.queryMapList(params);
            R r = R.ok();
            r.put("ruleList", rulelist);
            r.put("budgetList", purchaseOrderService.queryBudgetMapList(params));
            r.put("addressList", purchaseOrderService.queryAddressMapList(params));
            r.put("PurchaseOrder", o);
            return r;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：征订目录修改保存
     * @作者：tianbw
     * @时间：2018年9月8日 下午1:55:36
     * @param request
     * @param data
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(@Context HttpServletRequest request, String data, @QueryParam("jsonStr") String jsonStr,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = request.getServerName();
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            if ( StringUtils.isNotEmpty(data) )
            {
                purchaseOrder = (PurchaseOrder) JSONObject.toBean(JSONObject.fromObject(data), PurchaseOrder.class);
                params = objectMapper.readValue(data, Map.class);

                if ( params.get("createTime") != null && !params.get("createTime").equals("") )
                {
                    purchaseOrder.setCreateTime(DateUtils.parseDate(params.get("createTime")));
                }
            }
            if ( purchaseOrder.getPurchaseCode() == null || purchaseOrder.getPurchaseCode().equals("") )
            {
                return R.error().put("error", "缺少订购号！");
            }
            PurchaseOrder p = purchaseOrderService.selectByPrimaryKey(purchaseOrder.getId());
            p.setRuleId(purchaseOrder.getRuleId());
            p.setSummary(purchaseOrder.getSummary());
            p.setBudgetId(purchaseOrder.getBudgetId());
            p.setAddress(purchaseOrder.getAddress());
            int count = purchaseOrderService.updateByPrimaryKeyWithBLOBs(p);
            if ( count > 0 )
            {
                OperationLog o = operationLogService.getUserInfo(userid);
                o.setLibraryId(libraryId);
                o.setModuleId(1);
                o.setModuleName("征订目录");
                o.setOperationType("修改");
                o.setIp(ip);
                o.setUserId(userid);
                o.setOpContent("修改" + purchaseOrder.getPurchaseCode());
                operationLogService.insert(o);
            }
            return R.ok().put("count", count);
        }
        catch (Exception e)
        {
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
    public R delete(@Context HttpServletRequest request, String data, @QueryParam("jsonStr") String jsonStr,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = request.getServerName();
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( libraryId == null || !(libraryId > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String[] array = ids.split(",");
            int count = 0;
            for (String id : array)
            {
                Long idl = Long.parseLong(id);
                if ( idl > 0 )
                {
                    String purchaseCode = purchaseOrderService.selectByPrimaryKey(Long.parseLong(id)).getPurchaseCode();
                    int c = purchaseOrderService.delete(Long.parseLong(id));
                    if ( c > 0 )
                    {
                        count++;
                        if ( count > 0 )
                        {
                            OperationLog o = operationLogService.getUserInfo(userid);
                            o.setLibraryId(libraryId);
                            o.setModuleId(1);
                            o.setModuleName("征订目录");
                            o.setOperationType("删除");
                            o.setUserId(userid);
                            o.setOpContent("删除" + purchaseCode);
                            o.setIp(ip);
                            operationLogService.insert(o);
                        }
                    }
                }
            }
            return R.ok().put("count", count);
        }
        catch (Exception e)
        {
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
    public R exportPurchase(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr)
    {
        try
        {
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            String serverUrl = "http://" + request.getServerName() + ":" + new POITool().getExportPort();
            String fileName = "";
            String xlsurl = "";
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
                String ids = params.get("ids");
                if ( ids != null && !ids.equals("") )
                {
                    String[] ar = ids.split(",");
                    List list = Arrays.asList(ar);
                    fileName = purchaseOrderService.exportExcel(list);
                    xlsurl = serverUrl + fileName;
                }
            }
            return R.ok().put("xlsurl", xlsurl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 导出
     */
    @Path("/exportorder")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportOrder(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr)
    {
        try
        {
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            String serverUrl = "http://" + request.getServerName() + ":" + new POITool().getExportPort();
            String fileName = "";
            String xlsurl = "";
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                params = objectMapper.readValue(jsonStr, Map.class);
                String ids = params.get("ids");
                if ( ids != null && !ids.equals("") )
                {
                    String[] ar = ids.split(",");
                    List list = Arrays.asList(ar);
                    fileName = purchaseOrderService.exportOrderExcel(list);
                    xlsurl = serverUrl + fileName;
                }
            }
            return R.ok().put("xlsurl", xlsurl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    //    @Path("/addbooks")
    //    @POST
    //    @Produces(MediaType.APPLICATION_JSON)
    //    public R addbooks(@Context HttpServletRequest request, String data, @QueryParam("jsonStr") String jsonStr,
    //            @HeaderParam("Authorization") String authToken)
    //    {
    //        try
    //        {
    //            String ip = HttpServletRequestUtil.getIpAddr(request);
    //            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
    //            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
    //            if ( libraryId == null || !(libraryId > 0) )
    //            {
    //                return R.error().put("msg", "无权登录此平台！请联系管理员。");
    //            }
    //            Map<String, Object> params = new HashMap<String, Object>();
    //            ObjectMapper objectMapper = new ObjectMapper();
    //            params = objectMapper.readValue(data, Map.class);
    //            Long orderId = null;
    //            if ( params.get("orderId") != null && !params.get("orderId").equals("") )
    //            {
    //                orderId = Long.parseLong(params.get("orderId").toString());
    //            }
    //            String ids = (String) params.get("ids");//ids参数解释  当前选中 书籍id-数量
    //            String[] array = ids.split(",");
    //            int count = 0;
    //            if ( orderId > 0 && array != null && array.length > 0 )
    //            {
    //                count = purchaseOrderService.insertBooks(array, orderId, params);
    //                if ( count > 0 )
    //                {
    //                    String purchaseCode = purchaseOrderService.selectByPrimaryKey(orderId).getPurchaseCode();
    //                    OperationLog o = operationLogService.getUserInfo(userid);
    //                    o.setLibraryId(libraryId);
    //                    o.setModuleId(1);
    //                    o.setModuleName("征订目录");
    //                    o.setOperationType("修改已选书");
    //                    o.setUserId(userid);
    //                    o.setIp(ip);
    //                    o.setOpContent("添加订购号" + purchaseCode);
    //                    operationLogService.insert(o);
    //                }
    //            }
    //            return R.ok().put("count", count);
    //        }
    //        catch (Exception e)
    //        {
    //            e.printStackTrace();
    //            return R.error();
    //        }
    //    }

    /*
    *荐购的书加入订购单
    * */
    @Path("/addrecommbuy")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R addrecommbuy(@Context HttpServletRequest request, String data, @QueryParam("jsonStr") String jsonStr,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            Map<String, Object> params = new HashMap<String, Object>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            PurchaseOrder p = (PurchaseOrder) JSONObject.toBean(JSONObject.fromObject(data), PurchaseOrder.class);
            p.setCreator(userid);
            p.setLibraryId(libraryId);
            p.setCreateTime(new Date());
            p.setCheckStatus(new Integer("0"));//未审核
            p.setBookTypeQty(0);
            p.setTotalPrice(new Float(0));
            p.setTotalQuantity(0);
            if ( params.get("orderId") != null && !params.get("orderId").equals("") )
            {
                p = purchaseOrderService.selectByPrimaryKey(Long.parseLong((String) params.get("orderId")));
            }
            String ids = (String) params.get("ids");
            String[] array = ids.split(",");
            if ( array != null && array.length > 0 )
            {
                Map<String, Object> m = purchaseOrderService.insertBooks(array, p, params);
                OperationLog o = operationLogService.getUserInfo(userid);
                o.setLibraryId(libraryId);
                o.setModuleId(3);
                o.setModuleName("荐购处理");
                o.setOperationType("加入订购单");
                o.setUserId(userid);
                o.setIp(ip);
                o.setOpContent("加入订购单:" + p.getPurchaseCode());
                operationLogService.insert(o);
                return R.ok().put("res", m);
            }
            return R.ok().put("count", 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    /**
     * 退订单
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/backlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R backlist(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( userid == null || !(userid > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                purchaseOrderVO = (PurchaseOrderVO) JSONObject.toBean(JSONObject.fromObject(jsonStr),
                        PurchaseOrderVO.class);
            }
            purchaseOrderVO.setOrderCodeHave("1");
            purchaseOrderVO.setOrderStatus("7");//退回
            PageHelper.startPage(purchaseOrderVO.getPageNum(), purchaseOrderVO.getPageSize());
            List<PurchaseOrderVO> purchaseOrderVOList = purchaseOrderService.queryPage(purchaseOrderVO);
            setDesc(purchaseOrderVOList);
            PageInfo p = new PageInfo(purchaseOrderVOList);
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }
    
    @Path("/backlistbook")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R backlistbook(@QueryParam("orderId") String orderId, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            Long userid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
            if ( userid == null || !(userid > 0) )
            {
                return R.error().put("msg", "无权登录此平台！请联系管理员。");
            }
            PurchaseBookVO v = new PurchaseBookVO();
            v.setOrderId(NumberUtils.toLong(orderId, -1L));
            List<PurchaseBookVO> ls = purchaseBookService.queryPage(v);
            return R.ok().put("books", ls);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    @SuppressWarnings("rawtypes")
    @Path("/logs")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R logs(@QueryParam("operType") String operType, @QueryParam("userId") String userId,
            @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate,
            @QueryParam("pageNum") String pageNum, @QueryParam("pageSize") String pageSize,
            @HeaderParam("Authorization") String authToken)
    {
        try
        {
            if ( StringUtils.isBlank(pageNum) )
            {
                pageNum = "1";
            }
            if ( StringUtils.isBlank(pageSize) )
            {
                pageSize = "20";
            }

            EntityWrapper<TOperationLog> ew = new EntityWrapper<>();
            ew.eq("module_id", 1);
            ew.orderBy("create_time", false);

            if ( StringUtils.isNotBlank(operType) )
            {
                ew.eq("operation_type", operType);
            }
            if ( StringUtils.isNotBlank(userId) )
            {
                ew.eq("user_id", userId);
            }
            if ( StringUtils.isNotBlank(startDate) )
            {
                ew.ge("create_time", DateUtils.parseDate(startDate + " 00:00:00"));
            }
            if ( StringUtils.isNotBlank(endDate) )
            {
                ew.le("create_time", DateUtils.parseDate(endDate + " 23:59:59"));
            }

            PageHelper.startPage(NumberUtils.toInt(pageNum, 1), NumberUtils.toInt(pageSize, 20));
            List<TOperationLog> ls = operationLogMapper.selectList(ew);
            PageInfo page = new PageInfo<>(ls);

            return R.ok().put("page", page);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：订购单添加图书信息
     * @作者：tianbw
     * @时间：2018年9月5日 上午11:37:21
     * @param request
     * @param data
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/addbooks")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R addbooks(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            if ( data == null || data.isEmpty() )
            {
                return R.error( -1, "data 不能为空！");
            }

            /**
             *   校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }

            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
            String ip = HttpServletRequestUtil.getIpAddr(request);

            PurchaseBookVO purchaseBookVo = null;

            if ( StringUtils.isNotEmpty(data) )
            {
                JSONObject catalog = JSONObject.fromObject(data);
                purchaseBookVo = (PurchaseBookVO) JSONObject.toBean(catalog, PurchaseBookVO.class);
                JSONArray purchasebook = JSONArray.fromObject(catalog.get("purchasebooklist"));
                purchaseBookVo
                        .setPurchasebooklist(JSONArray.toList(purchasebook, new PurchaseBook(), new JsonConfig()));

                if ( purchaseBookVo.getOrderId() == null || purchaseBookVo.getOrderId() <= 0 )
                {
                    return R.error( -1, "orderId 订购号不能为空！");
                }
                if ( purchaseBookVo.getRuleId() == null || purchaseBookVo.getRuleId() <= 0 )
                {
                    return R.error( -1, "RuleId 订购规则id不能为空！");
                }
            }

            List<PurchaseBook> purchaseBook = purchaseBookVo.getPurchasebooklist();
            int count = 0;
            for (int i = 0; i < purchaseBook.size(); i++)
            {
                if ( purchaseBook.get(i).getSource() == null || purchaseBook.get(i).getSource() <= 0 )
                {
                    return R.error( -1, "source 数据源不能为空！");
                }
                if ( purchaseBook.get(i).getBookId() == null || purchaseBook.get(i).getBookId() <= 0 )
                {
                    return R.error( -1, "bookId不能为空！");
                }
                if ( purchaseBook.get(i).getBookQty() == null || purchaseBook.get(i).getBookQty() <= 0 )
                {
                    return R.error( -1, "bookQty 数量不能为空！");
                }
                purchaseBook.get(i).setLibraryId(libraryId.toString());
                purchaseBook.get(i).setOrderId(purchaseBookVo.getOrderId());

                if ( purchaseOrderService.queryPurchaseBook(purchaseBook.get(i)) != null )
                {
                    count = purchaseOrderService.updatePurchaseBook(purchaseBook.get(i));
                }
                else
                {
                    count = purchaseOrderService.insertPurchaseBook(purchaseBook.get(i));
                }
            }
            if ( count > 0 )
            {
                return R.ok();
            }
            else
            {
                return R.error( -1, "提交数据为空,保存失败" + count + purchaseBook.size());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *
     * @描述：通用校验权限方法
     * @作者：tianbw
     * @时间：2018年8月25日 上午9:03:48
     * @param authToken
     * @return
     */
    public Boolean checkAuthorization(String authToken)
    {

        Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
        if ( libraryId == null || !(libraryId > 0) )
        {
            return false;
        }
        return true;

    }
}
