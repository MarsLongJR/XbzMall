package com.macro.mall.service.impl.MemeberServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.Member.XbzMemberDao;
import com.macro.mall.dto.member.XbzMemberDetail;
import com.macro.mall.dto.member.XbzMemberInfoParam;
import com.macro.mall.dto.member.XbzMemberQueryParam;
import com.macro.mall.mapper.XbzMemberMapper;
import com.macro.mall.model.XbzMember;
import com.macro.mall.model.XbzMemberExample;
import com.macro.mall.service.MemberService.XbzMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName 前台用户的serviceImpl
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzMemberServiceImpl implements XbzMemberService {

    @Resource
    private XbzMemberMapper memberMapper;
    @Resource
    private XbzMemberDao memberDao;
    /**
     * 查询用户
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<XbzMember> list(XbzMemberQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return memberDao.getList(queryParam);
    }

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return
     */
    @Override
    public XbzMemberDetail detail(Long id) {
        return memberDao.getDetail(id);
    }

    /**
     * 操作编辑用户
     * @param memberInfoParam
     * @return count
     */
    @Override
    public int updateMemberInfo(XbzMemberInfoParam memberInfoParam) {
        XbzMember member=new XbzMember();
        member.setId(memberInfoParam.getId());
        member.setMemberLevelId(memberInfoParam.getMemberLevelId());
        member.setGender(memberInfoParam.getGender());
        member.setBirthday(memberInfoParam.getBirthday());
        member.setStatus(memberInfoParam.getStatus());
        int count = memberMapper.updateByPrimaryKeySelective(member);
        return count;
    }
    /**
     * 批量修改用户状态
     * @param ids
     * @param status
     * @return
     */
    @Override
    public int changeStatus(List<Long> ids, Integer status) {
        XbzMember record = new XbzMember();
        record.setStatus(status);
        XbzMemberExample example = new XbzMemberExample();
        example.createCriteria().andIdIn(ids);
        return memberMapper.updateByExampleSelective(record,example);
    }


}
