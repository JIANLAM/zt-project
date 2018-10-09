package com.szcti.lcloud.catalog.api;

import com.szcti.lcloud.catalog.config.JwtYmlConfig;
import com.szcti.lcloud.catalog.entity.CatalogDefaultparam;
import com.szcti.lcloud.catalog.repository.CatalogDefaultparamDao;
import com.szcti.lcloud.catalog.service.CatalogDefaultparamService;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @Title: 普通编目  默认参数设置 API
 * @Description: 普通编目  默认参数设置 API
 * @author: wangsiyi
 * @date: 2018/9/3 03:24
 */
@Component
@Path("defaultparam")
public class CatalogDefaultparamResource {

    @Autowired
    private JwtYmlConfig jwtymlconfig;

    @Autowired
    private CatalogDefaultparamDao catalogDefaultparamDao;

    @Autowired
    private CatalogDefaultparamService catalogDefaultparamService;

    /**
     * 查询 普通编目  默认参数设置
     * @param
     * @return catalogDefaultparam
     */
    @Path("/info")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R getBarCode(@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CatalogDefaultparam catalogDefaultparam = catalogDefaultparamDao.get(libraryId);
            return R.ok().put("catalogDefaultparam", catalogDefaultparam);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 新增   或修改 普通编目  默认参数设置
     * @param   data
     * @return
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data,@HeaderParam("Authorization") String authToken){
        try {
            Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtymlconfig.getSecret());
            Long userid=JwtUtil.getUserIdByToken(authToken, jwtymlconfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            CatalogDefaultparam catalogDefaultparam = new CatalogDefaultparam();
            if (StringUtils.isNotEmpty(data)) {
                catalogDefaultparam = (CatalogDefaultparam) JSONUtil.json2Object(data, CatalogDefaultparam.class);
            }
            catalogDefaultparam.setCreateBy(userid);   //创建者id
            catalogDefaultparam.setLibraryId(libraryId);        //图书馆ID
            catalogDefaultparamService.save(catalogDefaultparam);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }


}
