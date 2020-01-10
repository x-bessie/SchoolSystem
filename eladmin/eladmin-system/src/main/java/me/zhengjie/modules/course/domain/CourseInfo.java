package me.zhengjie.modules.course.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-09
*/
@Entity
@Data
@Table(name="course_info")
public class CourseInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "course_time",nullable = false)
    @NotNull
    private Integer courseTime;

    @Column(name = "course_num",nullable = false)
    @NotNull
    private Integer courseNum;

    @Column(name = "course_teacher",nullable = false)
    @NotBlank
    private String courseTeacher;

    @Column(name = "course_teacherID",nullable = false)
    @NotNull
    private Integer courseTeacherid;

    @Column(name = "courseCode")
    private String coursecode;

    public void copy(CourseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}