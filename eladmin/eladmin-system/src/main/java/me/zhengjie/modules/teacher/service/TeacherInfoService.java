package me.zhengjie.modules.teacher.service;

import me.zhengjie.modules.teacher.domain.TeacherInfo;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoDto;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author HUO
* @date 2020-01-09
*/
public interface TeacherInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TeacherInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TeacherInfoDto>
    */
    List<TeacherInfoDto> queryAll(TeacherInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TeacherInfoDto
     */
    TeacherInfoDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TeacherInfoDto
    */
    TeacherInfoDto create(TeacherInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TeacherInfo resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<TeacherInfoDto> all, HttpServletResponse response) throws IOException;
}