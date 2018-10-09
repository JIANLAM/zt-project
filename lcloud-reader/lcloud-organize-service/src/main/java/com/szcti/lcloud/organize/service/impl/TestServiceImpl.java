package com.szcti.lcloud.organize.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szcti.lcloud.organize.entity.Test;
import com.szcti.lcloud.organize.repository.TestDao;
import com.szcti.lcloud.organize.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("testService")
public class TestServiceImpl extends ServiceImpl<TestDao, Test> implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> findAll() {
        return testDao.findAll();
    }

}
