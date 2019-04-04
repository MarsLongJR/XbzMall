package com.macro.mall.service.MemberService;

import com.macro.mall.model.XbzMemberLevel;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus 是否为默认会员
     */
    List<XbzMemberLevel> list(Integer defaultStatus);
}
