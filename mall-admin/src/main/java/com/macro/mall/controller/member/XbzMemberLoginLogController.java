package com.macro.mall.controller.member;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.member.XbzMemberQueryLoginLogParam;
import com.macro.mall.model.XbzMember;
import com.macro.mall.model.XbzMemberLoginLog;
import com.macro.mall.service.MemberService.XbzMemberLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping
public class XbzMemberLoginLogController {
    @Autowired
    private XbzMemberLoginLogService memberLoginLogService;
    /**
     * 查询所有用户登录日志
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object loginLoglist(XbzMemberQueryLoginLogParam queryParam,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzMember> membeLoginLogList = memberLoginLogService.loginLoglist(queryParam, pageSize, pageNum);
        return new CommonResult().pageSuccess(membeLoginLogList);
    }

    /**
     * 根据id查询用户登录日志
     */
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(Long id) {
        XbzMemberLoginLog memberLoginLog=memberLoginLogService.getDetail(id);
        return new CommonResult().success(memberLoginLog);
    }

    /**
     * 根据用户手机号查询用户登陆日志
     */
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public Object phoneDetail(String phone) {
        XbzMemberLoginLog memberLoginLog=memberLoginLogService.getPhoneDetail(phone);
        return new CommonResult().success(memberLoginLog);
    }

}
