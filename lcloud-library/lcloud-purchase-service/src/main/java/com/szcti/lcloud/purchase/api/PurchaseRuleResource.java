package com.szcti.lcloud.purchase.api;

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
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.HttpServletRequestUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.OperationLog;
import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.TUser;
import com.szcti.lcloud.purchase.entity.vo.PurchaseRuleVO;
import com.szcti.lcloud.purchase.mapper.TUserMapper;
import com.szcti.lcloud.purchase.service.OperationLogService;
import com.szcti.lcloud.purchase.service.PurchaseOrderService;
import com.szcti.lcloud.purchase.service.PurchaseRuleService;

import net.sf.json.JSONObject;

/**
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("purchaserule")
public class PurchaseRuleResource {
	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(PurchaseRuleResource.class);

    @Autowired
    private PurchaseRuleService purchaseRuleService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    
    @Autowired
	private TUserMapper userMapper;
    
    /**
     * 列表
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            PurchaseRuleVO purchaseRuleVO = new PurchaseRuleVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                purchaseRuleVO =(PurchaseRuleVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), PurchaseRuleVO.class);
            }
            purchaseRuleVO.setLibraryId(libraryId);
            PageHelper.startPage(purchaseRuleVO.getPageNum(), purchaseRuleVO.getPageSize());
            List<PurchaseRuleVO> purchaseRuleVOList = purchaseRuleService.queryPage(purchaseRuleVO);
            PageInfo p = new PageInfo(purchaseRuleVOList);
            for (PurchaseRuleVO prv : purchaseRuleVOList) {
            	Long ch = prv.getChecker();
            	if(ch!=null) {
            		TUser u = userMapper.selectById(ch);
            		if(u!=null) {
            			prv.setCheckerName(u.getLoginName());
            		}else {
            			prv.setCheckerName("");
            		}
            	}else {
            		prv.setCheckerName("");
            	}
			}
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
    @Path("/nameList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R nameList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
            try {
                Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
                Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
                if(libraryId==null||!(libraryId>0)){
                    return R.error().put("msg", "无权操作！请联系管理员。");
                }
                Map<String, String> params = new HashMap<String, String>();
                List<HashMap<String,Object>> res = null;
                ObjectMapper objectMapper = new ObjectMapper();
                if(StringUtils.isNotEmpty(jsonStr)){
                    params = objectMapper.readValue(jsonStr, Map.class);
                }
                params.put("libraryId",String.valueOf(libraryId));
                res = purchaseRuleService.queryMapList(params);
                return R.ok().put("ruleList", res);
            } catch (Exception e) {
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
        PurchaseRule purchaseRule = purchaseRuleService.selectByPrimaryKey(id);
        return R.ok().put("purchaseRule", purchaseRule);
    }
    /**
     * 添加规则
     */
    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R add(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            PurchaseRuleVO vo = purchaseRuleService.add();
            vo.setLibraryId(libraryId);
            vo.setCreator(userid);
            return R.ok().put("PurchaseRule",vo);
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
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            List<HashMap<String,Object>> rulelist = null;
            ObjectMapper objectMapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            PurchaseRuleVO vo = purchaseRuleService.selectEdit(id);
            if(StringUtils.isBlank(vo.getSummary())) {
            	vo.setSummary("");
            }
            if(StringUtils.isBlank(vo.getPrefix())) {
            	vo.setPrefix("");
            }
            if(StringUtils.isBlank(vo.getPages())) {
            	vo.setPages("");
            }

            return R.ok().put("PurchaseRule",vo);
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
    public R save(@Context HttpServletRequest request, String data, @HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            PurchaseRuleVO purchaseRuleVO = new PurchaseRuleVO();
            PurchaseRule purchaseRule = new PurchaseRule();
            int count=0;
            if(StringUtils.isNotEmpty(data)){
            	log.debug(data);
                purchaseRuleVO =(PurchaseRuleVO)JSONObject.toBean(JSONObject.fromObject(data), PurchaseRuleVO.class);
                purchaseRuleService.getDataBean(purchaseRule, purchaseRuleVO);
            }
               purchaseRule.setRuleName("订购规则"+DateFormatUtils.format(new Date(),	"yyyyMMdd_HHmmss_SSS"));
                purchaseRule.setCreator(userid);
                purchaseRule.setLibraryId(libraryId);
                purchaseRule.setPublishYear(purchaseRuleVO.getPublishYear().substring(0, 4));
                count= purchaseRuleService.insert(purchaseRule);
           
                
            if(count>0){
                    OperationLog o=operationLogService.getUserInfo(userid);
                    o.setLibraryId(libraryId);
                    o.setModuleId(2);
                    o.setModuleName("订购规则");
                    o.setOperationType("添加");
                    o.setUserId(userid);
                    o.setIp(ip);
                    o.setOpContent("添加"+purchaseRule.getRuleName());
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
        	log.debug(data);
            String ip= request.getServerName();
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            PurchaseRuleVO purchaseRuleVO = new PurchaseRuleVO();
            PurchaseRule purchaseRule = new PurchaseRule();
            int count=0;
            if(StringUtils.isNotEmpty(data)){
                purchaseRuleVO =(PurchaseRuleVO)JSONObject.toBean(JSONObject.fromObject(data), PurchaseRuleVO.class);
                PurchaseRule pr =  purchaseRuleService.selectByPrimaryKey(purchaseRuleVO.getId());
                BeanUtils.copyProperties(pr, purchaseRule);
                
                purchaseRuleService.getDataBean(purchaseRule, purchaseRuleVO);
	            if(StringUtils.isBlank(purchaseRule.getRuleName())) {
	            	purchaseRule.setRuleName("订购规则"+DateFormatUtils.format(new Date(),	"yyyyMMdd_HHmmss_SSS"));
	            }
	            purchaseRule.setLibraryId(libraryId);
                 count= purchaseRuleService.updateByPrimaryKey(purchaseRule);
            }
            if(count>0){
                    OperationLog o=operationLogService.getUserInfo(userid);
                    o.setLibraryId(libraryId);
                    o.setModuleId(2);
                    o.setModuleName("订购规则");
                    o.setOperationType("修改");
                    o.setUserId(userid);
                    o.setIp(ip);
                    o.setOpContent("修改"+purchaseRule.getRuleName());
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
    public R delete(@Context HttpServletRequest request, String data,@HeaderParam("Authorization") String authToken){
        try {
            String ip=HttpServletRequestUtil.getIpAddr(request);
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
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
                    String ruleName=purchaseRuleService.selectByPrimaryKey(Long.parseLong(id)).getRuleName();
                    int c = purchaseRuleService.deleteByPrimaryKey(Long.parseLong(id));
                    if(c>0){
                        OperationLog o=operationLogService.getUserInfo(userid);
                        o.setLibraryId(libraryId);
                        o.setModuleId(2);
                        o.setModuleName("订购规则");
                        o.setOperationType("删除");
                        o.setUserId(userid);
                        o.setIp(ip);
                        o.setOpContent("删除"+ruleName);
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
     * 启用状态//status:1启用，0禁用
     */
    @Path("/setstatus")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R setstatus(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());;
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            Map<String, String> params = new HashMap<String, String>();
            ObjectMapper objectMapper = new ObjectMapper();
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String status=params.get("status");
            String[] array = ids.split(",");
            int count=0;
            if(ids!=null&&!ids.equals("")&&status!=null&&!status.equals("")){
                for(String id :array){
                    Long idl=Long.parseLong(id);
                    if(idl>0) {
                        PurchaseRule p =purchaseRuleService.selectByPrimaryKey(idl);
                        p.setStatus(new Integer(status));
                        int c =purchaseRuleService.updateByPrimaryKey(p);
                        if(c>0){
                            count++;
                        }
                    }
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    
    
    @Path("/operatorList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R operatorList(@HeaderParam("Authorization") String authToken, @Context HttpServletRequest request//

	) {

		Long loginuserid = JwtUtil.getUserIdByToken(authToken, jwtYmlConfig.getSecret());
		TUser u = userMapper.selectById(loginuserid);
		List<TUser> ulss = userMapper
				.selectList(new EntityWrapper<TUser>().eq("type", 1).and().eq("org_id", u.getOrgId()));
		for (TUser tUser : ulss) {
			tUser.setPassword("***");
		}
		return R.ok().put("oprlist", ulss);

	}
}
