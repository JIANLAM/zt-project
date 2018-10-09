package com.szcti.lcloud.recommbuy.service;

import com.szcti.lcloud.recommbuy.entity.vo.RecommBuyVO;

import java.util.List;

/**
 * 荐购表
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
public interface RecommBuyService {

    void insert(RecommBuyVO recommBuyVO);
    /**
     * 查找用户的荐购信息
     * @param recommbuyVO
     * @return List<RecommBuyVO>
     */
    List<RecommBuyVO> findList(RecommBuyVO recommbuyVO);

    /**
     * 新增一跳荐购信息
     * @param recommbuyVO
     * @return
     */
    boolean save(RecommBuyVO recommbuyVO);

    List<RecommBuyVO> queryPage(RecommBuyVO recommBuyVO);

    String exportExcel(RecommBuyVO vo);


    /*********************************     微信端  荐购     ************************************/
    /**
     * 查找用户的荐购信息
     * @param recommbuyVO
     * @return List<RecommBuyVO>
     */
    List<RecommBuyVO> weChatFindList(RecommBuyVO recommbuyVO);

}

