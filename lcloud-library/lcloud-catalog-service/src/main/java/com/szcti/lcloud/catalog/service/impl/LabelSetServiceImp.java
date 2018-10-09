package com.szcti.lcloud.catalog.service.impl;

import com.szcti.lcloud.catalog.entity.LabelSet;
import com.szcti.lcloud.catalog.entity.LabelSetCriteria;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LabelSetVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;
import com.szcti.lcloud.catalog.repository.HoldingRepository;
import com.szcti.lcloud.catalog.repository.LabelSetRepository;
import com.szcti.lcloud.catalog.service.LabelSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("labelSetService")
public class LabelSetServiceImp implements LabelSetService {
    @Autowired
    LabelSetRepository labelSetRepository;
    @Autowired
    HoldingRepository holdingRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return labelSetRepository.deleteByPrimaryKey( id);
    }

    @Override
    public int insert(LabelSet record) {
        return labelSetRepository.insert( record);
    }

    @Override
    public List<LabelSet> selectByExample(LabelSetCriteria example) {
        return labelSetRepository.selectByExample( example);
    }

    @Override
    public LabelSet selectByPrimaryKey(Long id) {
        return labelSetRepository.selectByPrimaryKey( id);
    }

    @Override
    public int updateByPrimaryKey(LabelSet record) {
        return labelSetRepository.updateByPrimaryKey( record);
    }

    @Override
    public List<LabelSetVO> queryPage(LabelSetVO vo) {
        return labelSetRepository.queryPage( vo);
    }
    @Override
    public List<LableVO> getPrintLabel(LableVO lableVO) {
        List<HoldingVO> hlist=holdingRepository.getPrintLabel(lableVO);
        List<LableVO> list =new ArrayList<LableVO>();
        LabelSet labelSet=null;
        if(lableVO.getLabelSetId()==null){
            LabelSetCriteria lc=new LabelSetCriteria();
            LabelSetCriteria.Criteria cc=lc.createCriteria();
            if(lableVO.getLibraryId()!=null&&lableVO.getLibraryId()>0){
                cc.andLibraryIdEqualTo(lableVO.getLibraryId());
            }
            if(lableVO.getLabelSetId()!=null&&lableVO.getLabelSetId()>0){
                cc.andIdEqualTo(lableVO.getLabelSetId());
            }
            List<LabelSet> laList=selectByExample(lc);
            if(laList!=null&&laList.size()>0){
                labelSet=laList.get(0);
            }
        }
        for(HoldingVO h:hlist){
            LableVO lv=new LableVO();
            lv.setHoldingId(h.getId());
            lv.setTitle(h.getTitle());
            lv.setAuthor(h.getAuthor());
            lv.setBarcode(h.getBarcode());
            lv.setCallNo(h.getCallNo());
            lv.setCurrSeednumber(h.getCurrSeednumber());
            lv.setSeriesNo(h.getSeriesNo());
            if(labelSet!=null) {
                lv.setOne(setRow(h, labelSet.getOneRow()));
                lv.setTwo(setRow(h, labelSet.getTwoRow()));
                lv.setThree(setRow(h, labelSet.getThreeRow()));
                lv.setFour(setRow(h, labelSet.getFourRow()));
            }
            list.add(lv);
        }
        return list;
    }

    private String  setRow(HoldingVO h, String oneRow) {
        int caseRow=0;
        if("barcode".equals(oneRow)){
            caseRow=1;
        }
        if("callNo".equals(oneRow)){
            caseRow=2;
        }
        if("currSeednumber".equals(oneRow)){
            caseRow=3;
        }
        if("seriesNo".equals(oneRow)){
            caseRow=4;
        }
        String textString="";
        if(caseRow>0){
            switch (caseRow) {
                case (1):
                    textString=h.getBarcode();
                    break;
                case (2):
                    textString=h.getCallNo();
                    break;
                case (3):
                    textString=h.getCurrSeednumber();
                    break;
                case (4):
                    textString=h.getSeriesNo();
                    break;
                default:
                    break;
            }
        }
        return textString;

    }
}