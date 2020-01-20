package me.zhengjie.modules.course.repository;

import me.zhengjie.modules.course.domain.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author HUO
* @date 2020-01-16
*/
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Integer>, JpaSpecificationExecutor<CourseInfo> {
}