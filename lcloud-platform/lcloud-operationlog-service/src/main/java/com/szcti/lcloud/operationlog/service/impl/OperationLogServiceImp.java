package com.szcti.lcloud.operationlog.service.impl;

import com.szcti.lcloud.operationlog.entity.OperationLog;
import com.szcti.lcloud.operationlog.entity.vo.OperationLogVO;
import com.szcti.lcloud.operationlog.repository.OperationLogRepository;
import com.szcti.lcloud.operationlog.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("operationLogService")
public class OperationLogServiceImp implements OperationLogService {
@Autowired
OperationLogRepository operationLogRepository;

    @Override
    public int save(Integer moduleId, String moduleName, String operationType, Long libraryId, Long userId, String ip, String opContent, String remark) {
        OperationLog o=new OperationLog();
        o.setModuleId(moduleId);
        o.setModuleName(moduleName);
        o.setOperationType(operationType);
        o.setLibraryId(libraryId);
        o.setUserId(userId);
        o.setIp(ip);
        o.setOpContent(opContent);
        o.setRemark(remark);
        return insert(o);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(OperationLog record) {
        record.setCreateTime(new Date());
        return operationLogRepository.insert(record);
    }

    @Override
    public OperationLog getUserInfo(OperationLog vo) {
        return operationLogRepository.getUserInfo(vo);
    }

    @Override
    public List<OperationLogVO> queryPage(OperationLogVO record) {
        return operationLogRepository.queryPage(record);
    }
    @Override
    public OperationLog getUserInfo(Long userId) {
        OperationLog o=new OperationLog();
        o.setUserId(userId);
        return getUserInfo(o);
    }
}