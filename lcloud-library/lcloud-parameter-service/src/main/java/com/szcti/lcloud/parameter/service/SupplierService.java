package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.Supplier;
import com.szcti.lcloud.parameter.entity.SupplierCriteria;
import com.szcti.lcloud.parameter.entity.vo.SupplierVO;

import java.util.List;

public interface SupplierService {
    long countByExample(SupplierCriteria example);

    int deleteByExample(SupplierCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierCriteria example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(Supplier record,SupplierCriteria example);

    int updateByExample(Supplier record,SupplierCriteria example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    List<SupplierVO> queryPage(SupplierVO supplierVO);
}