package com.szcti.lcloud.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.szcti.lcloud.common.utils.BeanUtil;
import com.szcti.lcloud.purchase.entity.Book;
import com.szcti.lcloud.purchase.entity.BookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookVO;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;
import com.szcti.lcloud.purchase.repository.BookRepository;
import com.szcti.lcloud.purchase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("bookService")
public class BookServiceImp implements BookService
{
    @Autowired
    BookRepository bookRepository;
    
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return bookRepository.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(Book record)
    {
        return bookRepository.insert(record);
    }
    
    @Override
    public Book selectByPrimaryKey(Long id)
    {
        return bookRepository.selectByPrimaryKey(id);
    }
    
    @Override
    public List<Book> selectByCriteria(BookCriteria criteria)
    {
        return bookRepository.selectByExample(criteria);
    }
    
    @Override
    public int updateByCriteriaSelective(Book record, BookCriteria criteria)
    {
        return bookRepository.updateByExampleSelective(record, criteria);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Book record)
    {
        return bookRepository.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(Book record)
    {
        return bookRepository.updateByPrimaryKey(record);
    }
    
    @Override
    public List<BookVO> queryPage(BookVO bookVO)
    {
        return bookRepository.queryPage(bookVO);
    }
    
    @Override
    public void getVO(BookVO vo, Book o)
    {
        BeanUtil.transMap2Bean(BeanUtil.transBean2Map(o), vo);
    }
    
    @Override
    public void getEntity(Book o, BookVO vo)
    {
        
        BeanUtil.transMap2Bean(BeanUtil.transBean2Map(vo), o);
    }
    
    @Override
    public List<PrebookVO> getPageBook(PrebookVO vo)
    {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize(), true);
        PrebookVO bvo = new PrebookVO();
        bvo.setId(vo.getId());
        bvo.setIsbn(vo.getIsbn());
        bvo.setTitle(vo.getTitle());
        bvo.setAuthor(vo.getAuthor());
        bvo.setBookType(vo.getBookType());
        return bookRepository.getPageBook(bvo);
    }
    
    @Override
    public List<PrebookVO> queryPagePreBook(PrebookVO vo)
    {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize(), true);
        PrebookVO bvo = new PrebookVO();
        bvo.setId(vo.getId());
        bvo.setIsbn(vo.getIsbn());
        bvo.setTitle(vo.getTitle());
        bvo.setAuthor(vo.getAuthor());
        bvo.setBookType(vo.getBookType());
        return bookRepository.queryPagePreBook(bvo);
    }
    
    @Override
    public List<PrebookVO> queryPageBookXH(PrebookVO vo)
    {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize(), true);
        PrebookVO bvo = new PrebookVO();
        bvo.setId(vo.getId());
        bvo.setIsbn(vo.getIsbn());
        bvo.setTitle(vo.getTitle());
        bvo.setAuthor(vo.getAuthor());
        bvo.setBookType(vo.getBookType());
        return bookRepository.queryPageBookXH(bvo);
    }
}
