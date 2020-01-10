package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author HUO
* @date 2020-01-08
*/
public interface MessageInfoRepository extends JpaRepository<MessageInfo, Integer>, JpaSpecificationExecutor<MessageInfo> {
}