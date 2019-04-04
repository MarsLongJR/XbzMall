package com.macro.mall.service.OrderService;

import com.macro.mall.model.XbzOrderSetting;

/**
 * 订单设置Service
 * Created by macro on 2018/10/16.
 */
public interface XbzOrderSettingService {
    /**
     * 获取指定订单设置
     */
    XbzOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id,XbzOrderSetting orderSetting);
}
