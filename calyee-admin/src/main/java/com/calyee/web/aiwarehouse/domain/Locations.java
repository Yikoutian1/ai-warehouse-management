package com.calyee.web.aiwarehouse.domain;

import com.calyee.common.annotation.Excel;
import com.calyee.common.core.domain.BaseEntity;
import lombok.*;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息对象 awm_locations
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Locations extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 库位唯一标识符
     */
    private Long locationId;

    /**
     * 库位所在的仓库区域（eg：a5）
     */
    @Excel(name = "库位所在的仓库区域（eg：a5）")
    private String area;

    /**
     * 库位所在的巷道，指的是仓库内用于通行和定位货架的通道（eg：1区域）
     */
    @Excel(name = "库位所在的巷道，指的是仓库内用于通行和定位货架的通道（eg：1区域）")
    private String aisle;

    /**
     * 库位所在的排（eg：3 第三排）
     */
    @Excel(name = "库位所在的排（eg：3 第三排）")
    private String bay;

    /**
     * 库位所在的层（eg：2 第二层）
     */
    @Excel(name = "库位所在的层（eg：2 第二层）")
    private String level;

    /**
     * 库位的具体位置（比如某个货架的某个槽位：中左/中右/右上...）
     */
    @Excel(name = "库位的具体位置（比如某个货架的某个槽位：中左/中右/右上...）")
    private String position;

    /**
     * 记录创建用户
     */
    @Excel(name = "记录创建用户")
    private String createUser;

    /**
     * 记录更新用户
     */
    @Excel(name = "记录更新用户")
    private String updateUser;

    /**
     * 删除标记，0表示未删除，1表示已删除
     */
    @Excel(name = "删除标记，0表示未删除，1表示已删除")
    private Integer deleteFlag;
}
