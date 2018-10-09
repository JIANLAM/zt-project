package com.szcti.lcloud.holding.service;

import com.szcti.lcloud.holding.entity.vo.BookCopyVO;

/**
 * @Title: 馆藏管理Service
 * @Description: 馆藏管理Service
 * @author: fengda
 * @date: 2018/7/26 8:50
 */
public interface HoldingService {

    /**
     * 批量删除馆藏书籍
     * @param ids
     * @param userId
     * @param libId
     * @param ip
     * @return Integer
     */
    Integer delete(String ids,Long userId,Long libId,String ip);

    /**
     * 批量修改馆藏书籍
     * @param bookCopyVO
     * @param ids
     * @param userId
     * @param libId
     * @param ip
     * @return
     */
    Integer update(BookCopyVO bookCopyVO, String ids,Long userId,Long libId,String ip);

    /**
     * 根据主键查询单个副本
     * @param id
     * @return
     */
    BookCopyVO get(Long id);


}
