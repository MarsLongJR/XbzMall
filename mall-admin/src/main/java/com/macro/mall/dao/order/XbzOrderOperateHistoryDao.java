package com.macro.mall.dao.order;

import com.macro.mall.model.XbzOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作记录自定义Dao
 * Created by macro on 2018/10/12.
 */
public interface XbzOrderOperateHistoryDao {
    int insertList(@Param("list") List<XbzOrderOperateHistory> orderOperateHistoryList);
}
