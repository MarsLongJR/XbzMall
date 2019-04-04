package com.macro.mall.controller.order;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzCompanyAddress;
import com.macro.mall.service.OrderService.XbzAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName 订单发货快递员管理Controller
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class XbzAdressController {
    @Autowired
    private XbzAddressService companyAddressService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        List<XbzCompanyAddress> companyAddressList = companyAddressService.list();
        return new CommonResult().success(companyAddressList);
    }

}
