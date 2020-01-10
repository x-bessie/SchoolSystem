package me.zhengjie.modules.studentCourse.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.studentCourse.domain.StudentCourseInfo;
import me.zhengjie.modules.studentCourse.service.StudentCourseInfoService;
import me.zhengjie.modules.studentCourse.service.dto.StudentCourseInfoQueryCriteria;
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
* @date 2020-01-10
*/
@Api(tags = "课程：学生课程管理")
@RestController
@RequestMapping("/api/studentCourseInfo")
public class StudentCourseInfoController {

    private final StudentCourseInfoService studentCourseInfoService;

    public StudentCourseInfoController(StudentCourseInfoService studentCourseInfoService) {
        this.studentCourseInfoService = studentCourseInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('studentCourseInfo:list')")
    public void download(HttpServletResponse response, StudentCourseInfoQueryCriteria criteria) throws IOException {
        studentCourseInfoService.download(studentCourseInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询课程：学生课程")
    @ApiOperation("查询课程：学生课程")
    @PreAuthorize("@el.check('studentCourseInfo:list')")
    public ResponseEntity<Object> getStudentCourseInfos(StudentCourseInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(studentCourseInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程：学生课程")
    @ApiOperation("新增课程：学生课程")
    @PreAuthorize("@el.check('studentCourseInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StudentCourseInfo resources){
        return new ResponseEntity<>(studentCourseInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程：学生课程")
    @ApiOperation("修改课程：学生课程")
    @PreAuthorize("@el.check('studentCourseInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StudentCourseInfo resources){
        studentCourseInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除课程：学生课程")
    @ApiOperation("删除课程：学生课程")
    @PreAuthorize("@el.check('studentCourseInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        studentCourseInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}