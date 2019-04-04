package com.macro.mall.controller.coupon;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzCouponHistory;
import com.macro.mall.service.couponService.XbzCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 优惠券领取记录管理Controller
 * Created by macro on 2018/11/6.
 */
@Controller
@RequestMapping("/couponHistory")
public class XbzCouponHistoryController {
    @Autowired
    private XbzCouponHistoryService historyService;
    //@ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "couponId",required = false) Long couponId,
                       @RequestParam(value = "useStatus",required = false) Integer useStatus,
                       @RequestParam(value = "orderSn",required = false) String orderSn,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<XbzCouponHistory> historyList = historyService.list(couponId,useStatus,orderSn,pageSize,pageNum);
        return new CommonResult().pageSuccess(historyList);
    }
}