package com.macro.mall.dao.order;

import com.macro.mall.dto.order.XbzOrderReturnApplyResult;
import com.macro.mall.dto.order.XbzReturnApplyQueryParam;
import com.macro.mall.model.XbzOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单退货申请自定义Dao
 * Created by macro on 2018/10/18.
 */
public interface XbzOrderReturnApplyDao {
    /**
     * 查询申请列表
     */
    List<XbzOrderReturnApply> getList(@Param("queryParam") XbzReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    XbzOrderReturnApplyResult getDetail(@Param("id")Long id);
}
