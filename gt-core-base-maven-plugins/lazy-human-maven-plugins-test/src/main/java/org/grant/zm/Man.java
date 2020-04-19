package org.grant.zm;

import org.grant.zm.lazy.GElastic;
import org.grant.zm.lazy.GElasticType;
import org.grant.zm.lazy.GElasticValue;

/**
 * grant
 * 18/4/2020 3:59 下午
 * 描述：
 */
public class Man implements People{

    @GElastic(
            propKey = "man.say", propVal = "true",
            success = {@GElasticType(typeName = "org.grant.zm.Tag", value = {
                    @GElasticValue(key = "value", value = "哈哈哈哈")
            })},
            failure = {@GElasticType(typeName = "org.grant.zm.Tag", value = {
                    @GElasticValue(key = "value", value = "HUHUHU")
            })}
    )
    public void say() {

    }
}
