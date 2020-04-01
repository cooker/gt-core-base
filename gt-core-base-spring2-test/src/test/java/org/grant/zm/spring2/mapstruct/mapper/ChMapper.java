package org.grant.zm.spring2.mapstruct.mapper;

import org.grant.zm.spring2.mapstruct.ErpEntity;
import org.grant.zm.spring2.mapstruct.PytEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

/**
 * grant
 * 30/3/2020 3:16 下午
 * 描述：
 */
@Mapper
public interface ChMapper {
    ChMapper INSTANCE = Mappers.getMapper(ChMapper.class);
    @Mappings(
            {
                @Mapping(source = "rmb", target = "rmb", resultType = BigDecimal.class),
                @Mapping(source = "name", target = "nn")
            }
    )
    ErpEntity toErp(PytEntity entity);
    List<ErpEntity> toErpList(List<PytEntity> pytEntities);
}
