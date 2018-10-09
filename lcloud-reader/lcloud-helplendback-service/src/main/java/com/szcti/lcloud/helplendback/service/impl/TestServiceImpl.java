package com.szcti.lcloud.helplendback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szcti.lcloud.helplendback.entity.Test;
import com.szcti.lcloud.helplendback.repository.TestDao;
import com.szcti.lcloud.helplendback.service.TestService;


@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> findAll() {
        return testDao.findAll();
    }

}
