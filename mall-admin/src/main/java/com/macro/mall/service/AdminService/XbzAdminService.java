package com.macro.mall.service.AdminService;

import com.macro.mall.dto.admin.XbzAdminParam;
import com.macro.mall.model.XbzAdmin;
import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName 后台管理员Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    XbzAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    XbzAdmin register(XbzAdminParam XbzAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    XbzAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<XbzAdmin> list(String name, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, XbzAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<XbzRole> getRoleList(Long adminId);

    /**
     * 修改用户的+-权限
     */
    @Transactional
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<XbzPermission> getPermissionList(Long adminId);
}
