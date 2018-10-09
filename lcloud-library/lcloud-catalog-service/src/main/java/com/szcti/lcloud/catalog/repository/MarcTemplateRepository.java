package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.MarcTemplate;
import com.szcti.lcloud.catalog.entity.MarcTemplateCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MarcTemplateRepository {
    int deleteByExample(MarcTemplateCriteria example);
    int insert(MarcTemplate record);
    List<MarcTemplate> selectByExample(MarcTemplateCriteria example);
    int updateByExample(@Param("record") MarcTemplate record, @Param("example") MarcTemplateCriteria example);
}