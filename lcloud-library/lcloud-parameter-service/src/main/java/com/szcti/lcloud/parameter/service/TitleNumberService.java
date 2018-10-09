package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.TitleNumberEntity;

import java.util.List;
import java.util.Map;

/**
 * @Title: 种次号维护 Service
 * @Description: 种次号维护 的Service
 * @author: wangsiyi
 * @date: 2018/7/26 19:58
 */
public interface TitleNumberService {

    /**
     * 根据主键删除一条 或多条 种次号维护数据记录
     * @param ids
     */
    void delTitleNumber(String ids);

    /**
     * 新增   或修改 一条种次号维护 数据记录
     * @param   titleNumberEntity
     * @return  Integer
     */
    Integer save(TitleNumberEntity titleNumberEntity);

    /**
     * 新增初始化    分类号 最大值加1 默认为1
     * @param
     * @return  Integer
     */
    Map  maxCurrSeednumber(Long libraryId , String classNumber);

    /**
     *导出 种次号维护 数据记录
     * @param   titleNumberEntity
     * @return
     */
    String exportExcel(TitleNumberEntity titleNumberEntity);

    /**
     *下载  导入 种次号维护模版 数据记录
     * @param
     * @return
     */
    String exportMouldExcel();

    /**
     *批量导入 种次号维护 数据记录
     * @param   maplist
     * @return
     */
    String importTitleNumber(List<Map> maplist , Long libraryId , Long createBy);
}
