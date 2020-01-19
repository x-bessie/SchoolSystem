package me.zhengjie.modules.student.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-09
*/
@Entity
@Data
@Table(name="student_info")
public class StudentInfo implements Serializable {

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 性别 */
    @Column(name = "sex")
    private String sex;

    /** 年龄 */
    @Column(name = "age")
    private Integer age;

    /** 班级 */
    @Column(name = "cid")
    private Integer cid;

    /** 身份证 */
    @Column(name = "IDNum")
    private String idnum;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 兴趣爱好 */
    @Column(name = "Hobby")
    private String hobby;

    /** 联系方式 */
    @Column(name = "TellNum")
    private Integer tellnum;

    /** 父母名称 */
    @Column(name = "ParentName")
    private String parentname;

    /** 父母联系方式 */
    @Column(name = "ParentNum")
    private String parentnum;

    /** 辅导员 */
    @Column(name = "TeacherName")
    private String teachername;

    /** 导师 */
    @Column(name = "GuiderTeacher")
    private String guiderteacher;

    @Column(name = "username",nullable = false)
    @NotBlank
    private String username;


    @Column(name = "user_id",nullable = false)
    @NotNull
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public void copy(StudentInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}