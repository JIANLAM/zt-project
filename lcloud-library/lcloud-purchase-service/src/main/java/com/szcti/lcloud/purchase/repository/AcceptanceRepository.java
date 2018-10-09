package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Acceptance;
import com.szcti.lcloud.purchase.entity.AcceptanceCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AcceptanceRepository {

    int deleteByPrimaryKey(Long id);

    int insert(Acceptance record);

    List<Acceptance> selectByExample(AcceptanceCriteria example);

    Acceptance selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Acceptance record);
}