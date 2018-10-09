package com.szcti.lcloud.book.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.book.entity.vo.BookVO;
import com.szcti.lcloud.book.repository.ForeighBookDao;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 查询新华或其他途径图书信息的API
 * @Description: 查询新华或其他途径图书信息的API
 * @author: fengda
 * @date: 2018/5/30 9:02
 */
@Component
@Path("foreighBook")
public class ForeighBookResource {


    @Autowired
    private ForeighBookDao foreighBookDao;

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr){
        try {
            BookVO bookVO = new BookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bookVO = (BookVO)JSONUtil.json2Object(jsonStr,BookVO.class);
            }
            PageHelper.startPage(bookVO.getPageNum(),bookVO.getPageSize());
            List<BookVO> bookVOList = foreighBookDao.findList(bookVO);
            PageInfo p = new PageInfo(bookVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{preBookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R get(@PathParam("preBookId") Long preBookId){
        try {
            BookVO bookVO = foreighBookDao.get(preBookId);
            //bookForm = 1 代表新华
            bookVO.setBookFrom(1);
            return R.ok().put("bookVO", bookVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
