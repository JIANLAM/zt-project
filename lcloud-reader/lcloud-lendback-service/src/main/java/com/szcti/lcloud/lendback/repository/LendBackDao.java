package com.szcti.lcloud.lendback.repository;

import com.szcti.lcloud.lendback.entity.vo.LendBackVO;
import com.szcti.lcloud.lendback.entity.ReLendEntity;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 借还信息DAO
 * @Description: 处理借还书以及续借操作
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LendBackDao {
    /**
     * 根据条件查找用户的借还信息
     * @param lendBackVO
     * @return List<LendBackVO>
     */
    List<LendBackVO> findList(LendBackVO lendBackVO);

    /**
     * 根据ID查找借还信息
     * @param lendId
     * @return LendBackVO
     */
    LendBackVO get(Long lendId);

    /**
     * 插入一条续借记录
     * @param reLendEntity
     */
    void insertReLendInfo(ReLendEntity reLendEntity);

    /**
     * 更新借书记录的相关时间和状态
     * @param lendId  借阅记录ID
     * @param dueBackTime  应还日期
     * @param backTime  时间还书日期
     * @param lendStatus 借阅状态
     */
    void updateStatus(@Param("lendId") Long lendId,@Param("dueBackTime") String dueBackTime,@Param("backTime") String backTime,@Param("lendStatus") Integer lendStatus);


    /**
     * 根据条件查找用户的借还信息            微信端
     * @param lendBackVO
     * @return List<LendBackVO>
     */
    List<LendBackVO> weChatList(LendBackVO lendBackVO);

    /**
     * 根据lendId查找用户的借还 每本书的详情信息            微信端
     * @param lendId
     * @return LendBackVO
     */
    LendBackVO weChatInfo(Long lendId);
}
