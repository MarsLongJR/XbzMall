package com.macro.mall.service.productService;

import com.macro.mall.dto.product.XbzProductCategoryParam;
import com.macro.mall.dto.product.XbzProductCategoryWithChildrenItem;
import com.macro.mall.model.XbzProductCategory;

import java.util.List;

/**
 * @ClassName 产品分类Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductCategoryService {
    int create(XbzProductCategoryParam productCategoryParam);

    int update(Long id, XbzProductCategoryParam productCategoryParam);

    List<XbzProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    XbzProductCategory getItem(Long id);

    int delete(Long id);

    int updateNavStatus(List<Long> ids, Integer navStatus);

    int updateShowStatus(List<Long> ids, Integer showStatus);

    List<XbzProductCategoryWithChildrenItem> listWithChildren();
}
