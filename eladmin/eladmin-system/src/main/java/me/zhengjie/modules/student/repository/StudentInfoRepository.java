package me.zhengjie.modules.student.repository;

import me.zhengjie.modules.student.domain.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author HUO
* @date 2020-01-09
*/
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>, JpaSpecificationExecutor<StudentInfo> {
}