package com.macro.mall.dao.couponDao;

import com.macro.mall.model.XbzCouponProductCategoryRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 优惠券和商品分类关系自定义Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCouponProductCategoryRelationDao {
    int insertList(@Param("list") List<XbzCouponProductCategoryRelation> productCategoryRelationList);
}
