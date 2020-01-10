package me.zhengjie.modules.student.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.student.domain.StudentInfo;
import me.zhengjie.modules.student.service.dto.StudentInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author HUO
* @date 2020-01-09
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentInfoMapper extends BaseMapper<StudentInfoDto, StudentInfo> {

}