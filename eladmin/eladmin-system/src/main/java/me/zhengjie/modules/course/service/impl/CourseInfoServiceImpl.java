package me.zhengjie.modules.course.service.impl;

import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.course.repository.CourseInfoRepository;
import me.zhengjie.modules.course.service.CourseInfoService;
import me.zhengjie.modules.course.service.dto.CourseInfoDto;
import me.zhengjie.modules.course.service.dto.CourseInfoQueryCriteria;
import me.zhengjie.modules.course.service.mapper.CourseInfoMapper;
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
* @date 2020-01-16
*/
@Service
//@CacheConfig(cacheNames = "courseInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseInfoServiceImpl implements CourseInfoService {

    private final CourseInfoRepository courseInfoRepository;

    private final CourseInfoMapper courseInfoMapper;

    public CourseInfoServiceImpl(CourseInfoRepository courseInfoRepository, CourseInfoMapper courseInfoMapper) {
        this.courseInfoRepository = courseInfoRepository;
        this.courseInfoMapper = courseInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(CourseInfoQueryCriteria criteria, Pageable pageable){
        Page<CourseInfo> page = courseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(courseInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<CourseInfoDto> queryAll(CourseInfoQueryCriteria criteria){
        return courseInfoMapper.toDto(courseInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public CourseInfoDto findById(Integer id) {
        CourseInfo courseInfo = courseInfoRepository.findById(id).orElseGet(CourseInfo::new);
        ValidationUtil.isNull(courseInfo.getId(),"CourseInfo","id",id);
        return courseInfoMapper.toDto(courseInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public CourseInfoDto create(CourseInfo resources) {
        return courseInfoMapper.toDto(courseInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(CourseInfo resources) {
        CourseInfo courseInfo = courseInfoRepository.findById(resources.getId()).orElseGet(CourseInfo::new);
        ValidationUtil.isNull( courseInfo.getId(),"CourseInfo","id",resources.getId());
        courseInfo.copy(resources);
        courseInfoRepository.save(courseInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            courseInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CourseInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CourseInfoDto courseInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("课程名称", courseInfo.getName());
            map.put("创建时间", courseInfo.getCreateTime());
            map.put("课程时长", courseInfo.getClasstime());
            map.put("周数", courseInfo.getClassNum());
            map.put("任课教师", courseInfo.getClassTeacher());
            map.put("教师Id", courseInfo.getTeacherId());
            map.put("课程代码", courseInfo.getClassCode());
            map.put("学期", courseInfo.getTearm());
            map.put("学年", courseInfo.getSchoolYear());
            map.put("状态值：0 禁用,1激活", courseInfo.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}