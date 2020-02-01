package me.zhengjie.modules.student.repository;

import me.zhengjie.modules.student.domain.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * 更新个人信息
     * @param idNum
     * @param address
     * @param email
     * @param hobby
     * @param parent_name
     * @param parent_num
     * @param tell_num
     * @param username
     */
    @Modifying
    @Query(value = "update student_info set  IDNum=?1, address=?2, email=?3,Hobby=?4,parent_name=?5,parent_num=?6, tell_num=?7 where username=?8 ", nativeQuery = true)
    void updateStudentInfos( String idNum, String address, String email, String hobby, String parent_name, String parent_num, String tell_num, String username);
}