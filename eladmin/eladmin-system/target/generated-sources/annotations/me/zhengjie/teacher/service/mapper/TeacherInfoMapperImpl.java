package me.zhengjie.teacher.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.teacher.domain.TeacherInfo;
import me.zhengjie.teacher.service.dto.TeacherInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-09T13:37:39+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class TeacherInfoMapperImpl implements TeacherInfoMapper {

    @Override
    public TeacherInfo toEntity(TeacherInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setId( dto.getId() );
        teacherInfo.setName( dto.getName() );
        teacherInfo.setAge( dto.getAge() );
        teacherInfo.setSex( dto.getSex() );
        teacherInfo.setIdnum( dto.getIdnum() );
        teacherInfo.setAddress( dto.getAddress() );
        teacherInfo.setEmail( dto.getEmail() );
        teacherInfo.setPhone( dto.getPhone() );
        teacherInfo.setCreatetime( dto.getCreatetime() );

        return teacherInfo;
    }

    @Override
    public TeacherInfoDto toDto(TeacherInfo entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherInfoDto teacherInfoDto = new TeacherInfoDto();

        teacherInfoDto.setId( entity.getId() );
        teacherInfoDto.setName( entity.getName() );
        teacherInfoDto.setAge( entity.getAge() );
        teacherInfoDto.setSex( entity.getSex() );
        teacherInfoDto.setIdnum( entity.getIdnum() );
        teacherInfoDto.setAddress( entity.getAddress() );
        teacherInfoDto.setEmail( entity.getEmail() );
        teacherInfoDto.setPhone( entity.getPhone() );
        teacherInfoDto.setCreatetime( entity.getCreatetime() );

        return teacherInfoDto;
    }

    @Override
    public List<TeacherInfo> toEntity(List<TeacherInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TeacherInfo> list = new ArrayList<TeacherInfo>( dtoList.size() );
        for ( TeacherInfoDto teacherInfoDto : dtoList ) {
            list.add( toEntity( teacherInfoDto ) );
        }

        return list;
    }

    @Override
    public List<TeacherInfoDto> toDto(List<TeacherInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TeacherInfoDto> list = new ArrayList<TeacherInfoDto>( entityList.size() );
        for ( TeacherInfo teacherInfo : entityList ) {
            list.add( toDto( teacherInfo ) );
        }

        return list;
    }
}
