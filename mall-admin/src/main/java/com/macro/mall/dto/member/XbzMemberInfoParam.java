package com.macro.mall.dto.member;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Data
public class XbzMemberInfoParam {
    private Long id;
    /**
     * 手机号码
     */
    private String phone;

    /**
     * 会员等级
     */
    private Long memberLevelId;

    /**
     * 性别：0->未知；1->男；2->女
     *
     * @mbggenerated
     */
    private Integer gender;

    /**
     * 生日
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * 所在的城市
     *
     * @mbggenerated
     */
    private String city;

    private String oldPassword;

    private String newPassword;

    /**
     * 帐号启用状态:0->禁用；1->启用
     *
     * @mbggenerated
     */
    private Integer status;

}
