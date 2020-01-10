package me.zhengjie.modules.student.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.student.domain.StudentInfo;
import me.zhengjie.modules.student.service.StudentInfoService;
import me.zhengjie.modules.student.service.dto.StudentInfoQueryCriteria;
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
* @date 2020-01-09
*/
@Api(tags = "学生管理管理")
@RestController
@RequestMapping("/api/studentInfo")
public class StudentInfoController {

    private final StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('studentInfo:list')")
    public void download(HttpServletResponse response, StudentInfoQueryCriteria criteria) throws IOException {
        studentInfoService.download(studentInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询学生管理")
    @ApiOperation("查询学生管理")
    @PreAuthorize("@el.check('studentInfo:list')")
    public ResponseEntity<Object> getStudentInfos(StudentInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(studentInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增学生管理")
    @ApiOperation("新增学生管理")
    @PreAuthorize("@el.check('studentInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody StudentInfo resources){
        return new ResponseEntity<>(studentInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改学生管理")
    @ApiOperation("修改学生管理")
    @PreAuthorize("@el.check('studentInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody StudentInfo resources){
        studentInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除学生管理")
    @ApiOperation("删除学生管理")
    @PreAuthorize("@el.check('studentInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        studentInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}