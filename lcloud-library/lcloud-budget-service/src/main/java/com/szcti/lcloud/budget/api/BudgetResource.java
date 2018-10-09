package com.szcti.lcloud.budget.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.budget.config.JwtYmlConfig;
import com.szcti.lcloud.budget.entity.OperationLog;
import com.szcti.lcloud.budget.service.OperationLogService;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.budget.entity.Budget;
import com.szcti.lcloud.budget.entity.vo.BudgetVO;
import com.szcti.lcloud.budget.service.BudgetService;
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
@Path("budget")
public class BudgetResource {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 列表 data为budgetVO的json格式
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            BudgetVO budgetVO = new BudgetVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                budgetVO =(BudgetVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), BudgetVO.class);
            }if(budgetVO.getLibraryId()==null||(budgetVO.getLibraryId()>0)){
                budgetVO.setLibraryId(libraryId);
            }
            PageHelper.startPage(budgetVO.getPageNum(), budgetVO.getPageSize());
            List<BudgetVO> budgetVOList = budgetService.queryPage(budgetVO);
            for(BudgetVO vo:budgetVOList){
                float t=(vo.getTotal()==null? 0:vo.getTotal())/10000;
                float r=(vo.getRemain()==null? 0:vo.getRemain())/10000;
                vo.setTotal(t);
                vo.setRemain(r);
            }
            PageInfo p = new PageInfo(budgetVOList);
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
        BudgetVO budgetVo = budgetService.selectById(id);
        return R.ok().put("orderBuyVO", budgetVo);
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
                BudgetVO o= new BudgetVO();
                o.setPageNum(null);
                o.setPageSize(null);
                o.setCreator(userid);
                o.setLibraryId(libraryId);
                if(params.get("userId")!=null&&StringUtils.isNotEmpty(params.get("userId"))){
                    o.setCreator(Long.parseLong(params.get("userId")));
                }
                o.setCreatTime(DateUtils.getDateTime());
                return R.ok().put("Budget",o);
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
    public R save(@Context HttpServletRequest request,String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip= request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Budget budget = new Budget();
            if(StringUtils.isNotEmpty(data)){
                budget =(Budget)JSONObject.toBean(JSONObject.fromObject(data), Budget.class);
            }
            budget.setCreator(userid);
            budget.setCreatTime(DateUtils.parseDate(DateUtils.getDateTime()));
            budget.setLibraryId(libraryId);
        int count= budgetService.insert(budget);
        if(count>0){
            OperationLog o=operationLogService.getUserInfo(userid);
            o.setLibraryId(libraryId);
            o.setModuleId(5);
            o.setModuleName("财经管理");
            o.setOperationType("添加");
            o.setUserId(userid);
            o.setIp(ip);
            o.setOpContent("添加"+budget.getCoding());
            operationLogService.insert(o);
            return R.ok().put("count",count).put("coding",budget.getCoding());
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
    public R edit(@PathParam("id") Long id ,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
            try {
                Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
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
                BudgetVO o = budgetService.selectById(id);
                if(o!=null){
                    o.setPageSize(null);
                    o.setPageNum(null);
                }
                return R.ok().put("Budget",o);
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
            Budget budget = new Budget();
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(data)){
                budget =(Budget)JSONObject.toBean(JSONObject.fromObject(data), Budget.class);
                params = objectMapper.readValue(data, Map.class);
            }
        Budget p = budgetService.selectByPrimaryKey(budget.getId());
        p.setCoding(budget.getCoding());
        p.setName(budget.getName());
        p.setTotal(budget.getTotal());
        p.setRemain(budget.getRemain());
        p.setRemark(budget.getRemark());
        p.setStatus(budget.getStatus());
        p.setType(budget.getType());
        int count= budgetService.updateByPrimaryKey(p);
        if(count>0){
            OperationLog o=operationLogService.getUserInfo(userid);
            o.setLibraryId(libraryId);
            o.setModuleId(5);
            o.setModuleName("财经管理");
            o.setOperationType("修改");
            o.setUserId(userid);
            o.setIp(ip);
            o.setOpContent("修改"+budget.getCoding());
            operationLogService.insert(o);
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
                    String code=budgetService.selectByPrimaryKey(Long.parseLong(id)).getCoding();
                    int c = budgetService.deleteByPrimaryKey(Long.parseLong(id));
                    if(c>0){
                        count++;
                            OperationLog o=operationLogService.getUserInfo(userid);
                            o.setLibraryId(libraryId);
                            o.setModuleId(5);
                            o.setModuleName("财经管理");
                            o.setOperationType("删除");
                            o.setUserId(userid);
                            o.setIp(ip);
                            o.setOpContent("删除"+code);
                            operationLogService.insert(o);
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
                    fileName=budgetService.exportExcel(list);
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
