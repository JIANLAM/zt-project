package com.szcti.lcloud.booksearch.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szcti.lcloud.booksearch.entity.TSearchDouban;

/**
 * <p>
 * 豆瓣搜索结果缓存 Mapper 接口
 * </p>
 *
 * @author dw
 * @since 2018-08-13
 */
@Mapper
public interface TSearchDoubanMapper extends BaseMapper<TSearchDouban> {

}
