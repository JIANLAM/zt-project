package com.szcti.lcloud.recommended.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 推荐图书信息VO
 * @Description: 推荐图书信息
 * @author: fengda
 * @date: 2018/5/30 15:13
 */
@Data
public class RecommendedBookVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private Integer recommType;//0导师推荐，1读者推荐，2猜你喜欢

    private Integer bookFrom;//0本馆，1成员馆，2新华

    private String recommReason;

    private Long bookId;

    private String createTime;

    private Long peopleId;
    private String userName;

    private String title;

    private String pic;

    private String author;

    private Float price;

    private String publisher;

    private String pubDate;

    private String bookType;

    private String summary;

    private String ISBN;

    private Integer recommTimes;

    private String icon;    //头像

    private Long collectionCount;

    private List<RecommReasonInfoVO> recommReasonList;

}
