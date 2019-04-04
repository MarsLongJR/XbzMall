package com.macro.mall.service.impl.OrderServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.XbzOrderReturnReasonMapper;
import com.macro.mall.model.XbzOrderReturnReason;
import com.macro.mall.model.XbzOrderReturnReasonExample;
import com.macro.mall.service.OrderService.XbzOrderReturnReasonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单原因管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
public class XbzOrderReturnReasonServiceImpl implements XbzOrderReturnReasonService {
    @Resource
    private XbzOrderReturnReasonMapper returnReasonMapper;
    @Override
    public int create(XbzOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return returnReasonMapper.insert(returnReason);
    }

    @Override
    public int update(Long id, XbzOrderReturnReason returnReason) {
        returnReason.setId(id);
        return returnReasonMapper.updateByPrimaryKey(returnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        XbzOrderReturnReasonExample example = new XbzOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return returnReasonMapper.deleteByExample(example);
    }

    @Override
    public List<XbzOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        XbzOrderReturnReasonExample example = new XbzOrderReturnReasonExample();
        example.setOrderByClause("sort desc");
        return returnReasonMapper.selectByExample(example);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        XbzOrderReturnReason record = new XbzOrderReturnReason();
        record.setStatus(status);
        XbzOrderReturnReasonExample example = new XbzOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return returnReasonMapper.updateByExampleSelective(record,example);
    }

    @Override
    public XbzOrderReturnReason getItem(Long id) {
        return returnReasonMapper.selectByPrimaryKey(id);
    }
}
