package com.macro.mall.dao.product;

import com.macro.mall.model.XbzSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义商品sku库存Dao
 * @Description sku的库存表
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzSkuStockDao {
    /**
     * 批量插入操作
     */
    int insertList(@Param("list") List<XbzSkuStock> skuStockList);

    /**
     * 批量插入或替换操作
     */
    int replaceList(@Param("list")List<XbzSkuStock> skuStockList);
}
