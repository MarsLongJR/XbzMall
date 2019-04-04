package com.macro.mall.dao.couponDao;

import com.macro.mall.model.XbzCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 优惠券和产品关系自定义Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCouponProductRelationDao {
    int insertList(@Param("list") List<XbzCouponProductRelation> productRelationList);
}
