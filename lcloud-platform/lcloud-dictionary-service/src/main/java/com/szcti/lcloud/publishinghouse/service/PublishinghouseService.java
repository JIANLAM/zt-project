package com.szcti.lcloud.publishinghouse.service;


import com.szcti.lcloud.publishinghouse.entity.PublishinghouseEntity;

import java.util.HashMap;

/**
 * @Title: 出版社数据 Service
 * @Description: 出版社数据 的Service
 * @author: fengda
 * @date: 2018/8/27 3:32
 */
public interface PublishinghouseService {

        /**
         * 根据主键删除一条 或多条 出版社记录
         * @param ids
         */
        void delPublishing(String ids);

        /**
         * 新增   或修改 出版社 数据记录
         * @param   publishinghouseEntity
         * @return  Integer
         */
        Integer save(PublishinghouseEntity publishinghouseEntity);

        /**
         * 根据ISBN查询图书（出版社）信息
         * @param ISBN
         * @return
         */
        HashMap<String,Object> getByISBN(String ISBN);

}
