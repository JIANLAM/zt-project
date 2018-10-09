package com.szcti.lcloud.holding.service.impl;

import com.szcti.lcloud.holding.entity.Test;
import com.szcti.lcloud.holding.service.TestService;
import com.szcti.lcloud.holding.repository.TestDao;
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
