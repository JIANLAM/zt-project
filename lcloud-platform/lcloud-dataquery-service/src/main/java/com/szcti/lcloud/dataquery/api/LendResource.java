package com.szcti.lcloud.dataquery.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.dataquery.config.JwtYmlConfig;
import com.szcti.lcloud.dataquery.entity.vo.BookVO;
import com.szcti.lcloud.dataquery.entity.vo.HoldingVO;
import com.szcti.lcloud.dataquery.entity.vo.LendRecordVO;
import com.szcti.lcloud.dataquery.repository.LendDao;
import com.szcti.lcloud.dataquery.service.LendService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: 借还信息
 * @Description: 借还信息
 * @author: fengda
 * @date: 2018/8/6 9:02
 */
@Component
@Path("lend")
public class LendResource {
    @Autowired
    private JwtYmlConfig jwtYmlConfig;

    @Autowired
    private LendService lendService;

    @Autowired
    private LendDao lendDao;

    @Path("/books")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            BookVO book = new BookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                book = (BookVO)JSONUtil.json2Object(jsonStr,BookVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                book.setLibId(libId);
            }

            PageHelper.startPage(book.getPageNum(),book.getPageSize());

            List<BookVO> bookList = lendService.findBookList(book);

            PageInfo p = new PageInfo(bookList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/books/holding")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R holdingList(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            HoldingVO holding = new HoldingVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                holding = (HoldingVO)JSONUtil.json2Object(jsonStr,HoldingVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                holding.setLibId(libId);
            }

            PageHelper.startPage(holding.getPageNum(),holding.getPageSize());

            List<HoldingVO> holdingList = lendDao.findHoldingList(holding);

            PageInfo p = new PageInfo(holdingList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/books/holding/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportHoldingList(@Context HttpServletRequest request, @QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            HoldingVO holding = new HoldingVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                holding = (HoldingVO)JSONUtil.json2Object(jsonStr,HoldingVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                holding.setLibId(libId);
            }
            String fileName = lendService.exportHoldingExcel(holding);
            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/books/record")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R lendRecord(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            LendRecordVO record = new LendRecordVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                record = (LendRecordVO)JSONUtil.json2Object(jsonStr,LendRecordVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                record.setLibId(libId);
            }

            PageHelper.startPage(record.getPageNum(),record.getPageSize());

            List<LendRecordVO> recordList = lendDao.findRecordList(record);

            PageInfo p = new PageInfo(recordList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/books/record/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportLendRecord(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            LendRecordVO record = new LendRecordVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                record = (LendRecordVO)JSONUtil.json2Object(jsonStr,LendRecordVO.class);
            }
            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                record.setLibId(libId);
            }

            String fileName = lendService.exportRecordExcel(record);

            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/reader/holding")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R readerLend(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());

            HoldingVO holding = new HoldingVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                holding = (HoldingVO)JSONUtil.json2Object(jsonStr,HoldingVO.class);
            }

            if(StringUtils.isEmpty(holding.getReaderCardNumber())&&StringUtils.isEmpty(holding.getCardNumber())){
                return R.error(406,"请输入读者信息查询");
            }
            HashMap map = lendDao.findReader(holding.getReaderCardNumber(),holding.getCardNumber());

            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                holding.setLibId(libId);
            }

            PageHelper.startPage(holding.getPageNum(),holding.getPageSize());

            List<HoldingVO> holdingList = lendDao.findHoldingByReader(holding);

            PageInfo p = new PageInfo(holdingList);

            return R.ok().put("reader",map).put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }

    @Path("/reader/holding/export")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R exportReaderLend(@Context HttpServletRequest request,@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Integer userType = JwtUtil.getUserTypeByToken(authToken,jwtYmlConfig.getSecret());
            String serverUrl = "http://"+request.getServerName()+":"+new POITool().getExportPort();
            HoldingVO holding = new HoldingVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                holding = (HoldingVO)JSONUtil.json2Object(jsonStr,HoldingVO.class);
            }

            if(StringUtils.isEmpty(holding.getReaderCardNumber())&&StringUtils.isEmpty(holding.getCardNumber())){
                return R.error(406,"请输入读者信息查询");
            }

            if(userType!=null&&userType==1){
                Long libId = JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
                holding.setLibId(libId);
            }

            String fileName = lendService.exportReaderLendExcel(holding);

            String xlsurl=serverUrl+fileName;
            return R.ok().put("xlsurl", xlsurl);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

    }
    
}
