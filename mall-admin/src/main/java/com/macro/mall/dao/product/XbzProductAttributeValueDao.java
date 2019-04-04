package com.macro.mall.dao.product;

import com.macro.mall.model.XbzProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 商品参数，商品自定义规格属性Dao
 * @Description 存储产品参数信息的表
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductAttributeValueDao {
    int insertList(@Param("list") List<XbzProductAttributeValue> productAttributeValueList);

}
