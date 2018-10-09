package com.szcti.lcloud.operationlog.service;

import com.szcti.lcloud.operationlog.entity.OperationLog;
import com.szcti.lcloud.operationlog.entity.vo.OperationLogVO;
import java.util.List;

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
    OperationLog getUserInfo(OperationLog vo);
    OperationLog getUserInfo(Long userId);
    List<OperationLogVO> queryPage(OperationLogVO record);
}