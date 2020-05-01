package com.github.cooker.entity;

import lombok.Data;

/**
 * grant
 * 22/4/2020 4:14 下午
 * 描述：
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;


    public String getAAA(){
        return "AAA";
    }

    public boolean isOk(){
        return true;
    }
}
