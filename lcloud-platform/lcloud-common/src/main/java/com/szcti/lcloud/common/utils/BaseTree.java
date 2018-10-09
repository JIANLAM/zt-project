package com.szcti.lcloud.common.utils;
 
import lombok.Data;
 
import java.io.Serializable;
import java.util.List;
 
/**
 * tree树形基础对象，后续的该类型的对象均继承该对象
 * 继承了此对象的实体主键将此对象的id作为主键
 */
@Data
public class BaseTree implements Serializable {
  
    /**
     *
     */
    private static final long serialVersionUID = -9189631784252440402L;
 
    private Long id;
 
    private Long parentId;//节点父id
 
    private String parentIds;
 
    private Boolean leaf = true;//是否为叶子节点，true表示是叶子节点，false表示不是叶子节点
 
    private Boolean expanded = true; //是否展开，默认true,展开
 
    private List<BaseTree> children;//孩子节点
 
 
    public BaseTree() {
 
    }
 
 
} 