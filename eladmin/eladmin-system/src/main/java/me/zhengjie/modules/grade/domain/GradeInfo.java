package me.zhengjie.modules.grade.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author bessie
* @date 2020-02-03
*/
@Entity
@Data
@Table(name="grade_info")
public class GradeInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 课程id */
    @Column(name = "class_id",nullable = false)
    @NotNull
    private Integer classId;

    /** 课程代码 */
    @Column(name = "class_code",nullable = false)
    @NotBlank
    private String classCode;

    /** 教师id */
    @Column(name = "teacher_id")
    private Integer teacherId;

    /** 教师名称 */
    @Column(name = "teacher_name")
    private String teacherName;

    /** 分数 */
    @Column(name = "grade",nullable = false)
    @NotNull
    private Float grade;

    /** 课程名称 */
    @Column(name = "course_name")
    private String courseName;

    /** 时间 */
    @Column(name = "pdate")
    private Timestamp pdate;

    /** 状态 */
    @Column(name = "status")
    private Integer status;

    /** 学号 */
    @Column(name = "username",nullable = false)
    @NotBlank
    private String username;

    /** 姓名 */
    @Column(name = "student_name",nullable = false)
    @NotBlank
    private String studentName;

    public void copy(GradeInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}