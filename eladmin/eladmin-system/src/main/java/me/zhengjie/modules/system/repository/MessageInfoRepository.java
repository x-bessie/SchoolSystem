package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.MessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author HUO
 * @date 2020-01-14
 */
@Repository
public interface MessageInfoRepository extends JpaRepository<MessageInfo, Integer>, JpaSpecificationExecutor<MessageInfo> {

    @Query(value = "select title,content,status,create_time from message_info where status=1  ORDER BY  create_time  DESC limit 5", nativeQuery = true)
    List<Map<String, Object>> queryMessageInfo();
}