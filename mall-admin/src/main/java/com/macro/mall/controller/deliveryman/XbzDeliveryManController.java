package com.macro.mall.controller.deliveryman;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.deliveryman.XbzDeliveryManDetail;
import com.macro.mall.dto.deliveryman.XbzDeliveryManQueryParam;
import com.macro.mall.model.XbzDeliveryman;
import com.macro.mall.service.deliveryManService.XbzDeliveryManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName 配送员contoller
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/deliveryMan")
public class XbzDeliveryManController {
    @Autowired
    private XbzDeliveryManService deliveryManService;

    /**
     * 查找所有的配送员
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(XbzDeliveryManQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzDeliveryman> deliverymanList = deliveryManService.list(queryParam,pageSize,pageNum);
        return new CommonResult().pageSuccess(deliverymanList);

    }

    /**
     * 根据id查找配送员
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable Long id, Integer delivery_company) {
        if(delivery_company==0)
            return new CommonResult().failed();
        if(delivery_company==1) {
            XbzDeliveryManDetail deliveryManDetailResult=deliveryManService.detail(id);
            return new CommonResult().success(deliveryManDetailResult);
        }
        return null;
    }

    /**
     * 批量审核配送员
     */
    @RequestMapping(value = "/update/verifyStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("verifyStatus") Integer verifyStatus,
                                      @RequestParam("detail") String detail
    ) {
        int count = deliveryManService.updateVerifyStatus(ids, verifyStatus, detail);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改配送员配送区域
     * @param ids
     * @param adressName
     * @return
     */
    @RequestMapping(value = "/update/verifyStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("adressname") String adressName) {
        int count =deliveryManService.updateAdressName(ids,adressName);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 批量更改配送员账号的状态
     */
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.GET)
    @ResponseBody
    public Object status(@RequestParam("ids") List<Long> ids,
                         @RequestParam("status") Integer status) {
        int count=deliveryManService.changeStatus(ids,status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

}
