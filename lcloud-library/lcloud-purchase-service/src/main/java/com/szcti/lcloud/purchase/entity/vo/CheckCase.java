package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CheckCase implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean title;

    private boolean author;

    private boolean publisher;

    private boolean pubdate;

    private boolean bookType;

    private boolean isbn;

    private boolean price;

 }