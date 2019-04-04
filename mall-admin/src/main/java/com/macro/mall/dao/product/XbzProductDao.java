package com.macro.mall.dao.product;

import com.macro.mall.dto.product.XbzProductResult;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName 商品自定义Dao
 * @Description 自定义商品信息表
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductDao {
    /**
     * 获取商品编辑信息
     */
    XbzProductResult getUpdateInfo(@Param("id") Long id);
}
