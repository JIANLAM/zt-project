package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.Publisher;
import com.szcti.lcloud.parameter.entity.PublisherCriteria;
import com.szcti.lcloud.parameter.entity.vo.PublisherVO;

import java.util.List;

public interface PublisherService {
    int countByExample(PublisherCriteria example);

    int deleteByExample(PublisherCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Publisher record);

    int insertSelective(Publisher record);

    List<Publisher> selectByExample(PublisherCriteria example);

    Publisher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(Publisher record,PublisherCriteria example);

    int updateByExample(Publisher record,PublisherCriteria example);

    int updateByPrimaryKeySelective(Publisher record);

    int updateByPrimaryKey(Publisher record);

    List<PublisherVO> queryPage(PublisherVO publisherVO);
}