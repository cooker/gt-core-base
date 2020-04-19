package org.grant.zm;

import org.grant.zm.lazy.GElastic;
import org.grant.zm.lazy.GElasticType;
import org.grant.zm.lazy.GElasticValue;
import org.grant.zm.lazy.GElasticValueType;

/**
 * grant
 * 18/4/2020 3:59 下午
 * 描述：
 */
public class Woman implements People{

    @GElastic(
            propKey = "woman.say", propVal = "true",
            success = {@GElasticType(typeName = "org.grant.zm.Tag", value = {
                    @GElasticValue(key = "value", value = "哈哈哈哈")
            })},
            failure = {@GElasticType(typeName = "org.grant.zm.Tag", value = {
                    @GElasticValue(key = "value", value = "HUHUHU"),
                    @GElasticValue(key = "age", keyType = GElasticValueType.INT,value = "18")
            })}
    )
    public void say() {

    }


    @Tag(value = "sss", age = 122)
    public void xx(){

    }
}
