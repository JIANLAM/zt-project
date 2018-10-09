package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.parameter.entity.Supplier;
import com.szcti.lcloud.parameter.entity.SupplierCriteria;
import com.szcti.lcloud.parameter.entity.vo.SupplierVO;
import com.szcti.lcloud.parameter.repository.SupplierRepository;
import com.szcti.lcloud.parameter.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("supplierService")
public class SupplierServiceImp implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Override
    public long countByExample(SupplierCriteria example) {
        return supplierRepository.countByExample(example);
    }

    @Override
    public int deleteByExample(SupplierCriteria example) {
        return supplierRepository.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return supplierRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Supplier record) {
        return supplierRepository.insert(record);
    }

    @Override
    public int insertSelective(Supplier record) {
        return supplierRepository.insertSelective(record);
    }

    @Override
    public List<Supplier> selectByExample(SupplierCriteria example) {
        return supplierRepository.selectByExample(example);
    }

    @Override
    public Supplier selectByPrimaryKey(Long id) {
        return supplierRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Supplier record, SupplierCriteria example) {
        return supplierRepository.updateByExampleSelective( record, example);
    }

    @Override
    public int updateByExample(Supplier record, SupplierCriteria example) {
        return supplierRepository.updateByExample( record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Supplier record) {
        return supplierRepository.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Supplier record) {
        if(ValidateUtils.numberORLetter(record.getCoding())){
            return 0;
        }
        return supplierRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<SupplierVO> queryPage(SupplierVO supplierVO) {
        return supplierRepository.queryPage(supplierVO);
    }
}
