package com.macro.mall.controller.order;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.order.XbzMoneyInfoParam;
import com.macro.mall.dto.order.XbzReceiverInfoParam;
import com.macro.mall.dto.order.XbzOrderDetail;
import com.macro.mall.dto.order.XbzOrderQueryParam;
import com.macro.mall.model.XbzOrder;
import com.macro.mall.service.OrderService.XbzOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/order")
@Api(tags = "XbzOrderController", description = "订单管理")
public class XbzOrderController {

    @Autowired
    private XbzOrderService orderService;

    /**
     * 查询订单
     * @param queryParam 参数
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(XbzOrderQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return new CommonResult().pageSuccess(orderList);

    }

        /**
         * "获取订单详情:订单信息、商品信息、操作记录"
         * 获取订单详情
         */
        @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        @ResponseBody
        public Object detail(@PathVariable Long id) {
            XbzOrderDetail orderDetailResult = orderService.detail(id);
            return new CommonResult().success(orderDetailResult);
        }

    /**
     * 修改收货人信息
     */
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateReceiverInfo(@RequestBody XbzReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
        }

    /**
     * 修改订单费用信息
     */
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateReceiverInfo(@RequestBody XbzMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    /**
     * 备注订单
     */
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    @ResponseBody
    public Object updateNote(@RequestParam("id") Long id,
                             @RequestParam("note") String note,
                             @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id,note,status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
    /**
     * 按日期查询订单（近7天）查询汇总
     */
}
