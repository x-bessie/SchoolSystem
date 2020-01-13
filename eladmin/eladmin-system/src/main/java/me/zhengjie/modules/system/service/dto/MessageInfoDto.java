package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author HUO
* @date 2020-01-13
*/
@Data
public class MessageInfoDto implements Serializable {

    private Integer id;

    /** 公告内容 */
    private String content;

    /** 状态值：0 可见 1 不可见 */
    private Integer status;

    /** 标题 */
    private String title;

    /** 创建时间 */
    private Timestamp createTime;

    /** 维护人 */
    private String maintain;
}