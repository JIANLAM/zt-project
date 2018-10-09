package com.szcti.lcloud.book.service;

import com.szcti.lcloud.book.entity.Test;

import java.util.List;

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

