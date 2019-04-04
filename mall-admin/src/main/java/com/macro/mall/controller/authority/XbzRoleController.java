package com.macro.mall.controller.authority;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzRole;
import com.macro.mall.service.AdminService.XbzRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户角色管理
 */
@Controller
@RequestMapping("/role")
public class XbzRoleController {
    @Autowired
    private XbzRoleService roleService;

    /**
     * 添加角色
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody XbzRole role) {
        int count = roleService.create(role);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    /**
     * 修改角色
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody XbzRole role) {
        int count = roleService.update(id,role);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    /**
     * 批量删除角色
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.delete(ids);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("获取相应角色权限")
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getPermissionList(@PathVariable Long roleId) {
        List<XbzPermission> permissionList =roleService.getPermissionList(roleId);
        return new CommonResult().success(permissionList);
    }

    //@ApiOperation("修改角色权限")
    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePermission(@RequestParam Long roleId,
                                   @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = roleService.updatePermission(roleId,permissionIds);
        if(count>0){
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    //@ApiOperation("获取所有角色")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(){
        List<XbzRole> roleList = roleService.list();
        return new CommonResult().success(roleList);
    }

}
