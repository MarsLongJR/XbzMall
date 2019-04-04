package com.macro.mall.dao.Member;

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
public interface XbzMemberLoginLogDao {
    /**
     * 根据id查询用户登录的日志
     * @param queryParam
     * @return
     */
    List<XbzMember> getLogList(XbzMemberQueryLoginLogParam queryParam);

    /**
     * 根据用户手机号查询用户登陆日志
     */
    XbzMemberLoginLog getPhoneDetail(String phone);
}
