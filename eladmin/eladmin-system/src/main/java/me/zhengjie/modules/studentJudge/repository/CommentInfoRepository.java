package me.zhengjie.modules.studentJudge.repository;

import me.zhengjie.modules.studentJudge.domain.CommentInfo;
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
 * @date 2020-02-02
 */
@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfo, Integer>, JpaSpecificationExecutor<CommentInfo> {
    @Modifying
    @Query(value = "insert ignore into comment_info (username,class_id,teacher_name,teacher_id,memo,createtime) values (?1,?2,?3,?4,?5,?6);", nativeQuery = true)
    void InsertCommentByStudent(String username, Integer class_id, String teacher_name, Integer teacher_id, String memo, Date createtime);
}