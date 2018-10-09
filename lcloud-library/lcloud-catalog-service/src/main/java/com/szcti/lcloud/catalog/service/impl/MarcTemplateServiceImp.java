package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.MarcTemplate;
import com.szcti.lcloud.catalog.entity.MarcTemplateCriteria;
import com.szcti.lcloud.catalog.repository.MarcTemplateRepository;
import com.szcti.lcloud.catalog.service.MarcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("marcTemplateService")
public class MarcTemplateServiceImp implements MarcTemplateService {
    @Autowired
    MarcTemplateRepository marcTemplateRepository;
    @Override
    public int deleteByExample(MarcTemplateCriteria example) {
        return marcTemplateRepository.deleteByExample(example);
    }

    @Override
    public int insert(MarcTemplate record) {
        return marcTemplateRepository.insert(record);
    }

    @Override
    public List<MarcTemplate> selectByExample(MarcTemplateCriteria example) {
        return marcTemplateRepository.selectByExample(example);
    }

    @Override
    public int updateByExample(MarcTemplate record, MarcTemplateCriteria example) {
        return marcTemplateRepository.updateByExample(record,example);
    }
}