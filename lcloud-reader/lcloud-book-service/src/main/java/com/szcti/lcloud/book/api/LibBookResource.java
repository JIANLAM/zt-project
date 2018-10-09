package com.szcti.lcloud.book.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.book.entity.vo.BookCopyVO;
import com.szcti.lcloud.book.entity.vo.BookVO;
import com.szcti.lcloud.book.repository.LibBookDao;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.book.service.LibBookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Title: 查询图书馆图书信息的API
 * @Description: 查询图书馆图书信息的API
 * @author: fengda
 * @date: 2018/5/30 9:02
 */
@Component
@Path("libBook")
public class LibBookResource {

    @Autowired
    private LibBookService libBookService;

    @Autowired
    private LibBookDao libBookDao;

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

            List<BookVO> bookVOList = libBookDao.findList(bookVO);

            PageInfo p = new PageInfo(bookVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/info/{bookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public R info(@PathParam("bookId") Long bookId){
        try {
            BookVO bookVO = libBookDao.get(bookId);
            //bookForm = 0 代表本馆
            bookVO.setBookFrom(0);
            return R.ok().put("bookVO", bookVO);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @Path("/bookCopy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R bookCopy(@QueryParam("jsonStr") String jsonStr){
        try {
            BookCopyVO bookCopyVO = new BookCopyVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bookCopyVO = (BookCopyVO)JSONUtil.json2Object(jsonStr,BookCopyVO.class);
            }

            PageHelper.startPage(bookCopyVO.getPageNum(),bookCopyVO.getPageSize());

            List<BookCopyVO> bookCopyVOList = libBookDao.findCopys(bookCopyVO.getBookId());

            PageInfo p = new PageInfo(bookCopyVOList);

            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查询本馆图书详情时查询  可借阅 可预览 已借出
     */
    @Path("/{status}/getBookInfoTable/{bookId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R getBookInfoTable(@PathParam("status") Long status,@PathParam("bookId") Long bookId){
        try {
            List<BookCopyVO> bookCopyVOList = libBookDao.getBookInfoTable(status,bookId);

            return R.ok().put("bookCopyVOList", bookCopyVOList);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
