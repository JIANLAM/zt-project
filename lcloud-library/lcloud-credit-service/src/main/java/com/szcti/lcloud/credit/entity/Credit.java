package com.szcti.lcloud.credit.entity;

import lombok.Data;

@Data
public class Credit {
    private Long id;

    private Long userId;

    private Integer defaultValue;

    private Integer ownValue;

    private Integer status;
    private Integer cardStatus;//读者证状态
    private String summary;
    private Integer islendBuy;

}