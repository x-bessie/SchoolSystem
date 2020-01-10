package me.zhengjie.modules.teacher.domain;

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
@Table(name="teacher_info")
public class TeacherInfo implements Serializable {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 年龄 */
    @Column(name = "age")
    private Integer age;

    /** 性别 */
    @Column(name = "sex",nullable = false)
    @NotBlank
    private String sex;

    /** 身份证 */
    @Column(name = "Idnum")
    private String idnum;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 电话 */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private String phone;

    /** 创建时间 */
    @Column(name = "create_time")
    private Timestamp createTime;

    public void copy(TeacherInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}