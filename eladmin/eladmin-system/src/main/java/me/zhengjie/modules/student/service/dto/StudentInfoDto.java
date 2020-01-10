package me.zhengjie.modules.student.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-09
*/
@Data
public class StudentInfoDto implements Serializable {

    /** 姓名 */
    private String name;

    /** 性别 */
    private String sex;

    /** 年龄 */
    private Integer age;

    /** 班级 */
    private Integer cid;

    /** 身份证 */
    private String idnum;

    /** 地址 */
    private String address;

    /** 邮箱 */
    private String email;

    /** 兴趣爱好 */
    private String hobby;

    /** 联系方式 */
    private Integer tellnum;

    /** 父母名称 */
    private String parentname;

    /** 父母联系方式 */
    private String parentnum;

    /** 辅导员 */
    private String teachername;

    /** 导师 */
    private String guiderteacher;

    private Long userId;

    private Integer id;
}