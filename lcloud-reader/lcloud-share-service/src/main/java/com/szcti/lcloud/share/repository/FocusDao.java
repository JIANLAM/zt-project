package com.szcti.lcloud.share.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * @Title: 粉丝关注DAO
 * @Description: 处理粉丝关注的操作
 * @author: fengda
 * @date: 2018/6/30 8:43
 */
@Mapper
public interface FocusDao {


    /**
     * 新增一条关注记录
     * @param focus
     * @param isFocused
     * @param createTime
     */
    void insert(@Param("focus") Long focus,@Param("isFocused") Long isFocused,@Param("createTime") String createTime);

    /**
     * 删除一条关注记录
     * @param focus
     * @param isFocused
     */
    void delete(@Param("focus") Long focus,@Param("isFocused") Long isFocused);

    /**
     * 查询关注记录
     * @param focus
     * @param isFocused
     * @return
     */
    List<HashMap> find(@Param("focus") Long focus,@Param("isFocused") Long isFocused);


    /**
     * 查询我的粉丝
     * @param readerId
     * @return
     */
    List<HashMap> findMyFans(Long readerId);

    /**
     * 查询我的关注
     * @param readerId
     * @return
     */
    List<HashMap> findMyFocus(Long readerId);

    /**
     * 查询我的关注数目，我的粉丝数目，我收到的赞数目
     * @param readerId
     * @param currentReaderId
     * @return
     */
    HashMap getCount(@Param("readerId")Long readerId,@Param("currentReaderId")Long currentReaderId);

}
