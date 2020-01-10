package me.zhengjie.modules.mnt.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.mnt.domain.ServerDeploy;
import me.zhengjie.modules.mnt.service.dto.ServerDeployDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:23+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ServerDeployMapperImpl implements ServerDeployMapper {

    @Override
    public ServerDeploy toEntity(ServerDeployDto dto) {
        if ( dto == null ) {
            return null;
        }

        ServerDeploy serverDeploy = new ServerDeploy();

        serverDeploy.setId( dto.getId() );
        serverDeploy.setName( dto.getName() );
        serverDeploy.setIp( dto.getIp() );
        serverDeploy.setPort( dto.getPort() );
        serverDeploy.setAccount( dto.getAccount() );
        serverDeploy.setPassword( dto.getPassword() );
        serverDeploy.setCreateTime( dto.getCreateTime() );

        return serverDeploy;
    }

    @Override
    public ServerDeployDto toDto(ServerDeploy entity) {
        if ( entity == null ) {
            return null;
        }

        ServerDeployDto serverDeployDto = new ServerDeployDto();

        serverDeployDto.setId( entity.getId() );
        serverDeployDto.setName( entity.getName() );
        serverDeployDto.setIp( entity.getIp() );
        serverDeployDto.setPort( entity.getPort() );
        serverDeployDto.setAccount( entity.getAccount() );
        serverDeployDto.setPassword( entity.getPassword() );
        serverDeployDto.setCreateTime( entity.getCreateTime() );

        return serverDeployDto;
    }

    @Override
    public List<ServerDeploy> toEntity(List<ServerDeployDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ServerDeploy> list = new ArrayList<ServerDeploy>( dtoList.size() );
        for ( ServerDeployDto serverDeployDto : dtoList ) {
            list.add( toEntity( serverDeployDto ) );
        }

        return list;
    }

    @Override
    public List<ServerDeployDto> toDto(List<ServerDeploy> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ServerDeployDto> list = new ArrayList<ServerDeployDto>( entityList.size() );
        for ( ServerDeploy serverDeploy : entityList ) {
            list.add( toDto( serverDeploy ) );
        }

        return list;
    }
}
