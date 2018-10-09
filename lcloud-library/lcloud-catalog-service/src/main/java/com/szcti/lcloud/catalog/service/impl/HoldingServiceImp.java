package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.Holding;
import com.szcti.lcloud.catalog.entity.HoldingCriteria;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;
import com.szcti.lcloud.catalog.repository.HoldingRepository;
import com.szcti.lcloud.catalog.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("holdingService")
public class HoldingServiceImp implements HoldingService {
	@Autowired
	HoldingRepository holdingRepository;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return holdingRepository.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Holding record) {
		return holdingRepository.insert(record);
	}

	@Override
	public List<Holding> selectByExample(HoldingCriteria example) {
		return holdingRepository.selectByExample(example);
	}

	@Override
	public Holding selectByPrimaryKey(Long id) {
		return holdingRepository.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Holding record) {
		return holdingRepository.updateByPrimaryKey(record);
	}

	@Override
	public List<HoldingVO> queryPage(HoldingVO vo) {
		return holdingRepository.queryPage(vo);
	}

	@Override
	public LableVO getMaxBarcode(LableVO lableVO) {
		LableVO l = null;
		List<LableVO> list = holdingRepository.getMaxBarcode(lableVO);
		if (list != null && list.size() > 0) {
			l = list.get(0);
		}
		return l;
	}

	@Override
	public List<HoldingVO> getPrintLabel(LableVO lableVO) {
		return holdingRepository.getPrintLabel(lableVO);
	}

	@Override
	public LableVO queryCurrBarcode(LableVO lableVO) {
		LableVO l = null;
		//l = holdingRepository.queryCurrBarcode(lableVO);
		return l;
	}

    @Override
    public List<HoldingVO> queryHolding(HoldingVO vo)
    {
        return holdingRepository.queryHoldingVO(vo);
    }

}