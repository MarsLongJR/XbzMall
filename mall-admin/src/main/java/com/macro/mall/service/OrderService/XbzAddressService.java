package com.macro.mall.service.OrderService;

import com.macro.mall.model.XbzCompanyAddress;

import java.util.List;

/**
 * @ClassName 收货地址管Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzAddressService {
    /**
     * 获取全部收货地址
     */
    List<XbzCompanyAddress> list();
}
