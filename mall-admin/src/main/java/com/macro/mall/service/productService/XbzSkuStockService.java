package com.macro.mall.service.productService;

import com.macro.mall.model.XbzSkuStock;

import java.util.List;

/**
 * @ClassName sku商品库存管理Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzSkuStockService {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<XbzSkuStock> getList(Long pid, String keyword);


    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<XbzSkuStock> skuStockList);
}
