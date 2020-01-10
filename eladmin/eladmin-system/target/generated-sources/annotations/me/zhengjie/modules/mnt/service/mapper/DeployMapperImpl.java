package me.zhengjie.modules.mnt.service.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import me.zhengjie.modules.mnt.domain.Deploy;
import me.zhengjie.modules.mnt.domain.ServerDeploy;
import me.zhengjie.modules.mnt.service.dto.DeployDto;
import me.zhengjie.modules.mnt.service.dto.ServerDeployDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:22+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class DeployMapperImpl implements DeployMapper {

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private ServerDeployMapper serverDeployMapper;

    @Override
    public Deploy toEntity(DeployDto dto) {
        if ( dto == null ) {
            return null;
        }

        Deploy deploy = new Deploy();

        if ( dto.getId() != null ) {
            deploy.setId( Long.parseLong( dto.getId() ) );
        }
        deploy.setApp( appMapper.toEntity( dto.getApp() ) );
        deploy.setDeploys( serverDeployDtoSetToServerDeploySet( dto.getDeploys() ) );
        deploy.setCreateTime( dto.getCreateTime() );

        return deploy;
    }

    @Override
    public DeployDto toDto(Deploy entity) {
        if ( entity == null ) {
            return null;
        }

        DeployDto deployDto = new DeployDto();

        if ( entity.getId() != null ) {
            deployDto.setId( String.valueOf( entity.getId() ) );
        }
        deployDto.setApp( appMapper.toDto( entity.getApp() ) );
        deployDto.setDeploys( serverDeploySetToServerDeployDtoSet( entity.getDeploys() ) );
        deployDto.setCreateTime( entity.getCreateTime() );

        return deployDto;
    }

    @Override
    public List<Deploy> toEntity(List<DeployDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Deploy> list = new ArrayList<Deploy>( dtoList.size() );
        for ( DeployDto deployDto : dtoList ) {
            list.add( toEntity( deployDto ) );
        }

        return list;
    }

    @Override
    public List<DeployDto> toDto(List<Deploy> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DeployDto> list = new ArrayList<DeployDto>( entityList.size() );
        for ( Deploy deploy : entityList ) {
            list.add( toDto( deploy ) );
        }

        return list;
    }

    protected Set<ServerDeploy> serverDeployDtoSetToServerDeploySet(Set<ServerDeployDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ServerDeploy> set1 = new HashSet<ServerDeploy>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServerDeployDto serverDeployDto : set ) {
            set1.add( serverDeployMapper.toEntity( serverDeployDto ) );
        }

        return set1;
    }

    protected Set<ServerDeployDto> serverDeploySetToServerDeployDtoSet(Set<ServerDeploy> set) {
        if ( set == null ) {
            return null;
        }

        Set<ServerDeployDto> set1 = new HashSet<ServerDeployDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServerDeploy serverDeploy : set ) {
            set1.add( serverDeployMapper.toDto( serverDeploy ) );
        }

        return set1;
    }
}
