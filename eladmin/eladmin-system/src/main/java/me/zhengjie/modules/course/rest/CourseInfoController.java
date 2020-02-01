package me.zhengjie.modules.course.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.course.domain.CourseInfo;
import me.zhengjie.modules.course.service.CourseInfoService;
import me.zhengjie.modules.course.service.dto.CourseInfoQueryCriteria;
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
* @date 2020-01-16
*/
@Api(tags = "课程管理管理")
@RestController
@RequestMapping("/api/courseInfo")
public class CourseInfoController {

    private final CourseInfoService courseInfoService;

    public CourseInfoController(CourseInfoService courseInfoService) {
        this.courseInfoService = courseInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('courseInfo:list')")
    public void download(HttpServletResponse response, CourseInfoQueryCriteria criteria) throws IOException {
        courseInfoService.download(courseInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询课程管理")
    @ApiOperation("查询课程管理")
    @PreAuthorize("@el.check('courseInfo:list')")
    public ResponseEntity<Object> getCourseInfos(CourseInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(courseInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程管理")
    @ApiOperation("新增课程管理")
    @PreAuthorize("@el.check('courseInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CourseInfo resources){
        return new ResponseEntity<>(courseInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程管理")
    @ApiOperation("修改课程管理")
    @PreAuthorize("@el.check('courseInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CourseInfo resources){
        courseInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除课程管理")
    @ApiOperation("删除课程管理")
    @PreAuthorize("@el.check('courseInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        courseInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCoruseTeacher")
    @Log("课程查询：查询授课教师")
    @ApiOperation("课程查询：查询授课教师")
    public ResponseEntity<Object> getCoruseTeacher(@RequestParam("name") String name ,@RequestParam("class_teacher") String class_teacher ) {
        return new ResponseEntity<>(courseInfoService.getCoruseTeacher(name,class_teacher), HttpStatus.OK);
    }
}