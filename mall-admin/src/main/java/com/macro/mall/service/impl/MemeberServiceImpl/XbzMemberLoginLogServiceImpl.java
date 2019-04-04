package com.macro.mall.service.impl.MemeberServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.Member.XbzMemberLoginLogDao;
import com.macro.mall.dto.member.XbzMemberQueryLoginLogParam;
import com.macro.mall.mapper.XbzMemberLoginLogMapper;
import com.macro.mall.model.XbzMember;
import com.macro.mall.model.XbzMemberLoginLog;
import com.macro.mall.service.MemberService.XbzMemberLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzMemberLoginLogServiceImpl implements XbzMemberLoginLogService {
    @Resource
    private XbzMemberLoginLogDao memberLoginLogDao;

    @Resource
    private XbzMemberLoginLogMapper memberLoginLogMapper;
    /**
     * 查询所有用户登录日志
     * @param queryParam 查询参数
     * @param pageSize 总页数
     * @param pageNum 当前页数
     * @return memberDao.getLogList(queryParam);
     */
    @Override
    public List<XbzMember> loginLoglist(XbzMemberQueryLoginLogParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return memberLoginLogDao.getLogList(queryParam);
    }

    @Override
    public XbzMemberLoginLog getDetail(Long id) {
        return memberLoginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public XbzMemberLoginLog getPhoneDetail(String phone) {
        return memberLoginLogDao.getPhoneDetail(phone);
    }


}
