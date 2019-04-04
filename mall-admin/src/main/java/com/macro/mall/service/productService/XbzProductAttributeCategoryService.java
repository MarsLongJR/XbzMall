package com.macro.mall.service.productService;

import com.macro.mall.dto.product.XbzProductAttributeCategoryItem;
import com.macro.mall.model.XbzProductAttributeCategory;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductAttributeCategoryService {
    int create(String name);

    int update(Long id, String name);

    int delete(Long id);

    XbzProductAttributeCategory getItem(Long id);

    List<XbzProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    List<XbzProductAttributeCategoryItem> getListWithAttr();
}
