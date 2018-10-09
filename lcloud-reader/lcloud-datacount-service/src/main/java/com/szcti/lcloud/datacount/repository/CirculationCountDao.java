package com.szcti.lcloud.datacount.repository;

import com.szcti.lcloud.datacount.entity.vo.*;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: 流通统计DAO
 * @Description: 各种统计
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CirculationCountDao {

    /**
     * 查询图书分类号
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> bookType();

    /**
     * 根据分类号 读者证类型统计数据
     * @param circulationVO
     * @return  Integer
     */
    Integer readerType(CirculationVO circulationVO);

    /**
     * 查询证类型参数 那种证件
     * @param id
     * @return  Map<String , Object>
     */
    Map<String , Object> basicParm(@Param("id") Long id );

    /**
     * 查询图书    文献流通类型
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> cirType(@Param("libraryId") Long libraryId);

    /**
     * 根据图书流通类型 读者证类型统计数据
     * @param circulationVO
     * @return  Integer
     */
    Integer cirReaderType(CirculationVO circulationVO );

    /**
     * 查询图书    馆藏地点
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> collAddressType(@Param("libraryId") Long libraryId);

    /**
     * 根据图书馆藏地点 读者证类型统计数据
     * @param circulationVO
     * @return  Integer
     */
    Integer collReaderType(CirculationVO circulationVO );

    /**
     * 查询图书    所有读者证类型集合
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> readerTypeList(@Param("libraryId") Long libraryId);

    /**
     * 查询所有 年级 集合
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> gradeList(@Param("libraryId") Long libraryId);

    /**
     * 查询所有 班级 集合
     * @param
     * @return  List<Map<String , Object>>
     */
    List<Map<String , Object>> classesList(@Param("libraryId") Long libraryId);

    /**
     * 查询所有 污损统计
     * @param
     * @return  Map<String , Object>
     */
    Map<String , Object> defileCount(CirculationVO circulationVO);

}
