package org.grant.zm.entity.zoo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * grant
 * 21/4/2020 12:56 PM
 * 描述：
 */
@Data
public class BTree implements Serializable {
    private String name;
    private String height;
    private Integer status;
    private BigDecimal total;


    private String qb;
}