package com.macro.mall.dto.deliveryman;

import com.macro.mall.model.XbzDeliveryman;
import com.macro.mall.model.XbzMachine;
import com.macro.mall.model.XbzOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzDeliveryManDetail extends XbzDeliveryman {
    @Getter
    @Setter
    private List<XbzMachine> machines;
    @Getter
    @Setter
    private List<XbzOrder> orders;


}
