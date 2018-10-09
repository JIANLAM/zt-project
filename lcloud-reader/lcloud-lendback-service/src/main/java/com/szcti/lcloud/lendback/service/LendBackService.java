package com.szcti.lcloud.lendback.service;

import com.szcti.lcloud.lendback.entity.vo.LendBackVO;

import java.util.List;

/**
 * @Title: 借还Service
 * @Description: 处理借还信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface LendBackService {

    /**
     * 查找用户的借还信息
     * @param lendBackVO
     * @return List<LendBackVO>
     */
    List<LendBackVO> findList(LendBackVO lendBackVO);

    /**
     * 续借
     * @param lendId
     * @return  String
     */
    String reLend(Long lendId);


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
