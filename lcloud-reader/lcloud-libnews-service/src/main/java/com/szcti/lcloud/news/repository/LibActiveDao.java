package com.szcti.lcloud.news.repository;

import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.LibactiveEnrollEntity;
import com.szcti.lcloud.news.entity.LibactiveImgEntity;
import com.szcti.lcloud.news.entity.vo.LibActiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 馆内活动DAO
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Mapper
public interface LibActiveDao {
    /**
     * 查询馆内活动
     * @param
     * @return List<LibActiveVo>
     */
    List<LibActiveVo> findList();

    /**
     * 查询馆内活动   图片集合
     * @param   libActiveId
     * @return List<LibactiveImgEntity>
     */
    List<LibactiveImgEntity> imgList(@Param("libActiveId") Long libActiveId);

    /**
     * 根据ID 查询馆内活动 的指定的一条记录
     * @param   activeId
     * @return LibNewsVo
     */
    LibActiveVo get(@Param("activeId") Long activeId);

    /**
     * 根据读者ID 活动ID   查询此活动是否已经报过名
     * @param   libActiveId,readerId
     * @return Integer
     */
    Integer isEnroll(@Param("libActiveId") Long libActiveId, @Param("readerId") Long readerId);

    /**
     * 新增一条报名记录
     * @param   libactiveEnrollEntity
     * @return Integer
     */
    void saveLibActiveEnroll(LibactiveEnrollEntity libactiveEnrollEntity);

    /**
     * 新增一条馆内活动
     * @param   libActiveVo
     * @return
     */
    void insertLibActive(LibActiveVo libActiveVo);

    /**
     * 新增一条馆内活动    图片集合
     * @param   list
     * @return
     */
    void insertLibActiveImg(List<LibactiveImgEntity> list);

    /**
     * 修改一条馆内活动
     * @param   libActiveVo
     * @return
     */
    void updateLibActive(LibActiveVo libActiveVo);

    /**
     * 删除馆内活动    图片集合
     * @param   libActiveId
     * @return
     */
    void delLibActiveImg(@Param("libActiveId") Long libActiveId);

    /**
     * 根据主键删除馆内活动
     * @param idArray
     */
    void delLibActiveById(@Param("idArray") Long[] idArray);

    /**
     * 根据主键删除馆内活动   图片
     * @param idArray
     */
    void delLibActiveImgById(@Param("idArray") Long[] idArray);
}
