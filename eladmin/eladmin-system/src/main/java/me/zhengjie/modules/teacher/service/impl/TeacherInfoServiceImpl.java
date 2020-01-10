package me.zhengjie.modules.teacher.service.impl;

import me.zhengjie.modules.teacher.domain.TeacherInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.teacher.repository.TeacherInfoRepository;
import me.zhengjie.modules.teacher.service.TeacherInfoService;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoDto;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoQueryCriteria;
import me.zhengjie.modules.teacher.service.mapper.TeacherInfoMapper;
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
* @date 2020-01-09
*/
@Service
//@CacheConfig(cacheNames = "teacherInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeacherInfoServiceImpl implements TeacherInfoService {

    private final TeacherInfoRepository teacherInfoRepository;

    private final TeacherInfoMapper teacherInfoMapper;

    public TeacherInfoServiceImpl(TeacherInfoRepository teacherInfoRepository, TeacherInfoMapper teacherInfoMapper) {
        this.teacherInfoRepository = teacherInfoRepository;
        this.teacherInfoMapper = teacherInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TeacherInfoQueryCriteria criteria, Pageable pageable){
        Page<TeacherInfo> page = teacherInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(teacherInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TeacherInfoDto> queryAll(TeacherInfoQueryCriteria criteria){
        return teacherInfoMapper.toDto(teacherInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TeacherInfoDto findById(Long id) {
        TeacherInfo teacherInfo = teacherInfoRepository.findById(id).orElseGet(TeacherInfo::new);
        ValidationUtil.isNull(teacherInfo.getId(),"TeacherInfo","id",id);
        return teacherInfoMapper.toDto(teacherInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TeacherInfoDto create(TeacherInfo resources) {
        return teacherInfoMapper.toDto(teacherInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TeacherInfo resources) {
        TeacherInfo teacherInfo = teacherInfoRepository.findById(resources.getId()).orElseGet(TeacherInfo::new);
        ValidationUtil.isNull( teacherInfo.getId(),"TeacherInfo","id",resources.getId());
        teacherInfo.copy(resources);
        teacherInfoRepository.save(teacherInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            teacherInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TeacherInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TeacherInfoDto teacherInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("姓名", teacherInfo.getName());
            map.put("年龄", teacherInfo.getAge());
            map.put("性别", teacherInfo.getSex());
            map.put("身份证", teacherInfo.getIdnum());
            map.put("地址", teacherInfo.getAddress());
            map.put("邮箱", teacherInfo.getEmail());
            map.put("电话", teacherInfo.getPhone());
            map.put("创建时间", teacherInfo.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}