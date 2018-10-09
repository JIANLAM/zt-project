package com.szcti.lcloud.account.service;

import com.szcti.lcloud.account.entity.vo.ReaderVO;
import com.szcti.lcloud.account.entity.vo.StaffVO;
import com.szcti.lcloud.account.entity.vo.UserInfoVO;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.Map;

/**
 * @Title: 用户Service
 * @Description: 处理用户相关信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface UserService {
    /**
     * 根据用户ID查找用户
     * @param userId
     * @return UserInfoVO
     */
    UserInfoVO getUserInfo(Long userId);

    /**
     * 根据用户ID查找读者
     * @param userId
     * @return ReaderVO
     */
    Map getReaderInfo(Long userId);

    /**
     * 根据用户ID查找职工
     * @param userId
     * @return ReaderVO
     */
    Map getStaffInfo(Long userId);

    /**
     * 修改用户密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return void
     */
    String modifyPwd(Long userId,String oldPassword,String newPassword);

    /**
     * 保存用户信息
     * @param userVO
     */
    void saveUserInfo(UserInfoVO userVO);

    /**
     * 保存读者信息
     * @param data
     */
    void saveReader(String data);

    /**
     * 保存职员信息
     * @param staffVO
     */
    void saveStaff(StaffVO staffVO);

}
