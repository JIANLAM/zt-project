package com.szcti.lcloud.catalog.repository;

import org.apache.ibatis.annotations.Mapper;

import com.szcti.lcloud.catalog.entity.Holding;
import com.szcti.lcloud.catalog.entity.vo.LableVO;
@Mapper
public interface BarcodeRepository {
	 LableVO queryCurrBarcode(LableVO lableVO);
	 
	 int updateCurrBarcode(Holding holding);

}
