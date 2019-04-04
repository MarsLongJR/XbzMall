package com.macro.mall.controller.member;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzMemberLevel;
import com.macro.mall.service.MemberService.XbzMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 会员等级管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@RequestMapping("/memberLevel")
public class XbzMemberLevelController {
    @Autowired
    private XbzMemberLevelService memberLevelService;
    /**
     * 查询会员等级
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam("defaultStatus") Integer defaultStatus){
        List<XbzMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return new CommonResult().success(memberLevelList);
    }



}
