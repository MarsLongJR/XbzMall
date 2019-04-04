package com.macro.mall.controller.coupon;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.coupon.XbzCouponParam;
import com.macro.mall.model.XbzCoupon;
import com.macro.mall.service.couponService.XbzCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券管理Controller
 * Created by macro on 2018/8/28.
 */
@Controller
@RequestMapping("/coupon")
public class XbzCouponController {
    @Autowired
    private XbzCouponService couponService;
    //@ApiOperation("添加优惠券")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody XbzCouponParam couponParam) {
        int count = couponService.create(couponParam);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("删除优惠券")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = couponService.delete(id);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("修改优惠券")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id,@RequestBody XbzCouponParam couponParam) {
        int count = couponService.update(id,couponParam);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzCoupon> couponList = couponService.list(name,type,pageSize,pageNum);
        return new CommonResult().pageSuccess(couponList);
    }

    //@ApiOperation("获取单个优惠券的详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzCouponParam couponParam = couponService.getItem(id);
        return new CommonResult().success(couponParam);
    }
}
