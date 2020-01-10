package me.zhengjie.modules.teacher.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-09
*/
@Data
public class TeacherInfoDto implements Serializable {

    /** id */
    private Long id;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 性别 */
    private String sex;

    /** 身份证 */
    private String idnum;

    /** 地址 */
    private String address;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /** 创建时间 */
    private Timestamp createTime;
}