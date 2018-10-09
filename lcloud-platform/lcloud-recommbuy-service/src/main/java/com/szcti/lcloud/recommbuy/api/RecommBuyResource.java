package com.szcti.lcloud.recommbuy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.*;
import com.szcti.lcloud.recommbuy.config.JwtYmlConfig;
import com.szcti.lcloud.recommbuy.entity.vo.RecommBuyVO;
import com.szcti.lcloud.recommbuy.service.RecommBuyService;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.*;


/**
 * 荐购表
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("recommbuy")
public class RecommBuyResource {
    @Autowired
    private RecommBuyService recommBuyService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 图书馆端荐购列表
     */
    @Path("/listlibrary")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R listLibrary(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            RecommBuyVO recommBuyVO = new RecommBuyVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommBuyVO =(RecommBuyVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), RecommBuyVO.class);
            }
            recommBuyVO.setLibraryId(libraryId);
            PageHelper.startPage(recommBuyVO.getPageNum(), recommBuyVO.getPageSize());
            List<RecommBuyVO> recommBuyVOList = recommBuyService.queryPage(recommBuyVO);
            PageInfo p = new PageInfo(recommBuyVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }


    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommBuyVO recommbuyVO = new RecommBuyVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommbuyVO = (RecommBuyVO)JSONUtil.json2Object(jsonStr,RecommBuyVO.class);
            }

            PageHelper.startPage(recommbuyVO.getPageNum(),recommbuyVO.getPageSize());

            List<RecommBuyVO> recommbuyVOList = recommBuyService.findList(recommbuyVO);

            PageInfo p = new PageInfo(recommbuyVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            RecommBuyVO recommbuyVO = new RecommBuyVO();
            if(StringUtils.isNotEmpty(data)){
                recommbuyVO = (RecommBuyVO)JSONUtil.json2Object(data,RecommBuyVO.class);
            }
            recommbuyVO.setReaderId(readerId);
            boolean res = recommBuyService.save(recommbuyVO);
            if(res){
                return R.ok();
            }else{
                return R.error(406,"你已荐购过这本书");
            }
        }catch (Exception e){
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
    public R exportRecommend(@Context HttpServletRequest request ,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Integer type=JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
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
                    RecommBuyVO vo=new RecommBuyVO();
                    if(type==1){
                        vo.setLibraryId(libraryId);
                    }
                    vo.setPreId(list);
                    fileName=recommBuyService.exportExcel(vo);
                    xlsurl=serverUrl+fileName;
                }
            }
            return R.ok().put("xlsurl",xlsurl);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /*********************************     微信端  荐购     ************************************/
    @Path("/weChatlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R weChatlist(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommBuyVO recommbuyVO = new RecommBuyVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommbuyVO = (RecommBuyVO)JSONUtil.json2Object(jsonStr,RecommBuyVO.class);
            }

            PageHelper.startPage(recommbuyVO.getPageNum(),recommbuyVO.getPageSize());

            List<RecommBuyVO> recommbuyVOList = recommBuyService.weChatFindList(recommbuyVO);

            PageInfo p = new PageInfo(recommbuyVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
