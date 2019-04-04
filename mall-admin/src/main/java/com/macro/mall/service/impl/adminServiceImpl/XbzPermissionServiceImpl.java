package com.macro.mall.service.impl.adminServiceImpl;

import com.macro.mall.dto.admin.XbzPermissionNode;
import com.macro.mall.mapper.XbzPermissionMapper;
import com.macro.mall.model.XbzPermission;
import com.macro.mall.model.XbzPermissionExample;
import com.macro.mall.service.AdminService.XbzPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 后台用户权限管理Service实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzPermissionServiceImpl implements XbzPermissionService {

    @Resource
    private XbzPermissionMapper permissionMapper;

    @Override
    public int create(XbzPermission permission) {
        permission.setStatus(1);
        permission.setCreateTime(new Date());
        permission.setSort(0);
        return permissionMapper.insert(permission);
    }

    @Override
    public int update(Long id, XbzPermission permission) {
        permission.setId(id);
        return permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public int delete(List<Long> ids) {
        XbzPermissionExample example = new XbzPermissionExample();
        example.createCriteria().andIdIn(ids);
        return permissionMapper.deleteByExample(example);
    }

    @Override
    public List<XbzPermissionNode> treeList() {
        List<XbzPermission> permissionList = permissionMapper.selectByExample(new XbzPermissionExample());
        List<XbzPermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission,permissionList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<XbzPermission> list() {
        return permissionMapper.selectByExample(new XbzPermissionExample());
    }
    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private XbzPermissionNode covert(XbzPermission permission,List<XbzPermission> permissionList){
        XbzPermissionNode node = new XbzPermissionNode();
        BeanUtils.copyProperties(permission,node);
        List<XbzPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission,permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
