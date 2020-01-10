package me.zhengjie.modules.studentCourse.repository;

import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author HUO
* @date 2020-01-10
*/
public interface StudentCourseInfoRepository extends JpaRepository<StudentCourseInfo, Integer>, JpaSpecificationExecutor<StudentCourseInfo> {
}