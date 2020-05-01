package org.grant.zm.entity.forest;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * grant
 * 21/4/2020 12:56 PM
 * 描述：
 */
@Data
public class ATree implements Serializable {
    private String name;
    private Double height;
    private BigDecimal status;
    private String total;

    private String xb;
}
