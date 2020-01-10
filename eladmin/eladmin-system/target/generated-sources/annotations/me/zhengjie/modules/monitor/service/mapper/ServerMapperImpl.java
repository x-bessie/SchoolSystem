package me.zhengjie.modules.monitor.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.monitor.domain.Server;
import me.zhengjie.modules.monitor.service.dto.ServerDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:21+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class ServerMapperImpl implements ServerMapper {

    @Override
    public Server toEntity(ServerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Server server = new Server();

        server.setId( dto.getId() );
        server.setName( dto.getName() );
        server.setAddress( dto.getAddress() );
        server.setPort( dto.getPort() );
        server.setState( dto.getState() );
        if ( dto.getCpuRate() != null ) {
            server.setCpuRate( dto.getCpuRate().doubleValue() );
        }
        server.setCpuCore( dto.getCpuCore() );
        if ( dto.getMemTotal() != null ) {
            server.setMemTotal( dto.getMemTotal().doubleValue() );
        }
        if ( dto.getMemUsed() != null ) {
            server.setMemUsed( dto.getMemUsed().doubleValue() );
        }
        if ( dto.getDiskTotal() != null ) {
            server.setDiskTotal( dto.getDiskTotal().doubleValue() );
        }
        if ( dto.getDiskUsed() != null ) {
            server.setDiskUsed( dto.getDiskUsed().doubleValue() );
        }
        if ( dto.getSwapTotal() != null ) {
            server.setSwapTotal( dto.getSwapTotal().doubleValue() );
        }
        if ( dto.getSwapUsed() != null ) {
            server.setSwapUsed( dto.getSwapUsed().doubleValue() );
        }
        server.setSort( dto.getSort() );

        return server;
    }

    @Override
    public ServerDTO toDto(Server entity) {
        if ( entity == null ) {
            return null;
        }

        ServerDTO serverDTO = new ServerDTO();

        serverDTO.setId( entity.getId() );
        serverDTO.setName( entity.getName() );
        serverDTO.setAddress( entity.getAddress() );
        serverDTO.setPort( entity.getPort() );
        serverDTO.setState( entity.getState() );
        if ( entity.getCpuRate() != null ) {
            serverDTO.setCpuRate( entity.getCpuRate().floatValue() );
        }
        serverDTO.setCpuCore( entity.getCpuCore() );
        if ( entity.getMemTotal() != null ) {
            serverDTO.setMemTotal( entity.getMemTotal().floatValue() );
        }
        if ( entity.getMemUsed() != null ) {
            serverDTO.setMemUsed( entity.getMemUsed().floatValue() );
        }
        if ( entity.getDiskTotal() != null ) {
            serverDTO.setDiskTotal( entity.getDiskTotal().floatValue() );
        }
        if ( entity.getDiskUsed() != null ) {
            serverDTO.setDiskUsed( entity.getDiskUsed().floatValue() );
        }
        if ( entity.getSwapTotal() != null ) {
            serverDTO.setSwapTotal( entity.getSwapTotal().floatValue() );
        }
        if ( entity.getSwapUsed() != null ) {
            serverDTO.setSwapUsed( entity.getSwapUsed().floatValue() );
        }
        serverDTO.setSort( entity.getSort() );

        return serverDTO;
    }

    @Override
    public List<Server> toEntity(List<ServerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Server> list = new ArrayList<Server>( dtoList.size() );
        for ( ServerDTO serverDTO : dtoList ) {
            list.add( toEntity( serverDTO ) );
        }

        return list;
    }

    @Override
    public List<ServerDTO> toDto(List<Server> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ServerDTO> list = new ArrayList<ServerDTO>( entityList.size() );
        for ( Server server : entityList ) {
            list.add( toDto( server ) );
        }

        return list;
    }
}
