package me.zhengjie.modules.course.repository;

import me.zhengjie.modules.course.domain.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author bessie
* @date 2020-01-16
*/
@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Integer>, JpaSpecificationExecutor<CourseInfo> {

    @Query(value="select * from  course_info  where   name=?1 and class_teacher=?2 ",nativeQuery = true)
    List<Map<String, Object>> getCoruseTeacher(String name, String class_teacher);
}