package me.zhengjie.modules.course.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-09
*/
@Data
public class CourseInfoDto implements Serializable {

    private Integer id;

    private String name;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer courseTime;

    private Integer courseNum;

    private String courseTeacher;

    private Integer courseTeacherid;

    private String coursecode;
}