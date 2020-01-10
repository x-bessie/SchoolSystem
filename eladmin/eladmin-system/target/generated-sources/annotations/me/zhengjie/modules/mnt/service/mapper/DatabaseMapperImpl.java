package me.zhengjie.modules.mnt.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.mnt.domain.Database;
import me.zhengjie.modules.mnt.service.dto.DatabaseDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:19+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class DatabaseMapperImpl implements DatabaseMapper {

    @Override
    public Database toEntity(DatabaseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Database database = new Database();

        database.setId( dto.getId() );
        database.setName( dto.getName() );
        database.setJdbcUrl( dto.getJdbcUrl() );
        database.setPwd( dto.getPwd() );
        database.setUserName( dto.getUserName() );
        database.setCreateTime( dto.getCreateTime() );

        return database;
    }

    @Override
    public DatabaseDto toDto(Database entity) {
        if ( entity == null ) {
            return null;
        }

        DatabaseDto databaseDto = new DatabaseDto();

        databaseDto.setId( entity.getId() );
        databaseDto.setName( entity.getName() );
        databaseDto.setJdbcUrl( entity.getJdbcUrl() );
        databaseDto.setPwd( entity.getPwd() );
        databaseDto.setUserName( entity.getUserName() );
        databaseDto.setCreateTime( entity.getCreateTime() );

        return databaseDto;
    }

    @Override
    public List<Database> toEntity(List<DatabaseDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Database> list = new ArrayList<Database>( dtoList.size() );
        for ( DatabaseDto databaseDto : dtoList ) {
            list.add( toEntity( databaseDto ) );
        }

        return list;
    }

    @Override
    public List<DatabaseDto> toDto(List<Database> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DatabaseDto> list = new ArrayList<DatabaseDto>( entityList.size() );
        for ( Database database : entityList ) {
            list.add( toDto( database ) );
        }

        return list;
    }
}
