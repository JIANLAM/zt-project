package com.szcti.lcloud.user.mapper;

import com.szcti.lcloud.user.entity.TPerm;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author dw
 * @since 2018-06-29
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface TPermMapper extends BaseMapper<TPerm> {

}
