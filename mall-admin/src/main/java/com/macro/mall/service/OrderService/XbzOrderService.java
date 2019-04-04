package com.macro.mall.service.OrderService;

import com.macro.mall.dto.order.XbzMoneyInfoParam;
import com.macro.mall.dto.order.XbzOrderDetail;
import com.macro.mall.dto.order.XbzOrderQueryParam;
import com.macro.mall.dto.order.XbzReceiverInfoParam;
import com.macro.mall.model.XbzOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzOrderService {
    /**
     * 订单查询
     * @param queryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<XbzOrder> list(XbzOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 获取指定订单详情
     * @param id
     * @return
     */
    XbzOrderDetail detail(Long id);

    /**
     * 修改订单费用信息
     */
    @Transactional
    int updateMoneyInfo(XbzMoneyInfoParam moneyInfoParam);
    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);

    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(XbzReceiverInfoParam receiverInfoParam);
}
