package com.macro.mall.dto.member;

import com.macro.mall.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName 用户详细记录
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzMemberDetail extends XbzMember {
    @Getter
    @Setter
    private List<XbzMemberLevel> memberLevels;
    @Getter
    @Setter
    private List<XbzMemberReceiveAddress> receiveAddresses;
    @Getter
    @Setter
    private List<XbzMemberLoginLog> memberLoginLogs;
    @Getter
    @Setter
    private List<XbzMemberStatisticsInfo> statisticsInfos;
}
