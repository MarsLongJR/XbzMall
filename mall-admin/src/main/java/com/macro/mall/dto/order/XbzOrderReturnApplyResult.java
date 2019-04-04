package com.macro.mall.dto.order;

import com.macro.mall.model.XbzCompanyAddress;
import com.macro.mall.model.XbzOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created by macro on 2018/10/18.
 */
public class XbzOrderReturnApplyResult extends XbzOrderReturnApply {
    @Getter
    @Setter
    private XbzCompanyAddress companyAddress;
}
