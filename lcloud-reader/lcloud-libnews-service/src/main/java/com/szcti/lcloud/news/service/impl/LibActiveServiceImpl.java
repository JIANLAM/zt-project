package com.szcti.lcloud.news.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.news.entity.LibNewsLookEntity;
import com.szcti.lcloud.news.entity.LibactiveEnrollEntity;
import com.szcti.lcloud.news.entity.LibactiveImgEntity;
import com.szcti.lcloud.news.entity.vo.LibActiveVo;
import com.szcti.lcloud.news.entity.vo.LibNewsVo;
import com.szcti.lcloud.news.repository.LibActiveDao;
import com.szcti.lcloud.news.repository.LibNewsDao;
import com.szcti.lcloud.news.service.LibActiveService;
import com.szcti.lcloud.news.service.LibNewsService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Service
public class LibActiveServiceImpl implements LibActiveService {

    @Autowired
    private LibActiveDao libActiveDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<LibActiveVo> findList(){
        List<LibActiveVo> libNewsVoList = libActiveDao.findList();
        for (LibActiveVo vo:libNewsVoList
                ) {
                vo.setLibImgList(libActiveDao.imgList(vo.getId()));
        }
        return libNewsVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LibActiveVo get(Long activeId){
        LibActiveVo libNewsVoObj = libActiveDao.get(activeId);
        libNewsVoObj.setLibImgList(libActiveDao.imgList(libNewsVoObj.getId()));
        return libNewsVoObj;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LibActiveVo libActiveVo){
        if(libActiveVo != null && libActiveVo.getId()!= null) {
            Long libActiveId = libActiveVo.getId();
            libActiveDao.updateLibActive(libActiveVo);
            libActiveDao.delLibActiveImg(libActiveVo.getId());
            List list = libActiveVo.getSaveImgList();
            if(null != list && list.size() > 0){
                saveActiveImg(list,libActiveId);
            }
        }else{
            Long libActiveId = IdGen.randomLong();
            libActiveVo.setId(libActiveId);
            libActiveVo.setCreateTime(DateUtils.getDateTime());
            //libActiveVo.setCreateBy(new Long(1));   //为创建者ID    后期 用token获取
            libActiveDao.insertLibActive(libActiveVo);
            List list = libActiveVo.getSaveImgList();
            if(null != list && list.size() > 0){
                saveActiveImg(list,libActiveId);
            }
        }
    }

    /**
     * 保存馆内活动   图片
     * @param list
     * @param libActiveId
     */
    public void saveActiveImg(List<Object> list,Long libActiveId){
        List<LibactiveImgEntity> imgList = new ArrayList<>();
        LibactiveImgEntity imgObj = null;
        for (Object vo:list
                ) {
            imgObj = new LibactiveImgEntity();
            imgObj.setLibActiveId(libActiveId);
            imgObj.setLibActiveImg(vo.toString());
            imgList.add(imgObj);
        }
        libActiveDao.insertLibActiveImg(imgList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delLibActiveById(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        libActiveDao.delLibActiveImgById(idArray);
        libActiveDao.delLibActiveById(idArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLibActiveEnroll(LibactiveEnrollEntity LibactiveEnrollEntity){
        if (libActiveDao.isEnroll(LibactiveEnrollEntity.getLibActiveId(),LibactiveEnrollEntity.getReaderId()) == 0){
            LibactiveEnrollEntity.setCreateTime(DateUtils.getDateTime());
            libActiveDao.saveLibActiveEnroll(LibactiveEnrollEntity);
            return true;
        }
        return false;
    }
}
