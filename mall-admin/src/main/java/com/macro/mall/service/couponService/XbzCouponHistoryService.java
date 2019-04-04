package com.macro.mall.service.couponService;

import com.macro.mall.model.XbzCouponHistory;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCouponHistoryService {
    List<XbzCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
