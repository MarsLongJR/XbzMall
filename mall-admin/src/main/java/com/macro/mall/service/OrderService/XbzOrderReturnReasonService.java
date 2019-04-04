package com.macro.mall.service.OrderService;

import com.macro.mall.model.XbzOrderReturnReason;

import java.util.List;

/**
 * 订单原因管理Service
 * Created by macro on 2018/10/17.
 */
public interface XbzOrderReturnReasonService {
    /**
     * 添加订单原因
     */
    int create(XbzOrderReturnReason returnReason);

    /**
     * 修改退货原因
     */
    int update(Long id, XbzOrderReturnReason returnReason);

    /**
     * 批量删除退货原因
     */
    int delete(List<Long> ids);

    /**
     * 分页获取退货原因
     */
    List<XbzOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * 批量修改退货原因状态
     */
    int updateStatus(List<Long> ids, Integer status);

    /**
     * 获取单个退货原因详情信息
     */
    XbzOrderReturnReason getItem(Long id);
}
