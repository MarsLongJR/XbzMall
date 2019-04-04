package com.macro.mall.service.impl.brandServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.brand.XbzBrandParam;
import com.macro.mall.mapper.XbzBrandMapper;
import com.macro.mall.mapper.XbzProductMapper;
import com.macro.mall.model.XbzBrand;
import com.macro.mall.model.XbzBrandExample;
import com.macro.mall.model.XbzProduct;
import com.macro.mall.model.XbzProductExample;
import com.macro.mall.service.XbzBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzBrandServiceImpl implements XbzBrandService {
    @Resource
    private XbzBrandMapper brandMapper;
    @Resource
    private XbzProductMapper productMapper;

    @Override
    public List<XbzBrand> listAllBrand() {
        return brandMapper.selectByExample(new XbzBrandExample());
    }

    @Override
    public int createBrand(XbzBrandParam pmsBrandParam) {
        XbzBrand pmsBrand = new XbzBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return brandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, XbzBrandParam pmsBrandParam) {
        XbzBrand pmsBrand = new XbzBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        XbzProduct product = new XbzProduct();
        product.setBrandName(pmsBrand.getName());
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andBrandIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        return brandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List listBrand(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        XbzBrandExample xbzBrandExample = new XbzBrandExample();
        xbzBrandExample.setOrderByClause("sort desc");
        XbzBrandExample.Criteria criteria = xbzBrandExample.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        return brandMapper.selectByExample(xbzBrandExample);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        XbzBrandExample pmsBrandExample = new XbzBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.deleteByExample(pmsBrandExample);
    }

    @Override
    public XbzBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        XbzBrand pmsBrand = new  XbzBrand();
        pmsBrand.setShowStatus(showStatus);
        XbzBrandExample pmsBrandExample = new XbzBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        XbzBrand pmsBrand = new  XbzBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        XbzBrandExample pmsBrandExample = new  XbzBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return brandMapper.updateByExampleSelective(pmsBrand, pmsBrandExample);
    }
}
