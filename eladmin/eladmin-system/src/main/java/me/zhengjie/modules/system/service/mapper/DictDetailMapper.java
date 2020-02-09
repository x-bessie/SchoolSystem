package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.system.domain.DictDetail;
import me.zhengjie.modules.system.service.dto.DictDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}