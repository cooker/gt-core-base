package org.grant.zm;

import io.vavr.collection.List;
import org.grant.zm.entity.SoapTestEntity;
import org.grant.zm.soapui.GSoapParam;
import org.grant.zm.utils.GSoapUtils;
import org.junit.Test;

/**
 * grant
 * 3/3/2020 2:18 下午
 * 描述：类型测试
 */
public class GClassTypeTest {

    @Test
    public void ss(){
        SoapTestEntity entity = new SoapTestEntity();
        entity.setLine(List.of("111", "11").asJava());
        String xml = GSoapUtils.makeTestData("http://a", "http://as", "wsUpload",
                new GSoapParam("lines", entity));
        System.out.println(xml);

    }
}
