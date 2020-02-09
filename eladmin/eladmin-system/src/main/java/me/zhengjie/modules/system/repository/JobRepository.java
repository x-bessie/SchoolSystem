package me.zhengjie.modules.system.repository;

import me.zhengjie.modules.system.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author bessie
 */
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
}