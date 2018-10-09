package com.szcti.lcloud.organize.service;


import com.szcti.lcloud.organize.entity.OrgEntity;

import java.util.List;

/**
 * @Title: 组织机构Service
 * @Description: 处理组织机构相关信息的Service
 * @author: fengda
 * @date: 2018/6/6 8:50
 */
public interface OrgService {

    /**
     * 获取组织机构树形数据
     * @param org
     * @return
     */
    List orgTree(OrgEntity org);

    /**
     * 修改或保存一条组织机构信息
     * @param org
     */
    void save(OrgEntity org);

    /**
     * 删除一条组织机构以及该机构包含的所有字机构
     * @param orgId
     */
    void delete(Long orgId);

}
