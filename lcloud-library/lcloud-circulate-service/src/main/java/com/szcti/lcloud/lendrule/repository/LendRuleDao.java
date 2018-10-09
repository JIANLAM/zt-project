package com.szcti.lcloud.lendrule.repository;

import com.szcti.lcloud.lendrule.entity.vo.LendRuleVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: 借阅规则 Dao
 * @Description: 借阅规则 Dao
 * @author: wangsiyi
 * @date: 2018/8/1 15:48
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LendRuleDao {

    /**
     * 查询借阅规则List集合
     * @param
     * @return List<LendRuleVo>
     */
    List<LendRuleVo> findList(LendRuleVo lendRuleVo);

    /**
     * 根据ID 查询一条借阅规则 记录
     * @param   id
     * @return LendRuleVo
     */
    LendRuleVo get(@Param("id") Long id , @Param("idArray") Long[] idArray);

    /**
     * 根据主键删除一条 或多条 借阅规则 数据记录
     * @param idArray
     */
    void delLendRule(@Param("idArray") Long[] idArray);

    /**
     * 新增一条借阅规则  数据记录
     * @param   lendRuleVo
     * @return
     */
    void insertLendRule(LendRuleVo lendRuleVo);

    /**
     * 修改一条自定义字段设置  数据记录
     * @param   lendRuleVo
     * @return
     */
    void updateLendRule(LendRuleVo lendRuleVo);

    /**
     * 借阅规则  同一图书馆 不能存在相同的规则代码
     * @param
     * @return Integer
     */
    Integer existsRuleCode(@Param("ruleCode") String ruleCode,@Param("ruleType") Integer ruleType,@Param("id") Long id , @Param("libraryId") Long libraryId);

    /**
     * 借阅规则 批量导入 文献类类型 图书馆是否存在
     * @param
     * @return Integer
     */
    String existsLiteratureType(@Param("literatureType") String literatureType, @Param("libraryId") Long libraryId);

    /**
     * 启用 一条规则
     * @param   idArray
     * @return
     */
    void ruleOpen(@Param("idArray") Long[] idArray);

    /**
     * 禁用 一条规则
     * @param   idArray
     * @return
     */
    void ruleClose(@Param("idArray") Long[] idArray);

    /**
     * 禁用 这个类型其他规则
     * @param   readerType
     * @return
     */
    void ruleAllClose(@Param("readerType") Long readerType ,@Param("libraryId") Long libraryId);

    /**
     *  根据did查询读者信息
     * @param readerId
     * @return
     */
    HashMap getReaderInfo(Long readerId);

    /**
     *  统计读者已经借阅了几本书
     * @param readerId
     * @return Integer
     */
    Integer readerCount(Long readerId);

    /**
     *  读者信用值分
     * @param readerId
     * @return Integer
     */
    Integer creditNumber(Long readerId);

    /**
     *  查询每本书的续借次数
     * @param holdingId , readerId
     * @return Integer
     */
    Integer bookRelendCount(@Param("holdingId") Long holdingId , @Param("readerId") Long readerId);

    /**
     *  查询书的文献类型
     * @param holdingId
     * @return HashMap
     */
    HashMap bookArticleType(@Param("holdingId") Long holdingId);

    /**
     * 一种读者证类型对应 一条馆内规则    一条馆际规则      查询馆内规则
     * @param   readerCardId
     * @return LendRuleVo
     */
    LendRuleVo readerMuseumRule(@Param("readerCardId") Long readerCardId);

    /**
     * 一种读者证类型对应 一条馆内规则    一条馆际规则      查询馆际规则
     * @param   readerCardId
     * @return LendRuleVo
     */
    LendRuleVo readerInterRule(@Param("readerCardId") Long readerCardId);

}
