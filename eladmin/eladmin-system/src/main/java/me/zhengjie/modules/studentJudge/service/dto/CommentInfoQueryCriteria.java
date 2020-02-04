package me.zhengjie.modules.studentJudge.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bessie
* @date 2020-01-30
*/
@Data
public class CommentInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String teacherName;
}