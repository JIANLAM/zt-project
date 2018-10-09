package com.szcti.lcloud.user.mapper;

import com.szcti.lcloud.user.entity.TRolePermButton;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色权限按钮 Mapper 接口
 * </p>
 *
 * @author dw
 * @since 2018-06-26
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface TRolePermButtonMapper extends BaseMapper<TRolePermButton> {

}
