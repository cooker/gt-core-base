package org.grant.zm.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * grant
 * 3/3/2020 2:35 下午
 * 描述：
 */
@Data
@XmlType(name = "SoapTestEntity", propOrder = {
    "lines"
})
public class SoapTestEntity {
    @XmlElement(name = "line")
    List<String> line;

}
