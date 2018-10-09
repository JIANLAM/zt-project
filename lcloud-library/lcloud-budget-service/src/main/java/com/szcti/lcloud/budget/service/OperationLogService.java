package com.szcti.lcloud.budget.service;

import com.szcti.lcloud.budget.entity.OperationLog;

public interface OperationLogService {
    int save(Integer moduleId,
             String moduleName,
             String operationType,
             Long libraryId,
             Long userId,
             String ip,
             String opContent,
             String remark);
    int insert(OperationLog record);
    OperationLog getUserInfo(OperationLog o);
    OperationLog getUserInfo(Long userId);
}