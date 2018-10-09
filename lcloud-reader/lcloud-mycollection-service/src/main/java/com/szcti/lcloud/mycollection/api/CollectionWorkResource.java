package com.szcti.lcloud.mycollection.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.mycollection.config.JwtYmlConfig;
import com.szcti.lcloud.mycollection.entity.vo.CollectionVO;
import com.szcti.lcloud.mycollection.repository.CollectionDao;
import com.szcti.lcloud.mycollection.service.CollectionService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 个人收藏api
 * @Description: 个人收藏api
 * @author: fengda
 * @date: 2018/5/29
 */
@Component
@Path("collectWork")
public class CollectionWorkResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionDao collectionDao;

    @Path("/bookList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            CollectionVO collectionVO = new CollectionVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                collectionVO = (CollectionVO)JSONUtil.json2Object(jsonStr,CollectionVO.class);
            }
            if(userType!=null&&userType==0){
                Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
                collectionVO.setReaderId(readerId);
            }

            PageHelper.startPage(collectionVO.getPageNum(),collectionVO.getPageSize());

            List<CollectionVO> collectionVOList = collectionDao.findList(collectionVO);

            PageInfo p = new PageInfo(collectionVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/{collectFrom}/getInfo/{bookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R getInfo(@PathParam("collectFrom") Long collectFrom,@PathParam("bookId") Long bookId){
        try {

            CollectionVO collectionVOObj = collectionDao.getInfo(collectFrom,bookId);

            return R.ok().put("collectionVOObj", collectionVOObj);
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
            CollectionVO collectionVO = new CollectionVO();
            if(StringUtils.isNotEmpty(data)){
                collectionVO = (CollectionVO)JSONUtil.json2Object(data,CollectionVO.class);
            }
            Long readerId = JwtUtil.getReaderIdByToken(authToken,jwtYmlConfig.getSecret());
            collectionVO.setReaderId(readerId);
            String res = collectionService.save(collectionVO);
            if("success".equals(res)){
                return R.ok();
            }else{
                return R.error(406,"已收藏过的户籍");
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{collectionIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("collectionIds") String collectionIds){
        try {
            collectionService.delete(collectionIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
