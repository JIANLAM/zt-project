package com.szcti.lcloud.purchase.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dw
 * @since 2018-08-16
 */
@TableName("t_people")
public class TPeople extends Model<TPeople> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 1男，2女
     */
    private Integer sex;
    /**
     * 身份证卡号
     */
    @TableField("card_number")
    private String cardNumber;
    /**
     * 身份证|军人证|港澳台通行证|护照
     */
    @TableField("card_type")
    private Integer cardType;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 图标头像
     */
    private String icon;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 出生年月日
     */
    private String birthday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPeople{" +
        ", id=" + id +
        ", userId=" + userId +
        ", username=" + username +
        ", sex=" + sex +
        ", cardNumber=" + cardNumber +
        ", cardType=" + cardType +
        ", phone=" + phone +
        ", email=" + email +
        ", icon=" + icon +
        ", nickName=" + nickName +
        ", signature=" + signature +
        ", birthday=" + birthday +
        "}";
    }
}
