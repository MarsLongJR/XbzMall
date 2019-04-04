package com.macro.mall.dao.deliverymanDao;

import com.macro.mall.model.XbzDeliveryVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 配送员审核dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzDeliveryVertifyRecordDao {
    /**
     * 插入操作记录
     * @param list
     * @return
     */
    int insertList(@Param("list") List<XbzDeliveryVertifyRecord> list);
}
