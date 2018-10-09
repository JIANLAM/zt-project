package com.szcti.lcloud.account.service.impl;

import com.szcti.lcloud.account.entity.SendeeEntity;
import com.szcti.lcloud.account.repository.SendeeDao;
import com.szcti.lcloud.account.service.SendeeService;
import com.szcti.lcloud.common.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class SendeeServiceImpl implements SendeeService {

    @Autowired
    private SendeeDao sendeeDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SendeeEntity sendeeEntity){
        if(sendeeEntity!=null&&sendeeEntity.getId()!=null){
            //修改前判断 用户是否存在默认地址
            //if(sendeeEntity.getIsDefault() == 1) {
                //cancelDefault(sendeeEntity.getPeopleId());
            //}
            sendeeDao.update(sendeeEntity);
        }else{
            sendeeEntity.setId(IdGen.randomLong());
            //新增前判断 用户是否存在默认地址
            //if(sendeeEntity.getIsDefault() == 1) {
            sendeeDao.cancelDefault(sendeeEntity.getPeopleId());
            //}
            sendeeEntity.setIsDefault(1);
            sendeeDao.insert(sendeeEntity);
        }
    }

    /**
     * 设置默认地址
     * @param sendeeId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefault(Long sendeeId,Long peopleId){
        //修改  is_default = 0 （ 1 为默认     0 非默认 ）
        sendeeDao.cancelDefault(peopleId);            //传登录用户的peopleId
        sendeeDao.setDefault(sendeeId);
    }

}
