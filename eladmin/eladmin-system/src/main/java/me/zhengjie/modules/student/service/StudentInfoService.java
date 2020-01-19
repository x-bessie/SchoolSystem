package me.zhengjie.modules.student.service;

import me.zhengjie.modules.student.domain.StudentInfo;
import me.zhengjie.modules.student.service.dto.StudentInfoDto;
import me.zhengjie.modules.student.service.dto.StudentInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author HUO
* @date 2020-01-09
*/
public interface StudentInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(StudentInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<StudentInfoDto>
    */
    List<StudentInfoDto> queryAll(StudentInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return StudentInfoDto
     */
    StudentInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return StudentInfoDto
    */
    StudentInfoDto create(StudentInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(StudentInfo resources);

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
    void download(List<StudentInfoDto> all, HttpServletResponse response) throws IOException;

    /**
     * 根据学生用户登录名查询个人信息
     * @return
     */
    Object getStudentInfoByName();
}