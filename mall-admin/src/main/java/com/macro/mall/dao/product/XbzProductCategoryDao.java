package com.macro.mall.dao.product;

import com.macro.mall.dto.product.XbzProductCategoryWithChildrenItem;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductCategoryDao {
    List<XbzProductCategoryWithChildrenItem> listWithChildren();
}
