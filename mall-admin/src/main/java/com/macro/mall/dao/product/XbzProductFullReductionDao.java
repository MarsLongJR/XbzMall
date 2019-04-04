package com.macro.mall.dao.product;

import com.macro.mall.model.XbzProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义商品满减Dao
 * @Description 产品满减表(只针对同商品)
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductFullReductionDao {
    int insertList(@Param("list") List<XbzProductFullReduction> productFullReductionList);
}
