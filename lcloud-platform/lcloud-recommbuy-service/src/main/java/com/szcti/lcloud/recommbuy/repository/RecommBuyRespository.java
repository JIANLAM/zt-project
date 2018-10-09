package com.szcti.lcloud.recommbuy.repository;

import com.szcti.lcloud.recommbuy.entity.vo.RecommBuyVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 荐购表
 * 
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface RecommBuyRespository {


    /**
     * 根据条件查找用户的荐购信息
     * @param recommBuyVO
     * @return List<RecommBuyVO>
     */
    List<RecommBuyVO> findList(RecommBuyVO recommBuyVO);

    /**
     * 新增一条荐购信息
     * @param recommBuyVO
     */
    void insert(RecommBuyVO recommBuyVO);

    /**
     * 根据prebook书籍的主键preBookId和读者主键readerId查询一条荐购记录
     * @param preBookId
     * @param readerId
     * @return RecommBuyVO
     */
    RecommBuyVO get(@Param("preBookId") Long preBookId,@Param("readerId") Long readerId);
    /**
     * 图书馆端，荐购查询方法
     * @param recommBuyVO
     * @return List
     */
    List<RecommBuyVO> queryPage(RecommBuyVO recommBuyVO);
    /**
     * 图书馆端，荐购查询方法
     * @param vo 要导出的prebookId 荐购单
     * @return List
     */
    List<RecommBuyVO> exportExcel(RecommBuyVO vo);


    /*********************************     微信端  荐购     ************************************/
    /**
     * 查找用户的荐购信息
     * @param recommbuyVO
     * @return List<RecommBuyVO>
     */
    List<RecommBuyVO> weChatFindList(RecommBuyVO recommbuyVO);
}
