package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.MessageInfo;
import me.zhengjie.modules.system.service.dto.MessageInfoDto;
import me.zhengjie.modules.system.service.dto.MessageInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author HUO
* @date 2020-01-08
*/
public interface MessageInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(MessageInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<MessageInfoDto>
    */
    List<MessageInfoDto> queryAll(MessageInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return MessageInfoDto
     */
    MessageInfoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return MessageInfoDto
    */
    MessageInfoDto create(MessageInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(MessageInfo resources);

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
    void download(List<MessageInfoDto> all, HttpServletResponse response) throws IOException;
}