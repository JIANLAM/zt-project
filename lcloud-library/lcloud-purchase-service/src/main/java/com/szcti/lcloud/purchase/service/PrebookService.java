package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.Prebook;
import com.szcti.lcloud.purchase.entity.PrebookCriteria;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;

import java.util.List;

/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
public interface PrebookService {
    ///PageUtils queryPage(Map<String, Object> params);
    Prebook selectByPrimaryKey(Long id);

    List<Prebook> selectByExampleWithBLOBs(PrebookCriteria criteria);

    List<Prebook> selectByExample(PrebookCriteria criteria);
    int updateByPrimaryKey(Prebook prebook);

    List<PrebookVO> queryPage(PrebookVO prebookVO);

    int deleteBatchIds(List<String> strings);

    int insert(Prebook prebook);

}

