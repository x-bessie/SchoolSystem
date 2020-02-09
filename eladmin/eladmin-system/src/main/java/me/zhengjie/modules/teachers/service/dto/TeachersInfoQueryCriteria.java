package me.zhengjie.modules.teachers.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author bessie
* @date 2020-01-13
*/
@Data
public class TeachersInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private Long userId;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String teacherid;
}