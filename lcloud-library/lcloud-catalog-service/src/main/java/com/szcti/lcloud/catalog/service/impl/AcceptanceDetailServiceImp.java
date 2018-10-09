package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.AcceptanceDetail;
import com.szcti.lcloud.catalog.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.catalog.entity.vo.AcceptanceDetailVO;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.repository.AcceptanceDetailRepository;
import com.szcti.lcloud.catalog.service.AcceptanceDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("acceptanceDetailService")
public class AcceptanceDetailServiceImp implements AcceptanceDetailService {
    @Autowired
    AcceptanceDetailRepository acceptanceDetailRepository;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return acceptanceDetailRepository.deleteByPrimaryKey( id);
    }

    @Override
    public int insert(AcceptanceDetail record) {
        return acceptanceDetailRepository.insert( record);
    }

    @Override
    public List<AcceptanceDetail> selectByExample(AcceptanceDetailCriteria example) {
        return acceptanceDetailRepository.selectByExample( example);
    }

    @Override
    public AcceptanceDetail selectByPrimaryKey(Long id) {
        return acceptanceDetailRepository.selectByPrimaryKey( id);
    }

    @Override
    public int updateByPrimaryKey(AcceptanceDetail record) {
        return acceptanceDetailRepository.updateByPrimaryKey( record);
    }

    @Override
    public List<AcceptanceDetailVO> queryPage(AcceptanceDetailVO vo) {
        return acceptanceDetailRepository.queryPage( vo);
    }
    private void setParameter(AcceptanceDetailVO vo) {
        if(vo!=null&&vo.getSearchValue()!=null&&!vo.getSearchValue().equals("")){
            if(("isbn").equals(vo.getSearchKey())){
                vo.setIsbn(vo.getSearchValue());
            }
            if(("id").equals(vo.getSearchKey())){
                vo.setId(Long.parseLong(vo.getSearchValue()));
            }
            if(("title").equals(vo.getSearchKey())){
                vo.setTitle(vo.getSearchValue());
            }
            if(("bookType").equals(vo.getSearchKey())){
                vo.setBookType(vo.getSearchValue());
            }
            if(("barcode").equals(vo.getSearchKey())){
                vo.setBarcode(vo.getSearchValue());
            }
            if(("callNo").equals(vo.getSearchKey())){
                vo.setCallNo(vo.getSearchValue());
            }
            if(("author").equals(vo.getSearchKey())){
                vo.setAuthor(vo.getSearchValue());
            }
        }
    }
}