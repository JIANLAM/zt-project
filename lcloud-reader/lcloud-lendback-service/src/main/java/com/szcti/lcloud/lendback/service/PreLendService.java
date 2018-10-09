package com.szcti.lcloud.lendback.service;



import com.szcti.lcloud.lendback.entity.vo.PreLendVO;

import java.util.List;

/**
 * @Title: 预借Service
 * @Description: 处理预借信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface PreLendService {

    /**
     * 查找用户的预借信息
     * @param preLendVO
     * @return List<PreLendVO>
     */
    List<PreLendVO> findList(PreLendVO preLendVO);

    /**
     * 取消预借
     * @param preLendId
     */
    void cancelPreLend(Long preLendId);

    /**
     * 再次申请预借
     * @param preLendId
     */
    void recoverPreLend(Long preLendId);

    /**
     * 完成预借
     * @param preLendId
     */
    void finishPreLend(Long preLendId);

    /*************************************     微信端     *****************************/
    /**
     * 根据条件查找当前用户的预借信息
     * @param preLendVO
     * @return List<PreLendVO>
     */
    List<PreLendVO> weChatFindList(PreLendVO preLendVO);
}
