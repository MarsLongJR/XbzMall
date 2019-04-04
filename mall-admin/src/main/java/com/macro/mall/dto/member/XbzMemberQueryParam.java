package com.macro.mall.dto.member;

import lombok.Data;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Data
public class XbzMemberQueryParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 帐号启用状态:0->禁用；1->启用
     */
    private Integer status;
    /**
     * 手机号码
     */
    private String phone;
    /**
     *用户注册时间
     */
    private String createTime;
    /**
     * 用户来源
     */
    private Integer sourceType;
}
