package com.szcti.lcloud.account.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szcti.lcloud.account.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * 
 * @author fengda
 * @email ${email}
 * @date 2018-05-04 17:03:30
 */
@Mapper
public interface TestDao extends BaseMapper<Test> {
    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
	
}
