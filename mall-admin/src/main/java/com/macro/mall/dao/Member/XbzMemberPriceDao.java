package com.macro.mall.dao.Member;

import com.macro.mall.model.XbzMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 自定义会员价格Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzMemberPriceDao {
    int insertList(@Param("list") List<XbzMemberPrice> memberPriceList);
}
