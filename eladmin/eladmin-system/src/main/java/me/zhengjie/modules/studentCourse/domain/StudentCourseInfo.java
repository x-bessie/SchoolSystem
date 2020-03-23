package me.zhengjie.modules.studentCourse.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author bessie
* @date 2020-01-10
*/
@Entity
@Data
@Table(name="student_course_info")
public class StudentCourseInfo implements Serializable {

    @Column(name = "student_id",nullable = false)
    private Integer studentId;

    @Column(name = "student_code",nullable = false)
    @NotNull
    private String studentCode;

    @Column(name = "student_name",nullable = false)
    @NotNull
    private String studentName;

    @Column(name = "course_code",nullable = false)
    @NotNull
    private String courseCode;

    @Column(name="course_name",nullable = false)
    @NotNull
    private String courseName;

    @Column(name = "pdate")
    private Timestamp pdate;

    @Column(name = "tearm")
    private Integer tearm;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public void copy(StudentCourseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}