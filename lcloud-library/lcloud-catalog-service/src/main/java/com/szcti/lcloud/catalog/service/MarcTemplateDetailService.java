package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.MarcTemplateDetail;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetailCriteria;

import java.util.List;

public interface MarcTemplateDetailService {
    int deleteByPrimaryKey(Long id);
    int insert(MarcTemplateDetail record);
    List<MarcTemplateDetail> selectByExample(MarcTemplateDetailCriteria example);
    MarcTemplateDetail selectByPrimaryKey(Long id);
    int updateByPrimaryKey(MarcTemplateDetail record);
}