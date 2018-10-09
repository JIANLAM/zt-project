package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.parameter.entity.BookSize;
import com.szcti.lcloud.parameter.entity.BookSizeCriteria;
import com.szcti.lcloud.parameter.entity.vo.BookSizeVO;
import com.szcti.lcloud.parameter.repository.BookSizeRepository;
import com.szcti.lcloud.parameter.service.BookSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("bookSizeService")
public class BookSizeServiceImp implements BookSizeService {
    @Autowired
    BookSizeRepository bookSizeRepository;

    @Override
    public int countByExample(BookSizeCriteria example) {
        return bookSizeRepository.countByExample(example);
    }

    @Override
    public int deleteByExample(BookSizeCriteria example) {
        return bookSizeRepository.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookSizeRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BookSize record) {
        return bookSizeRepository.insert(record);
    }

    @Override
    public int insertSelective(BookSize record) {
        return bookSizeRepository.insertSelective(record);
    }

    @Override
    public List<BookSize> selectByExample(BookSizeCriteria example) {
        return bookSizeRepository.selectByExample(example);
    }

    @Override
    public BookSize selectByPrimaryKey(Integer id) {
        return bookSizeRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(BookSize record, BookSizeCriteria example) {
        return bookSizeRepository.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(BookSize record, BookSizeCriteria example) {
        return bookSizeRepository.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(BookSize record) {
        return bookSizeRepository.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookSize record) {
        return bookSizeRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<BookSizeVO> queryPage(BookSizeVO bookSizeVO) {
        return bookSizeRepository.queryPage(bookSizeVO);
    }
}
