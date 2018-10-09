package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.MarcTemplateDetail;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetailCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MarcTemplateDetailRepository {
    int deleteByPrimaryKey(Long id);
    int insert(MarcTemplateDetail record);
    List<MarcTemplateDetail> selectByExample(MarcTemplateDetailCriteria example);
    MarcTemplateDetail selectByPrimaryKey(Long id);
    int updateByPrimaryKey(MarcTemplateDetail record);
}