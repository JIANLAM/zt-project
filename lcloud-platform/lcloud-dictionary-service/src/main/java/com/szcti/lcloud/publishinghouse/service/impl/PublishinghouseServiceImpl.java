package com.szcti.lcloud.publishinghouse.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.publishinghouse.entity.PublishinghouseEntity;
import com.szcti.lcloud.publishinghouse.repository.PublishinghouseDao;
import com.szcti.lcloud.publishinghouse.service.PublishinghouseService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @Title: 出版社数据 Service
 * @Description: 出版社数据 的Service
 * @author: fengda
 * @date: 2018/8/27 3:32
 */

@Service
public class PublishinghouseServiceImpl implements PublishinghouseService {

    @Autowired
    private PublishinghouseDao publishinghouseDao;

    /**
     * 根据主键删除一条 或多条 出版社记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delPublishing(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        publishinghouseDao.delPublishing(idArray);
    }

    /**
     * 新增   或修改 一条出版社  数据记录
     * @param   publishinghouseEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(PublishinghouseEntity publishinghouseEntity){
        if(publishinghouseEntity != null && publishinghouseEntity.getId()!= null) {
            if(publishinghouseDao.existsPublishing(publishinghouseEntity.getCode(),publishinghouseEntity.getId()) > 0){
                return 0;
            }
            publishinghouseDao.updatePublishing(publishinghouseEntity);
            return 1;
        }else{
            if(publishinghouseDao.existsPublishing(publishinghouseEntity.getCode(),publishinghouseEntity.getId()) > 0){
                return 0;
            }
            Long barCodeId = IdGen.randomLong();
            publishinghouseEntity.setId(barCodeId);
            publishinghouseEntity.setCreateDate(DateUtils.getDateTime());
            publishinghouseDao.insertPublishing(publishinghouseEntity);
            return 1;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HashMap<String,Object> getByISBN(String ISBN){
        ISBN = ISBN.replace("-","");
        HashMap<String,Object> map;
        map = publishinghouseDao.getFromXHByISBN(ISBN);
        if(map==null){
            map = publishinghouseDao.getFromPubByISBN(ISBN);
        }
        return map;
    }
}
