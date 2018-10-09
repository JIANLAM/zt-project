package com.szcti.lcloud.dataquery.service.impl;

import com.szcti.lcloud.dataquery.entity.Test;
import com.szcti.lcloud.dataquery.repository.TestDao;
import com.szcti.lcloud.dataquery.service.TestService;
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
