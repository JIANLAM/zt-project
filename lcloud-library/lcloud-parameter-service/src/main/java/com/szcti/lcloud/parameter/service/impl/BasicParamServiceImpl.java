package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;
import com.szcti.lcloud.parameter.repository.BasicParamDao;
import com.szcti.lcloud.parameter.service.BasicParamService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 自定义字段设置 Service
 * @Description: 自定义字段设置 的Service
 * @date: 2018/7/31 17:02
 */

@Service
public class BasicParamServiceImpl implements BasicParamService {

    @Autowired
    private BasicParamDao basicParamDao;

    /**
     * 根据主键删除一条 或多条 自定义字段设置记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delCustom(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        basicParamDao.delCustom(idArray);
    }

    /**
     * 删除 一条或多条 读者证类型
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delReaderCard(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        basicParamDao.delReaderCard(idArray);
    }

    /**
     * 新增   或修改 一条自定义字段设置  数据记录
     * @param   BasicParamEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(BasicParamEntity BasicParamEntity){

        if(basicParamDao.existsValue(BasicParamEntity.getType(),BasicParamEntity.getValue(),BasicParamEntity.getId(),BasicParamEntity.getLibraryId()) > 0){
            return 0;
        }else  if(ValidateUtils.numberORLetter(BasicParamEntity.getValue())){
            return 2;
        }else{
            if(BasicParamEntity != null && BasicParamEntity.getId()!= null) {
                basicParamDao.updateCustom(BasicParamEntity);
                return 1;
            }else{
                Long barCodeId = IdGen.randomLong();
                BasicParamEntity.setId(barCodeId);
                BasicParamEntity.setCreateDate(DateUtils.getDateTime());
                basicParamDao.insertCustom(BasicParamEntity);
                return 1;
            }
        }
    }

    /**
     * 新增   或修改 一条读者证类型  数据记录
     * @param   readerCardEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer savereaderCard(ReaderCardEntity readerCardEntity){
        if(basicParamDao.existsReaderValue(readerCardEntity.getValue(),readerCardEntity.getId(),readerCardEntity.getLibraryId()) > 0){
            return 0;
        }else if(ValidateUtils.numberORLetter(readerCardEntity.getValue())){
            return 2;
        }else{
            if(readerCardEntity != null && readerCardEntity.getId()!= null) {
                basicParamDao.updateReaderCard(readerCardEntity);
                return 1;
            }else{
                Long barCodeId = IdGen.randomLong();
                readerCardEntity.setId(barCodeId);
                readerCardEntity.setCreateDate(DateUtils.getDateTime());
                basicParamDao.insertReaderCard(readerCardEntity);
                return 1;
            }
        }
    }

}
