package org.grant.zm.spring2.mapstruct;

import org.assertj.core.util.Lists;
import org.grant.zm.spring2.mapstruct.mapper.ChMapper;
import org.junit.Test;

/**
 * grant
 * 30/3/2020 3:20 下午
 * 描述：
 */
public class MapperTest {

    @Test
    public void toErp(){
        PytEntity pytEntity = new PytEntity();
        pytEntity.setName("1111");
        pytEntity.setRmb("100.111111111111199999999999999999");
        System.out.println(ChMapper.INSTANCE.toErpList(Lists.newArrayList(pytEntity)));
    }


    @Test
    public void toPyt(){
        ErpEntity pytEntity = new ErpEntity();
        pytEntity.setSex("男");
//        pytEntity.setName("1111");
        pytEntity.setItem(new ErpItemEntity());
        System.out.println(ChMapper.INSTANCE.toPyt(pytEntity));
    }
}
