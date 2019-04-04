package com.macro.mall.dto.member;

import com.macro.mall.model.XbzMember;
import com.macro.mall.model.XbzMemberLoginLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzMemberLoginLogResult extends XbzMember {
    @Getter
    @Setter
    private XbzMemberLoginLog memberLoginLogs;
}
