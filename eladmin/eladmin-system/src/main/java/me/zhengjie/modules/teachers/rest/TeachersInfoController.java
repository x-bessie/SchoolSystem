package me.zhengjie.modules.teachers.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.teachers.domain.TeachersInfo;
import me.zhengjie.modules.teachers.service.TeachersInfoService;
import me.zhengjie.modules.teachers.service.dto.TeachersInfoQueryCriteria;
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
 * @date 2020-01-13
 */
@Api(tags = "教师：信息管理")
@RestController
@RequestMapping("/api/teachersInfo")
public class TeachersInfoController {

    private final TeachersInfoService teachersInfoService;

    public TeachersInfoController(TeachersInfoService teachersInfoService) {
        this.teachersInfoService = teachersInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('teachersInfo:list')")
    public void download(HttpServletResponse response, TeachersInfoQueryCriteria criteria) throws IOException {
        teachersInfoService.download(teachersInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询教师：信息")
    @ApiOperation("查询教师：信息")
    @PreAuthorize("@el.check('teachersInfo:list')")
    public ResponseEntity<Object> getTeachersInfos(TeachersInfoQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(teachersInfoService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增教师：信息")
    @ApiOperation("新增教师：信息")
    @PreAuthorize("@el.check('teachersInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TeachersInfo resources) {
        return new ResponseEntity<>(teachersInfoService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改教师：信息")
    @ApiOperation("修改教师：信息")
    @PreAuthorize("@el.check('teachersInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TeachersInfo resources) {
        teachersInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除教师：信息")
    @ApiOperation("删除教师：信息")
    @PreAuthorize("@el.check('teachersInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        teachersInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getTeachersInfoByUserName")
    @Log("教师查询：教师个人信息")
    @ApiOperation("教师查询：教师个人信息")
    public ResponseEntity<Object> getTeachersInfoByUserName() {
        return new ResponseEntity<>(teachersInfoService.getTeachersInfoByUserName(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherCourseMessage")
    @Log("教师查询：查询课程安排")
    @ApiOperation("教师查询：查询课程安排")
    public ResponseEntity<Object> getTeacherCourseMessage() {
        return new ResponseEntity<>(teachersInfoService.getTeacherCourseMessage(), HttpStatus.OK);
    }


    @GetMapping(value = "/getTeacherCourseToSeeStudent")
    @Log("教师查询：查询课程下的学生")
    @ApiOperation("教师查询：查询课程下的学生")
    public ResponseEntity<Object> getTeacherCourseToSeeStudent(@RequestParam("teacher_id") String teacher_id, @RequestParam("class_code") String class_code) {
        return new ResponseEntity<>(teachersInfoService.getTeacherCourseToSeeStudent(teacher_id, class_code), HttpStatus.OK);
    }

    @Log("更新个人信息")
    @ApiOperation("更新个人信息")
    @PostMapping(value = "/updateTeacherInfo")
    public ResponseEntity<Object> updateTeacherInfo(
            @RequestParam("Idnum") String Idnum,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("teacherid") String teacherid

    ) {
        teachersInfoService.updateTeacherInfo(Idnum, address, phone, teacherid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Log("更新至教师表")
    @ApiOperation("更新至教师表")
    @PostMapping(value = "/InsertTeacherInfo")
    public ResponseEntity<Object> InsertTeacherInfo(
            @RequestParam("name") String name,
            @RequestParam("teacherid") String teacherid

    ) {
        teachersInfoService.InsertTeacherInfo(name, teacherid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/getTeachersGrade")
    @Log("教师查询：教师个人评分")
    @ApiOperation("教师查询：教师个人评分")
    public ResponseEntity<Object> getTeachersGrade() {
        return new ResponseEntity<>(teachersInfoService.getTeachersGrade(), HttpStatus.OK);
    }

}