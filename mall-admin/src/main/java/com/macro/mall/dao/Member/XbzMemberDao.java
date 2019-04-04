package com.macro.mall.dao.Member;

import com.macro.mall.dto.member.XbzMemberDetail;
import com.macro.mall.dto.member.XbzMemberQueryParam;
import com.macro.mall.model.XbzMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 前台用户的dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberDao {
    /**
     * 查询所有用户
     * @param queryParam
     * @return
     */
    List<XbzMember> getList(@Param("queryParam")XbzMemberQueryParam queryParam);

    /**
     * 根据用户id查询用户详情
     * @param id 用户id
     * @return
     */
    XbzMemberDetail getDetail(Long id);

}
