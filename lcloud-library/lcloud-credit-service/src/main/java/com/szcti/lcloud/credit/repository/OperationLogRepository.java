package com.szcti.lcloud.credit.repository;

import com.szcti.lcloud.credit.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogRepository {
    int insert(OperationLog record);
    OperationLog getUserInfo(OperationLog vo);
}