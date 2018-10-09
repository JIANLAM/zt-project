package com.szcti.lcloud.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 财经类型
 * </p>
 *
 * @author dw
 * @since 2018-07-24
 */
@TableName("t_finance_type")
public class TFinanceType extends Model<TFinanceType> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TFinanceType{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
