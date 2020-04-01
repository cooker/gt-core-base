package org.grant.zm.spring2.base;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * grant
 * 30/3/2020 3:45 下午
 * 描述：实体转换器
 */
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGMapperConvert<S, T> {

    T toTarget(S s);

    @InheritInverseConfiguration(name = "toTarget")
    S toSource(T t);

    List<T> toTargets(List<S> s);

    @InheritInverseConfiguration(name = "toTargets")
    List<S> toSources(List<T> t);


}
