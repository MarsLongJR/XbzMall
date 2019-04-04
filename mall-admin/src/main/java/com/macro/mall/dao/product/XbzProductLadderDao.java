package com.macro.mall.dao.product;

import com.macro.mall.model.XbzProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义会员阶梯价格Dao
 * @Description 产品阶梯表，只针对同一个商品
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductLadderDao {
    int insertList(@Param("list") List<XbzProductLadder> productLadderList);
}
