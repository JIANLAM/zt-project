package com.szcti.lcloud.account.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szcti.lcloud.account.entity.Test;
import com.szcti.lcloud.account.repository.TestDao;
import com.szcti.lcloud.account.service.TestService;
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
