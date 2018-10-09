package com.szcti.lcloud.grade.service;

import com.szcti.lcloud.grade.entity.ClassesEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;

/**
 * @Title: 班级管理Service
 * @Description: 班级管理Service
 * @author: fengda
 * @date: 2018/7/31 8:50
 */
public interface ClassesService {

    /**
     * 删除班级信息
     * @param id
     */
    void delete(Long id);

    /**
     * 新增或修改班级信息
     * @param classes
     */
    boolean save(ClassesEntity classes);


}
