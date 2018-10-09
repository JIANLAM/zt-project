package com.szcti.lcloud.lendbuy.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.lendbuy.config.JwtYmlConfig;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;
import com.szcti.lcloud.lendbuy.repository.RuleDao;
import com.szcti.lcloud.lendbuy.service.RuleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 借购规则api
 * @Description: 借购规则api
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("rule")
public class RuleResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RuleDao ruleDao;

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            LendBuyRuleVO lendBuyRuleVO = new LendBuyRuleVO();
            if(StringUtils.isNotEmpty(data)){
                lendBuyRuleVO = (LendBuyRuleVO)JSONUtil.json2Object(data,LendBuyRuleVO.class);
            }
            if(lendBuyRuleVO!=null&&lendBuyRuleVO.getId()!=null){
                lendBuyRuleVO.setUpdateBy(userId);
            }else{
                lendBuyRuleVO.setCreateBy(userId);
                lendBuyRuleVO.setLibraryId(libId);
            }
            boolean res = ruleService.save(lendBuyRuleVO);
            if(res){
                return R.ok();
            }else{
                return  R.error(406,"同一种读者类型的规则只能有一个！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            LendBuyRuleVO lendBuyRuleVO = new LendBuyRuleVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                lendBuyRuleVO = (LendBuyRuleVO)JSONUtil.json2Object(jsonStr,LendBuyRuleVO.class);
            }
            lendBuyRuleVO.setLibraryId(libId);

            PageHelper.startPage(lendBuyRuleVO.getPageNum(),lendBuyRuleVO.getPageSize());

            List<LendBuyRuleVO> lendBuyRuleVOList = ruleDao.findList(lendBuyRuleVO);

            PageInfo p = new PageInfo(lendBuyRuleVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@PathParam("id") Long id){
        try {
            LendBuyRuleVO lendBuyRuleVO = ruleDao.getById(id);
            return R.ok().put("rule",lendBuyRuleVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/on/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R turnOn(@PathParam("id") Long id){
        try {
            //1为启用状态
            ruleService.changeStatus(id,1);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/off/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R turnOff(@PathParam("id") Long id){
        try {
            //0为未启用状态
            ruleService.changeStatus(id,0);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("ids") String ids){
        try {
            ruleService.delete(ids);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
