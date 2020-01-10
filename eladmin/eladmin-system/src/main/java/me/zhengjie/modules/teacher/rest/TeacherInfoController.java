package me.zhengjie.modules.teacher.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.teacher.domain.TeacherInfo;
import me.zhengjie.modules.teacher.service.TeacherInfoService;
import me.zhengjie.modules.teacher.service.dto.TeacherInfoQueryCriteria;
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
@Api(tags = "老师：管理管理")
@RestController
@RequestMapping("/api/teacherInfo")
public class TeacherInfoController {

    private final TeacherInfoService teacherInfoService;

    public TeacherInfoController(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('teacherInfo:list')")
    public void download(HttpServletResponse response, TeacherInfoQueryCriteria criteria) throws IOException {
        teacherInfoService.download(teacherInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询老师：管理")
    @ApiOperation("查询老师：管理")
    @PreAuthorize("@el.check('teacherInfo:list')")
    public ResponseEntity<Object> getTeacherInfos(TeacherInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(teacherInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增老师：管理")
    @ApiOperation("新增老师：管理")
    @PreAuthorize("@el.check('teacherInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TeacherInfo resources){
        return new ResponseEntity<>(teacherInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改老师：管理")
    @ApiOperation("修改老师：管理")
    @PreAuthorize("@el.check('teacherInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TeacherInfo resources){
        teacherInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除老师：管理")
    @ApiOperation("删除老师：管理")
    @PreAuthorize("@el.check('teacherInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        teacherInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}