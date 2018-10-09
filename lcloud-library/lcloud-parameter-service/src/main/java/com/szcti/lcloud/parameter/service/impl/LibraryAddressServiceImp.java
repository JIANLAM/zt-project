package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.parameter.entity.LibraryAddress;
import com.szcti.lcloud.parameter.entity.LibraryAddressCriteria;
import com.szcti.lcloud.parameter.entity.vo.LibraryAddressVO;
import com.szcti.lcloud.parameter.repository.LibraryAddressRepository;
import com.szcti.lcloud.parameter.service.LibraryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("libraryAddressService")
public class LibraryAddressServiceImp implements LibraryAddressService {
    
    @Autowired
    LibraryAddressRepository libraryAddressRepository;
    @Override
    public long countByExample(LibraryAddressCriteria example) {
        return libraryAddressRepository.countByExample( example);
    }

    @Override
    public int deleteByExample(LibraryAddressCriteria example) {
        return libraryAddressRepository.deleteByExample( example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return libraryAddressRepository.deleteByPrimaryKey( id);
    }

    @Override
    public int insert(LibraryAddress record) {
        return libraryAddressRepository.insert( record);
    }

    @Override
    public int insertSelective(LibraryAddress record) {
        return libraryAddressRepository.insertSelective( record);
    }

    @Override
    public List<LibraryAddress> selectByExample(LibraryAddressCriteria example) {
        return libraryAddressRepository.selectByExample( example);
    }

    @Override
    public LibraryAddress selectByPrimaryKey(Long id) {
        return libraryAddressRepository.selectByPrimaryKey( id);
    }

    @Override
    public int updateByExampleSelective(LibraryAddress record, LibraryAddressCriteria example) {
        return libraryAddressRepository.updateByExampleSelective( record,  example);
    }

    @Override
    public int updateByExample(LibraryAddress record, LibraryAddressCriteria example) {
        return libraryAddressRepository.updateByExample( record,  example);
    }

    @Override
    public int updateByPrimaryKeySelective(LibraryAddress record) {
        return libraryAddressRepository.updateByPrimaryKeySelective( record);
    }

    @Override
    public int updateByPrimaryKey(LibraryAddress record) {
        return libraryAddressRepository.updateByPrimaryKey( record);
    }

    @Override
    public List<LibraryAddressVO> queryPage(LibraryAddressVO libraryAddressVO) {
        return libraryAddressRepository.queryPage(libraryAddressVO);
    }
}
