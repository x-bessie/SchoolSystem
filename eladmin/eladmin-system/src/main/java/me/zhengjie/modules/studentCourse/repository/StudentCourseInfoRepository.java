package me.zhengjie.modules.studentCourse.repository;

import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author bessie
* @date 2020-01-10
*/
@Repository
public interface StudentCourseInfoRepository extends JpaRepository<StudentCourseInfo, Integer>, JpaSpecificationExecutor<StudentCourseInfo> {

    /**
     * 学生本人课程详细信息
     * @param userName
     * @return
     */
    @Query(value = "select t.course_id,t.username,t.name,c.`name` as course_name,c.classtime,c.class_num,c.class_teacher," +
            "c.teacher_id,c.class_code,c.tearm,c.school_year from " +
            "(select  a.student_id ,a.course_id,a.pdate,b.username,b.name from " +
            "student_course_info  as a, student_info  as b  where " +
            " a.student_id=b.id and b.username= ?1 ) t,course_info as c   where c.id=t.course_id", nativeQuery = true)
    List<Map<String, Object>> getStudentInfoByUserName(String userName);
}