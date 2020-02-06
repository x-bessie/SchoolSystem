package me.zhengjie.modules.teachers.service.impl;

import me.zhengjie.modules.teachers.domain.TeachersInfo;
import me.zhengjie.utils.*;
import me.zhengjie.modules.teachers.repository.TeachersInfoRepository;
import me.zhengjie.modules.teachers.service.TeachersInfoService;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoDto;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoQueryCriteria;
import me.zhengjie.modules.teachers.service.mapper.TeachersInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author HUO
 * @date 2020-01-13
 */
@Service
//@CacheConfig(cacheNames = "teachersInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeachersInfoServiceImpl implements TeachersInfoService {

    private final TeachersInfoRepository teachersInfoRepository;

    private final TeachersInfoMapper teachersInfoMapper;

    public TeachersInfoServiceImpl(TeachersInfoRepository teachersInfoRepository, TeachersInfoMapper teachersInfoMapper) {
        this.teachersInfoRepository = teachersInfoRepository;
        this.teachersInfoMapper = teachersInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(TeachersInfoQueryCriteria criteria, Pageable pageable) {
        Page<TeachersInfo> page = teachersInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(teachersInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TeachersInfoDto> queryAll(TeachersInfoQueryCriteria criteria) {
        return teachersInfoMapper.toDto(teachersInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TeachersInfoDto findById(Integer id) {
        TeachersInfo teachersInfo = teachersInfoRepository.findById(id).orElseGet(TeachersInfo::new);
        ValidationUtil.isNull(teachersInfo.getId(), "TeachersInfo", "id", id);
        return teachersInfoMapper.toDto(teachersInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TeachersInfoDto create(TeachersInfo resources) {
        return teachersInfoMapper.toDto(teachersInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TeachersInfo resources) {
        TeachersInfo teachersInfo = teachersInfoRepository.findById(resources.getId()).orElseGet(TeachersInfo::new);
        ValidationUtil.isNull(teachersInfo.getId(), "TeachersInfo", "id", resources.getId());
        teachersInfo.copy(resources);
        teachersInfoRepository.save(teachersInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            teachersInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TeachersInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TeachersInfoDto teachersInfo : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("教师工号", teachersInfo.getUserId());
            map.put("姓名", teachersInfo.getName());
            map.put("年龄", teachersInfo.getAge());
            map.put("性别", teachersInfo.getSex());
            map.put("地址", teachersInfo.getAddress());
            map.put("电话", teachersInfo.getPhone());
            map.put("身份证", teachersInfo.getIdnum());
            map.put("工号", teachersInfo.getTeacherid());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 教师查询：查询课程安排
     *
     * @return
     */
    @Override
    public Object getTeacherCourseMessage() {

        String username = SecurityUtils.getUsername();
        System.out.println(username);

        List<Map<String, Object>> list = teachersInfoRepository.getTeacherCourseMessage(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    /**
     * 教师查询：查询课程下的学生
     *
     * @param teacher_id
     * @param class_code
     * @return
     */
    @Override
    public Object getTeacherCourseToSeeStudent(Integer teacher_id, String class_code) {

        List<Map<String, Object>> list = teachersInfoRepository.getTeacherCourseToSeeStudent(teacher_id, class_code);
        if (list == null) {
            return "此课程暂时无上课学生";
        }
        return list;
    }

    /**
     * @return
     */
    @Override
    public Object getTeachersInfoByUserName() {
        //获取当前用户名
        String username = SecurityUtils.getUsername();
        System.out.println(username);

        List<Map<String, Object>> list = teachersInfoRepository.getTeachersInfoByUserName(username);
        if (list == null) {
            return "暂时无教师个人信息";
        }

        return list;
    }

    /**
     * 更新个人信息
     *
     * @param idnum
     * @param address
     * @param phone
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTeacherInfo(String idnum, String address, String phone, String teacherid) {
        teachersInfoRepository.updateTeacherInfo(idnum, address, phone, teacherid);
    }

    /**
     * 更新至教师表
     * @param name
     * @param teacherid
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InsertTeacherInfo(String name, String teacherid) {
        teachersInfoRepository.InsertTeacherInfo(name, teacherid);

    }
}