package com.macro.mall.dto.deliveryman;

import com.macro.mall.model.*;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzDeliveryManParam extends XbzDeliveryman {
    /**
     * 根据机器信息查询配送员
     */
    private List<XbzMachine> machines;
    /**
     * 根据后台用户查询配送员
     */
    private List<XbzAdmin> admins;
    /**
     * 根据订单查询配送员
     */
    private List<XbzOrder> orders;


    public List<XbzMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<XbzMachine> machines) {
        this.machines = machines;
    }

    public List<XbzAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<XbzAdmin> admins) {
        this.admins = admins;
    }

    public List<XbzOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<XbzOrder> orders) {
        this.orders = orders;
    }
}
