package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author bessie
 */
@Entity
@Data
@Table(name="message_info")
public class MessageInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 公告内容 */
    @Column(name = "content")
    private String content;

    /** 状态值：0 可见 1 不可见 */
    @Column(name = "status")
    private Integer status;

    /** 标题 */
    @Column(name = "title",nullable = false)
    @NotBlank
    private String title;

    /** 创建时间 */
    @Column(name = "create_time")
    private Timestamp createTime;

    /** 维护人 */
    @Column(name = "maintain")
    private String maintain;

    public void copy(MessageInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}