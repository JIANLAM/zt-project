package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.LibraryAddress;
import com.szcti.lcloud.parameter.entity.LibraryAddressCriteria;
import com.szcti.lcloud.parameter.entity.vo.LibraryAddressVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LibraryAddressRepository {
    long countByExample(LibraryAddressCriteria example);

    int deleteByExample(LibraryAddressCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(LibraryAddress record);

    int insertSelective(LibraryAddress record);

    List<LibraryAddress> selectByExample(LibraryAddressCriteria example);

    LibraryAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LibraryAddress record, @Param("example") LibraryAddressCriteria example);

    int updateByExample(@Param("record") LibraryAddress record, @Param("example") LibraryAddressCriteria example);

    int updateByPrimaryKeySelective(LibraryAddress record);

    int updateByPrimaryKey(LibraryAddress record);
    List<LibraryAddressVO> queryPage(LibraryAddressVO libraryAddressVO);
}