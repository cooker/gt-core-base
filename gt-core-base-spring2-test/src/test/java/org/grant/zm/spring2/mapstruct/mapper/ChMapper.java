package org.grant.zm.spring2.mapstruct.mapper;

import org.grant.zm.spring2.mapstruct.ErpEntity;
import org.grant.zm.spring2.mapstruct.PytEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

/**
 * grant
 * 30/3/2020 3:16 下午
 * 描述：
 */
@Mapper(uses = Ch2Mapper.class)
public interface ChMapper {
    ChMapper INSTANCE = Mappers.getMapper(ChMapper.class);
    @Mappings(
            {
                @Mapping(source = "rmb", target = "rmb", resultType = BigDecimal.class),
                @Mapping(source = "name", target = "nn"),
                @Mapping(target = "sex", expression = "java(entity.getSex()==1?\"男\":\"女\")")
            }
    )
    @InheritConfiguration(name = "toPyt")
    ErpEntity toErp(PytEntity entity);
    List<ErpEntity> toErpList(List<PytEntity> pytEntities);

    @InheritConfiguration(name = "toErp")
    @Mappings(
            {
                    @Mapping(target = "sex", expression = "java(\"男\".equals(entity.getSex())?1:0)")
            }
    )
    PytEntity toPyt(ErpEntity entity);

    List<PytEntity> toPytList(List<ErpEntity> erpEntities);
}
