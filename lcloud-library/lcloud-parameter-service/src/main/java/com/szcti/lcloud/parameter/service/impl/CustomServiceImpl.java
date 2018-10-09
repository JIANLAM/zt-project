package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.parameter.entity.CustomTypeEntity;
import com.szcti.lcloud.parameter.entity.vo.CustomVO;
import com.szcti.lcloud.parameter.repository.CustomDao;
import com.szcti.lcloud.parameter.service.CustomService;
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
public class CustomServiceImpl implements CustomService {

    @Autowired
    private CustomDao customDao;

    /**
     * 根据主键删除一条 或多条 自定义字段设置记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delCustom(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        customDao.delCustom(idArray);
    }

    /**
     * 新增   或修改 一条自定义字段设置  数据记录
     * @param   customVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(CustomVO customVO){
        if(customDao.existsValue(customVO.getType(),customVO.getValue(),customVO.getId(),customVO.getLibraryId()) > 0){
            return 0;
        }else if(ValidateUtils.numberORLetter(customVO.getValue())){
            return 2;
        }else{
            if(customVO != null && customVO.getId()!= null) {
                customDao.updateCustom(customVO);
                return 1;
            }else{
            /*
            if(customDao.findTypeList(customVO.getType(),customVO.getLibraryId()) == null){
                Long typeid = IdGen.randomLong();
                CustomTypeEntity entity = new CustomTypeEntity();
                entity.setId(typeid);
                entity.setType(customVO.getType());
                entity.setDescription(customVO.getDescription());
                entity.setLibraryId(customVO.getLibraryId());
                customDao.insertCustomType(entity);
                customVO.setCustomTypeId(entity.getId());
            }else{
                customDao.updateCustomType(customVO.getLibraryId(),customVO.getType(),customVO.getDescription());
            }
*/
                Long barCodeId = IdGen.randomLong();
                customVO.setId(barCodeId);
                customVO.setCreateDate(DateUtils.getDateTime());
                customDao.insertCustom(customVO);
                return 1;
            }
        }

    }

    /**
     * 新增   或修改 自定义字段四五六    设置 数据记录
     * @param customTypeEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSetup(CustomTypeEntity customTypeEntity){
        if(customDao.findTypeList(customTypeEntity.getType(),customTypeEntity.getLibraryId()) == null){
            Long barCodeId = IdGen.randomLong();
            customTypeEntity.setId(barCodeId);
            customDao.insertCustomType(customTypeEntity);
        }else {
            customDao.updateCustomType(customTypeEntity.getLibraryId(),customTypeEntity.getDescription(),customTypeEntity.getType());
        }
    }

}
