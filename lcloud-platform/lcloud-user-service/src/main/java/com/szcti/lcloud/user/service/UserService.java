package com.szcti.lcloud.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.MenuVO;
import com.szcti.lcloud.user.entity.vo.QueryUserVo;
import com.szcti.lcloud.user.entity.vo.ReaderVO;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;

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
     * @param readerVO
     */
    void saveReader(ReaderVO readerVO);



	PageInfo getUserList(QueryUserVo qrv);

	void deleteUserInfo(List<UserInfoVO> userVOList);

	void copyUserInfo(List<UserInfoVO> userVO);

	TUser getUserByName(String loginName);




	MenuVO getUserMenu(Long uid,String userType);




	TUser getUserById(Long userid);




	TUser getUserByToken(String token);

}
