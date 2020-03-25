package me.zhengjie.modules.system.domain.vo;

import lombok.Data;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * 批量自定义新增
 */
@Data
public class UserBatchVo  {

    private String username;
    private String email;
    private String phone;
    private String nickName;
    private String sex;
    private String profession;

//    @ManyToMany
//    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
//    private Set<Role> roles;
//
//    @OneToOne
//    @JoinColumn(name = "job_id")
//    private Job job;
//
//    @OneToOne
//    @JoinColumn(name = "dept_id")
//    private Dept dept;

}
