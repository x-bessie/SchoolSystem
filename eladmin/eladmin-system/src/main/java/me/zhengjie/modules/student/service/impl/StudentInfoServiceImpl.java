package me.zhengjie.modules.student.service.impl;

import me.zhengjie.modules.student.domain.StudentInfo;
import me.zhengjie.utils.*;
import me.zhengjie.modules.student.repository.StudentInfoRepository;
import me.zhengjie.modules.student.service.StudentInfoService;
import me.zhengjie.modules.student.service.dto.StudentInfoDto;
import me.zhengjie.modules.student.service.dto.StudentInfoQueryCriteria;
import me.zhengjie.modules.student.service.mapper.StudentInfoMapper;
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
 * @date 2020-01-09
 */
@Service
//@CacheConfig(cacheNames = "studentInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentInfoServiceImpl implements StudentInfoService {

    private final StudentInfoRepository studentInfoRepository;

    private final StudentInfoMapper studentInfoMapper;

    public StudentInfoServiceImpl(StudentInfoRepository studentInfoRepository, StudentInfoMapper studentInfoMapper) {
        this.studentInfoRepository = studentInfoRepository;
        this.studentInfoMapper = studentInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(StudentInfoQueryCriteria criteria, Pageable pageable) {
        Page<StudentInfo> page = studentInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(studentInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<StudentInfoDto> queryAll(StudentInfoQueryCriteria criteria) {
        return studentInfoMapper.toDto(studentInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public StudentInfoDto findById(Integer id) {
        StudentInfo studentInfo = studentInfoRepository.findById(id).orElseGet(StudentInfo::new);
        ValidationUtil.isNull(studentInfo.getId(), "StudentInfo", "id", id);
        return studentInfoMapper.toDto(studentInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public StudentInfoDto create(StudentInfo resources) {
        return studentInfoMapper.toDto(studentInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(StudentInfo resources) {
        StudentInfo studentInfo = studentInfoRepository.findById(resources.getId()).orElseGet(StudentInfo::new);
        ValidationUtil.isNull(studentInfo.getId(), "StudentInfo", "id", resources.getId());
        studentInfo.copy(resources);
        studentInfoRepository.save(studentInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            studentInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<StudentInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (StudentInfoDto studentInfo : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("姓名", studentInfo.getName());
            map.put("性别", studentInfo.getSex());
            map.put("年龄", studentInfo.getAge());
            map.put("身份证", studentInfo.getIdnum());
            map.put("地址", studentInfo.getAddress());
            map.put("邮箱", studentInfo.getEmail());
            map.put("兴趣爱好", studentInfo.getHobby());
            map.put("联系方式", studentInfo.getTellnum());
            map.put("父母名称", studentInfo.getParentname());
            map.put("父母联系方式", studentInfo.getParentnum());
            map.put("辅导员", studentInfo.getTeachername());
            map.put("导师", studentInfo.getGuiderteacher());
            map.put("userId", studentInfo.getUserId());
            map.put("username", studentInfo.getUsername());
            map.put("专业", studentInfo.getProfession());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 根据登录用户信息查询学生个人信息
     *
     * @return
     */
    @Override
    public Object getStudentInfoByName() {
        //获取当前用户名
        String username = SecurityUtils.getUsername();
        System.out.println(username);

        List<Map<String, Object>> list = studentInfoRepository.getStudentInfoByUserName(username);
        if (list == null) {
            return "请管理员添加student信息";
        }
        return list;
    }

    /**
     * 参数提交个人信息更新
     *
     * @param idNum
     * @param address
     * @param email
     * @param hobby
     * @param parent_name
     * @param parent_num
     * @param username
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentInfos(String idNum, String address, String email, String hobby, String parent_name, String parent_num, String tell_num, String username) {
        studentInfoRepository.updateStudentInfos(idNum, address, email, hobby, parent_name, parent_num, tell_num, username);

    }

    /**
     * 新增到学生信息
     *
     * @param username
     * @param name
     * @param sex
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InsertStudentInfo(String username, String name, String sex, String profession) {
        studentInfoRepository.InsertStudentInfo(username, name, sex, profession);

    }

}