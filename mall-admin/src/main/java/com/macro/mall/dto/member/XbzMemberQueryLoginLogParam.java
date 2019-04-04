package com.macro.mall.dto.member;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName 查询用户登录日志的实体类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Data
public class XbzMemberQueryLoginLogParam {
    private Long id;

    private Long memberId;

    private Date createTime;

    private String ip;

    private String city;

    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     *
     * @mbggenerated
     */
    private Integer loginType;

    private String province;

}
