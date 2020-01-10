package me.zhengjie.modules.studentCourse.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author HUO
* @date 2020-01-10
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentCourseInfoMapper extends BaseMapper<StudentCourseInfoDto, StudentCourseInfo> {

}