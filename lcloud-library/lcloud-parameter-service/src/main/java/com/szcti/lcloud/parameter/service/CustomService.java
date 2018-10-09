package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.CustomTypeEntity;
import com.szcti.lcloud.parameter.entity.vo.CustomVO;

/**
 * @Title: 自定义字段设置 Service
 * @Description: 自定义字段设置 的Service
 * @author: wangsiyi
 * @date: 2018/7/31 17:02
 */
public interface CustomService {
    /**
     * 根据主键删除一条 或多条 自定义字段设置记录
     * @param ids
     */
    void delCustom(String ids);

    /**
     * 新增   或修改 自定义字段设置 数据记录
     * @param   customVO
     * @return Integer
     */
    Integer save(CustomVO customVO);

    /**
     * 新增   或修改 自定义字段四五六    设置 数据记录
     * @param   customTypeEntity
     * @return Integer
     */
    void saveSetup(CustomTypeEntity customTypeEntity);
}
