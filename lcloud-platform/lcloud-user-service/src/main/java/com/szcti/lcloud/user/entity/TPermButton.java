package com.szcti.lcloud.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 菜单权限按钮
 * </p>
 *
 * @author dw
 * @since 2018-06-26
 */
@TableName("t_perm_button")
public class TPermButton extends Model<TPermButton> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 权限菜单id
     */
    @TableField("perm_id")
    private Long permId;
    /**
     * 按钮id
     */
    @TableField("button_id")
    private Long buttonId;
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPermButton{" +
        ", id=" + id +
        ", permId=" + permId +
        ", buttonId=" + buttonId +
        ", createTime=" + createTime +
        "}";
    }
}
