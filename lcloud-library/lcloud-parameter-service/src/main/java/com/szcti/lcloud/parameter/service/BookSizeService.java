package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.BookSize;
import com.szcti.lcloud.parameter.entity.BookSizeCriteria;
import com.szcti.lcloud.parameter.entity.vo.BookSizeVO;

import java.util.List;

public interface BookSizeService {
    int countByExample(BookSizeCriteria example);

    int deleteByExample(BookSizeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookSize record);

    int insertSelective(BookSize record);

    List<BookSize> selectByExample(BookSizeCriteria example);

    BookSize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(BookSize record,BookSizeCriteria example);

    int updateByExample(BookSize record, BookSizeCriteria example);

    int updateByPrimaryKeySelective(BookSize record);

    int updateByPrimaryKey(BookSize record);

    List<BookSizeVO> queryPage(BookSizeVO bookSizeVO);
}