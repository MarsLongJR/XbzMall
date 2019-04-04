package com.macro.mall.dto.product;

import com.macro.mall.model.*;

import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * Created by macro on 2018/4/26.
 */
public class XbzProductParam extends XbzProduct {
    //@ApiModelProperty("商品阶梯价格设置")
    private List<XbzProductLadder> productLadderList;
    //@ApiModelProperty("商品满减价格设置")
    private List<XbzProductFullReduction> productFullReductionList;
    //@ApiModelProperty("商品会员价格设置")
    private List<XbzMemberPrice> memberPriceList;
    //@ApiModelProperty("商品的sku库存信息")
    private List<XbzSkuStock> skuStockList;
    //@ApiModelProperty("商品参数及自定义规格属性")
    private List<XbzProductAttributeValue> productAttributeValueList;


    public List<XbzProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<XbzProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }
    public List<XbzMemberPrice> getMemberPriceList() {
        return memberPriceList;
    }

    public void setMemberPriceList(List<XbzMemberPrice> memberPriceList) {
        this.memberPriceList = memberPriceList;
    }

    public List<XbzSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<XbzSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<XbzProductAttributeValue> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<XbzProductAttributeValue> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public List<XbzProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<XbzProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }
}
