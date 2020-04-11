package org.grant.zm.spring2.mapstruct;

import lombok.Data;

/**
 * grant
 * 30/3/2020 3:15 下午
 * 描述：
 */
@Data
public class PytEntity {
    private String rmb;
    private String name;
    private int sex;

    private PytItemEntity item;
}
