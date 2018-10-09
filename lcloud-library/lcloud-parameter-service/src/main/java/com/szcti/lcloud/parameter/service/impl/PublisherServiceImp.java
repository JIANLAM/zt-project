package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.parameter.entity.Publisher;
import com.szcti.lcloud.parameter.entity.PublisherCriteria;
import com.szcti.lcloud.parameter.entity.vo.PublisherVO;
import com.szcti.lcloud.parameter.repository.PublisherRepository;
import com.szcti.lcloud.parameter.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("publisherService")
public class PublisherServiceImp implements PublisherService {
    @Autowired
    PublisherRepository publisherRepository;
    @Override
    public int countByExample(PublisherCriteria example) {
        return publisherRepository.countByExample( example);
    }

    @Override
    public int deleteByExample(PublisherCriteria example) {
        return publisherRepository.deleteByExample( example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return publisherRepository.deleteByPrimaryKey( id);
    }

    @Override
    public int insert(Publisher record) {
        return publisherRepository.insert( record);
    }

    @Override
    public int insertSelective(Publisher record) {
        return publisherRepository.insertSelective( record);
    }

    @Override
    public List<Publisher> selectByExample(PublisherCriteria example) {
        return publisherRepository.selectByExample( example);
    }

    @Override
    public Publisher selectByPrimaryKey(Integer id) {
        return publisherRepository.selectByPrimaryKey( id);
    }

    @Override
    public int updateByExampleSelective(Publisher record, PublisherCriteria example) {
        return publisherRepository.updateByExampleSelective( record,  example);
    }

    @Override
    public int updateByExample(Publisher record, PublisherCriteria example) {
        return publisherRepository.updateByExample( record,  example);
    }

    @Override
    public int updateByPrimaryKeySelective(Publisher record) {
        return publisherRepository.updateByPrimaryKeySelective( record);
    }

    @Override
    public int updateByPrimaryKey(Publisher record) {
        return publisherRepository.updateByPrimaryKey( record);
    }

    @Override
    public List<PublisherVO> queryPage(PublisherVO publisherVO) {
        return publisherRepository.queryPage(publisherVO);
    }
}
