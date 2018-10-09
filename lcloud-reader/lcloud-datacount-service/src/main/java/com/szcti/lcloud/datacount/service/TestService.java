package com.szcti.lcloud.datacount.service;

import com.baomidou.mybatisplus.service.IService;
import com.szcti.lcloud.datacount.entity.Test;

import java.util.List;

/**
 *
 *
 * @author wangsiyi
 * @email
 * @date 2018/7/16 1:41
 */
public interface TestService extends IService<Test> {
    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
}

