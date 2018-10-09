package com.szcti.lcloud.notice.service;

import com.baomidou.mybatisplus.service.IService;
import com.szcti.lcloud.notice.entity.Test;

import java.util.List;

/**
 *
 *
 * @author fengda
 * @email ${email}
 * @date 2018-05-04 17:03:30
 */
public interface TestService extends IService<Test> {
    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
}

