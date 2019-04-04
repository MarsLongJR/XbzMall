package com.macro.mall.dto.product;

import com.macro.mall.model.XbzProductCategory;

import java.util.List;

/**
 * Created by macro on 2018/5/25.
 */
public class XbzProductCategoryWithChildrenItem extends XbzProductCategory {
    private List<XbzProductCategory> children;

    public List<XbzProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<XbzProductCategory> children) {
        this.children = children;
    }
}
