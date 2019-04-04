package com.macro.mall.dao.product;

import com.macro.mall.dto.product.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义商品属性Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductAttributeDao {
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
