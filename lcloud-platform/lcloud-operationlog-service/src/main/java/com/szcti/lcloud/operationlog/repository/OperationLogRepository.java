package com.szcti.lcloud.operationlog.repository;

import com.szcti.lcloud.operationlog.entity.OperationLog;
import com.szcti.lcloud.operationlog.entity.vo.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogRepository {
    int insert(OperationLog record);
    OperationLog getUserInfo(OperationLog vo);
    List<OperationLogVO> queryPage(OperationLogVO record);
}