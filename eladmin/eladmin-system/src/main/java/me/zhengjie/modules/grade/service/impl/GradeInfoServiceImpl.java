package me.zhengjie.modules.grade.service.impl;

import me.zhengjie.modules.grade.domain.GradeInfo;
import me.zhengjie.utils.*;
import me.zhengjie.modules.grade.repository.GradeInfoRepository;
import me.zhengjie.modules.grade.service.GradeInfoService;
import me.zhengjie.modules.grade.service.dto.GradeInfoDto;
import me.zhengjie.modules.grade.service.dto.GradeInfoQueryCriteria;
import me.zhengjie.modules.grade.service.mapper.GradeInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bessie
 * @date 2020-02-03
 */
@Service
//@CacheConfig(cacheNames = "gradeInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GradeInfoServiceImpl implements GradeInfoService {

    private final GradeInfoRepository gradeInfoRepository;

    private final GradeInfoMapper gradeInfoMapper;

    public GradeInfoServiceImpl(GradeInfoRepository gradeInfoRepository, GradeInfoMapper gradeInfoMapper) {
        this.gradeInfoRepository = gradeInfoRepository;
        this.gradeInfoMapper = gradeInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(GradeInfoQueryCriteria criteria, Pageable pageable) {
        Page<GradeInfo> page = gradeInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(gradeInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<GradeInfoDto> queryAll(GradeInfoQueryCriteria criteria) {
        return gradeInfoMapper.toDto(gradeInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public GradeInfoDto findById(Integer id) {
        GradeInfo gradeInfo = gradeInfoRepository.findById(id).orElseGet(GradeInfo::new);
        ValidationUtil.isNull(gradeInfo.getId(), "GradeInfo", "id", id);
        return gradeInfoMapper.toDto(gradeInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public GradeInfoDto create(GradeInfo resources) {
        return gradeInfoMapper.toDto(gradeInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(GradeInfo resources) {
        GradeInfo gradeInfo = gradeInfoRepository.findById(resources.getId()).orElseGet(GradeInfo::new);
        ValidationUtil.isNull(gradeInfo.getId(), "GradeInfo", "id", resources.getId());
        gradeInfo.copy(resources);
        gradeInfoRepository.save(gradeInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            gradeInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<GradeInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (GradeInfoDto gradeInfo : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("课程id", gradeInfo.getClassId());
            map.put("课程代码", gradeInfo.getClassCode());
            map.put("教师id", gradeInfo.getTeacherId());
            map.put("教师名称", gradeInfo.getTeacherName());
            map.put("分数", gradeInfo.getGrade());
            map.put("课程名称", gradeInfo.getCourseName());
            map.put("时间", gradeInfo.getPdate());
            map.put("状态", gradeInfo.getStatus());
            map.put("学号", gradeInfo.getUsername());
            map.put("姓名", gradeInfo.getStudentName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 查询课程安排
     *
     * @return
     */
    @Override
    public Object getTeacherCourse() {
        String username = SecurityUtils.getUsername();
        System.out.println(username);

        List<Map<String, Object>> list = gradeInfoRepository.getTeacherCourse(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    /**
     * 成绩登记：教师登记成绩
     *
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InsertStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name, Float grade, String course_name, String username, String student_name, Integer status) {
        gradeInfoRepository.InsertStudentGrade(class_id, class_code, teacher_id, teacher_name, grade, course_name, username, student_name, status, new Date());
    }

    /**
     * 查询成绩
     *
     * @param username
     * @param class_code
     * @return
     */
    @Override
    public Object getCourseGrade(String username, String class_code) {

        List<Map<String, Object>> list = gradeInfoRepository.getCourseGrade(username, class_code);
        if (list == null) {
            return "暂时本课程无成绩";
        }
        return list;
    }

    /**
     * 成绩登记：教师更新成绩
     *
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentGrade(Integer class_id, String class_code, Integer teacher_id, String teacher_name, Float grade, String course_name, String username, String student_name, Integer status,Integer id) {
        gradeInfoRepository.updateStudentGrade(class_id, class_code, teacher_id, teacher_name, grade, course_name, username, student_name, status,new Date(),id);
    }

    /**
     * 学生查询成绩
     * @return
     */
    @Override
    public Object getCourseGradeByStudent() {
        String username = SecurityUtils.getUsername();
        System.out.println(username);

        List<Map<String, Object>> list = gradeInfoRepository.getCourseGradeByStudent(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }
}