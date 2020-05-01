package com.github.cooker.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

/**
 * grant
 * 28/4/2020 11:28 下午
 * 描述：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@SessionScope
@JsonIgnoreProperties(value = {"beanExpressionResolver","beanFactory","targetSource","advisors", "classFilter"})
public class MySession implements Serializable {
    private String name;
}
