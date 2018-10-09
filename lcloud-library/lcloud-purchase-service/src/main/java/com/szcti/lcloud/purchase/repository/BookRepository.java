package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Book;
import com.szcti.lcloud.purchase.entity.BookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookVO;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface BookRepository {
    int countByExample(BookCriteria example);

    int deleteByExample(BookCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExampleWithBLOBs(BookCriteria example);

    List<Book> selectByExample(BookCriteria example);

    Book selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Book record, @Param("example") BookCriteria example);

    int updateByExample(@Param("record") Book record, @Param("example") BookCriteria example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKeyWithBLOBs(Book record);

    int updateByPrimaryKey(Book record);
    //扩展方法
    List<BookVO> queryPage(BookVO vo);

    List<PrebookVO> getPageBook(PrebookVO bvo);

    List<PrebookVO> queryPagePreBook(PrebookVO bvo);

    List<PrebookVO> queryPageBookXH(PrebookVO bvo);
}