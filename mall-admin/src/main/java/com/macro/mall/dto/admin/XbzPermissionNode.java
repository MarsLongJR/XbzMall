package com.macro.mall.dto.admin;

import com.macro.mall.model.XbzPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by macro on 2018/9/30.
 */
public class XbzPermissionNode extends XbzPermission {
    @Getter
    @Setter
    private List<XbzPermissionNode> children;
}
