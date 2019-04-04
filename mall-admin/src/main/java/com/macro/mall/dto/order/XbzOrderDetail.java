package com.macro.mall.dto.order;

import com.macro.mall.model.XbzOrder;
import com.macro.mall.model.XbzOrderItem;
import com.macro.mall.model.XbzOrderOperateHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 * Created by macro on 2018/10/11.
 */
public class XbzOrderDetail extends XbzOrder {
    @Getter
    @Setter
    private List<XbzOrderItem> orderItemList;
    @Getter
    @Setter
    private List<XbzOrderOperateHistory> historyList;
}
