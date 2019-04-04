package com.macro.mall.service.impl.productServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.product.XbzProductAttributeDao;
import com.macro.mall.dto.product.ProductAttrInfo;
import com.macro.mall.dto.product.XbzProductAttributeParam;
import com.macro.mall.mapper.XbzProductAttributeCategoryMapper;
import com.macro.mall.mapper.XbzProductAttributeMapper;
import com.macro.mall.model.XbzProductAttribute;
import com.macro.mall.model.XbzProductAttributeCategory;
import com.macro.mall.model.XbzProductAttributeExample;
import com.macro.mall.service.productService.XbzProductAttributeService;
import org.springframework.beans.BeanUtils;
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
public class XbzProductAttributeServiceImp implements XbzProductAttributeService {
    @Resource
    private XbzProductAttributeMapper productAttributeMapper;
    @Resource
    private XbzProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Resource
    private XbzProductAttributeDao productAttributeDao;

    @Override
    public List<XbzProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzProductAttributeExample example = new XbzProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andProductAttributeCategoryIdEqualTo(cid).andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(example);
    }

    @Override
    public int create(XbzProductAttributeParam productAttributeParam) {
        XbzProductAttribute xbzProductAttribute = new XbzProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, xbzProductAttribute);
        int count = productAttributeMapper.insertSelective(xbzProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        XbzProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(xbzProductAttribute.getProductAttributeCategoryId());
        if(xbzProductAttribute.getType()==0){
            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount()+1);
        }else if(xbzProductAttribute.getType()==1){
            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(productAttributeCategory);
        return count;
    }

    @Override
    public int update(Long id, XbzProductAttributeParam productAttributeParam) {
        XbzProductAttribute productAttribute = new XbzProductAttribute();
        productAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        return productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
    }

    @Override
    public XbzProductAttribute getItem(Long id) {
        return  productAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        XbzProductAttribute xbzProductAttribute = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = xbzProductAttribute.getType();
        XbzProductAttributeCategory xbzProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(xbzProductAttribute.getProductAttributeCategoryId());
        XbzProductAttributeExample example = new XbzProductAttributeExample();
        example.createCriteria().andIdIn(ids);
        int count = productAttributeMapper.deleteByExample(example);
        //删除完成后修改数量
        if(type==0){
            if(xbzProductAttributeCategory.getAttributeCount()>=count){
                xbzProductAttributeCategory.setAttributeCount(xbzProductAttributeCategory.getAttributeCount()-count);
            }else{
                xbzProductAttributeCategory.setAttributeCount(0);
            }
        }else if(type==1){
            if(xbzProductAttributeCategory.getParamCount()>=count){
                xbzProductAttributeCategory.setParamCount(xbzProductAttributeCategory.getParamCount()-count);
            }else{
                xbzProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateByPrimaryKey(xbzProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }
}
