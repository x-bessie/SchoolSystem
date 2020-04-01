package me.zhengjie.modules.studentJudge.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.studentJudge.domain.CommentInfo;
import me.zhengjie.modules.studentJudge.service.CommentInfoService;
import me.zhengjie.modules.studentJudge.service.dto.CommentInfoQueryCriteria;
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
 * @author bessie
 * @date 2020-01-30
 */
@Api(tags = "教师评价管理")
@RestController
@RequestMapping("/api/commentInfo")
public class CommentInfoController {

    private final CommentInfoService commentInfoService;

    public CommentInfoController(CommentInfoService commentInfoService) {
        this.commentInfoService = commentInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('commentInfo:list')")
    public void download(HttpServletResponse response, CommentInfoQueryCriteria criteria) throws IOException {
        commentInfoService.download(commentInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询教师评价")
    @ApiOperation("查询教师评价")
    @PreAuthorize("@el.check('commentInfo:list')")
    public ResponseEntity<Object> getCommentInfos(CommentInfoQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(commentInfoService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增教师评价")
    @ApiOperation("新增教师评价")
    @PreAuthorize("@el.check('commentInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CommentInfo resources) {
        return new ResponseEntity<>(commentInfoService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改教师评价")
    @ApiOperation("修改教师评价")
    @PreAuthorize("@el.check('commentInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CommentInfo resources) {
        commentInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除教师评价")
    @ApiOperation("删除教师评价")
    @PreAuthorize("@el.check('commentInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        commentInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("评价：学生评价教师")
    @ApiOperation("评价：学生评价教师")
    @PostMapping(value = "/InsertCommentByStudent")
    public ResponseEntity<Object> InsertCommentByStudent(
            @RequestParam("username") String username,
            @RequestParam("class_id") String class_id,
            @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_id") String teacher_id,
            @RequestParam("memo") String memo,
            @RequestParam("class_name") String class_name

    ) {
        commentInfoService.InsertCommentByStudent(username, class_id, teacher_name, teacher_id, memo,class_name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ApiOperation("评价：评价之前的查询")
    @GetMapping(value = "/queryCommentByStudent")
    public ResponseEntity<Object> queryCommentByStudent(
            @RequestParam("class_id") String class_id,
            @RequestParam("teacher_name") String teacher_name,
            @RequestParam("status") String status,
            @RequestParam("username") String username
    ) {

        return new ResponseEntity<>(commentInfoService.queryCommentByStudent(class_id, teacher_name, status,username),HttpStatus.OK);
    }


}