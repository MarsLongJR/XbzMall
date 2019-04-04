package com.macro.mall.service.impl.OrderServiceImpl;

import com.macro.mall.mapper.XbzOrderSettingMapper;
import com.macro.mall.model.XbzOrderSetting;
import com.macro.mall.service.OrderService.XbzOrderSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单设置管理Service实现类
 * Created by macro on 2018/10/16.
 */
@Service
public class XbzOrderSettingServiceImpl implements XbzOrderSettingService {
    @Resource
    private XbzOrderSettingMapper orderSettingMapper;

    @Override
    public XbzOrderSetting getItem(Long id) {
        return orderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, XbzOrderSetting orderSetting) {
        orderSetting.setId(id);
        return orderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}
