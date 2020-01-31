package me.zhengjie.modules.teachers.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-13
*/
@Data
public class TeachersInfoDto implements Serializable {

    private Integer id;

    /** 教师工号 */
    private Long userId;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 性别 */
    private String sex;

    /** 地址 */
    private String address;

    /** 电话 */
    private String phone;

    /** 身份证 */
    private String idnum;

    /** 工号 */
    private String teacherid;
}