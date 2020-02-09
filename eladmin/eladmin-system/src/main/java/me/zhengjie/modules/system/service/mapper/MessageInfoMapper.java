package me.zhengjie.modules.system.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.system.domain.MessageInfo;
import me.zhengjie.modules.system.service.dto.MessageInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author bessie
* @date 2020-01-14
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageInfoMapper extends BaseMapper<MessageInfoDto, MessageInfo> {

}