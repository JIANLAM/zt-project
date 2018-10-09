package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.SysMarc;
import com.szcti.lcloud.catalog.entity.SysMarcCriteria;
import com.szcti.lcloud.catalog.repository.SysMarcRepository;
import com.szcti.lcloud.catalog.service.SysMarcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("sysMarcService")
public class SysMarcServiceImp implements SysMarcService {
    @Autowired
    SysMarcRepository sysMarcRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return sysMarcRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMarc record) {
        return sysMarcRepository.insert(record);
    }

    @Override
    public List<SysMarc> selectByExample(SysMarcCriteria example) {
        return sysMarcRepository.selectByExample(example);
    }

    @Override
    public SysMarc selectByPrimaryKey(Long id) {
        return sysMarcRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(SysMarc record) {
        return sysMarcRepository.updateByPrimaryKey(record);
    }
}