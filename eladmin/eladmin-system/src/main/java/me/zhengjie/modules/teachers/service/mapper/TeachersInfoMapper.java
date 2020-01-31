package me.zhengjie.modules.teachers.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.teachers.domain.TeachersInfo;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author HUO
* @date 2020-01-13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeachersInfoMapper extends BaseMapper<TeachersInfoDto, TeachersInfo> {

}