package com.macro.mall.dao.admin;

import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzRolePermissionRelation;

import java.util.List;

/**
 * @ClassName 后台用户角色管理自定义Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzRolePermissionRelationDao {
    /**
     * 根据角色获取权限
     */
    List<XbzPermission> getPermissionList(Long roleId);

    /**
     * 批量插入角色和权限关系
     */
    int insertList(List<XbzRolePermissionRelation> relationList);




}
