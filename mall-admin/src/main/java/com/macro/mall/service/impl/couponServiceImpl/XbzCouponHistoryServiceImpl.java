package com.macro.mall.service.impl.couponServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.XbzCouponHistoryMapper;
import com.macro.mall.model.XbzCouponHistory;
import com.macro.mall.model.XbzCouponHistoryExample;
import com.macro.mall.service.couponService.XbzCouponHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzCouponHistoryServiceImpl implements XbzCouponHistoryService {
    @Resource
    private XbzCouponHistoryMapper historyMapper;
    @Override
    public List<XbzCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        XbzCouponHistoryExample example = new XbzCouponHistoryExample();
        XbzCouponHistoryExample.Criteria criteria = example.createCriteria();
        if(couponId!=null){
            criteria.andCouponIdEqualTo(couponId);
        }
        if(useStatus!=null){
            criteria.andUseStatusEqualTo(useStatus);
        }
        if(!StringUtils.isEmpty(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }
        return historyMapper.selectByExample(example);
    }
}
