package com.szcti.lcloud.account.service.impl;

import com.szcti.lcloud.account.entity.SendeeEntity;
import com.szcti.lcloud.account.entity.vo.ReaderVO;
import com.szcti.lcloud.account.entity.vo.StaffVO;
import com.szcti.lcloud.account.entity.vo.UserInfoVO;
import com.szcti.lcloud.account.repository.SendeeDao;
import com.szcti.lcloud.account.repository.UserDao;
import com.szcti.lcloud.account.service.UserService;
import com.szcti.lcloud.common.utils.BeanUtil;
import com.szcti.lcloud.common.utils.IdGen;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.szcti.lcloud.common.utils.JSONUtil;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SendeeDao sendeeDao;

    @Override
    @Transactional(readOnly = true)
    public UserInfoVO getUserInfo(@NonNull Long userId){
        return userDao.getUserInfo(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map getReaderInfo(@NonNull Long userId){
        UserInfoVO user = userDao.getUserInfo(userId);
        ReaderVO reader = userDao.getReader(user.getPeopleId());
        SendeeEntity sendeeEntity = sendeeDao.getIsDefault(user.getPeopleId());
        Map map = new HashMap(16);
        if(user!=null&&reader!=null ){
            map.putAll(BeanUtil.transBean2Map(user));
            map.putAll(BeanUtil.transBean2Map(reader));
            if(sendeeEntity != null){
                map.putAll(BeanUtil.transBean2Map(sendeeEntity));
            }
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public Map getStaffInfo(@NonNull Long userId){
        UserInfoVO user = userDao.getUserInfo(userId);
        StaffVO staff = userDao.getStaff(user.getPeopleId());
        Map map = new HashMap(16);
        if(user!=null&&staff!=null){
            map.putAll(BeanUtil.transBean2Map(user));
            map.putAll(BeanUtil.transBean2Map(staff));
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String modifyPwd(@NonNull Long userId,@NonNull String oldPassword,@NonNull String newPassword){
        UserInfoVO userVO = getUserInfo(userId);
        String flag;
        if(userVO!=null&&oldPassword.equals(userVO.getPassword())){
            userDao.modifyPwd(userId,newPassword);
            flag = "success";
        }else{
            flag = "false";
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserInfo(UserInfoVO userVO){
        if(userVO!=null&&userVO.getUserId()!=null){
            userDao.updateUser(userVO);
            userDao.updatePeople(userVO);
        }else{
            Long userId = IdGen.randomLong();
            Long peopleId = IdGen.randomLong();
            userVO.setUserId(userId);
            userVO.setPeopleId(peopleId);
            userDao.insertUser(userVO);
            userDao.insertPeople(userVO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReader(String data){
        ReaderVO readerVO = new ReaderVO();
        UserInfoVO userInfoVO = new UserInfoVO();
        if(StringUtils.isNotEmpty(data)){
            readerVO = (ReaderVO)JSONUtil.json2Object(data,ReaderVO.class);
            userInfoVO = (UserInfoVO)JSONUtil.json2Object(data,UserInfoVO.class);
        }
       if(readerVO.getId()!=null){
            userDao.updatePeople(userInfoVO);
        }else{
            Long id = IdGen.randomLong();
            readerVO.setId(id);
            userDao.insertReader(readerVO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStaff(StaffVO staffVO){
        if(staffVO!=null&&staffVO.getId()!=null&&staffVO.getPeopleId()!=null){
            userDao.updateStaff(staffVO);
        }else{
            Long id = IdGen.randomLong();
            staffVO.setId(id);
            userDao.insertStaff(staffVO);
        }
    }

}
