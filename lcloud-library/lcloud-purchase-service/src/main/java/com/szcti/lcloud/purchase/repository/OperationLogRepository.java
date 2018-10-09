package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.OperationLog;
import com.szcti.lcloud.purchase.entity.vo.OperationLogVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OperationLogRepository {
    int insert(OperationLog record);
    OperationLog getUserInfo(OperationLog vo);
}