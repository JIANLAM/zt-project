package com.szcti.lcloud.book.service.impl;

import com.szcti.lcloud.book.entity.Test;
import com.szcti.lcloud.book.service.TestService;
import com.szcti.lcloud.book.repository.TestDao;
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
