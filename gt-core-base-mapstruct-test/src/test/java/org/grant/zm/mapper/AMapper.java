package org.grant.zm.mapper;

import org.grant.zm.entity.forest.ATree;
import org.grant.zm.entity.zoo.BTree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * grant
 * 21/4/2020 12:58 PM
 * 描述：
 */
@Mapper
public interface AMapper extends BaseMapper<ATree, BTree> {

    @Mappings(
            {
                    @Mapping(source = "qb", target = "xb"),
                    @Mapping(target = "status", expression = "java(new java.math.BigDecimal(b.getStatus()+\"\"))")
            }
    )
    ATree toA(BTree b);


    @Mappings(
            {
                    @Mapping(source = "xb", target = "qb"),
                    @Mapping(target = "status", expression = "java(Integer.valueOf(a.getStatus().toPlainString()))")
            }
    )
    BTree toB(ATree a);
}
