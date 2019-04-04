package com.macro.mall.service.AdminService;

import com.macro.mall.dto.admin.XbzPermissionNode;
import com.macro.mall.model.XbzPermission;

import java.util.List;

/**
 * @ClassName 后台用户权限管理Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzPermissionService {

    /**
     * 添加权限
     */
    int create(XbzPermission permission);

    /**
     * 修改权限
     */
    int update(Long id, XbzPermission permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<XbzPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<XbzPermission> list();
}
