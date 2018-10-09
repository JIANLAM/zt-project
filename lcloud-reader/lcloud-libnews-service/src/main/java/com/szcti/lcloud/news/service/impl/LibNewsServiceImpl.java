package com.szcti.lcloud.news.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.vo.LibNewsVo;
import com.szcti.lcloud.news.repository.LibNewsDao;
import com.szcti.lcloud.news.service.LibNewsService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Service
public class LibNewsServiceImpl implements LibNewsService {

    @Autowired
    private LibNewsDao libNewsDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LibNewsVo get(Long newsId){
        LibNewsVo libNewsVoObj = libNewsDao.get(newsId);
        return libNewsVoObj;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LibNewsVo libNewsVo){
        if(libNewsVo != null && libNewsVo.getId()!= null) {
            libNewsDao.updateLibNews(libNewsVo);
        }else{
            libNewsVo.setId(IdGen.randomLong());
            //libNewsVo.setCreateBy(new Long(1));         //暂时 写死 创建者ID
            libNewsVo.setCreateTime(DateUtils.getDateTime());
            libNewsDao.insertLibNews(libNewsVo);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delLibNewsById(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        libNewsDao.delLibNewsById(idArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLibNewsLook(LibNewsLookEntity libNewsLookEntity){
        if (libNewsDao.isLook(libNewsLookEntity.getLibNewsId(),libNewsLookEntity.getReaderId()) == 0){
            libNewsDao.saveLibNewsLook(libNewsLookEntity);
        }
    }
}
