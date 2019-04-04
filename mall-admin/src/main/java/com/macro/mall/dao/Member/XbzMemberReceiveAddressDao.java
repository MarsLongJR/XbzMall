package com.macro.mall.dao.Member;

import com.macro.mall.dto.member.XbzMemberRecieveDetail;
import com.macro.mall.dto.member.XbzMemberRecieveQueryParam;
import com.macro.mall.model.XbzMemberReceiveAddress;

import java.util.List;

/**
 * @ClassName 收货人地址管理Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberReceiveAddressDao {
    
    List<XbzMemberReceiveAddress> list(XbzMemberRecieveQueryParam queryParam);

    XbzMemberRecieveDetail getDetail(Long id);
}
