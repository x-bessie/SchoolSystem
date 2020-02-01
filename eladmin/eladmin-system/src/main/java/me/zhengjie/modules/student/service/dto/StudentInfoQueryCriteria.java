package me.zhengjie.modules.student.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bessie
* @date 2020-01-09
*/
@Data
public class StudentInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private Long userId;


    @Query(propName="id",joinName = "user")
    private Long studentMessage;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}