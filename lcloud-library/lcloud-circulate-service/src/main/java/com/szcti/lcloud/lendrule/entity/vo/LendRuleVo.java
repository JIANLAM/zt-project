package com.szcti.lcloud.lendrule.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * @Title: mrc模板 实体     （sys_marc）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */
@Data
public class LendRuleVo extends QueryParam implements Serializable {

        private Long id;     //借阅规则表（t_lend_rule）

        private String ruleCode;        //规则代码

        private String ruleName;   //规则名称

        private Integer lendQty;   //借阅册数

        private Integer lendDays;   //允许借阅天数

        private Integer renewQty;   //续借次数

        private Integer renewDays;   //续接天数

        private float overduePayrate;    //逾期罚款率

        private float lostPayrate;    //丢失罚款率

        private float brokenPayrate;    //污损罚款率

        private Integer ruleType;   //规则类别

        @Builder.Default
        private Integer credit = 0;      //要求信用分

        private String literatureType;    //可借文献类型

        private String literatureTypeTest;    //可借文献类型          文本

        private Integer status;     //是否启用 1是  0不启用

        private Long libraryId;       //图书馆标识

        private Long createBy;       //创建者

        private String createTime;       //创建时间

        private Long updateBy;       //修改者

        private String updateTime;       //修改时间

        private String createName;         //创建者名字

        private String updaterName;    //修改者姓名

        private String readerTypeCode;          //读者类型代码

        private String readerTypeName;          //读者类型名称

        private String remarks;         //备注

        private Long ownlib;            //所属馆

        private String ruleTypeTest;   //规则类别

}