package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.LabelSet;
import com.szcti.lcloud.catalog.entity.LabelSetCriteria;
import com.szcti.lcloud.catalog.entity.vo.LabelSetVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;

import java.util.List;

public interface LabelSetService {
    int deleteByPrimaryKey(Long id);

    int insert(LabelSet record);

    List<LabelSet> selectByExample(LabelSetCriteria example);

    LabelSet selectByPrimaryKey(Long id);

    int updateByPrimaryKey(LabelSet record);

    List<LabelSetVO> queryPage(LabelSetVO vo);
    List<LableVO> getPrintLabel(LableVO lableVO);
}