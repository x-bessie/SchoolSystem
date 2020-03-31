package me.zhengjie.modules.teachers.service;

import me.zhengjie.modules.teachers.domain.TeachersInfo;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoDto;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bessie
 * @date 2020-01-13
 */
public interface TeachersInfoService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(TeachersInfoQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List<TeachersInfoDto>
     */
    List<TeachersInfoDto> queryAll(TeachersInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return TeachersInfoDto
     */
    TeachersInfoDto findById(Integer id);

    /**
     * 创建
     *
     * @param resources /
     * @return TeachersInfoDto
     */
    TeachersInfoDto create(TeachersInfo resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(TeachersInfo resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(Integer[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<TeachersInfoDto> all, HttpServletResponse response) throws IOException;

    /**
     * 教师查询：查询课程安排
     *
     * @return
     */
    Object getTeacherCourseMessage();

    /**
     * 教师查询：查询课程下的学生
     *
     * @param teacher_id
     * @param class_code
     * @return
     */
    Object getTeacherCourseToSeeStudent(String teacher_id, String class_code);

    /**
     * 教师查询：教师个人信息查询
     *
     * @return
     */
    Object getTeachersInfoByUserName();

    /**
     * 更新个人信息
     *
     * @param idnum
     * @param address
     * @param phone
     */
    void updateTeacherInfo(String idnum, String address, String phone, String teacherid);

    /**
     * 更新至教师表
     * @param name
     * @param teacherid
     */
    void InsertTeacherInfo(String name, String teacherid);
}