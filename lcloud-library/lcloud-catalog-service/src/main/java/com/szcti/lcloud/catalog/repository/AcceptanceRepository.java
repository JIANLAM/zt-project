package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.Acceptance;
import com.szcti.lcloud.catalog.entity.AcceptanceCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AcceptanceRepository {

    int deleteByPrimaryKey(Long id);

    int insert(Acceptance record);

    List<Acceptance> selectByExample(AcceptanceCriteria example);

    Acceptance selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Acceptance record);
}