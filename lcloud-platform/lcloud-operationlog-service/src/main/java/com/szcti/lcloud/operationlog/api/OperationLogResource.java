package com.szcti.lcloud.operationlog.api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.operationlog.config.JwtYmlConfig;
import com.szcti.lcloud.operationlog.entity.vo.OperationLogVO;
import com.szcti.lcloud.operationlog.service.OperationLogService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Component
@Path("operationlog")
public class OperationLogResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    @Autowired
    private OperationLogService operationLogService;
    /**
     * 列表 data为operationLogVO的json格式
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            Integer type=JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            OperationLogVO operationLogVO = new OperationLogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                operationLogVO =(OperationLogVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), OperationLogVO.class);
            }if(operationLogVO.getLibraryId()==null||(operationLogVO.getLibraryId()>0)){
                if(type!=null&&type==1) {
                    operationLogVO.setLibraryId(libraryId);
                }
            }
            PageHelper.startPage(operationLogVO.getPageNum(), operationLogVO.getPageSize());
            List<OperationLogVO> operationLogVOList = operationLogService.queryPage(operationLogVO);
            PageInfo p = new PageInfo(operationLogVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
