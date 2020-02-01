package me.zhengjie.modules.studentCourse.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author bessie
* @date 2020-01-10
*/
@Data
public class StudentCourseInfoDto implements Serializable {

    private Integer studentId;

    private Integer courseId;

    private Timestamp pdate;

    private Integer tearm;

    private Integer id;
}