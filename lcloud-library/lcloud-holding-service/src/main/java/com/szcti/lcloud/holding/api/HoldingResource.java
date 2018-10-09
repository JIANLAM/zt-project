package com.szcti.lcloud.holding.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.HttpServletRequestUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.holding.config.JwtYmlConfig;
import com.szcti.lcloud.holding.entity.vo.BookCopyVO;
import com.szcti.lcloud.holding.entity.vo.OpLogVO;
import com.szcti.lcloud.holding.repository.HoldingDao;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.holding.repository.OpLogDao;
import com.szcti.lcloud.holding.service.HoldingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 馆藏管理API
 * @Description: 馆藏管理API
 * @author: fengda
 * @date: 2018/7/26 9:02
 */
@Component
@Path("manage")
public class HoldingResource {

    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private HoldingService holdingService;

    @Autowired
    private HoldingDao holdingDao;

    @Autowired
    private OpLogDao opLogDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            BookCopyVO bookCopyVO = new BookCopyVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bookCopyVO = (BookCopyVO)JSONUtil.json2Object(jsonStr,BookCopyVO.class);
            }
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            bookCopyVO.setLibId(libId);

            PageHelper.startPage(bookCopyVO.getPageNum(),bookCopyVO.getPageSize());

            List<BookCopyVO> bookCopyVOList = holdingDao.findList(bookCopyVO);

            PageInfo p = new PageInfo(bookCopyVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/log/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R logList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            OpLogVO opLogVO = new OpLogVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                opLogVO = (OpLogVO)JSONUtil.json2Object(jsonStr,OpLogVO.class);
            }

            opLogVO.setModuleId(8);
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            opLogVO.setLibraryId(libId);

            PageHelper.startPage(opLogVO.getPageNum(),opLogVO.getPageSize());

            List<OpLogVO> opLogVOList = opLogDao.findList(opLogVO);

            PageInfo p = new PageInfo(opLogVOList);

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
            BookCopyVO bookCopyVO = holdingService.get(id);
            return R.ok().put("bookCopy", bookCopyVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/delete/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R delete(@PathParam("ids") String ids,@Context HttpServletRequest request,@HeaderParam("Authorization") String authToken){
        try {
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Integer count = holdingService.delete(ids,userId,libId,ip);
            return R.ok().put("count",count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/update/{ids}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(String data, @PathParam("ids") String ids, @Context HttpServletRequest request, @HeaderParam("Authorization") String authToken){
        try {
            BookCopyVO bookCopyVO = new BookCopyVO();
            if(StringUtils.isNotEmpty(data)){
                bookCopyVO = (BookCopyVO)JSONUtil.json2Object(data,BookCopyVO.class);
            }
            Long userId = JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            String ip = HttpServletRequestUtil.getIpAddr(request);
            Integer count = holdingService.update(bookCopyVO,ids,userId,libId,ip);
            return R.ok().put("count",count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
