package me.zhengjie.modules.grade.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author bessie
* @date 2020-02-03
*/
@Data
public class GradeInfoDto implements Serializable {

    private Integer id;

    /** 课程id */
    private Integer classId;

    /** 课程代码 */
    private String classCode;

    /** 教师工号*/
    private String teacherId;

    /** 教师名称 */
    private String teacherName;

    /** 分数 */
    private Float grade;

    /** 课程名称 */
    private String courseName;

    /** 时间 */
    private Timestamp pdate;

    /** 状态 */
    private Integer status;

    /** 学号 */
    private String username;

    /** 姓名 */
    private String studentName;
}