package com.szcti.lcloud.account.service;

import com.szcti.lcloud.account.entity.SendeeEntity;


/**
 * @Title: 收件人Service
 * @Description: 处理收件人信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface SendeeService {


    /**
     * 修改收件人信息
     * @param sendeeEntity
     */
    void save(SendeeEntity sendeeEntity);

    /**
     * 修改默认地址       为默认  is_default = 1
     * @param sendeeId
     */
    void setDefault(Long sendeeId,Long peopleId);
}
