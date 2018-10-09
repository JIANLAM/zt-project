package com.szcti.lcloud.user.mapper;

import com.szcti.lcloud.user.entity.TRolePerm;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author dw
 * @since 2018-06-13
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface TRolePermMapper extends BaseMapper<TRolePerm> {

}
