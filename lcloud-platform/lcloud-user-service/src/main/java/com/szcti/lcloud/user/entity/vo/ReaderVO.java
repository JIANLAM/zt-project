package com.szcti.lcloud.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: 读者VO
 * @Description: 读者信息
 * @author: fengda
 * @date: 2018/5/15 18:02
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ReaderVO implements Serializable {

	private static final long serialVersionUID = -8098658150437157587L;
	
	private Long id;
    /**
     * 个人信息id
     */
    private Long peopleId;
    /**
     * 证件类型 0读书证
     */
    private Integer readerCardType;
    /**
     * 证件号码
     */
    private String readerCardNumber;
    /**
     * 状态0禁用1正常2注销3挂失
     */
    private Integer status;
    /**
     * 班级
     */
    private Long grade;
    private Long classes;
    /**
     * 备注
     */
    private String remark;
    /**
     * 所属图书馆id
     */
    private Long libraryId;
    private Date startDate;
    private Date endDate;
    
    private String libraryName="";
    
    private String readerName="";
    private String sex="";
    /**
     * 借阅总次数
     */
    private Integer borrowCount=0;
    /**
     * 馆际互借次数
     */
    private Integer InterLibBorrowCount=0;
    /**
     * 预约次数
     */
    private Integer reservationCount=0;
    /**
     * 违约次数
     */
    private Integer violateCount=0;
    
    /**
     * 续借次数
     */
    private Integer renewCount=0;
    /**
     * 预付款金额
     */
    private Double prePayment=0.0;
    
    /**
     * 欠款金额
     */
    private Double arrear=0.0;
    
    private String IDCard;
    private String birthday;
    private String phone;
    private String password;
    private Date createDate;
    private String readerTypeName;
    private String pic;
    private String stuworkNumber;
    /**
     * 用户名
     */
    private String username;
   
   
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
    private String nickName;
    /**
     * 个性签名
     */
    private String signature;
    
    private String startDateStr;
    private String endDateStr;
    private String createDateStr;

    private Integer readerType;
}
