package com.szcti.lcloud.lendback.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.lendback.entity.ReLendEntity;
import com.szcti.lcloud.lendback.entity.vo.LendBackVO;
import com.szcti.lcloud.lendback.repository.LendBackDao;
import com.szcti.lcloud.lendback.service.LendBackService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class LendBackServiceImpl implements LendBackService {

    @Autowired
    private LendBackDao lendBackDao;

    @Override
    @Transactional(readOnly = true)
    public List<LendBackVO> findList(@NonNull LendBackVO lendBackVO){
        return lendBackDao.findList(lendBackVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String reLend(@NonNull Long lendId){

        LendBackVO lendBackVO = lendBackDao.get(lendId);
        ReLendEntity reLendEntity = new ReLendEntity();
        reLendEntity.setId(IdGen.randomLong());
        reLendEntity.setLendId(lendBackVO.getId());
        reLendEntity.setPrimaryBackTime(lendBackVO.getDueBackTime());

        String dueBackTime = DateUtils.formatDateTime(DateUtils.getDateToMonth(new Date(),1));
        reLendEntity.setReLendBackTime(dueBackTime);
        reLendEntity.setReLendTime(DateUtils.getDateTime());
        //更改借阅状态为续借状态，并变更应还日期
        lendBackDao.updateStatus(lendId,dueBackTime,null,2);
        lendBackDao.insertReLendInfo(reLendEntity);
        return dueBackTime;
    }

    /**
     * 根据条件查找用户的借还信息            微信端
     * @param lendBackVO
     * @return List<LendBackVO>
     */
    @Override
    @Transactional(readOnly = true)
    public List<LendBackVO> weChatList(@NonNull LendBackVO lendBackVO){
        return lendBackDao.weChatList(lendBackVO);
    }

    /**
     * 根据lendId查找用户的借还 每本书的详情信息            微信端
     * @param lendId
     * @return LendBackVO
     */
    @Override
    @Transactional(readOnly = true)
    public LendBackVO weChatInfo(@NonNull Long lendId){
        return lendBackDao.weChatInfo(lendId);
    }
}
