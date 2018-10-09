package com.szcti.lcloud.dictionary.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.dictionary.config.JwtYmlConfig;
import com.szcti.lcloud.dictionary.entity.DictionaryEntity;
import com.szcti.lcloud.dictionary.repository.DictionaryDao;
import com.szcti.lcloud.dictionary.service.DictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * @Title: 数据字典 API
 * @Description: 数据字典 API
 * @author: wangsiyi
 * @date: 2018/7/30 10:26
 */
@Component
@Path("dict")
public class DictionaryResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查询数据字典   类型List集合
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/dicTypeList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R dicTypeList(){
        try {
            List<String> typeList = dictionaryDao.findTypeList();

            return R.ok().put("typeList", typeList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询指定字典项
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/label/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R dicLabel(@PathParam("type") String type){
        try {
            DictionaryEntity dictionaryEntity = new DictionaryEntity();
            dictionaryEntity.setType(type);
            List<DictionaryEntity> dictionaryEntityList = dictionaryDao.findList(dictionaryEntity);
            return R.ok().put("dic", dictionaryEntityList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


    /**
     * 查询数据字典List集合
     * @param
     * @return List<DictionaryEntity>
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            DictionaryEntity dictionaryEntity = new DictionaryEntity();
            if(StringUtils.isNotEmpty(jsonStr)){
                dictionaryEntity = (DictionaryEntity) JSONUtil.json2Object(jsonStr,DictionaryEntity.class);
            }
            PageHelper.startPage(dictionaryEntity.getPageNum(),dictionaryEntity.getPageSize());

            List<DictionaryEntity> dictionaryEntityList = dictionaryDao.findList(dictionaryEntity);

            PageInfo p = new PageInfo(dictionaryEntityList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据ID 查询一条数据字典 记录
     * @param   dicId
     * @return DictionaryEntity
     */
    @Path("/info/{dicId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getTitleNumber(@PathParam("dicId") Long dicId){
        try {
            DictionaryEntity dictionaryEntity = dictionaryDao.get(dicId);
            return R.ok().put("dictionaryEntity", dictionaryEntity);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 根据主键删除一条 或多条 字典数据记录
     * @param dicId
     */
    @Path("/delete/{dicId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R delTitleNumber(@PathParam("dicId") String dicId){
        try {
            dictionaryService.delDictionary(dicId);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 一条字典数据 数据记录
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data , @HeaderParam("Authorization") String authToken){
        try {
            Long userid= JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            DictionaryEntity dictionaryEntity = new DictionaryEntity();
            if (StringUtils.isNotEmpty(data)) {
                dictionaryEntity = (DictionaryEntity) JSONUtil.json2Object(data, DictionaryEntity.class);
            }
            dictionaryEntity.setCreateBy(userid);   //创建者id暂时为1  后期token获取
            Integer result = dictionaryService.save(dictionaryEntity);
            if(result == 0){
                return R.error(406, "代码已存在");
            }else if(result == 2){
                return R.error(407, "代码只能由数字或字母组成");
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询字典     用MAP分类 返回所有数据
     * @param
     * @return Map<String , Object>
     */
    @Path("/listMap")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R findListMap(){
        try {

            Map<String , Object> dictMapList = dictionaryService.findListMap();

            return R.ok().put("dictMapList" , dictMapList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }


}
