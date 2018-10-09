package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.SysMarc;
import com.szcti.lcloud.catalog.entity.SysMarcCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysMarcRepository {

    int deleteByPrimaryKey(Long id);

    int insert(SysMarc record);

    List<SysMarc> selectByExample(SysMarcCriteria example);

    SysMarc selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysMarc record);
}