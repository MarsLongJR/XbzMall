package com.macro.mall.dao.couponDao;

import com.macro.mall.dto.coupon.XbzCouponParam;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName 优惠券管理自定义查询Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCouponDao {
    XbzCouponParam getItem(@Param("id") Long id);
}
