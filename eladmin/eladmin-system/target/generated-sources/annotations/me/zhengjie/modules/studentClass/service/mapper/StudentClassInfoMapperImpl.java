package me.zhengjie.modules.studentClass.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.studentClass.domain.StudentClassInfo;
import me.zhengjie.modules.studentClass.service.dto.StudentClassInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-09T17:14:03+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class StudentClassInfoMapperImpl implements StudentClassInfoMapper {

    @Override
    public StudentClassInfo toEntity(StudentClassInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentClassInfo studentClassInfo = new StudentClassInfo();

        studentClassInfo.setStudentId( dto.getStudentId() );
        studentClassInfo.setClassId( dto.getClassId() );
        studentClassInfo.setPdate( dto.getPdate() );
        studentClassInfo.setTearm( dto.getTearm() );

        return studentClassInfo;
    }

    @Override
    public StudentClassInfoDto toDto(StudentClassInfo entity) {
        if ( entity == null ) {
            return null;
        }

        StudentClassInfoDto studentClassInfoDto = new StudentClassInfoDto();

        studentClassInfoDto.setStudentId( entity.getStudentId() );
        studentClassInfoDto.setClassId( entity.getClassId() );
        studentClassInfoDto.setPdate( entity.getPdate() );
        studentClassInfoDto.setTearm( entity.getTearm() );

        return studentClassInfoDto;
    }

    @Override
    public List<StudentClassInfo> toEntity(List<StudentClassInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<StudentClassInfo> list = new ArrayList<StudentClassInfo>( dtoList.size() );
        for ( StudentClassInfoDto studentClassInfoDto : dtoList ) {
            list.add( toEntity( studentClassInfoDto ) );
        }

        return list;
    }

    @Override
    public List<StudentClassInfoDto> toDto(List<StudentClassInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StudentClassInfoDto> list = new ArrayList<StudentClassInfoDto>( entityList.size() );
        for ( StudentClassInfo studentClassInfo : entityList ) {
            list.add( toDto( studentClassInfo ) );
        }

        return list;
    }
}
