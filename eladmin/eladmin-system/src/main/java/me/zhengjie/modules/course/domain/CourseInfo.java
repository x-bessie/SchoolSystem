package me.zhengjie.modules.course.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author bessie
* @date 2020-01-16
*/
@Entity
@Data
@Table(name="course_info")
public class CourseInfo implements Serializable {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 课程名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 创建时间 */
    @Column(name = "create_time",nullable = false)
    @NotNull
    private Timestamp createTime;

    /** 课程时长 */
    @Column(name = "classtime",nullable = false)
    @NotNull
    private Integer classtime;

    /** 周数 */
    @Column(name = "class_num",nullable = false)
    @NotNull
    private Integer classNum;

    /** 任课教师 */
    @Column(name = "class_teacher",nullable = false)
    @NotBlank
    private String classTeacher;

    /** 教师Id */
    @Column(name = "teacher_id",nullable = false)
    @NotNull
    private Integer teacherId;

    /** 课程代码 */
    @Column(name = "class_code",nullable = false)
    @NotBlank
    private String classCode;

    /** 学期 */
    @Column(name = "tearm",nullable = false)
    @NotNull
    private Integer tearm;

    /** 学年 */
    @Column(name = "school_year",nullable = false)
    @NotBlank
    private String schoolYear;

    /** 状态值：0 禁用,1激活 */
    @Column(name = "status")
    private Integer status;

    public void copy(CourseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}