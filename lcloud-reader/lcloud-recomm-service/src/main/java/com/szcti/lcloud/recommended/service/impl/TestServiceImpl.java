package com.szcti.lcloud.recommended.service.impl;

import com.szcti.lcloud.recommended.entity.Test;
import com.szcti.lcloud.recommended.service.TestService;
import com.szcti.lcloud.recommended.repository.TestDao;
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
