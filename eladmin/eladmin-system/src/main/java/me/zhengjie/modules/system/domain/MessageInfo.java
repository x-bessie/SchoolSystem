package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-08
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

    /** 创建时间 */
    @Column(name = "createTime")
    private Timestamp createtime;

    public void copy(MessageInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}