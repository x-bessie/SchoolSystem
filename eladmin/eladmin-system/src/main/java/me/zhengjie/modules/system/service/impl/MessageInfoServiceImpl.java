package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.MessageInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.system.repository.MessageInfoRepository;
import me.zhengjie.modules.system.service.MessageInfoService;
import me.zhengjie.modules.system.service.dto.MessageInfoDto;
import me.zhengjie.modules.system.service.dto.MessageInfoQueryCriteria;
import me.zhengjie.modules.system.service.mapper.MessageInfoMapper;
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
 * @author HUO
 * @date 2020-01-13
 */
@Service
//@CacheConfig(cacheNames = "messageInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MessageInfoServiceImpl implements MessageInfoService {

    private final MessageInfoRepository messageInfoRepository;

    private final MessageInfoMapper messageInfoMapper;

    public MessageInfoServiceImpl(MessageInfoRepository messageInfoRepository, MessageInfoMapper messageInfoMapper) {
        this.messageInfoRepository = messageInfoRepository;
        this.messageInfoMapper = messageInfoMapper;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(MessageInfoQueryCriteria criteria, Pageable pageable) {
        Page<MessageInfo> page = messageInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(messageInfoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<MessageInfoDto> queryAll(MessageInfoQueryCriteria criteria) {
        return messageInfoMapper.toDto(messageInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public MessageInfoDto findById(Integer id) {
        MessageInfo messageInfo = messageInfoRepository.findById(id).orElseGet(MessageInfo::new);
        ValidationUtil.isNull(messageInfo.getId(), "MessageInfo", "id", id);
        return messageInfoMapper.toDto(messageInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public MessageInfoDto create(MessageInfo resources) {
        return messageInfoMapper.toDto(messageInfoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(MessageInfo resources) {
        MessageInfo messageInfo = messageInfoRepository.findById(resources.getId()).orElseGet(MessageInfo::new);
        ValidationUtil.isNull(messageInfo.getId(), "MessageInfo", "id", resources.getId());
        messageInfo.copy(resources);
        messageInfoRepository.save(messageInfo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            messageInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<MessageInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MessageInfoDto messageInfo : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("公告内容", messageInfo.getContent());
            map.put("状态值：0 可见 1 不可见", messageInfo.getStatus());
            map.put("标题", messageInfo.getTitle());
            map.put("创建时间", messageInfo.getCreateTime());
            map.put("维护人", messageInfo.getMaintain());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 用户：展示公告栏信息
     *
     * @return
     */
    @Override
    public Object queryMessageInfo() {
//        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
//        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = messageInfoRepository.queryMessageInfo();
        if (list.size() == 0) {
            return "暂无最新公告！";
        }
        return list;
    }
}