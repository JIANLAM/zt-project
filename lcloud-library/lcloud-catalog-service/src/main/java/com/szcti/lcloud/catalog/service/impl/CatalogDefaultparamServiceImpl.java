package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.CatalogDefaultparam;
import com.szcti.lcloud.catalog.repository.CatalogDefaultparamDao;
import com.szcti.lcloud.catalog.service.CatalogDefaultparamService;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 普通编目  默认参数设置 ServiceImpi
 * @Description: 普通编目  默认参数设置 的ServiceImpi
 * @author: wangsiyi
 * @date: 2018/9/3 03:24
 */

@Service
public class CatalogDefaultparamServiceImpl implements CatalogDefaultparamService {

    @Autowired
    private CatalogDefaultparamDao catalogDefaultparamDao;

    /**
     * 新增   或修改 一条条码分区参数 数据记录
     * @param   catalogDefaultparam
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CatalogDefaultparam catalogDefaultparam) {

        if (catalogDefaultparam != null && catalogDefaultparam.getId() != null) {
            catalogDefaultparam.setUpdateTime(DateUtils.getDateTime());
            catalogDefaultparam.setUpdateBy(catalogDefaultparam.getCurrUser());
            catalogDefaultparamDao.update(catalogDefaultparam);
        } else {
            Long barCodeId = IdGen.randomLong();
            catalogDefaultparam.setId(barCodeId);
            catalogDefaultparam.setCreateTime(DateUtils.getDateTime());
            catalogDefaultparamDao.insert(catalogDefaultparam);
        }
    }
}
