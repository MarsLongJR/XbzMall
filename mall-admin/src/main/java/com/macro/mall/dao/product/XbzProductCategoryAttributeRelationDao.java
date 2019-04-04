package com.macro.mall.dao.product;

import com.macro.mall.model.XbzProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义商品分类和属性关系Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductCategoryAttributeRelationDao {
    int insertList(@Param("list") List<XbzProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}
