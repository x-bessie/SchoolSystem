package me.zhengjie.modules.teachers.repository;

import me.zhengjie.modules.teachers.domain.TeachersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author bessie
 * @date 2020-01-13
 */
@Repository
public interface TeachersInfoRepository extends JpaRepository<TeachersInfo, Integer>, JpaSpecificationExecutor<TeachersInfo> {

    /**
     * 教师查询：查询课程安排
     *
     * @param username
     * @return
     */
    @Query(value = "SELECT a.* FROM course_info AS a, teachers_info AS b WHERE a.teacher_id=b.teacherid  AND b.teacherid= ?1 and status=1", nativeQuery = true)
    List<Map<String, Object>> getTeacherCourseMessage(String username);

    /**
     * 教师查询：查询课程下的学生
     *
     * @param teacher_id
     * @param class_code
     * @return
     */
    @Query(value = "SELECT t.*,c.username,c.name as student_name  FROM ( SELECT a.*, b.student_id  FROM course_info AS a, student_course_info AS b  WHERE a.teacher_id = ?1 AND a.class_code = b.course_code  AND a.class_code = ?2 ) t  ,student_info c where t.student_id=c.id", nativeQuery = true)
    List<Map<String, Object>> getTeacherCourseToSeeStudent(String teacher_id, String class_code);

    /**
     * 教师查询：教师个人信息查询
     *
     * @param username
     * @return
     */
    @Query(value = "select * from teachers_info  WHERE teacherid=?1", nativeQuery = true)
    List<Map<String, Object>> getTeachersInfoByUserName(String username);

    /**
     * 更新个人信息
     *
     * @param idnum
     * @param address
     * @param phone
     */
    @Modifying
    @Query(value = "update teachers_info set Idnum=?1,address=?2,phone=?3 where teacherid=?4 ", nativeQuery = true)
    void updateTeacherInfo(String idnum, String address, String phone, String teacherid);

    /**
     * 更新至教师表
     *
     * @param name
     * @param teacherid
     */
    @Modifying
    @Query(value = "Insert ignore  into teachers_info(name,teacherid) values (?1,?2) ", nativeQuery = true)
    void InsertTeacherInfo(String name, String teacherid);
}