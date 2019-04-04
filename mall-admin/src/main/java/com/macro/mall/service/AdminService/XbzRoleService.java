package com.macro.mall.service.AdminService;

import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName 后台角色管理Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzRoleService {
    /**
     * 添加角色
     */
    int create(XbzRole role);

    /**
     * 修改角色信息
     */
    int update(Long id, XbzRole role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     */
    List<XbzPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取角色列表
     */
    List<XbzRole> list();
}
