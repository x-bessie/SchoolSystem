package me.zhengjie.modules.course.service;

import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.modules.course.service.dto.CourseInfoDto;
import me.zhengjie.modules.course.service.dto.CourseInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author HUO
* @date 2020-01-16
*/
public interface CourseInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(CourseInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CourseInfoDto>
    */
    List<CourseInfoDto> queryAll(CourseInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CourseInfoDto
     */
    CourseInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return CourseInfoDto
    */
    CourseInfoDto create(CourseInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(CourseInfo resources);

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
    void download(List<CourseInfoDto> all, HttpServletResponse response) throws IOException;
}