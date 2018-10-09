package com.szcti.lcloud.helplendback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.szcti.lcloud.helplendback.entity.Test;


/**
 *
 * 
 * @author fengda
 * @email ${email}
 * @date 2018-05-04 17:03:30
 */
@Mapper
public interface TestDao {
    /**
     * 查询所有
     * @return
     */
	List<Test> findAll();

}
