package me.zhengjie.modules.course.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.modules.course.service.dto.CourseInfoDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:20+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class CourseInfoMapperImpl implements CourseInfoMapper {

    @Override
    public CourseInfo toEntity(CourseInfoDto dto) {
        if ( dto == null ) {
            return null;
        }

        CourseInfo courseInfo = new CourseInfo();

        courseInfo.setId( dto.getId() );
        courseInfo.setName( dto.getName() );
        courseInfo.setCreateTime( dto.getCreateTime() );
        courseInfo.setUpdateTime( dto.getUpdateTime() );
        courseInfo.setCourseTime( dto.getCourseTime() );
        courseInfo.setCourseNum( dto.getCourseNum() );
        courseInfo.setCourseTeacher( dto.getCourseTeacher() );
        courseInfo.setCourseTeacherid( dto.getCourseTeacherid() );
        courseInfo.setCoursecode( dto.getCoursecode() );

        return courseInfo;
    }

    @Override
    public CourseInfoDto toDto(CourseInfo entity) {
        if ( entity == null ) {
            return null;
        }

        CourseInfoDto courseInfoDto = new CourseInfoDto();

        courseInfoDto.setId( entity.getId() );
        courseInfoDto.setName( entity.getName() );
        courseInfoDto.setCreateTime( entity.getCreateTime() );
        courseInfoDto.setUpdateTime( entity.getUpdateTime() );
        courseInfoDto.setCourseTime( entity.getCourseTime() );
        courseInfoDto.setCourseNum( entity.getCourseNum() );
        courseInfoDto.setCourseTeacher( entity.getCourseTeacher() );
        courseInfoDto.setCourseTeacherid( entity.getCourseTeacherid() );
        courseInfoDto.setCoursecode( entity.getCoursecode() );

        return courseInfoDto;
    }

    @Override
    public List<CourseInfo> toEntity(List<CourseInfoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CourseInfo> list = new ArrayList<CourseInfo>( dtoList.size() );
        for ( CourseInfoDto courseInfoDto : dtoList ) {
            list.add( toEntity( courseInfoDto ) );
        }

        return list;
    }

    @Override
    public List<CourseInfoDto> toDto(List<CourseInfo> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourseInfoDto> list = new ArrayList<CourseInfoDto>( entityList.size() );
        for ( CourseInfo courseInfo : entityList ) {
            list.add( toDto( courseInfo ) );
        }

        return list;
    }
}
