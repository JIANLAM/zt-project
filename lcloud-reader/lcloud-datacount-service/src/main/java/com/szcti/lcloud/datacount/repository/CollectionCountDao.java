package com.szcti.lcloud.datacount.repository;

import com.szcti.lcloud.datacount.entity.vo.CirculationVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @Title: 馆藏统计DAO
 * @Description: 馆藏统计
 * @author: wangsiyi
 * @date: 2018/8/9 9:27
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CollectionCountDao {

    /**
     * 查询馆藏分类统计
     * @param circulationVO
     * @return  Integer
     */
    Float collectionTypeCount(CirculationVO circulationVO );

    /**
     * 查询图书  一条    馆藏地点   或    文献流通类型
     * @param
     * @return  Map<String , Object>
     */
    Map<String , Object> cirORcollRowType(Long id);

    /**
     * 查询馆藏日志  解析书的条码号
     * @param
     * @return  List<String>
     */
    List<String> opContentList(@Param("operationType") String operationType , @Param("libraryId") Long libraryId ,@Param("startTime") String startTime ,@Param("endTime") String endTime  ,@Param("userId") Long userId);

    /**
     * 查询馆藏处理统计    操作人员
     * @param
     * @return  Map<String , Object>
     */
    List<Map<String , Object>> operationStaff( @Param("libraryId") Long libraryId);


}
