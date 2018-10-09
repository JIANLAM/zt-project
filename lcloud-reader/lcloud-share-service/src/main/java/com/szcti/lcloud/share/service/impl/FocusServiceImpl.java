package com.szcti.lcloud.share.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.share.entity.vo.ShareVO;
import com.szcti.lcloud.share.repository.FocusDao;
import com.szcti.lcloud.share.repository.ShareDao;
import com.szcti.lcloud.share.service.FocusService;
import com.szcti.lcloud.share.service.ShareService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: 粉丝关注处理
 * @Description: 粉丝关注处理
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class FocusServiceImpl implements FocusService {

    @Autowired
    private FocusDao focusDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull Long currentReaderId,@NonNull Long readerId){
        List<HashMap> list = focusDao.find(currentReaderId,readerId);
        if(list!=null&&list.size()>0){
            return false;
        }else{
            focusDao.insert(currentReaderId,readerId, DateUtils.getDateTime());
            return true;
        }
    }

}
