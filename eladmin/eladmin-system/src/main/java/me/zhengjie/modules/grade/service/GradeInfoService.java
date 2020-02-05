package me.zhengjie.modules.grade.service;

import me.zhengjie.modules.grade.domain.GradeInfo;
import me.zhengjie.modules.grade.service.dto.GradeInfoDto;
import me.zhengjie.modules.grade.service.dto.GradeInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author bessie
* @date 2020-02-03
*/
public interface GradeInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(GradeInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<GradeInfoDto>
    */
    List<GradeInfoDto> queryAll(GradeInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return GradeInfoDto
     */
    GradeInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return GradeInfoDto
    */
    GradeInfoDto create(GradeInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(GradeInfo resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<GradeInfoDto> all, HttpServletResponse response) throws IOException;

    /**
     * 查询课程安排
     * @return
     */
    Object getTeacherCourse();

    /**
     * 成绩登记：教师登记成绩
     * @param class_id
     * @param class_code
     * @param teacher_id
     * @param teacher_name
     * @param grade
     * @param course_name
     * @param username
     * @param student_name
     * @param status
     */
    void InsertStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name, Float grade,
                            String course_name, String username, String student_name, Integer status);

    /**
     * 查询成绩
     * @param username
     * @param class_code
     * @return
     */
    Object getCourseGrade(String username, String class_code);

    /**
     * 成绩登记：教师更新成绩
     * @param class_id
     * @param class_code
     * @param teacher_id
     * @param teacher_name
     * @param grade
     * @param course_name
     * @param username
     * @param student_name
     * @param status
     */
    void updateStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name, Float grade, String course_name, String username, String student_name, Integer status,Integer id);

    /*
    学生查询成绩
     */
    Object getCourseGradeByStudent();
}