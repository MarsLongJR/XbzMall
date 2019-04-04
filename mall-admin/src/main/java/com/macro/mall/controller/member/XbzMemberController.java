package com.macro.mall.controller.member;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.member.XbzMemberDetail;
import com.macro.mall.dto.member.XbzMemberInfoParam;
import com.macro.mall.dto.member.XbzMemberQueryParam;
import com.macro.mall.model.XbzMember;
import com.macro.mall.service.MemberService.XbzMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName 前台用户的controller
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/member")
public class XbzMemberController {
    @Autowired
    private XbzMemberService memberService;

    /**
     * 查询用户信息
     * @param queryParam 查询参数
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(XbzMemberQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzMember> memberList=memberService.list(queryParam, pageSize, pageNum);
        return new CommonResult().pageSuccess(memberList);

    }

    /**
     * 根据id
     * 获取用户详情信息:职业，昵称等
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable Long id) {
        XbzMemberDetail memberDetailResult=memberService.detail(id);
        return new CommonResult().success(memberDetailResult);
    }

    /**
     * 操作编辑用户
     */
    @RequestMapping(value = "/update/memberInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateMemberInfo(@RequestBody XbzMemberInfoParam memberInfoParam) {
        int count = memberService.updateMemberInfo(memberInfoParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    /**
     * 批量用户状态
     */
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.GET)
    @ResponseBody
    public Object status(@RequestParam("ids") List<Long> ids,
                         @RequestParam("deleteStatus") Integer status) {
        int count = memberService.changeStatus(ids,status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

}