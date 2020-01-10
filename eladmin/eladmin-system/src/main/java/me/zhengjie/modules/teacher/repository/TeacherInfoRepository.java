package me.zhengjie.modules.teacher.repository;

import me.zhengjie.modules.teacher.domain.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author HUO
* @date 2020-01-09
*/
public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Long>, JpaSpecificationExecutor<TeacherInfo> {
}