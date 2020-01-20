package me.zhengjie.modules.course.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-16
*/
@Data
public class CourseInfoDto implements Serializable {

    /** id */
    private Integer id;

    /** 课程名称 */
    private String name;

    /** 创建时间 */
    private Timestamp createTime;

    /** 课程时长 */
    private Integer classtime;

    /** 周数 */
    private Integer classNum;

    /** 任课教师 */
    private String classTeacher;

    /** 教师Id */
    private Integer teacherId;

    /** 课程代码 */
    private String classCode;

    /** 学期 */
    private Integer tearm;

    /** 学年 */
    private String schoolYear;

    /** 状态值：0 禁用,1激活 */
    private Integer status;
}