package org.grant.zm.spring2.mapstruct.mapper;

import org.grant.zm.spring2.mapstruct.ErpItemEntity;
import org.grant.zm.spring2.mapstruct.PytEntity;
import org.grant.zm.spring2.mapstruct.PytItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * grant
 * 1/4/2020 4:54 下午
 * 描述：
 */
@Mapper
public interface Ch2Mapper {
    Ch2Mapper INSTANCE = Mappers.getMapper(Ch2Mapper.class);

    ErpItemEntity toErp(PytItemEntity entity);
    List<ErpItemEntity> toErpList(List<PytItemEntity> pytEntities);


    PytItemEntity toPyt(ErpItemEntity entity);

    List<PytEntity> toPytList(List<ErpItemEntity> erpEntities);
}
