package com.szcti.lcloud.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szcti.lcloud.user.entity.TOrganizationType;

/**
 * <p>
 * 机构类别 Mapper 接口
 * </p>
 *
 * @author dw
 * @since 2018-06-25
 */
@Mapper
//	@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface TOrganizationTypeMapper extends BaseMapper<TOrganizationType> {

}
