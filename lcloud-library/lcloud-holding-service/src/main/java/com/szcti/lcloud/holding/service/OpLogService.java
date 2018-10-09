package com.szcti.lcloud.holding.service;

import com.szcti.lcloud.holding.entity.vo.BookCopyVO;
import com.szcti.lcloud.holding.entity.vo.OpLogVO;

/**
 * @Title: 馆藏管理操作日志Service
 * @Description: 馆藏管理操作日志Service
 * @author: fengda
 * @date: 2018/7/26 8:50
 */
public interface OpLogService {

    /**
     * 插入一条删除操作日志
     * @param idArray
     * @param opLogVO
     */
    void opDelete(Long[] idArray,OpLogVO opLogVO);

    /**
     * 插入一条修改操作日志
     * @param idArray
     * @param opLogVO
     */
    void opUpdate(Long[] idArray,OpLogVO opLogVO);


}
