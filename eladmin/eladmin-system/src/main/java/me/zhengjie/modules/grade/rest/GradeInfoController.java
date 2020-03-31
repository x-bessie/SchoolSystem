package me.zhengjie.modules.grade.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.grade.domain.GradeInfo;
import me.zhengjie.modules.grade.service.GradeInfoService;
import me.zhengjie.modules.grade.service.dto.GradeInfoQueryCriteria;
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
 * @date 2020-02-03
 */
@Api(tags = "学生成绩管理")
@RestController
@RequestMapping("/api/gradeInfo")
public class GradeInfoController {

    private final GradeInfoService gradeInfoService;

    public GradeInfoController(GradeInfoService gradeInfoService) {
        this.gradeInfoService = gradeInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('gradeInfo:list')")
    public void download(HttpServletResponse response, GradeInfoQueryCriteria criteria) throws IOException {
        gradeInfoService.download(gradeInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询学生成绩")
    @ApiOperation("查询学生成绩")
    @PreAuthorize("@el.check('gradeInfo:list')")
    public ResponseEntity<Object> getGradeInfos(GradeInfoQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(gradeInfoService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增学生成绩")
    @ApiOperation("新增学生成绩")
    @PreAuthorize("@el.check('gradeInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody GradeInfo resources) {
        return new ResponseEntity<>(gradeInfoService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改学生成绩")
    @ApiOperation("修改学生成绩")
    @PreAuthorize("@el.check('gradeInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody GradeInfo resources) {
        gradeInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除学生成绩")
    @ApiOperation("删除学生成绩")
    @PreAuthorize("@el.check('gradeInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        gradeInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherCourse")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getTeacherCourse() {
        return new ResponseEntity<>(gradeInfoService.getTeacherCourse(), HttpStatus.OK);
    }

    @Log("成绩登记：教师登记成绩")
    @ApiOperation("成绩登记：教师登记成绩")
    @PostMapping(value = "/InsertStudentGrade")
    public ResponseEntity<Object> InsertStudentGrade(
//            @RequestParam("class_id") Integer class_id,
            @RequestParam("class_code") String class_code,
            @RequestParam("teacher_id") String teacher_id,
            @RequestParam("teacher_name") String teacher_name,
            @RequestParam("grade") Float grade,
            @RequestParam("course_name") String course_name,
            @RequestParam("username") String username,
            @RequestParam("student_name") String student_name,
            @RequestParam("status") Integer status
    ) {
        gradeInfoService.InsertStudentGrade(class_code, teacher_id, teacher_name, grade, course_name,
                username, student_name, status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCourseGrade")
    @Log("查询成绩")
    @ApiOperation("查询成绩")
    public ResponseEntity<Object> getCourseGrade(
            @RequestParam("username") String username,
            @RequestParam("class_code") String class_code
    ) {
        return new ResponseEntity<>(gradeInfoService.getCourseGrade(username, class_code), HttpStatus.OK);
    }

    @Log("成绩登记：教师更新成绩")
    @ApiOperation("成绩登记：教师更新成绩")
    @PostMapping(value = "/updateStudentGrade")
    public ResponseEntity<Object> updateStudentGrade(
//            @RequestParam("class_id") Integer class_id,
            @RequestParam("class_code") String class_code,
            @RequestParam("teacher_id") String teacher_id,
            @RequestParam("teacher_name") String teacher_name,
            @RequestParam("grade") Float grade,
            @RequestParam("course_name") String course_name,
            @RequestParam("username") String username,
            @RequestParam("student_name") String student_name,
            @RequestParam("status") Integer status,
            @RequestParam("id") Integer id
    ) {
        gradeInfoService.updateStudentGrade( class_code, teacher_id, teacher_name, grade, course_name,
                username, student_name, status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCourseGradeByStudent")
    @Log("学生查询成绩")
    @ApiOperation("学生查询成绩")
    public ResponseEntity<Object> getCourseGradeByStudent(
    ) {
        return new ResponseEntity<>(gradeInfoService.getCourseGradeByStudent(), HttpStatus.OK);
    }
}