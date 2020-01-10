package me.zhengjie.modules.studentCourse.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author HUO
* @date 2020-01-10
*/
@Data
public class StudentCourseInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private Integer studentId;
}