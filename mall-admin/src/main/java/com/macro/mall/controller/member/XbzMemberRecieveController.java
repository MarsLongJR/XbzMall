package com.macro.mall.controller.member;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.member.XbzMemberRecieveQueryParam;
import com.macro.mall.model.XbzMemberReceiveAddress;
import com.macro.mall.service.MemberService.XbzMemberRecieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName 收货人地址管理controller
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/memberRecieve")
public class XbzMemberRecieveController {

    @Autowired
    private XbzMemberRecieveService memberRecieveService;
    /**
     * 分页查询所有收货人的所有的地址信息
     * @param queryParam 查询参数
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(XbzMemberRecieveQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzMemberReceiveAddress> memberRecieveList=memberRecieveService.list(queryParam, pageSize, pageNum);
        return new CommonResult().pageSuccess(memberRecieveList);
    }
    /**
     * 根据id查询用户的收货地址
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable Long id) {
        XbzMemberReceiveAddress memberRecieveDetail=memberRecieveService.getList(id);
        return new CommonResult().success(memberRecieveDetail);
    }

}
