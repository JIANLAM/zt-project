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
 * 权限
 * </p>
 *
 * @author dw
 * @since 2018-06-29
 */
@TableName("t_perm")
public class TPerm extends Model<TPerm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限父id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 是否隐藏0否，1是
     */
    private Integer hidden;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 1，一级菜单；2,二级菜单；3，三级菜单
     */
    private Integer type;
    /**
     * 权限代码
     */
    @TableField("perm_code")
    private String permCode;
    /**
     * 菜单所对应的页面
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 对应的后台服务url
     */
    private String url;
    /**
     * 权限类型，reader 读者；library 图书馆；operate，平台运营管理
     */
    @TableField("perm_type")
    private String permType;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPerm{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", path=" + path +
        ", sort=" + sort +
        ", hidden=" + hidden +
        ", name=" + name +
        ", type=" + type +
        ", permCode=" + permCode +
        ", component=" + component +
        ", icon=" + icon +
        ", url=" + url +
        ", permType=" + permType +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", remark=" + remark +
        "}";
    }
}
