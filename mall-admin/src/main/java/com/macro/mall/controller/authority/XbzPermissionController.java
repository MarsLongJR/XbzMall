package com.macro.mall.controller.authority;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.admin.XbzPermissionNode;
import com.macro.mall.model.XbzPermission;
import com.macro.mall.service.AdminService.XbzPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 * Created by macro on 2018/9/29.
 */
@Controller
@RequestMapping("/permission")
public class XbzPermissionController {
    @Autowired
    private XbzPermissionService permissionService;
    //@ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody XbzPermission permission) {
        int count = permissionService.create(permission);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody XbzPermission permission) {
        int count = permissionService.update(id,permission);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("根据id批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public Object treeList() {
        List<XbzPermissionNode> permissionNodeList = permissionService.treeList();
        return new CommonResult().success(permissionNodeList);
    }

    //@ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        List<XbzPermission> permissionList = permissionService.list();
        return new CommonResult().success(permissionList);
    }
}
