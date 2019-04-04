package com.macro.mall.dao.order;

import com.macro.mall.dto.order.XbzOrderDetail;
import com.macro.mall.dto.order.XbzOrderQueryParam;
import com.macro.mall.model.XbzOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzOrderDao {
    /**
     * 条件查询订单
     */
    List<XbzOrder> getList(@Param("queryParam") XbzOrderQueryParam queryParam);

    /**
     * 获取订单详情
     */
    XbzOrderDetail getDetail(@Param("id") Long id);
}
