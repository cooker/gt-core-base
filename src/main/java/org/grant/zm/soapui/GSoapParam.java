package org.grant.zm.soapui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * grant
 * 3/3/2020 12:12 下午
 * 描述：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSoapParam {
    String nameSpace;
    Object param;
}
