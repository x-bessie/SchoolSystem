package me.zhengjie.modules.grade.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bessie
* @date 2020-02-03
*/
@Data
public class GradeInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String classCode;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String courseName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}