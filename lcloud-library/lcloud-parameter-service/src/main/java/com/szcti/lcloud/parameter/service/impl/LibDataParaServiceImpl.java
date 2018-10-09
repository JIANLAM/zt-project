package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.parameter.entity.LibdataParaEntity;
import com.szcti.lcloud.parameter.repository.LibDataParaDao;
import com.szcti.lcloud.parameter.service.LibDataParaService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: Z39.50地址设置Service
 * @Description: 获取数据库信息的Service
 * @author: wangsiyi
 * @date: 2018/7/25 11:04
 */

@Service
public class LibDataParaServiceImpl implements LibDataParaService {

    @Autowired
    private LibDataParaDao libDataParaDao;

    /**
     * 新增   或修改 一条数据记录
     * @param   libdataParaEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LibdataParaEntity libdataParaEntity){
        if(libdataParaEntity != null && libdataParaEntity.getId()!= null) {
            libDataParaDao.updateLibDataBaseInfo(libdataParaEntity);
        }else{
            Long libDataParaId = IdGen.randomLong();
            libdataParaEntity.setId(libDataParaId);
            libdataParaEntity.setCreateTime(DateUtils.getDateTime());
            libDataParaDao.insertLibDataBaseInfo(libdataParaEntity);
        }
    }

    /**
     * 根据主键删除一条 或多条 数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delLibDataBaseInfo(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        libDataParaDao.delLibDataBaseInfo(idArray);
    }
}
