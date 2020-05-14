package com.github.cooker.spring.beanlife;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * grant
 * 11/5/2020 4:24 下午
 * 描述：
 */
@Data
@Component
public class QHello {
    private Integer num;
}
