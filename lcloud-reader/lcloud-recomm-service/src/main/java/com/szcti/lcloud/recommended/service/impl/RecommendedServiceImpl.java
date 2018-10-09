package com.szcti.lcloud.recommended.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.recommended.entity.vo.RecommReasonInfoVO;
import com.szcti.lcloud.recommended.entity.vo.RecommendedBookVO;
import com.szcti.lcloud.recommended.repository.RecommendedDao;
import com.szcti.lcloud.recommended.service.RecommendedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/30 8:53
 */
@Service
public class RecommendedServiceImpl implements RecommendedService {

    @Autowired
    private RecommendedDao recommendedDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RecommendedBookVO recommendedBookVO){
        recommendedBookVO.setId(IdGen.randomLong());
        recommendedBookVO.setCreateTime(DateUtils.getDateTime());
        recommendedDao.insert(recommendedBookVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RecommendedBookVO get(RecommendedBookVO recommendedBookVO){
        List<RecommReasonInfoVO> recommReasonList =recommendedDao.recommReasonList(recommendedBookVO.getBookId(),recommendedBookVO.getRecommType());
        RecommendedBookVO vo = recommendedDao.get(recommendedBookVO);
        vo.setRecommReasonList(recommReasonList);
        return vo;
    }

}
