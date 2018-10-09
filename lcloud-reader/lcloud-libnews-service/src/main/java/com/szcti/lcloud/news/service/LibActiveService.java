package com.szcti.lcloud.news.service;

import com.szcti.lcloud.news.entity.LibactiveEnrollEntity;
import com.szcti.lcloud.news.entity.vo.LibActiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 馆内活动Service
 * @Description: 处理馆内活动的Service
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
public interface LibActiveService {

    /**
     * 查询馆内活动
     * @param
     * @return List<LibNewsVo>
     */
    List<LibActiveVo> findList();

    /**
     * 根据ID 查询馆内活动 的指定的一条记录
     * @param   activeId
     * @return LibActiveVo
     */
    LibActiveVo get(@Param("activeId") Long activeId);

    /**
     * 新增   或修改 一条馆内活动
     * @param   libActiveVo
     * @return
     */
    void save(LibActiveVo libActiveVo);

    /**
     * 根据主键删除馆内活动
     * @param ids
     */
    void delLibActiveById(String ids);

    /**
     * 新增一条馆内活动
     * @param   LibactiveEnrollEntity
     * @return Integer
     */
    boolean saveLibActiveEnroll(LibactiveEnrollEntity LibactiveEnrollEntity);
}
