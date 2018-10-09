package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.Acceptance;
import com.szcti.lcloud.catalog.entity.AcceptanceCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcceptanceService {

    int deleteByPrimaryKey(Long id);

    int insert(Acceptance record);

    List<Acceptance> selectByExample(AcceptanceCriteria example);

    Acceptance selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Acceptance record);

    void refleshStatus(String acceptCode);
}