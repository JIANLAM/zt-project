package com.szcti.lcloud.lendback.service.impl;

import com.szcti.lcloud.lendback.entity.Test;
import com.szcti.lcloud.lendback.repository.TestDao;
import com.szcti.lcloud.lendback.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> findAll() {
        return testDao.findAll();
    }

}
