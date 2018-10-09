package com.szcti.lcloud.catalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szcti.lcloud.catalog.entity.vo.LableVO;
import com.szcti.lcloud.catalog.repository.BarcodeRepository;
import com.szcti.lcloud.catalog.service.BarcodeService;

@Service("BarcodeService")
public class BarcodeServiceImpl implements BarcodeService {

	@Autowired
	BarcodeRepository barcodeRepository;

	@Override
	public LableVO queryCurrBarcode(LableVO lableVO) {
		LableVO l = null;
		l = barcodeRepository.queryCurrBarcode(lableVO);
		return l;
	}

}
