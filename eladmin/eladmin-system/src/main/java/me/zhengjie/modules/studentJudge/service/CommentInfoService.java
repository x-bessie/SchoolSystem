package me.zhengjie.modules.studentJudge.service;

import me.zhengjie.modules.studentJudge.domain.CommentInfo;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoDto;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author bessie
* @date 2020-01-30
*/
public interface CommentInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(CommentInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CommentInfoDto>
    */
    List<CommentInfoDto> queryAll(CommentInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CommentInfoDto
     */
    CommentInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return CommentInfoDto
    */
    CommentInfoDto create(CommentInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(CommentInfo resources);

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
    void download(List<CommentInfoDto> all, HttpServletResponse response) throws IOException;

    /**
     * 评价：学生评价教师
     * @param username
     * @param class_id
     * @param teacher_name
     * @param teacher_id
     * @param memo
     */
    void InsertCommentByStudent(String username, Integer class_id, String teacher_name, Integer teacher_id, String memo);
}