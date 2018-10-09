package com.szcti.lcloud.grade.service;

import com.szcti.lcloud.grade.entity.GradeEntity;

/**
 * @Title: 年级管理Service
 * @Description: 年级管理Service
 * @author: fengda
 * @date: 2018/7/26 8:50
 */
public interface GradeService {

    /**
     * 删除年级信息
     * @param id
     */
    void delete(Long id);

    /**
     * 新增或修改年级信息
     * @param grade
     * @return
     */
    boolean save(GradeEntity grade);

    /**
     * 年级升级
     * @param id
     * @return String
     */
    String upgrade(Long id);

    /**
     * 毕业
     * @param id
     * @return boolean
     */
    boolean graduate(Long id);


}
