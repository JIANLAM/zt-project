package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.Supplier;
import com.szcti.lcloud.parameter.entity.SupplierCriteria;
import com.szcti.lcloud.parameter.entity.vo.SupplierVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SupplierRepository {
    long countByExample(SupplierCriteria example);

    int deleteByExample(SupplierCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierCriteria example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierCriteria example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierCriteria example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
    List<SupplierVO> queryPage(SupplierVO supplierVO);
}