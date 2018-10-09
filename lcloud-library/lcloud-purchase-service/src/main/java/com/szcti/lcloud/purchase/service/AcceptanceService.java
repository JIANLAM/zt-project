package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.Acceptance;
import com.szcti.lcloud.purchase.entity.AcceptanceCriteria;

import java.util.List;

public interface AcceptanceService {

    int deleteByPrimaryKey(Long id);

    int insert(Acceptance record);

    List<Acceptance> selectByExample(AcceptanceCriteria example);

    Acceptance selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Acceptance record);

    void refleshStatus(String acceptCode);
}