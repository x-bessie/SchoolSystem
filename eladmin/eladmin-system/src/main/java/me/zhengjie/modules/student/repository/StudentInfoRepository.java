package me.zhengjie.modules.student.repository;

import me.zhengjie.modules.student.domain.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author HUO
 * @date 2020-01-09
 */
@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>, JpaSpecificationExecutor<StudentInfo> {


    /**
     * 根据登录用户信息查询个人信息
     *
     * @param userName
     * @return
     */
    @Query(value = "select * from student_info  WHERE user_id in ( select id from user  where username = ?1 )", nativeQuery = true)
    List<Map<String, Object>> getStudentInfoByUserName(String userName);


}