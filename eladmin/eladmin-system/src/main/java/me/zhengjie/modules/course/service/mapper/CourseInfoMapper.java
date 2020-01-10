package me.zhengjie.modules.course.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.modules.course.service.dto.CourseInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author HUO
* @date 2020-01-09
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseInfoMapper extends BaseMapper<CourseInfoDto, CourseInfo> {

}