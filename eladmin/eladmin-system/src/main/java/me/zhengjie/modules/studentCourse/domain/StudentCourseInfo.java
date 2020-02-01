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
    @NotNull
    private Integer studentId;

    @Column(name = "course_id",nullable = false)
    @NotNull
    private Integer courseId;

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