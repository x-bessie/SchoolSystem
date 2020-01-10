package me.zhengjie.modules.system.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.system.domain.MessageInfo;
import me.zhengjie.modules.system.service.dto.MessageInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:22+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class MessageInfoMapperImpl implements MessageInfoMapper {

    @Override
    public MessageInfo toEntity(MessageInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        MessageInfo messageInfo = new MessageInfo();

        messageInfo.setId( dto.getId() );
        messageInfo.setContent( dto.getContent() );
        messageInfo.setStatus( dto.getStatus() );
        messageInfo.setCreatetime( dto.getCreatetime() );

        return messageInfo;
    }

    @Override
    public MessageInfoDto toDto(MessageInfo entity) {
        if ( entity == null ) {
            return null;
        }

        MessageInfoDto messageInfoDto = new MessageInfoDto();

        messageInfoDto.setId( entity.getId() );
        messageInfoDto.setContent( entity.getContent() );
        messageInfoDto.setStatus( entity.getStatus() );
        messageInfoDto.setCreatetime( entity.getCreatetime() );

        return messageInfoDto;
    }

    @Override
    public List<MessageInfo> toEntity(List<MessageInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MessageInfo> list = new ArrayList<MessageInfo>( dtoList.size() );
        for ( MessageInfoDto messageInfoDto : dtoList ) {
            list.add( toEntity( messageInfoDto ) );
        }

        return list;
    }

    @Override
    public List<MessageInfoDto> toDto(List<MessageInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MessageInfoDto> list = new ArrayList<MessageInfoDto>( entityList.size() );
        for ( MessageInfo messageInfo : entityList ) {
            list.add( toDto( messageInfo ) );
        }

        return list;
    }
}
