package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.MarcTemplate;
import com.szcti.lcloud.catalog.entity.MarcTemplateCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarcTemplateService {
    int deleteByExample(MarcTemplateCriteria example);
    int insert(MarcTemplate record);
    List<MarcTemplate> selectByExample(MarcTemplateCriteria example);
    int updateByExample(@Param("record") MarcTemplate record, @Param("example") MarcTemplateCriteria example);
}