package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.LabelSet;
import com.szcti.lcloud.catalog.entity.LabelSetCriteria;
import com.szcti.lcloud.catalog.entity.vo.LabelSetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LabelSetRepository {
    int deleteByPrimaryKey(Long id);

    int insert(LabelSet record);

    List<LabelSet> selectByExample(LabelSetCriteria example);

    LabelSet selectByPrimaryKey(Long id);

    int updateByPrimaryKey(LabelSet record);

    List<LabelSetVO> queryPage(LabelSetVO vo);
}