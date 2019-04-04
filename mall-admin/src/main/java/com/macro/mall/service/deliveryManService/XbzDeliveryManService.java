package com.macro.mall.service.deliveryManService;

import com.macro.mall.dto.deliveryman.XbzDeliveryManDetail;
import com.macro.mall.dto.deliveryman.XbzDeliveryManQueryParam;
import com.macro.mall.model.XbzDeliveryman;

import java.util.List;

/**
 * @ClassName 配送员Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzDeliveryManService {
    /**
     * 查找所有的配送员
     */
    List<XbzDeliveryman> list(XbzDeliveryManQueryParam queryParam, Integer pageSize, Integer pageNum);
    /**
     * 根据id查找配送员
     */
    XbzDeliveryManDetail detail(Long id);
    /**
     * 批量审核配送员
     */
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);
    /**
     * 修改配送员配送区域
     * @param ids
     * @param adressName
     * @return
     */
    int updateAdressName(List<Long> ids, String adressName);
    /**
     * 批量更改配送员账号的状态
     */
    int changeStatus(List<Long> ids, Integer status);
}
