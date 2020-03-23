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
    @Query(value = "SELECT t.course_code,t.username,t. NAME,c.`name` AS course_name," +
            "c.classtime,c.class_num,c.class_teacher,c.teacher_id,c.class_code,c.tearm, c.school_year " +
            " FROM ( SELECT a.student_code, a.course_code, a.pdate,b.username, b. NAME  FROM student_course_info AS a,student_info AS b WHERE a.student_code = b.username  AND b.username = ?1 ) t," +
            "course_info AS c  WHERE c.class_code = t.course_code ", nativeQuery = true)
    List<Map<String, Object>> getStudentInfoByUserName(String userName);
}