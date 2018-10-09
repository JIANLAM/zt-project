package com.szcti.lcloud.news.repository;

import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.vo.LibNewsVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: 最新资讯DAO
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Mapper
public interface LibNewsDao {
    /**
     * 查询最新资讯
     * @param
     * @return List<LibNewsVo>
     */
    List<LibNewsVo> findList(@Param("startTime") String startTime,@Param("closureTime") String closureTime);

    /**
     * 根据ID 查询最新资讯 的指定的一条记录
     * @param   newsId
     * @return LibNewsVo
     */
    LibNewsVo get(@Param("newsId") Long newsId);

    /**
     * 根据读者ID   查询是否已经看过 新增过阅读记录
     * @param   libNewsId,readerId
     * @return Integer
     */
    Integer isLook(@Param("libNewsId") Long libNewsId,@Param("readerId") Long readerId);

    /**
     * 新增一条阅读记录
     * @param   libNewsLookEntity
     * @return
     */
    void saveLibNewsLook(LibNewsLookEntity libNewsLookEntity);

    /**
     * 新增一条最新资讯
     * @param   libNewsVo
     * @return
     */
    void insertLibNews(LibNewsVo libNewsVo);

    /**
     * 修改一条最新资讯
     * @param   libNewsVo
     * @return
     */
    void updateLibNews(LibNewsVo libNewsVo);

    /**
     * 根据主键删除最新资讯
     * @param idArray
     */
    void delLibNewsById(@Param("idArray") Long[] idArray);
}
