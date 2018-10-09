package com.szcti.lcloud.news.service;

import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.vo.LibNewsVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: 最新资讯Service
 * @Description: 处理最新资讯的Service
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
public interface LibNewsService {

    /**
     * 根据ID 查询最新资讯 的指定的一条记录
     * @param   newsId
     * @return LibNewsVo
     */
    LibNewsVo get(@Param("newsId") Long newsId);

    /**
     * 新增   或修改一条最新资讯
     * @param   libNewsVo
     * @return
     */
    void save(LibNewsVo libNewsVo);

    /**
     * 根据主键删除最新资讯
     * @param ids
     */
    void delLibNewsById(String ids);

    /**
     * 新增一条阅读记录
     * @param   libNewsLookEntity
     * @return Integer
     */
    void saveLibNewsLook(LibNewsLookEntity libNewsLookEntity);
}
