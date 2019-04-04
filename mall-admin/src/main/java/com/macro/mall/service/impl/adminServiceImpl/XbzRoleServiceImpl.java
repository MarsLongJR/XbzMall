package com.macro.mall.service.impl.adminServiceImpl;

import com.macro.mall.dao.admin.XbzRolePermissionRelationDao;
import com.macro.mall.mapper.XbzRoleMapper;
import com.macro.mall.mapper.XbzRolePermissionRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.AdminService.XbzRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by macro on 2018/9/30.
 */
@Service
public class XbzRoleServiceImpl implements XbzRoleService {
    @Resource
    private XbzRoleMapper roleMapper;
    @Resource
    private XbzRolePermissionRelationMapper rolePermissionRelationMapper;
    @Resource
    private XbzRolePermissionRelationDao rolePermissionRelationDao;
    @Override
    public int create(XbzRole role) {
        role.setCreateTime(new Date());
        role.setStatus(1);
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, XbzRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int delete(List<Long> ids) {
        XbzRoleExample example = new XbzRoleExample();
        example.createCriteria().andIdIn(ids);
        return roleMapper.deleteByExample(example);
    }

    @Override
    public List<XbzPermission> getPermissionList(Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        //先删除原有关系
        XbzRolePermissionRelationExample example=new XbzRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(example);
        //批量插入新关系
        List<XbzRolePermissionRelation> relationList = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            XbzRolePermissionRelation relation = new XbzRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            relationList.add(relation);
        }
        return rolePermissionRelationDao.insertList(relationList);
    }

    @Override
    public List<XbzRole> list() {
        return roleMapper.selectByExample(new XbzRoleExample());
    }
}
