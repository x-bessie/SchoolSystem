package me.zhengjie.modules.grade.repository;

import me.zhengjie.modules.grade.domain.GradeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author bessie
 * @date 2020-02-03
 */
@Repository
public interface GradeInfoRepository extends JpaRepository<GradeInfo, Integer>, JpaSpecificationExecutor<GradeInfo> {
    @Query(value = "SELECT a.* FROM course_info AS a, teachers_info AS b WHERE a.teacher_id=b.id  AND b.teacherid= ?1 and status=1", nativeQuery = true)
    List<Map<String, Object>> getTeacherCourse(String username);

    @Modifying
    @Query(value = "INSERT IGNORE  INTO grade_info (class_id,class_code,teacher_id,teacher_name,grade,course_name,username,student_name,status,pdate) " +
            " VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    void InsertStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name, Float grade,
                            String course_name, String username, String student_name, Integer status, Date pdate);

    @Query(value = "select * from grade_info  where username=?1  and class_code=?2 ", nativeQuery = true)
    List<Map<String, Object>> getCourseGrade(String username, String class_code);

    @Modifying
    @Query(value = "UPDATE grade_info SET class_id = ?1, class_code = ?2,  teacher_id = ?3,  teacher_name = ?4, " +
            "grade = ?5, course_name = ?6, username = ?7, student_name = ?8, STATUS = ?9,  pdate = ?10  WHERE id=?11", nativeQuery = true)
    void updateStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name,
                            Float grade, String course_name, String username, String student_name, Integer status, Date pdate, Integer id);

    @Query(value = "select * from grade_info where username=?1", nativeQuery = true)
    List<Map<String, Object>> getCourseGradeByStudent(String username);
}