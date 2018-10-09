package com.szcti.lcloud.lendback.service.impl;
import com.szcti.lcloud.lendback.entity.vo.PreLendVO;
import com.szcti.lcloud.lendback.repository.PreLendDao;
import com.szcti.lcloud.lendback.service.PreLendService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class PreLendServiceImpl implements PreLendService {

    @Autowired
    private PreLendDao preLendDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPreLend(@NonNull Long preLendId){
        preLendDao.changeStatus(preLendId,2);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recoverPreLend(@NonNull Long preLendId){
        preLendDao.changeStatus(preLendId,0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishPreLend(@NonNull Long preLendId){
        preLendDao.changeStatus(preLendId,1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PreLendVO> findList(@NonNull PreLendVO preLendVO){
        return preLendDao.findList(preLendVO);
    }

    /*************************************     微信端     *****************************/
    @Override
    @Transactional(readOnly = true)
    public List<PreLendVO> weChatFindList(@NonNull PreLendVO preLendVO){
        return preLendDao.weChatFindList(preLendVO);
    }
}
