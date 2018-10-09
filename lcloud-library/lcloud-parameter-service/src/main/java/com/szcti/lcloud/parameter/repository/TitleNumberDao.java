package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.TitleNumberEntity;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: 种次号维护 Dao
 * @Description: 种次号维护 Dao
 * @author: wangsiyi
 * @date: 2018/7/26 19:58
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface TitleNumberDao {

    /**
     * 查询种次号维护List集合
     * @param titleNumberEntity
     * @return List<TitleNumberEntity>
     */
    List<TitleNumberEntity> findTitleNumberList(TitleNumberEntity titleNumberEntity);

    /**
     * 根据ID 查询一条种次号维护 记录
     * @param   id
     * @return TitleNumberEntity
     */
    TitleNumberEntity getTitleNumber(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 种次号维护数据记录
     * @param idArray
     */
    void delTitleNumber(@Param("idArray") Long[] idArray);

    /**
     * 新增一条种次号维护 数据记录
     * @param   titleNumberEntity
     * @return
     */
    void insertTitleNumber(TitleNumberEntity titleNumberEntity);

    /**
     * 修改一条种次号维护 数据记录
     * @param   titleNumberEntity
     * @return
     */
    void updateTitleNumber(TitleNumberEntity titleNumberEntity);

    /**
     * 新增初始化   种次号维护 当前种号 最大值加1 默认为1
     * @param
     * @return String
     */
    Map maxCurrSeednumber(@Param("libraryId") Long libraryId , @Param("classNumber") String classNumber);

    /**
     * 新增或修改 保存 判断是否已经存在分类号 存在返回1 不存在返回0
     * @param
     * @return Integer
     */
    Integer existsTitlenumber(@Param("classNumber") String classNumber,@Param("id") Long id,@Param("libraryId") Long libraryId);
}
