package com.szcti.lcloud.mycollection.repository;

import com.szcti.lcloud.mycollection.entity.vo.CollectionVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 个人收藏信息DAO
 * @Description: 处理个人收藏书籍的操作
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CollectionDao {
    /**
     * 根据条件查找用户的个人收藏书籍
     * @param collectionVO
     * @return List<CollectionVO>
     */
    List<CollectionVO> findList(CollectionVO collectionVO);

    /**
     * 根据preBookId和readerId查找个人收藏书籍
     * @param preBookId
     * @param readerId
     * @return CollectionVO
     */
    CollectionVO get(@Param("preBookId") Long preBookId,@Param("readerId") Long readerId);

    /**
     * 查看收藏图书每本书的详情
     * @param collectFrom，bookId
     * @return CollectionVO
     */
    CollectionVO getInfo(@Param("collectFrom") Long collectFrom,@Param("bookId") Long bookId);

    /**
     * 新增一条收藏
     * @param collectionVO
     */
    void insert(CollectionVO collectionVO);

    /**
     * 删除个人收藏书籍
     * @param collectionIds
     */
    void delete(@Param("collectionIds") Long[] collectionIds);

    /**
     * 查询每本书被收藏的次数
     * @param preBookId,collectFrom
     * @return List<CollectionVO>
     */
    Integer bookCollectionCount(@Param("preBookId") Long preBookId,@Param("collectFrom") Long collectFrom);

}
