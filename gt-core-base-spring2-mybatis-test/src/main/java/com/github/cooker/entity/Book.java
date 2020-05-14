package com.github.cooker.entity;

import lombok.Data;

/**
 * ZoomGrant 2020/5/12 20:27
 */
@Data
public class Book {
    private Integer id;
    private String name;
    //作者id
    private Integer aid;
    //书架id
    private Integer lid;
}
