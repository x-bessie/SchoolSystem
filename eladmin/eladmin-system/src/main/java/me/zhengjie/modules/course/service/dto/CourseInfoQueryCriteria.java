package me.zhengjie.modules.course.service.dto;

import lombok.Data;

import java.util.List;

import me.zhengjie.annotation.Query;

/**
 * @author bessie
 * @date 2020-01-16
 */
@Data
public class CourseInfoQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String classCode;
    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String tearm;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String schoolYear;

    /** 精确 */
    @Query
    private Integer status;

}