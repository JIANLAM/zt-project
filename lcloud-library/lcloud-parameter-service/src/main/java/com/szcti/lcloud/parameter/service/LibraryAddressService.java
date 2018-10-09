package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.LibraryAddress;
import com.szcti.lcloud.parameter.entity.LibraryAddressCriteria;
import com.szcti.lcloud.parameter.entity.vo.LibraryAddressVO;

import java.util.List;

public interface LibraryAddressService {
    long countByExample(LibraryAddressCriteria example);

    int deleteByExample(LibraryAddressCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(LibraryAddress record);

    int insertSelective(LibraryAddress record);

    List<LibraryAddress> selectByExample(LibraryAddressCriteria example);

    LibraryAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(LibraryAddress record,LibraryAddressCriteria example);

    int updateByExample(LibraryAddress record,LibraryAddressCriteria example);

    int updateByPrimaryKeySelective(LibraryAddress record);

    int updateByPrimaryKey(LibraryAddress record);

    List<LibraryAddressVO> queryPage(LibraryAddressVO libraryAddressVO);
}