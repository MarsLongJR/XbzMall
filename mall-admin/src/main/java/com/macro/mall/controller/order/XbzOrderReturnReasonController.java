package com.macro.mall.controller.order;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzOrderReturnReason;
import com.macro.mall.service.OrderService.XbzOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退货原因管理Controller
 * Created by macro on 2018/10/17.
 */
@Controller
//@Api(tags = "OmsOrderReturnReasonController", description = "退货原因管理")
@RequestMapping("/returnReason")
public class XbzOrderReturnReasonController {
    @Autowired
    private XbzOrderReturnReasonService orderReturnReasonService;

    //@ApiOperation("添加退货原因")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody XbzOrderReturnReason returnReason) {
        int count = orderReturnReasonService.create(returnReason);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("修改退货原因")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody XbzOrderReturnReason returnReason) {
        int count = orderReturnReasonService.update(id,returnReason);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.delete(ids);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("分页查询全部退货原因")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzOrderReturnReason> reasonList = orderReturnReasonService.list(pageSize,pageNum);
        return new CommonResult().pageSuccess(reasonList);
    }

    //@ApiOperation("获取单个退货原因详情信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzOrderReturnReason reason = orderReturnReasonService.getItem(id);
        return new CommonResult().success(reason);
    }

    //@ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@RequestParam(value = "status") Integer status,
                               @RequestParam("ids") List<Long> ids) {
        int count = orderReturnReasonService.updateStatus(ids,status);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
