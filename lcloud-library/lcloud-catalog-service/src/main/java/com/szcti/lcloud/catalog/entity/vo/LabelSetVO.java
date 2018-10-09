package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LabelSetVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;//模板id
    private String labelName;//模板名乐称
    private Long creator;//创建者
    private String createTime;//创建时间
    private Long libraryId;//图书馆 id
    private String paperSize;//纸张大小
    private String paperHeight;//纸张高度
    private String paperWeight;//纸张宽度
    private Integer rowCount;//行数
    private Integer columnCount;//列数
    private String fontSize;//字体大小
    private String fontThick;//字体加粗
    private String lableHeght;//标签高度
    private String lableWeight;//标签宽度
    private String leftSpace;//左间距
    private String topSpace;//上间距
    private String alignment;//对齐方式
    private String repeatNum;//重复数
    private String oneRow;//第一行
    private String twoRow;//第二行
    private String threeRow;//第三行
    private String fourRow;//第四行
    private String fiveRow;//第五行
    private String sixRow;//第六行
    private String sevenRow;//第七行
    private String spaceRow;//行间距
}