package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.LibdataParaEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Title: Z39.50地址设置 Dao
 * @Description: 获取数据库信息 Dao
 * @author: wangsiyi
 * @date: 2018/7/25 11:04
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LibDataParaDao {
    /**
     * 新增一条数据记录
     * @param   libdataParaEntity
     * @return
     */
    void insertLibDataBaseInfo(LibdataParaEntity libdataParaEntity);

    /**
     * 根据主键删除一条 或多条 数据记录
     * @param idArray
     */
    void delLibDataBaseInfo(@Param("idArray") Long[] idArray);

    /**
     * 修改一条数据记录
     * @param   libdataParaEntity
     * @return
     */
    void updateLibDataBaseInfo(LibdataParaEntity libdataParaEntity);

    /**
     * 查询List集合
     * @param
     * @return List<LibdataParaEntity>
     */
    List<LibdataParaEntity> findLibDataBaseList(@Param("libraryId") Long libraryId);

    /**
     * 根据ID 查询一条数据记录
     * @param   id
     * @return LibdataParaEntity
     */
    LibdataParaEntity getLibDataBase(@Param("id") Long id);
}
