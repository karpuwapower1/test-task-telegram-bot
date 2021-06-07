package com.test.task.telegram.mapper;

import com.test.task.telegram.dto.AdviceDto;
import com.test.task.telegram.entity.Advice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdviceMapper {

//    @Mapping(target = "cityUuid", source = "cityUuid")
    AdviceDto toAdviceDto(Advice advice, String cityUuid);

    default List<AdviceDto> toAdviceDto(List<Advice> advices) {
        return advices.stream()
            .map(advice -> toAdviceDto(advice, advice.getCity().getUuid()))
            .collect(Collectors.toList());
    }
}
