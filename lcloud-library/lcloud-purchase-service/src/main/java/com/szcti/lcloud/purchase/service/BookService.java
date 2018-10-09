package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.Book;
import com.szcti.lcloud.purchase.entity.BookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookVO;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;

import java.util.List;

public interface BookService
{
    int deleteByPrimaryKey(Long id);
    
    int insert(Book record);
    
    Book selectByPrimaryKey(Long id);
    
    List<Book> selectByCriteria(BookCriteria criteria);
    
    int updateByCriteriaSelective(Book record, BookCriteria criteria);
    
    int updateByPrimaryKeySelective(Book record);
    
    int updateByPrimaryKey(Book record);
    
    List<BookVO> queryPage(BookVO bookVO);
    
    void getVO(BookVO vo, Book o);
    
    void getEntity(Book o, BookVO vo);
    
    /**
     * 
     * @描述：中央库查询
     * @作者：tianbw
     * @时间：2018年9月3日 下午6:54:22
     * @param vo
     * @return
     */
    List<PrebookVO> getPageBook(PrebookVO vo);
    
    /**
     * 
     * @描述：采访库查询
     * @作者：tianbw
     * @时间：2018年9月3日 下午6:54:22
     * @param vo
     * @return
     */
    List<PrebookVO> queryPagePreBook(PrebookVO vo);
    
    /**
     * 
     * @描述：新华库查询
     * @作者：tianbw
     * @时间：2018年9月3日 下午6:54:22
     * @param vo
     * @return
     */
    List<PrebookVO> queryPageBookXH(PrebookVO vo);
}
