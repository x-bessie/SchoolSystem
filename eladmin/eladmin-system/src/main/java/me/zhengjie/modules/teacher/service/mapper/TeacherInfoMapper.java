package me.zhengjie.modules.teacher.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.teacher.domain.TeacherInfo;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author HUO
* @date 2020-01-09
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherInfoMapper extends BaseMapper<TeacherInfoDto, TeacherInfo> {

}