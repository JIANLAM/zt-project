package com.szcti.lcloud.parameter.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.parameter.config.JwtYmlConfig;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import com.szcti.lcloud.parameter.entity.vo.OutBoxVO;
import com.szcti.lcloud.parameter.repository.BarCodeDao;
import com.szcti.lcloud.parameter.repository.OutBoxDao;
import com.szcti.lcloud.parameter.service.BarCodeService;
import com.szcti.lcloud.parameter.service.OutBoxService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 发送邮箱设置 API
 * @Description: 发送邮箱设置 API
 * @author: wangsiyi
 * @date: 2018/8/16 11:15
 */
@Component
@Path("mailbox")
public class OutBoxResource {


    @Autowired
    private OutBoxDao outBoxDao;

    @Autowired
    private OutBoxService outBoxService;

    /**
     * 查询发送邮箱设置  List集合
     * @param
     * @return List<OutBoxVO>
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {

            OutBoxVO outBoxVO = new OutBoxVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                outBoxVO = (OutBoxVO) JSONUtil.json2Object(jsonStr,OutBoxVO.class);
            }
            PageHelper.startPage(outBoxVO.getPageNum(),outBoxVO.getPageSize());
            outBoxVO.setLibraryId(Long.valueOf("293109093374"));     //图书馆ID
            List<OutBoxVO> OutBoxVOList = outBoxDao.findList(outBoxVO);        //图书馆ID

            PageInfo p = new PageInfo(OutBoxVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条发送邮箱设置  记录
     * @param   outboxId
     * @return OutBoxVO
     */
    @Path("/info/{outboxId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R get(@PathParam("outboxId") Long outboxId){
        try {
            OutBoxVO outBoxVO = outBoxDao.get(outboxId);
            return R.ok().put("outBoxVO", outBoxVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 发送邮箱设置  数据记录
     * @param outboxIds
     */
    @Path("/delete/{outboxIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delOutbox(@PathParam("outboxIds") String outboxIds){
        try {
            outBoxService.delOutbox(outboxIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条发送邮箱设置  数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        try {
            OutBoxVO outBoxVO = new OutBoxVO();
            if (StringUtils.isNotEmpty(data)) {
                outBoxVO = (OutBoxVO) JSONUtil.json2Object(data, OutBoxVO.class);
            }
            outBoxVO.setCreateBy(new Long(1));   //创建者id
            outBoxService.save(outBoxVO);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


}
