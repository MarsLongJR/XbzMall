package com.macro.mall.service.impl.productServiceImpl;

import com.macro.mall.dao.product.XbzSkuStockDao;
import com.macro.mall.mapper.XbzSkuStockMapper;
import com.macro.mall.model.XbzSkuStock;
import com.macro.mall.model.XbzSkuStockExample;
import com.macro.mall.service.productService.XbzSkuStockService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName 商品sku库存管理Service实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzSkuStockServiceImpl implements XbzSkuStockService {
    @Resource
    private XbzSkuStockMapper skuStockMapper;
    @Resource
    private XbzSkuStockDao skuStockDao;

    @Override
    public List<XbzSkuStock> getList(Long pid, String keyword) {
        XbzSkuStockExample example = new XbzSkuStockExample();
        XbzSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andSkuCodeLike("%" + keyword + "%");
        }
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public int update(Long pid, List<XbzSkuStock> skuStockList) {
        return skuStockDao.replaceList(skuStockList);
    }
}
