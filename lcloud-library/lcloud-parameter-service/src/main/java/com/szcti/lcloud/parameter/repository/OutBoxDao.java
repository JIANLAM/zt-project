package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.vo.OutBoxVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Title: 发送邮箱设置 Dao
 * @Description: 发送邮箱设置 Dao
 * @author: wangsiyi
 * @date: 2018/8/16 11:15
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface OutBoxDao {

    /**
     * 查询   发送邮箱设置  List集合
     * @param
     * @return List<outBoxVO>
     */
    List<OutBoxVO> findList(OutBoxVO outBoxVO);

    /**
     * 根据ID 查询一条发送邮箱设置 记录
     * @param   id
     * @return outBoxVO
     */
    OutBoxVO get(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 发送邮箱设置 数据记录
     * @param idArray
     */
    void delOutbox(@Param("idArray") Long[] idArray);

    /**
     * 新增一条发送邮箱设置  数据记录
     * @param   outBoxVO
     * @return
     */
    void insertOutbox(OutBoxVO outBoxVO);

    /**
     * 修改一条发送邮箱设置  数据记录
     * @param   outBoxVO
     * @return
     */
    void updateOutbox(OutBoxVO outBoxVO);


}
