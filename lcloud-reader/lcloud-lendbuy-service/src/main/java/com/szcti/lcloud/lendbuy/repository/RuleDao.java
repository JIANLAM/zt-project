package com.szcti.lcloud.lendbuy.repository;

import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 借购规则信息DAO
 * @Description: 处理借购规则操作
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface RuleDao {

    /**
     * 条件查询规则列表
     * @param lendBuyRuleVO
     * @return
     */
    List<LendBuyRuleVO> findList(LendBuyRuleVO lendBuyRuleVO);

    /**
     * 根据ID查询单个规则详情
     * @param id
     * @return
     */
    LendBuyRuleVO getById(Long id);

    /**
     * 根据图书馆ID和读者类型查询单个规则
     * @param libraryId 不为空
     * @param readerType 不为空
     * @param status 可为空
     * @return
     */
    LendBuyRuleVO getByType(@Param("libraryId") Long libraryId,@Param("readerType") Integer readerType,@Param("status") Integer status);

    /**
     * 获取用户信用值
     * @param userId
     * @return
     */
    Integer getCredit(Long userId);

    /**
     * 修改规则信息
     * @param lendBuyRuleVO
     */
    void update(LendBuyRuleVO lendBuyRuleVO);

    /**
     * 新增规则信息
     * @param lendBuyRuleVO
     */
    void insert(LendBuyRuleVO lendBuyRuleVO);

    /**
     * 新增规则信息
     * @param idArray
     */
    void delete(@Param("idArray") Long[] idArray);

}
