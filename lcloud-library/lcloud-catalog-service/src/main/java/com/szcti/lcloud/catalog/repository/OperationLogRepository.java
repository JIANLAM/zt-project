package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogRepository {
    int insert(OperationLog record);
    OperationLog getUserInfo(OperationLog vo);
}