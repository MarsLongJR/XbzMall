package com.macro.mall.dto.member;

import com.macro.mall.model.XbzMember;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzMemberRecieveDetail extends XbzMember {
    @Getter
    @Setter
    List<XbzMember> memberLists;
}
