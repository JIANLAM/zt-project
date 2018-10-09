package com.szcti.lcloud.catalog.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.catalog.config.JwtYmlConfig;
import com.szcti.lcloud.catalog.entity.vo.AcceptanceDetailVO;
import com.szcti.lcloud.catalog.service.AcceptanceDetailService;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("acceptancedetail")
public class AcceptanceDetailResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    @Autowired
    private AcceptanceDetailService acceptanceDetailService;

    /**
     * 验收编目 查询
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken){
        try {
            //Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            if(libraryId==null||!(libraryId>0)){
                return R.error().put("msg", "无权操作！请联系管理员。");
            }
            AcceptanceDetailVO vo = new AcceptanceDetailVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                vo =(AcceptanceDetailVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), AcceptanceDetailVO.class);
            }if(vo.getLibraryId()==null||(vo.getLibraryId()>0)){
                vo.setLibraryId(libraryId);
            }
            PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
            List<AcceptanceDetailVO> AcceptanceDetailVOList = acceptanceDetailService.queryPage(vo);
            PageInfo p = new PageInfo(AcceptanceDetailVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
