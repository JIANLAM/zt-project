package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.MarcMouldEntity;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @Title: mrc模板 Service
 * @Description: mrc模板 的Service
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */
public interface MarcTempletService {

    /*************************************************    MARC字段       ***************************************************/

    /**
     * 根据主键删除一条 或多条mrc字段 数据记录
     * @param ids
     */
    void delMarc(String ids);

    /**
     * 新增   或修改 一条mrc字段  数据记录
     * @param   marcFieldEntity
     * @return
     */
    Integer save(MarcFieldEntity marcFieldEntity);

    /**
     *导出 marc字段 数据记录
     * @param   marcFieldEntity
     * @return
     */
    String exportMarcExcel(MarcFieldEntity marcFieldEntity);

    /**
     *导出 marc字段模板 数据记录
     * @param
     * @return
     */
    String exportMouldExcel();

    /**
     *批量导入 marc字段 数据记录
     * @param   maplist
     * @return
     */
    String importMarc(List<Map> maplist , Long libraryId , Long createBy);

    /*************************************************    MARC模版       ***************************************************/

    /**
     * 根据主键删除一条 或多条mrc模板数据记录
     * @param ids
     */
    void delMarcMould(String ids);

    /**
     * 新增   或修改 一条mrc模板 数据记录
     * @param   marcMouldEntity
     * @return  Integer
     */
    Integer saveMould(MarcMouldEntity marcMouldEntity);

    /**
     * MARC模板     启用一个模版 设为默认
     * @param   id , libraryId
     * @return  Integer
     */
    void openMould(Long id ,  Long libraryId);

}
