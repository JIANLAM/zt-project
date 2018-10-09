package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;
import com.szcti.lcloud.parameter.entity.vo.OutBoxVO;
import com.szcti.lcloud.parameter.repository.BasicParamDao;
import com.szcti.lcloud.parameter.repository.OutBoxDao;
import com.szcti.lcloud.parameter.service.BasicParamService;
import com.szcti.lcloud.parameter.service.OutBoxService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 发送邮箱设置 ServiceImpi
 * @Description: 发送邮箱设置 ServiceImpi
 * @author: wangsiyi
 * @date: 2018/8/16 11:15
 */

@Service
public class OutBoxServiceImpl implements OutBoxService {

    @Autowired
    private OutBoxDao outBoxDao;

    /**
     * 根据主键删除一条 或多条 发送邮箱设置记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delOutbox(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        outBoxDao.delOutbox(idArray);
    }


    /**
     * 新增   或修改 一条发送邮箱设置  数据记录
     * @param   outBoxVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OutBoxVO outBoxVO){
        if(outBoxVO != null && outBoxVO.getId()!= null) {
            outBoxDao.updateOutbox(outBoxVO);
        }else{
            Long barCodeId = IdGen.randomLong();
            outBoxVO.setId(barCodeId);
            outBoxVO.setCreateDate(DateUtils.getDateTime());
            outBoxDao.insertOutbox(outBoxVO);
        }
    }

}
