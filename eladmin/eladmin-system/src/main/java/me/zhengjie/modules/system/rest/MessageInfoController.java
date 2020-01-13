package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.MessageInfo;
import me.zhengjie.modules.system.service.MessageInfoService;
import me.zhengjie.modules.system.service.dto.MessageInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HUO
 * @date 2020-01-13
 */
@Api(tags = "公告栏管理")
@RestController
@RequestMapping("/api/messageInfo")
public class MessageInfoController {

    private final MessageInfoService messageInfoService;

    public MessageInfoController(MessageInfoService messageInfoService) {
        this.messageInfoService = messageInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('messageInfo:list')")
    public void download(HttpServletResponse response, MessageInfoQueryCriteria criteria) throws IOException {
        messageInfoService.download(messageInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询公告栏")
    @ApiOperation("查询公告栏")
    @PreAuthorize("@el.check('messageInfo:list')")
    public ResponseEntity<Object> getMessageInfos(MessageInfoQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(messageInfoService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增公告栏")
    @ApiOperation("新增公告栏")
    @PreAuthorize("@el.check('messageInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody MessageInfo resources) {
        return new ResponseEntity<>(messageInfoService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改公告栏")
    @ApiOperation("修改公告栏")
    @PreAuthorize("@el.check('messageInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody MessageInfo resources) {
        messageInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除公告栏")
    @ApiOperation("删除公告栏")
    @PreAuthorize("@el.check('messageInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        messageInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * *******用户****
     */

    @Log("展示公告栏")
    @ApiOperation("展示公告栏")
    @GetMapping(value="/queryMessageInfo")
    public ResponseEntity<Object> queryMessageInfo() {
        return new ResponseEntity<>(messageInfoService.queryMessageInfo(), HttpStatus.OK);

    }
}