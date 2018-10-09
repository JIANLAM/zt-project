package com.szcti.lcloud.credit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.credit.entity.ReaderCredit;
import com.szcti.lcloud.credit.entity.vo.ReaderCreditVO;
import com.szcti.lcloud.credit.service.ReaderCreditService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("readercredit")
public class ReaderCreditResource {
    @Autowired
    ReaderCreditService readerCreditService;
    /**
     * 读者扣分记录列表
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            ReaderCreditVO readerCreditVO = new ReaderCreditVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                readerCreditVO =(ReaderCreditVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), ReaderCreditVO.class);
            }
            PageHelper.startPage(readerCreditVO.getPageNum(), readerCreditVO.getPageSize());
            List<ReaderCreditVO> readerCreditVOList = readerCreditService.queryPage(readerCreditVO);
            PageInfo p = new PageInfo(readerCreditVOList);
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
        ReaderCredit readerCredit = readerCreditService.selectByPrimaryKey(id);
        return R.ok().put("readerCredit", readerCredit);
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
            ReaderCredit o = readerCreditService.selectByPrimaryKey(id);
            ReaderCreditVO vo=new ReaderCreditVO();
            readerCreditService.getReaderCreditVO(vo,o);
            return R.ok().put("ReaderCredit",vo);
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
        ReaderCreditVO readerCreditVO = new ReaderCreditVO();
        ReaderCredit readerCredit = new ReaderCredit();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                readerCreditVO =(ReaderCreditVO)JSONObject.toBean(JSONObject.fromObject(data), ReaderCreditVO.class);
                readerCreditService.getDataBean(readerCredit, readerCreditVO);
            }
            if(readerCredit.getReaderId()!=null&&readerCredit.getReaderId()>0)
            {
                count= readerCreditService.insert(readerCredit);
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
        ReaderCreditVO readerCreditVO = new ReaderCreditVO();
        ReaderCredit readerCredit = new ReaderCredit();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                readerCreditVO =(ReaderCreditVO)JSONObject.toBean(JSONObject.fromObject(data), ReaderCreditVO.class);
                readerCreditService.getDataBean(readerCredit, readerCreditVO);
            }
            if(readerCredit.getId()!=null&& readerCredit.getId()>0)
            {
                count= readerCreditService.updateByPrimaryKey(readerCredit);
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
                    int c = readerCreditService.deleteByPrimaryKey(Long.parseLong(id));
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
                        ReaderCredit p =readerCreditService.selectByPrimaryKey(idl);
                        p.setStatus(Integer.parseInt(status));
                        int c =readerCreditService.updateByPrimaryKey(p);
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
