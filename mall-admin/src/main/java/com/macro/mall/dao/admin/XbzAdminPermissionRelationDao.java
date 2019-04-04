package com.macro.mall.dao.admin;

import com.macro.mall.model.XbzAdminPermissionRelation;

import java.util.List;

/**
 * @ClassName 用户权限自定义Dao
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzAdminPermissionRelationDao {
    int insertList(List<XbzAdminPermissionRelation> relationList);
}
