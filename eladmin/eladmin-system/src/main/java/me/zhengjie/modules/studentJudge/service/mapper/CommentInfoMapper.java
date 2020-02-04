package me.zhengjie.modules.studentJudge.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.studentJudge.domain.CommentInfo;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author bessie
* @date 2020-01-30
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentInfoMapper extends BaseMapper<CommentInfoDto, CommentInfo> {

}