package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author bessie
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return /
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return /
     */
    User findByEmail(String email);

    /**
     * 修改密码
     *
     * @param username              用户名
     * @param pass                  密码
     * @param lastPasswordResetTime /
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where username = ?1", nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改邮箱
     *
     * @param username 用户名
     * @param email    邮箱
     */
    @Modifying
    @Query(value = "update user set email = ?2 where username = ?1", nativeQuery = true)
    void updateEmail(String username, String email);


    @Query(value = "select * from user  where userName = ?1", nativeQuery = true)
    List<Map<String, Object>> getUserIdByName(String userName);

    @Modifying
    @Query(value = "insert into user (username,email,enabled,password,phone,dept_id,job_id,nick_name,sex,create_time) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
    void saveUsers(String username, String email, Long enabled, String password, String phone, Long dept, Long job, String nickname, String sex, Date createTime);
}
