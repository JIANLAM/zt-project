package com.szcti.lcloud.parameter.service;


import com.szcti.lcloud.parameter.entity.LibdataParaEntity;

/**
 * @Title: Z39.50地址设置Service
 * @Description: 获取数据库信息的Service
 * @author: wangsiyi
 * @date: 2018/7/25 11:04
 */
public interface LibDataParaService {
    /**
     * 新增   或修改 一条数据记录
     * @param   libdataParaEntity
     * @return
     */
    void save(LibdataParaEntity libdataParaEntity);

    /**
     * 根据主键删除一条 或多条 数据记录
     * @param ids
     */
    void delLibDataBaseInfo(String ids);

}
