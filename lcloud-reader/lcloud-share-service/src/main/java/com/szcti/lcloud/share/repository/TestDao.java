package com.szcti.lcloud.share.repository;

import com.szcti.lcloud.share.entity.Test;
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
public interface TestDao {
    /**
     * 查询所有
     * @return
     */
    List<Test> findAll();
	
}
