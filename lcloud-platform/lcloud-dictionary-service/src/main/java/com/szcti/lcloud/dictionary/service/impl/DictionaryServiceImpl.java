package com.szcti.lcloud.dictionary.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.dictionary.repository.DictionaryDao;
import com.szcti.lcloud.dictionary.service.DictionaryService;
import com.szcti.lcloud.dictionary.entity.DictionaryEntity;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 数据字典 Service
 * @Description: 数据字典 的Service
 * @date: 2018/7/30 10:26
 */

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 根据主键删除一条 或多条 字典数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delDictionary(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        dictionaryDao.delDictionary(idArray);
    }

    /**
     * 新增   或修改 一条字典数据  数据记录
     * @param   dictionaryEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(DictionaryEntity dictionaryEntity){
        if(dictionaryDao.existsValue(dictionaryEntity.getType(),dictionaryEntity.getValue(),dictionaryEntity.getId()) > 0){
            return 0;
        }else if(ValidateUtils.numberORLetter(dictionaryEntity.getValue())){
            return 2;
        }else{
            if(dictionaryEntity != null && dictionaryEntity.getId()!= null) {
                dictionaryDao.updateTitleNumber(dictionaryEntity);
                return 1;
            }else{
                Long barCodeId = IdGen.randomLong();
                dictionaryEntity.setId(barCodeId);
                dictionaryEntity.setCreateDate(DateUtils.getDateTime());
                dictionaryDao.insertTitleNumber(dictionaryEntity);
                return 1;
            }
        }

    }

    /**
     * 查询字典     用MAP分类 返回所有数据
     * @param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String , Object> findListMap(){
        Map<String , Object> resultMap = new LinkedHashMap<>(16);
        //拿到数据字典所有类型
        List<String> dictTypeList = dictionaryDao.findTypeList();
        //拿到相应类型的 集合数据
        List<DictionaryEntity> DictionaryEntityList = null;
        //查询条件
        DictionaryEntity entity = null;
        for (String item : dictTypeList) {
            entity = new DictionaryEntity();
            entity.setType(item);
            DictionaryEntityList = dictionaryDao.findList(entity);
            resultMap.put(item , DictionaryEntityList);
        }
        return resultMap;
    }

}
