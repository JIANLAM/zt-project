package com.szcti.lcloud.mycollection.service;


import com.szcti.lcloud.mycollection.entity.vo.CollectionVO;

/**
 * @Title: 个人收藏Service
 * @Description: 处理收藏书籍的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface CollectionService {

    /**
     * 新增一条收藏
     * @param collectionVO
     * @return String
     */
    String save(CollectionVO collectionVO);

    /**
     * 批量删除
     * @param collectionIds
     */
    void delete(String collectionIds);

}
