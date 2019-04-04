package com.macro.mall.dto.product;

import com.macro.mall.model.XbzProductAttribute;
import com.macro.mall.model.XbzProductAttributeCategory;

import java.util.List;

/**
 * 包含有分类下属性的dto
 * Created by macro on 2018/5/24.
 */
public class XbzProductAttributeCategoryItem extends XbzProductAttributeCategory {
    private List<XbzProductAttribute> productAttributeList;

    public List<XbzProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<XbzProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
