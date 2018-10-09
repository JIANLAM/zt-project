package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.OperationLog;
import com.szcti.lcloud.purchase.entity.vo.OperationLogVO;
import java.util.List;

public interface OperationLogService {
    int save( Integer moduleId,
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
}