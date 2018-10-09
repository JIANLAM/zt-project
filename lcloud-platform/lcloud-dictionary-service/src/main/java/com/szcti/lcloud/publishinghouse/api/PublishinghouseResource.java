package com.szcti.lcloud.publishinghouse.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.dictionary.config.JwtYmlConfig;
import com.szcti.lcloud.dictionary.entity.DictionaryEntity;
import com.szcti.lcloud.dictionary.repository.DictionaryDao;
import com.szcti.lcloud.dictionary.service.DictionaryService;
import com.szcti.lcloud.publishinghouse.entity.PublishinghouseEntity;
import com.szcti.lcloud.publishinghouse.repository.PublishinghouseDao;
import com.szcti.lcloud.publishinghouse.service.PublishinghouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: 出版社数据 API
 * @Description: 出版社数据 API
 * @author: fengda
 * @date: 2018/8/27 3:32
 */
@Component
@Path("pubhouse")
public class PublishinghouseResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private PublishinghouseDao publishinghouseDao;

    @Autowired
    private PublishinghouseService publishinghouseService;

    /**
     * 查询出版社数据List集合
     * @param jsonStr
     * @return List<PublishinghouseEntity>
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            PublishinghouseEntity publishinghouseEntity = new PublishinghouseEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                publishinghouseEntity = (PublishinghouseEntity) JSONUtil.json2Object(jsonStr,PublishinghouseEntity.class);
            }
            PageHelper.startPage(publishinghouseEntity.getPageNum(),publishinghouseEntity.getPageSize());

            List<PublishinghouseEntity> publishinghouseEntityList = publishinghouseDao.findList(publishinghouseEntity);

            PageInfo p = new PageInfo(publishinghouseEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条出版社数据 记录
     * @param   pubId
     * @return PublishinghouseEntity
     */
    @Path("/info/{pubId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R get(@PathParam("pubId") Long pubId){
        try {
            PublishinghouseEntity publishinghouseEntity = publishinghouseDao.get(pubId);
            return R.ok().put("publishinghouseEntity", publishinghouseEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条出版社数据 记录
     * @param   ISBN
     * @return
     */
    @Path("/info/isbn/{ISBN}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getByISBN(@PathParam("ISBN") String ISBN){
        try {
            HashMap<String,Object> map = publishinghouseService.getByISBN(ISBN);
            return R.ok().put("info", map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 出版社数据记录
     * @param pubIds
     */
    @Path("/delete/{pubIds}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("pubIds") String pubIds){
        try {
            publishinghouseService.delPublishing(pubIds);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条出版社 数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        try {
            PublishinghouseEntity publishinghouseEntity = new PublishinghouseEntity();
            if (StringUtils.isNotEmpty(data)) {
                publishinghouseEntity = (PublishinghouseEntity) JSONUtil.json2Object(data, PublishinghouseEntity.class);
            }
            Integer result = publishinghouseService.save(publishinghouseEntity);
            if(result == 0){
                return R.error(406, "出版社代码已存在");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
