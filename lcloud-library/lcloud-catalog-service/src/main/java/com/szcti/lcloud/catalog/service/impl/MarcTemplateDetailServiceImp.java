package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.MarcTemplateDetail;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetailCriteria;
import com.szcti.lcloud.catalog.repository.MarcTemplateDetailRepository;
import com.szcti.lcloud.catalog.service.MarcTemplateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("marcTemplateDetailService")
public class MarcTemplateDetailServiceImp implements MarcTemplateDetailService {
    @Autowired
    MarcTemplateDetailRepository marcTemplateDetailRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return marcTemplateDetailRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MarcTemplateDetail record) {
        return marcTemplateDetailRepository.insert(record);
    }

    @Override
    public List<MarcTemplateDetail> selectByExample(MarcTemplateDetailCriteria example) {
        return marcTemplateDetailRepository.selectByExample(example);
    }

    @Override
    public MarcTemplateDetail selectByPrimaryKey(Long id) {
        return marcTemplateDetailRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(MarcTemplateDetail record) {
        return marcTemplateDetailRepository.updateByPrimaryKey(record);
    }
}