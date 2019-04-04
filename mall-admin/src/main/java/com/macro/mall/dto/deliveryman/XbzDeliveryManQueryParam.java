package com.macro.mall.dto.deliveryman;

import lombok.Data;

/**
 * @ClassName 配送人查询参数
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Data
public class XbzDeliveryManQueryParam {
    /**
     * 配送员的姓名
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 配送员手机号
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 配送范围
     *
     * @mbggenerated
     */
    private String adressName;

    /**
     * 账号启用状态
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 机器id
     */
    private Long machineId;

    /**
     * 审核状态
     */
    private Integer verifyStatus;
}
