package com.szcti.lcloud.user.entity;

import java.io.Serializable;

/**
 * <p>
 * 机构类别
 * </p>
 *
 * @author dw
 * @since 2018-06-25
 */
public class TOrganizationType implements Serializable {

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
    public String toString() {
        return "TOrganizationType{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
