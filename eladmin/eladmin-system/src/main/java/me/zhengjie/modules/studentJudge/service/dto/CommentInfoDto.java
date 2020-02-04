package me.zhengjie.modules.studentJudge.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;

/**
* @author bessie
* @date 2020-01-30
*/
@Data
public class CommentInfoDto implements Serializable {

    private Integer id;

    /** 学号 */
    private String username;

    /** 课程id */
    private Integer classId;

    /** 教师id */
    private Integer teacherId;

    /** 教师名称 */
    private String teacherName;

    /** 评价 */
    private String memo;

    /** 创建时间 */
    private Date createtime;

    /** 状态 */
    private Integer status;
}