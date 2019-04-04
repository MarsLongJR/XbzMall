package com.macro.mall.service.couponService;

import com.macro.mall.dto.coupon.XbzCouponParam;
import com.macro.mall.model.XbzCoupon;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName 优惠券管理Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCouponService {
    /**
     * 添加优惠券
     */
    @Transactional
    int create(XbzCouponParam couponParam);

    /**
     * 根据优惠券id删除优惠券
     */
    @Transactional
    int delete(Long id);

    /**
     * 根据优惠券id更新优惠券信息
     */
    @Transactional
    int update(Long id, XbzCouponParam couponParam);

    /**
     * 分页获取优惠券列表
     */
    List<XbzCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 获取优惠券详情
     * @param id 优惠券表id
     */
    XbzCouponParam getItem(Long id);
}
