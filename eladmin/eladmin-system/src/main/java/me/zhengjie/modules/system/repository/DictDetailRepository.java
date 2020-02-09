package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.DictDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bessie
 */
public interface DictDetailRepository extends JpaRepository<DictDetail, Long>, JpaSpecificationExecutor<DictDetail> {
}