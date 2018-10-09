package com.szcti.lcloud.helplendback.repository;

import com.szcti.lcloud.helplendback.entity.vo.HelpLendBackVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 代借还信息DAO
 * @Description: 处理代借还书以及续借操作
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface HelpLendBackDao {
    /**
     * 根据条件查找用户的代借还信息
     * @param helpLendBackVO
     * @return List<HelpLendBackVO>
     */
    List<HelpLendBackVO> findList(HelpLendBackVO helpLendBackVO);

    /**
     * 根据ID查找代借还信息
     * @param helpLendBackId
     * @return HelpLendBackVO
     */
    HelpLendBackVO get(Long helpLendBackId);


    /**
     * 更改代借还记录的状态
     * @param helpLendBackId
     * @param status
     */
    void changeStatus(@Param("helpLendBackId") Long helpLendBackId,@Param("status") Integer status);

    /************************************微信端   代借******************************************/

    /**
     * 根据条件查找用户的代借还信息
     * @param helpLendBackVO
     * @return List<HelpLendBackVO>
     */
    List<HelpLendBackVO> weChatfindList(HelpLendBackVO helpLendBackVO);

}
