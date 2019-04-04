package com.macro.mall.service.impl.MemeberServiceImpl;

import com.macro.mall.mapper.XbzMemberLevelMapper;
import com.macro.mall.model.XbzMemberLevel;
import com.macro.mall.model.XbzMemberLevelExample;
import com.macro.mall.service.MemberService.XbzMemberLevelService;
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
public class XbzMemberLevelServiceImpl implements XbzMemberLevelService {
    @Resource
    private XbzMemberLevelMapper memberLevelMapper;
    @Override
    public List<XbzMemberLevel> list(Integer defaultStatus) {
        XbzMemberLevelExample example = new XbzMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
