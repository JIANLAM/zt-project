package com.szcti.lcloud.purchase.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.Book;
import com.szcti.lcloud.purchase.entity.vo.BookVO;
import com.szcti.lcloud.purchase.service.BookService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Component
@Path("book")
public class BookResource {
    @Autowired
    BookService bookService;
    @Autowired
    private JwtYmlConfig jwtYmlConfig;
    /**
     * 本馆图书书列表
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            BookVO bookVO = new BookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                bookVO =(BookVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), BookVO.class);
            }
            bookVO.setLibraryId(libraryId);
            PageHelper.startPage(bookVO.getPageNum(), bookVO.getPageSize());
            List<BookVO> bookVOList = bookService.queryPage(bookVO);
            PageInfo p = new PageInfo(bookVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 信息
     */
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R info(@PathParam("id") Long id){
        Book book = bookService.selectByPrimaryKey(id);
        return R.ok().put("book", book);
    }
    /**
     * 修改
     */
    @Path("/edit/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R edit(@PathParam("id") Long id ,@QueryParam("jsonStr") String jsonStr) {
        Map<String, String> params = new HashMap<String, String>();
        List<HashMap<String,Object>> rulelist = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(StringUtils.isNotEmpty(jsonStr)){
                params = objectMapper.readValue(jsonStr, Map.class);
            }
            BookVO vo =new BookVO();
            vo.setId(id);
            List<BookVO> list = bookService.queryPage(vo);
            if(list!=null&&list.size()>0){
                return R.ok().put("BookVO",list.get(0));
            }
            return R.error();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 保存
     */
    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R save(String data){
        BookVO bookVO = new BookVO();
        Book book = new Book();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                bookVO =(BookVO)JSONObject.toBean(JSONObject.fromObject(data), BookVO.class);
                bookService.getEntity(book, bookVO);
            }
            if(book.getTitle()!=null&&!book.getTitle().equals(""))
            {
                count= bookService.insert(book);
            }
            if(count>0){
                return R.ok().put("count",count);
            }else{
                return R.error().put("errormsg","保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 修改
     */
    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R update(String data){
        BookVO bookVO = new BookVO();
        Book book = new Book();
        int count=0;
        try {
            if(StringUtils.isNotEmpty(data)){
                bookVO =(BookVO)JSONObject.toBean(JSONObject.fromObject(data), BookVO.class);
                bookService.getEntity(book, bookVO);
            }
            if(book.getId()!=null&& book.getId()>0)
            {
                count= bookService.updateByPrimaryKey(book);
            }
            if(count>0){
                return R.ok().put("count",count);
            }else{
                return R.error().put("errormsg","保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 删除
     */
    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public R delete(String data){
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String[] array = ids.split(",");
            int count=0;
            for(String id :array){
                Long idl=Long.parseLong(id);
                if(idl>0) {
                    int c = bookService.deleteByPrimaryKey(Long.parseLong(id));
                    if(c>0){
                        count++;
                    }
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
    /**
     * 更改书类型//bookType
     */
    @Path("/setbooktype")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R setstatus(String data){
        Map<String, String> params = new HashMap<String, String>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            params = objectMapper.readValue(data, Map.class);
            String ids = params.get("ids");
            String bookType=params.get("bookType");
            String[] array = ids.split(",");
            int count=0;
            if(ids!=null&&!ids.equals("")&&bookType!=null&&!bookType.equals("")){
                for(String id :array){
                    Long idl=Long.parseLong(id);
                    if(idl>0) {
                        Book p =bookService.selectByPrimaryKey(idl);
                        p.setBookType(bookType);
                        int c =bookService.updateByPrimaryKey(p);
                        if(c>0){
                            count++;
                        }
                    }
                }
            }
            return R.ok().put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
