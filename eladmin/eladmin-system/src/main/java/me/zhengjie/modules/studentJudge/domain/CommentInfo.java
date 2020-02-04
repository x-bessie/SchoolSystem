package me.zhengjie.modules.studentJudge.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Date;

/**
* @author bessie
* @date 2020-01-30
*/
@Entity
@Data
@Table(name="comment_info")
public class CommentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 学号 */
    @Column(name = "username",nullable = false)
    @NotBlank
    private String username;

    /** 课程id */
    @Column(name = "class_id",nullable = false)
    @NotNull
    private Integer classId;

    /** 教师id */
    @Column(name = "teacher_id",nullable = false)
    @NotNull
    private Integer teacherId;

    /** 教师名称 */
    @Column(name = "teacher_name",nullable = false)
    @NotBlank
    private String teacherName;

    /** 评价 */
    @Column(name = "memo")
    private String memo;

    /** 创建时间 */
    @Column(name = "createtime")
    private Date createtime;

    /** 状态 */
    @Column(name = "status")
    private Integer status;

    public void copy(CommentInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}