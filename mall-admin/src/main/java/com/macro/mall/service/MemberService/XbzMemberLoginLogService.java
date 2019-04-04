package com.macro.mall.service.MemberService;

import com.macro.mall.dto.member.XbzMemberQueryLoginLogParam;
import com.macro.mall.model.XbzMember;
import com.macro.mall.model.XbzMemberLoginLog;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberLoginLogService {
    /**
     * 查询所有用户登录日志
     */
    List<XbzMember> loginLoglist(XbzMemberQueryLoginLogParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 根据日志id查询用户登录日志
     * @return
     */
    XbzMemberLoginLog getDetail(Long id);

    /**
     * 根据用户手机号查询用户登陆日志
     */
    XbzMemberLoginLog getPhoneDetail(String phone);
}
