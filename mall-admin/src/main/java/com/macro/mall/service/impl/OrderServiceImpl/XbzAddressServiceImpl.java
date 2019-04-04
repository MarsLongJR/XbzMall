package com.macro.mall.service.impl.OrderServiceImpl;

import com.macro.mall.mapper.XbzCompanyAddressMapper;
import com.macro.mall.model.XbzCompanyAddress;
import com.macro.mall.model.XbzCompanyAddressExample;
import com.macro.mall.service.OrderService.XbzAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzAddressServiceImpl implements XbzAddressService {
    @Resource
    private XbzCompanyAddressMapper companyAddressMapper;
    @Override
    public List<XbzCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new XbzCompanyAddressExample());
    }
}
