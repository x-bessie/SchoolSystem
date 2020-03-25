package me.zhengjie.modules.studentJudge.service.impl;

import me.zhengjie.modules.studentJudge.domain.CommentInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.studentJudge.repository.CommentInfoRepository;
import me.zhengjie.modules.studentJudge.service.CommentInfoService;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoDto;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoQueryCriteria;
import me.zhengjie.modules.studentJudge.service.mapper.CommentInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bessie
 * @date 2020-01-30
 */
@Service
//@CacheConfig(cacheNames = "commentInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CommentInfoServiceImpl implements CommentInfoService {

    private final CommentInfoRepository commentInfoRepository;

    private final CommentInfoMapper commentInfoMapper;

    public CommentInfoServiceImpl(CommentInfoRepository commentInfoRepository, CommentInfoMapper commentInfoMapper) {
        this.commentInfoRepository = commentInfoRepository;
        this.commentInfoMapper = commentInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(CommentInfoQueryCriteria criteria, Pageable pageable) {
        Page<CommentInfo> page = commentInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(commentInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<CommentInfoDto> queryAll(CommentInfoQueryCriteria criteria) {
        return commentInfoMapper.toDto(commentInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public CommentInfoDto findById(Integer id) {
        CommentInfo commentInfo = commentInfoRepository.findById(id).orElseGet(CommentInfo::new);
        ValidationUtil.isNull(commentInfo.getId(), "CommentInfo", "id", id);
        return commentInfoMapper.toDto(commentInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CommentInfoDto create(CommentInfo resources) {
        return commentInfoMapper.toDto(commentInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(CommentInfo resources) {
        CommentInfo commentInfo = commentInfoRepository.findById(resources.getId()).orElseGet(CommentInfo::new);
        ValidationUtil.isNull(commentInfo.getId(), "CommentInfo", "id", resources.getId());
        commentInfo.copy(resources);
        commentInfoRepository.save(commentInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            commentInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CommentInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CommentInfoDto commentInfo : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("学号", commentInfo.getUsername());
            map.put("课程代码", commentInfo.getClassId());
            map.put("教师工号", commentInfo.getTeacherId());
            map.put("课程名称", commentInfo.getClassName());
            map.put("教师名称", commentInfo.getTeacherName());
            map.put("评价", commentInfo.getMemo());
            map.put("创建时间", commentInfo.getCreatetime());
            map.put("状态", commentInfo.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 评价：学生评价教师
     *
     * @param username
     * @param class_id
     * @param teacher_name
     * @param teacher_id
     * @param memo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InsertCommentByStudent(String username, String class_id, String teacher_name, String teacher_id, String memo,String class_name) {
        commentInfoRepository.InsertCommentByStudent(username, class_id, teacher_name, teacher_id, memo, class_name,new Date());

    }

    /**
     * 评价之前的查询
     *
     * @param class_id
     * @param teacher_name
     * @param status
     */
    @Override
    public Object queryCommentByStudent(String class_id, String teacher_name, String status) {
        List<Map<String, Object>> list = commentInfoRepository.queryCommentByStudent(class_id, teacher_name, status);
        if (list == null) {
            return "暂时本课程无成绩";
        }
        return list;
    }
}