package me.zhengjie.modules.system.service.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.Job;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.UserAvatar;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;
import me.zhengjie.modules.system.service.dto.JobSmallDto;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;
import me.zhengjie.modules.system.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-10T11:57:21+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private JobMapper jobMapper;

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setUsername( dto.getUsername() );
        user.setNickName( dto.getNickName() );
        user.setSex( dto.getSex() );
        user.setEmail( dto.getEmail() );
        user.setPhone( dto.getPhone() );
        user.setEnabled( dto.getEnabled() );
        user.setPassword( dto.getPassword() );
        user.setCreateTime( dto.getCreateTime() );
        user.setLastPasswordResetTime( dto.getLastPasswordResetTime() );
        user.setRoles( roleSmallDtoSetToRoleSet( dto.getRoles() ) );
        user.setJob( jobSmallDtoToJob( dto.getJob() ) );
        user.setDept( deptSmallDtoToDept( dto.getDept() ) );

        return user;
    }

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        String realName = userUserAvatarRealName( user );
        if ( realName != null ) {
            userDto.setAvatar( realName );
        }
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setNickName( user.getNickName() );
        userDto.setSex( user.getSex() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhone( user.getPhone() );
        userDto.setEnabled( user.getEnabled() );
        userDto.setPassword( user.getPassword() );
        userDto.setLastPasswordResetTime( user.getLastPasswordResetTime() );
        userDto.setRoles( roleSetToRoleSmallDtoSet( user.getRoles() ) );
        userDto.setJob( jobToJobSmallDto( user.getJob() ) );
        userDto.setDept( deptToDeptSmallDto( user.getDept() ) );
        userDto.setCreateTime( user.getCreateTime() );

        return userDto;
    }

    protected Role roleSmallDtoToRole(RoleSmallDto roleSmallDto) {
        if ( roleSmallDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleSmallDto.getId() );
        role.setName( roleSmallDto.getName() );
        role.setDataScope( roleSmallDto.getDataScope() );
        role.setLevel( roleSmallDto.getLevel() );

        return role;
    }

    protected Set<Role> roleSmallDtoSetToRoleSet(Set<RoleSmallDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleSmallDto roleSmallDto : set ) {
            set1.add( roleSmallDtoToRole( roleSmallDto ) );
        }

        return set1;
    }

    protected Job jobSmallDtoToJob(JobSmallDto jobSmallDto) {
        if ( jobSmallDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( jobSmallDto.getId() );
        job.setName( jobSmallDto.getName() );

        return job;
    }

    protected Dept deptSmallDtoToDept(DeptSmallDto deptSmallDto) {
        if ( deptSmallDto == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setId( deptSmallDto.getId() );
        dept.setName( deptSmallDto.getName() );

        return dept;
    }

    private String userUserAvatarRealName(User user) {
        if ( user == null ) {
            return null;
        }
        UserAvatar userAvatar = user.getUserAvatar();
        if ( userAvatar == null ) {
            return null;
        }
        String realName = userAvatar.getRealName();
        if ( realName == null ) {
            return null;
        }
        return realName;
    }

    protected RoleSmallDto roleToRoleSmallDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleSmallDto roleSmallDto = new RoleSmallDto();

        roleSmallDto.setId( role.getId() );
        roleSmallDto.setName( role.getName() );
        roleSmallDto.setLevel( role.getLevel() );
        roleSmallDto.setDataScope( role.getDataScope() );

        return roleSmallDto;
    }

    protected Set<RoleSmallDto> roleSetToRoleSmallDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleSmallDto> set1 = new HashSet<RoleSmallDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleSmallDto( role ) );
        }

        return set1;
    }

    protected JobSmallDto jobToJobSmallDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobSmallDto jobSmallDto = new JobSmallDto();

        jobSmallDto.setId( job.getId() );
        jobSmallDto.setName( job.getName() );

        return jobSmallDto;
    }

    protected DeptSmallDto deptToDeptSmallDto(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptSmallDto deptSmallDto = new DeptSmallDto();

        deptSmallDto.setId( dept.getId() );
        deptSmallDto.setName( dept.getName() );

        return deptSmallDto;
    }
}
