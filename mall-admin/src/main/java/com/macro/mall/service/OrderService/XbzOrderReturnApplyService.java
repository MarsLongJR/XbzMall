package com.macro.mall.service.OrderService;

import com.macro.mall.dto.order.XbzOrderReturnApplyResult;
import com.macro.mall.dto.order.XbzReturnApplyQueryParam;
import com.macro.mall.dto.order.XbzUpdateStatusParam;
import com.macro.mall.model.XbzOrderReturnApply;

import java.util.List;

/**
 * 退货申请管理Service
 * Created by macro on 2018/10/18.
 */
public interface XbzOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    List<XbzOrderReturnApply> list(XbzReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, XbzUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    XbzOrderReturnApplyResult getItem(Long id);
}
