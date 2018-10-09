package com.szcti.lcloud.recommended.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.recommended.entity.vo.RecommendedBookVO;
import com.szcti.lcloud.recommended.repository.RecommendedDao;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.recommended.service.RecommendedService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 推荐图书信息的API
 * @Description: 推荐信息的API
 * @author: fengda
 * @date: 2018/5/30 9:02
 */
@Component
@Path("book")
public class RecommendedResource {

    @Autowired
    private RecommendedService recommendedService;

    @Autowired
    private RecommendedDao recommendedDao;

    @Path("/teacherCommend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R teacherCommend(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommendedBookVO recommendedBookVO = new RecommendedBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommendedBookVO = (RecommendedBookVO)JSONUtil.json2Object(jsonStr,RecommendedBookVO.class);
            }
            //0表示导师推荐
            recommendedBookVO.setRecommType(0);
            PageHelper.startPage(recommendedBookVO.getPageNum(),recommendedBookVO.getPageSize());

            List<RecommendedBookVO> recommendedBookVOList = recommendedDao.findList(recommendedBookVO);

            PageInfo p = new PageInfo(recommendedBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/readerCommend")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R readerCommend(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommendedBookVO recommendedBookVO = new RecommendedBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommendedBookVO = (RecommendedBookVO)JSONUtil.json2Object(jsonStr,RecommendedBookVO.class);
            }
            //1表示读者推荐
            recommendedBookVO.setRecommType(1);
            PageHelper.startPage(recommendedBookVO.getPageNum(),recommendedBookVO.getPageSize());

            List<RecommendedBookVO> recommendedBookVOList = recommendedDao.findList(recommendedBookVO);

            PageInfo p = new PageInfo(recommendedBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/mayLike")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R mayLike(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommendedBookVO recommendedBookVO = new RecommendedBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommendedBookVO = (RecommendedBookVO)JSONUtil.json2Object(jsonStr,RecommendedBookVO.class);
            }
            //2表示猜你喜欢
            recommendedBookVO.setRecommType(2);
            PageHelper.startPage(recommendedBookVO.getPageNum(),recommendedBookVO.getPageSize());

            List<RecommendedBookVO> recommendedBookVOList = recommendedDao.findList(recommendedBookVO);

            PageInfo p = new PageInfo(recommendedBookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    @Path("/info")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@QueryParam("jsonStr") String jsonStr){
        try {
            RecommendedBookVO recommendedBook = new RecommendedBookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                recommendedBook = (RecommendedBookVO)JSONUtil.json2Object(jsonStr,RecommendedBookVO.class);
            }
            RecommendedBookVO recommendedBookVO = recommendedService.get(recommendedBook);
            return R.ok().put("recommendedBookVO", recommendedBookVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        try {
            RecommendedBookVO recommendedBookVO = new RecommendedBookVO();
            if(StringUtils.isNotEmpty(data)){
                recommendedBookVO = (RecommendedBookVO)JSONUtil.json2Object(data,RecommendedBookVO.class);
            }
            recommendedService.save(recommendedBookVO);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

}
