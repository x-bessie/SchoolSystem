package me.zhengjie.modules.studentCourse.service;

import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoDto;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author bessie
* @date 2020-01-10
*/
public interface StudentCourseInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(StudentCourseInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<StudentCourseInfoDto>
    */
    List<StudentCourseInfoDto> queryAll(StudentCourseInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return StudentCourseInfoDto
     */
    StudentCourseInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return StudentCourseInfoDto
    */
    StudentCourseInfoDto create(StudentCourseInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(StudentCourseInfo resources);

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
    void download(List<StudentCourseInfoDto> all, HttpServletResponse response) throws IOException;

    /**
     * 学生本人课程详细信息
     * @return
     */
    Object getStudentCourseMessage();
}