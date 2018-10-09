package com.szcti.lcloud.grade.service;

import com.szcti.lcloud.grade.entity.DeptEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;

/**
 * @Title: 部门管理Service
 * @Description: 部门管理Service
 * @author: fengda
 * @date: 2018/8/29 8:50
 */
public interface DeptService {

    /**
     * 删除部门信息
     * @param id
     */
    void delete(Long id);

    /**
     * 新增或修改部门信息
     * @param dept
     * @return
     */
    boolean save(DeptEntity dept);

}
