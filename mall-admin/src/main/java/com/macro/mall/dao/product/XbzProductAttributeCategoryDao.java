package com.macro.mall.dao.product;

import com.macro.mall.dto.product.XbzProductAttributeCategoryItem;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductAttributeCategoryDao {
    List<XbzProductAttributeCategoryItem> getListWithAttr();
}
