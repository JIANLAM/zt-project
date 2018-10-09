package com.szcti.lcloud.lendrule.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.circulate.config.JwtYmlConfig;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.lendrule.entity.vo.LendRuleVo;
import com.szcti.lcloud.lendrule.repository.LendRuleDao;
import com.szcti.lcloud.lendrule.service.LendRuleService;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 借阅规则 API
 * @Description: 借阅规则 API
 * @author: wangsiyi
 * @date: 2018/8/1 15:48
 */
@Component
@Path("rule")
public class LendRuleResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LendRuleDao lendRuleDao;

    @Autowired
    private LendRuleService lendRuleService;

    /**
     * 查询馆内借阅规则List集合
     * @param
     * @return List<LendRuleVo>
     */
    @Path("/museumRulelist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R museumRulelist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            LendRuleVo lendRuleVo = new LendRuleVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendRuleVo = (LendRuleVo) JSONUtil.json2Object(jsonStr,LendRuleVo.class);
            }
            PageHelper.startPage(lendRuleVo.getPageNum(),lendRuleVo.getPageSize());

            lendRuleVo.setRuleType(0);
            lendRuleVo.setLibraryId(libraryId);       //图书馆ID
            List<LendRuleVo> LendRuleVoList = lendRuleDao.findList(lendRuleVo);

            PageInfo p = new PageInfo(LendRuleVoList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询馆际借阅规则List集合
     * @param
     * @return List<LendRuleVo>
     */
    @Path("/interlibraryRulelist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R interlibraryRulelist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId= JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            LendRuleVo lendRuleVo = new LendRuleVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendRuleVo = (LendRuleVo) JSONUtil.json2Object(jsonStr,LendRuleVo.class);
            }
            PageHelper.startPage(lendRuleVo.getPageNum(),lendRuleVo.getPageSize());

            lendRuleVo.setRuleType(1);
            lendRuleVo.setLibraryId(libraryId);       //图书馆ID
            List<LendRuleVo> LendRuleVoList = lendRuleDao.findList(lendRuleVo);

            PageInfo p = new PageInfo(LendRuleVoList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询一行借阅规则
     * @param   ruleId
     * @return LendRuleVo
     */
    @Path("/info/{ruleId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R get(@PathParam("ruleId") Long ruleId ){
        try {
            LendRuleVo lendRuleVo = lendRuleService.get(ruleId);
            return R.ok().put("lendRuleVo", lendRuleVo);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 借阅规则记录
     * @param ruleId
     */
    @Path("/delete/{ruleId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delLendRule(@PathParam("ruleId") String ruleId){
        try {
            lendRuleService.delLendRule(ruleId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条借阅规则 数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data ,@HeaderParam("Authorization") String authToken){
        try {

            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            LendRuleVo lendRuleVo = new LendRuleVo();
            if (StringUtils.isNotEmpty(data)) {
                lendRuleVo = (LendRuleVo) JSONUtil.json2Object(data, LendRuleVo.class);
            }
            if(lendRuleVo.getId() != null){
                lendRuleVo.setUpdateBy(userid);    //修改者ID 暂时为1 后期token获取
            }else{
                lendRuleVo.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            }
            lendRuleVo.setLibraryId(libraryId);   //创建学校 外键id暂时为1 后期token获取
            Integer res = lendRuleService.save(lendRuleVo);
            if (res == 1) {
                return R.error(406, "规则代码已存在");
            }else if(res == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 启用 一条规则
     * @param   ruleids
     * @return
     */
    @Path("/ruleOpen/{ruleids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R ruleOpen(@PathParam("ruleids") String ruleids){
        try {
            lendRuleService.ruleOpen(ruleids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 禁用 一条规则
     * @param   ruleids
     * @return
     */
    @Path("/ruleClose/{ruleids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R ruleClose(@PathParam("ruleids") String ruleids){
        try {
            lendRuleService.ruleClose(ruleids);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *导出 馆内借阅规则 数据记录
     * @param
     * @return
     */
    @Path("/exportMuseumRule")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMuseumRule(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            LendRuleVo lendRuleVo = new LendRuleVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendRuleVo = (LendRuleVo)JSONUtil.json2Object(jsonStr,LendRuleVo.class);
            }
            lendRuleVo.setRuleType(0);
            lendRuleVo.setLibraryId(libraryId);       //图书馆ID
            String fileName = lendRuleService.exportRuleExcel(lendRuleVo);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     *导出 馆际借阅规则 数据记录
     * @param
     * @return
     */
    @Path("/exportInterlibraryRule")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportInterlibraryRule(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr ,@HeaderParam("Authorization") String authToken){
        try {

            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }

            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            LendRuleVo lendRuleVo = new LendRuleVo();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendRuleVo = (LendRuleVo)JSONUtil.json2Object(jsonStr,LendRuleVo.class);
            }
            lendRuleVo.setRuleType(1);
            lendRuleVo.setLibraryId(libraryId);       //图书馆ID
            String fileName = lendRuleService.exportRuleExcel(lendRuleVo);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     *下载导入 借阅规则模版 数据记录
     * @param
     * @return
     *//*
    @Path("/exportMould")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportMouldExcel(@Context HttpServletRequest request){
        try {
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();

            String fileName = lendRuleService.exportMouldExcel();
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    *//**
     *导入 借阅规则模版  数据记录
     * @param
     * @return
     *//*
    @Path("/import")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)//接收数据类型为MULTIPART_FORM_DATA
    @Produces(MediaType.APPLICATION_JSON)
    public R importLendRule(@Context HttpServletRequest request,MultipartFormDataInput multipartFormDataInput){
        try {
            String filePathName=new FileUploadUtil().uploadStoreFile(multipartFormDataInput,"fileName");
            if(filePathName != null){
                List<Map>  maplist= POITool.getData(filePathName);
                Long libraryId = new Long(1);       //图书馆ID
                Long createBy = new Long(1);       //创建者
                String result = lendRuleService.importLendRule(maplist , libraryId , createBy);
                if(result != null){
                    String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
                    String xlsurl=serverUrl+result;
                    return R.error().put("errorReason","错误可能原因：1、规则代码已存在 2、规则类别错误 3、可借文献类型错误").put("xlsurl", xlsurl);
                }
                return R.ok();
            }
            return R.error(406, "文件名为空");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }*/

    /**
     * 借阅图书时 校验规则
     * @param readerId,bookId
     * @return
     */
    @Path("/{readerId}/checkLendRule/{holdingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R checkLendRule(@PathParam("readerId") Long readerId,@PathParam("holdingId") Long holdingId){
        try {
            LendRuleVo ruleVo = lendRuleService.checkLendRule(readerId,holdingId);
            return R.ok().put("ruleVo", ruleVo);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 续借图书时 校验规则
     * @param readerId,bookId
     * @return
     */
    @Path("/{readerId}/checkRelendRule/{holdingId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R checkRelendRule(@PathParam("readerId") Long readerId,@PathParam("holdingId") Long holdingId){
        try {
            LendRuleVo ruleVo = lendRuleService.checkRelendRule(readerId,holdingId);
            return R.ok().put("ruleVo", ruleVo);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
