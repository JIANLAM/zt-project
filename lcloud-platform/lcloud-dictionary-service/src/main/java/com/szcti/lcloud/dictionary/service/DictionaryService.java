package com.szcti.lcloud.dictionary.service;


import com.szcti.lcloud.dictionary.entity.DictionaryEntity;

import java.util.Map;


/**
 * @Title: 数据字典 Service
 * @Description: 数据字典 的Service
 * @author: wangsiyi
 * @date: 2018/7/30 10:26
 */
public interface DictionaryService {
    /**
     * 根据主键删除一条 或多条 字典数据记录
     * @param ids
     */
    void delDictionary(String ids);

    /**
     * 新增   或修改 字典数据 数据记录
     * @param   dictionaryEntity
     * @return  Integer
     */
    Integer save(DictionaryEntity dictionaryEntity);

    /**
     * 查询字典     用MAP分类 返回所有数据
     * @param
     * @return  Integer
     */
    Map<String , Object> findListMap();
}
