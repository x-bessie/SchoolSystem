package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptSmallMapper extends BaseMapper<DeptSmallDto, Dept> {

}