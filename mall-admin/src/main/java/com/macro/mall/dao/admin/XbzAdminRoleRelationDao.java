package com.macro.mall.dao.admin;

import com.macro.mall.model.XbzAdminRoleRelation;
import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName 后台用户与角色管理自定义Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<XbzAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<XbzRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<XbzPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<XbzPermission> getPermissionList(@Param("adminId") Long adminId);
}
