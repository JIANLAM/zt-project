package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.SysMarc;
import com.szcti.lcloud.catalog.entity.SysMarcCriteria;

import java.util.List;

public interface SysMarcService {

    int deleteByPrimaryKey(Long id);

    int insert(SysMarc record);

    List<SysMarc> selectByExample(SysMarcCriteria example);

    SysMarc selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysMarc record);
}