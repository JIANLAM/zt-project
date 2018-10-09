package com.szcti.lcloud.mycollection.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.mycollection.entity.vo.CollectionVO;
import com.szcti.lcloud.mycollection.repository.CollectionDao;
import com.szcti.lcloud.mycollection.service.CollectionService;
import lombok.NonNull;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 个人收藏书籍息处理
 * @Description: 个人收藏书籍处理
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull String collectionIds){
        String []collectionIdStr = collectionIds.split(",");
        Long []collectionIdArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        collectionDao.delete(collectionIdArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(CollectionVO collectionVO){
        CollectionVO coll = collectionDao.get(collectionVO.getPreBookId(),collectionVO.getReaderId());
        if(coll==null){
            collectionVO.setId(IdGen.randomLong());
            collectionDao.insert(collectionVO);
            return "success";
        }else{
            return "false";
        }
    }

}
