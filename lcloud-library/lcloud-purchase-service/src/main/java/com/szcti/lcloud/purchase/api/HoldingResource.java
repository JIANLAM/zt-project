package com.szcti.lcloud.purchase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.purchase.entity.Holding;
import com.szcti.lcloud.purchase.entity.vo.HoldingVO;
import com.szcti.lcloud.purchase.service.HoldingService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Component
@Path("holding")
public class HoldingResource {
    @Autowired
    HoldingService holdingService;
    /**
     * 读者扣分记录列表
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            HoldingVO holdingVO = new HoldingVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                holdingVO =(HoldingVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), HoldingVO.class);
            }
            PageHelper.startPage(holdingVO.getPageNum(), holdingVO.getPageSize());
            List<HoldingVO> holdingVOList = holdingService.queryPage(holdingVO);
            PageInfo p = new PageInfo(holdingVOList);
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
        Holding holding = holdingService.selectByPrimaryKey(id);
        return R.ok().put("holding", holding);
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
            Holding o = holdingService.selectByPrimaryKey(id);
            HoldingVO vo=new HoldingVO();
            holdingService.getVO(vo,o);
            return R.ok().put("HoldingVO",vo);
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
    public R save(String data){
        HoldingVO holdingVO = new HoldingVO();
        Holding holding = new Holding();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                holdingVO =(HoldingVO)JSONObject.toBean(JSONObject.fromObject(data), HoldingVO.class);
                holdingService.getEntity(holding, holdingVO);
            }
            if(holding.getBookId()!=null&&holding.getBookId()>0)
            {
                count= holdingService.insert(holding);
            }
            if(count>0){
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
    public R update(String data){
        HoldingVO holdingVO = new HoldingVO();
        Holding holding = new Holding();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                holdingVO =(HoldingVO)JSONObject.toBean(JSONObject.fromObject(data), HoldingVO.class);
                holdingService.getEntity(holding, holdingVO);
            }
            if(holding.getId()!=null&& holding.getId()>0)
            {
                count= holdingService.updateByPrimaryKey(holding);
            }
            if(count>0){
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
                    int c = holdingService.deleteByPrimaryKey(Long.parseLong(id));
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
     * 启用状态//status:1启用，0禁用
     */
    @Path("/setstatus")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R setstatus(String data){
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String status=params.get("status");
            String[] array = ids.split(",");
            int count=0;
            if(ids!=null&&!ids.equals("")&&status!=null&&!status.equals("")){
                for(String id :array){
                    Long idl=Long.parseLong(id);
                    if(idl>0) {
                        Holding p =holdingService.selectByPrimaryKey(idl);
                        p.setStatus(new Integer(status));
                        int c =holdingService.updateByPrimaryKey(p);
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
}
