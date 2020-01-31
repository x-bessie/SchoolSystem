package me.zhengjie.modules.teachers.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-13
*/
@Entity
@Data
@Table(name="teachers_info")
public class TeachersInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 教师工号 */
    @Column(name = "user_id",nullable = false)
    @NotNull
    private Long userId;

    /** 姓名 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 年龄 */
    @Column(name = "age")
    private Integer age;

    /** 性别 */
    @Column(name = "sex")
    private String sex;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 电话 */
    @Column(name = "phone")
    private String phone;

    /** 身份证 */
    @Column(name = "Idnum")
    private String idnum;

    /** 工号 */
    @Column(name = "teacherid")
    private String teacherid;

    public void copy(TeachersInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}