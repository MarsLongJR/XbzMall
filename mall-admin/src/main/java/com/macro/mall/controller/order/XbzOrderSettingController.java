package com.macro.mall.controller.order;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzOrderSetting;
import com.macro.mall.service.OrderService.XbzOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
//@Api(tags = "OmsOrderSettingController", description = "订单设置管理")
@RequestMapping("/orderSetting")
public class XbzOrderSettingController {
    @Autowired
    private XbzOrderSettingService orderSettingService;

    //@ApiOperation("获取指定订单设置")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzOrderSetting orderSetting = orderSettingService.getItem(id);
        return new CommonResult().success(orderSetting);
    }

    //@ApiOperation("修改指定订单设置")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody XbzOrderSetting orderSetting) {
        int count = orderSettingService.update(id,orderSetting);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }



}
