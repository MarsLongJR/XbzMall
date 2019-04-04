package com.macro.mall.service.MemberService;

import com.macro.mall.dto.member.XbzMemberDetail;
import com.macro.mall.dto.member.XbzMemberInfoParam;
import com.macro.mall.dto.member.XbzMemberQueryParam;
import com.macro.mall.model.XbzMember;

import java.util.List;

/**
 * @ClassName 前台用户的service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberService {
    /**
     * 用户查询
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<XbzMember> list(XbzMemberQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 查询单个用户的信息
     * @param id 用户id
     * @return
     */
    XbzMemberDetail detail(Long id);

    /**
     * 操作编辑用户
     */
    int updateMemberInfo(XbzMemberInfoParam memberInfoParam);


    /**
     * 批量修改用户状态
     * @param ids
     * @param status
     * @return
     */
    int changeStatus(List<Long> ids, Integer status);
}
