package org.grant.zm;

import org.grant.zm.entity.forest.ATree;
import org.grant.zm.entity.zoo.BTree;
import org.grant.zm.mapper.AMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.MapperConfig;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

/**
 * grant
 * 21/4/2020 1:07 PM
 * 描述：
 */
public class AMapperTest {

    @Test
    public void a2b(){
        AMapper mapper = Mappers.getMapper(AMapper.class);
        BTree bTree = new BTree();
        bTree.setName("111");
        bTree.setTotal(new BigDecimal("100.2"));
        bTree.setHeight("111");
        bTree.setStatus(1);
        bTree.setQb("aaaa");
        ATree aTree = mapper.toA(bTree);

        Assert.assertEquals(aTree.getName(), bTree.getName());

        Assert.assertEquals(aTree.getTotal(), bTree.getTotal().toPlainString());

        Assert.assertEquals(aTree.getHeight(), Double.valueOf(bTree.getHeight()));

        Assert.assertEquals(aTree.getStatus().toString(), bTree.getStatus().toString());

        Assert.assertEquals(aTree.getXb(), bTree.getQb());
    }
}
