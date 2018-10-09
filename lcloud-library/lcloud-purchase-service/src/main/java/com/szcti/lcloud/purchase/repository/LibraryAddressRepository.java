package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.LibraryAddress;
import com.szcti.lcloud.purchase.entity.vo.LibraryAddressVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface LibraryAddressRepository {
    LibraryAddress selectByPrimaryKey(Long id);
    List<LibraryAddressVO> queryPage(LibraryAddressVO libraryAddressVO);
    List<HashMap<String,Object>> queryMapList(Map params);
}