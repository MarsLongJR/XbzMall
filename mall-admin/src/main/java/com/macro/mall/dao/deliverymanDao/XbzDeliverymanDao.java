package com.macro.mall.dao.deliverymanDao;

import com.macro.mall.dto.deliveryman.XbzDeliveryManDetail;
import com.macro.mall.dto.deliveryman.XbzDeliveryManQueryParam;
import com.macro.mall.model.XbzDeliveryman;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzDeliverymanDao {
    /**
     * 条件查询配送员
     * @param queryParam
     * @return
     */
    List<XbzDeliveryman> getList(XbzDeliveryManQueryParam queryParam);

    /**
     * 查询配送员详情
     * @param id
     * @return
     */
    XbzDeliveryManDetail detail(Long id);
}
