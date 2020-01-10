package me.zhengjie.modules.studentCourse.service.impl;

import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.studentCourse.repository.StudentCourseInfoRepository;
import me.zhengjie.modules.studentCourse.service.StudentCourseInfoService;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoDto;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoQueryCriteria;
import me.zhengjie.modules.studentCourse.service.mapper.StudentCourseInfoMapper;
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
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author HUO
* @date 2020-01-10
*/
@Service
//@CacheConfig(cacheNames = "studentCourseInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentCourseInfoServiceImpl implements StudentCourseInfoService {

    private final StudentCourseInfoRepository studentCourseInfoRepository;

    private final StudentCourseInfoMapper studentCourseInfoMapper;

    public StudentCourseInfoServiceImpl(StudentCourseInfoRepository studentCourseInfoRepository, StudentCourseInfoMapper studentCourseInfoMapper) {
        this.studentCourseInfoRepository = studentCourseInfoRepository;
        this.studentCourseInfoMapper = studentCourseInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(StudentCourseInfoQueryCriteria criteria, Pageable pageable){
        Page<StudentCourseInfo> page = studentCourseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(studentCourseInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<StudentCourseInfoDto> queryAll(StudentCourseInfoQueryCriteria criteria){
        return studentCourseInfoMapper.toDto(studentCourseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public StudentCourseInfoDto findById(Integer id) {
        StudentCourseInfo studentCourseInfo = studentCourseInfoRepository.findById(id).orElseGet(StudentCourseInfo::new);
        ValidationUtil.isNull(studentCourseInfo.getId(),"StudentCourseInfo","id",id);
        return studentCourseInfoMapper.toDto(studentCourseInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public StudentCourseInfoDto create(StudentCourseInfo resources) {
        return studentCourseInfoMapper.toDto(studentCourseInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(StudentCourseInfo resources) {
        StudentCourseInfo studentCourseInfo = studentCourseInfoRepository.findById(resources.getId()).orElseGet(StudentCourseInfo::new);
        ValidationUtil.isNull( studentCourseInfo.getId(),"StudentCourseInfo","id",resources.getId());
        studentCourseInfo.copy(resources);
        studentCourseInfoRepository.save(studentCourseInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            studentCourseInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<StudentCourseInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StudentCourseInfoDto studentCourseInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" studentId",  studentCourseInfo.getStudentId());
            map.put(" courseId",  studentCourseInfo.getCourseId());
            map.put(" pdate",  studentCourseInfo.getPdate());
            map.put(" tearm",  studentCourseInfo.getTearm());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}