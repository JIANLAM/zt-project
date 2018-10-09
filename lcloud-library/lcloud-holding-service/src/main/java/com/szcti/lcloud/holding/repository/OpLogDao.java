package com.szcti.lcloud.holding.repository;

import com.szcti.lcloud.holding.entity.vo.BookCopyVO;
import com.szcti.lcloud.holding.entity.vo.OpLogVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 操作日志DAO
 * @Description: 处理操作日志
 * @author: fengda
 * @date: 2018/7/26 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface OpLogDao {

    /**
     * 查找图书所有的副本
     * @param opLogVO
     * @return
     */
    List<OpLogVO> findList(OpLogVO opLogVO);


    /**
     * 插入一条操作日志
     * @param opLogVO
     */
    void insert(OpLogVO opLogVO);
}
