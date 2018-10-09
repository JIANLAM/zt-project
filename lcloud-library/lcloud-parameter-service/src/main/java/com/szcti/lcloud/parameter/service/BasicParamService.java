package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;

/**
 * @Title: 自定义字段设置 Service
 * @Description: 自定义字段设置 的Service
 * @author: wangsiyi
 * @date: 2018/7/31 17:02
 */
public interface BasicParamService {
    /**
     * 根据主键删除一条 或多条 自定义字段设置记录
     * @param ids
     */
    void delCustom(String ids);

    /**
     * 新增   或修改 自定义字段设置 数据记录
     * @param   BasicParamEntity
     * @return Integer
     */
    Integer save(BasicParamEntity BasicParamEntity);

    /**************************************************        读者证类型         ***************************************************/

    /**
     * 删除 一条或多条 读者证类型
     * @param ids
     */
    void delReaderCard(String ids);

    /**
     * 新增   或修改 读者证类型 数据记录
     * @param   readerCardEntity
     * @return Integer
     */
    Integer savereaderCard(ReaderCardEntity readerCardEntity);
}
