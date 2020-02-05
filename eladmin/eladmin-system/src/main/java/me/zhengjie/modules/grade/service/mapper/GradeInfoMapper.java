package me.zhengjie.modules.grade.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.grade.domain.GradeInfo;
import me.zhengjie.modules.grade.service.dto.GradeInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author bessie
* @date 2020-02-03
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GradeInfoMapper extends BaseMapper<GradeInfoDto, GradeInfo> {

}