package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bessie
 */
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long>, JpaSpecificationExecutor<UserAvatar> {

}
