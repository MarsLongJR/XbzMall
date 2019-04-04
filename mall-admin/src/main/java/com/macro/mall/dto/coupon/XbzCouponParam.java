package com.macro.mall.dto.coupon;

import com.macro.mall.model.XbzCoupon;
import com.macro.mall.model.XbzCouponProductCategoryRelation;
import com.macro.mall.model.XbzCouponProductRelation;

import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和绑定分类
 * Created by macro on 2018/8/28.
 */
public class XbzCouponParam extends XbzCoupon {
    //优惠券绑定的商品
    private List<XbzCouponProductRelation> productRelationList;
    //优惠券绑定的商品分类
    private List<XbzCouponProductCategoryRelation> productCategoryRelationList;

    public List<XbzCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<XbzCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<XbzCouponProductCategoryRelation> getProductCategoryRelationList() {
        return productCategoryRelationList;
    }

    public void setProductCategoryRelationList(List<XbzCouponProductCategoryRelation> productCategoryRelationList) {
        this.productCategoryRelationList = productCategoryRelationList;
    }
}
