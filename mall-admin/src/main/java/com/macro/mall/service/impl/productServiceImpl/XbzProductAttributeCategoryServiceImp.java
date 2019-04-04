package com.macro.mall.service.impl.productServiceImpl;

import com.macro.mall.dao.product.XbzProductAttributeCategoryDao;
import com.macro.mall.dto.product.XbzProductAttributeCategoryItem;
import com.macro.mall.mapper.XbzProductAttributeCategoryMapper;
import com.macro.mall.model.XbzProductAttributeCategory;
import com.macro.mall.model.XbzProductAttributeCategoryExample;
import com.macro.mall.service.productService.XbzProductAttributeCategoryService;
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
public class XbzProductAttributeCategoryServiceImp implements XbzProductAttributeCategoryService {
    @Resource
    private XbzProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Resource
    private XbzProductAttributeCategoryDao productAttributeCategoryDao;


    @Override
    public int create(String name) {
        XbzProductAttributeCategory productAttributeCategory = new XbzProductAttributeCategory();
        productAttributeCategory.setName(name);
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        XbzProductAttributeCategory productAttributeCategory = new XbzProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public XbzProductAttributeCategory getItem(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XbzProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        return productAttributeCategoryMapper.selectByExample(new XbzProductAttributeCategoryExample());
    }

    @Override
    public List<XbzProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryDao.getListWithAttr();
    }
}
