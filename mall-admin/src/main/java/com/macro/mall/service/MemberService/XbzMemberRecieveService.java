package com.macro.mall.service.MemberService;

import com.macro.mall.dto.member.XbzMemberRecieveQueryParam;
import com.macro.mall.model.XbzMemberReceiveAddress;

import java.util.List;

/**
 * @ClassName 收货人地址管理Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberRecieveService {
    /**
     * 查询所有收货人的所有的地址信息
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<XbzMemberReceiveAddress> list(XbzMemberRecieveQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 根据id获得收货地址
     * @param id
     * @return
     */
    XbzMemberReceiveAddress getList(Long id);
}
