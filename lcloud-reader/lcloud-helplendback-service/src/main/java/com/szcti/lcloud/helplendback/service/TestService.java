package com.szcti.lcloud.helplendback.service;

import java.util.List;

import com.szcti.lcloud.helplendback.entity.Test;

/**
 *
 *
 * @author fengda
 * @email ${email}
 * @date 2018-05-04 17:03:30
 */
public interface TestService {
    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
}

