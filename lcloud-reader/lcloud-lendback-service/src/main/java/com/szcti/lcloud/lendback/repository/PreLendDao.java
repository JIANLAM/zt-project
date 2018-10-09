package com.szcti.lcloud.lendback.repository;

import com.szcti.lcloud.lendback.entity.vo.PreLendVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 预借信息DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PreLendDao {
    /**
     * 根据条件查找当前用户的预借信息
     * @param preLendVO
     * @return List<PreLendVO>
     */
    List<PreLendVO> findList(PreLendVO preLendVO);

    /**
     * 更改预借状态
     * @param preLendId
     * @param preLendStatus
     * @return void
     */
    void changeStatus(@Param("preLendId") Long preLendId,@Param("preLendStatus") Integer preLendStatus);


    /*************************************     微信端     *****************************/
    /**
     * 根据条件查找当前用户的预借信息
     * @param preLendVO
     * @return List<PreLendVO>
     */
    List<PreLendVO> weChatFindList(PreLendVO preLendVO);

}
