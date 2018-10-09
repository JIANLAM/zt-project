package com.szcti.lcloud.newbook.repository;


import com.szcti.lcloud.newbook.entity.vo.NewBookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface NewBookRepository {
    List<NewBookVO> queryPage(NewBookVO newBookVO);
}