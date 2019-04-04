package com.macro.mall.service.impl.MemeberServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.Member.XbzMemberReceiveAddressDao;
import com.macro.mall.dto.member.XbzMemberRecieveQueryParam;
import com.macro.mall.mapper.XbzMemberReceiveAddressMapper;
import com.macro.mall.model.XbzMemberReceiveAddress;
import com.macro.mall.service.MemberService.XbzMemberRecieveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName 收货人地址管理ServiceImpl
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzMemberRecieveServiceImpl implements XbzMemberRecieveService {
    @Resource
    private XbzMemberReceiveAddressMapper memberReceiveAddressMapper;

    @Resource
    private XbzMemberReceiveAddressDao memberReceiveAddressDao;

    /**
     * 查询所有收货人的所有的地址信息
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<XbzMemberReceiveAddress> list(XbzMemberRecieveQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return memberReceiveAddressDao.list(queryParam);
    }

    @Override
    public XbzMemberReceiveAddress getList(Long id) {
        return memberReceiveAddressMapper.selectByPrimaryKey(id);
    }


}
