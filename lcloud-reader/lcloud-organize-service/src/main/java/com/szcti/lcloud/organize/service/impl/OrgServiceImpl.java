package com.szcti.lcloud.organize.service.impl;

import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.TreeUtils;
import com.szcti.lcloud.organize.entity.OrgEntity;
import com.szcti.lcloud.organize.repository.OrgDao;
import com.szcti.lcloud.organize.service.OrgService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/6/6 8:53
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao orgDao;

    @Override
    @Transactional(readOnly = true)
    public List orgTree(OrgEntity org){
        List<OrgEntity> list = orgDao.findList(org);
        return TreeUtils.formatTree(list,true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OrgEntity org){
        if(org!=null&&org.getId()!=null){
            OrgEntity oldOrg = orgDao.get(org.getId());
            String oldParentIds = oldOrg.getParentIds();

            OrgEntity query = new OrgEntity();
            query.setId(org.getId());
            List<OrgEntity> orgList = orgDao.findList(query);
            //判断是否有子级
            if(orgList!=null&&orgList.size()>1){
                for (OrgEntity bean:orgList) {
                    orgDao.updatePrtIds(bean.getId(),bean.getParentIds().replace(oldParentIds,org.getParentIds()));
                }
            }
            orgDao.update(org);
        }else{
            org.setId(IdGen.randomLong());
            orgDao.insert(org);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull Long orgId){
        OrgEntity org = new OrgEntity();
        org.setId(orgId);
        List<OrgEntity> orgList = orgDao.findList(org);
        Long [] ids = new Long[orgList.size()];
        for (int i=0;i<orgList.size();i++){
            ids[i] = orgList.get(i).getId();
        }
        orgDao.delete(ids);
    }

}
