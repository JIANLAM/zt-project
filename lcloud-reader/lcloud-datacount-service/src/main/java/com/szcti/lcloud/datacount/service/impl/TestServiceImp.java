package com.szcti.lcloud.datacount.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szcti.lcloud.datacount.entity.Test;
import com.szcti.lcloud.datacount.service.TestService;
import com.szcti.lcloud.datacount.repository.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("testService")
public class TestServiceImp extends ServiceImpl<TestDao, Test> implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> findAll() {
        return testDao.findAll();
    }

}
