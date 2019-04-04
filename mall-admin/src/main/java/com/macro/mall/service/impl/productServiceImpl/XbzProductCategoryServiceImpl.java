package com.macro.mall.service.impl.productServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.product.XbzProductCategoryAttributeRelationDao;
import com.macro.mall.dao.product.XbzProductCategoryDao;
import com.macro.mall.dto.product.XbzProductCategoryParam;
import com.macro.mall.dto.product.XbzProductCategoryWithChildrenItem;
import com.macro.mall.mapper.XbzProductCategoryAttributeRelationMapper;
import com.macro.mall.mapper.XbzProductCategoryMapper;
import com.macro.mall.mapper.XbzProductMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.productService.XbzProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XbzProductCategoryService实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzProductCategoryServiceImpl implements XbzProductCategoryService {
    @Resource
    private XbzProductCategoryMapper productCategoryMapper;
    @Resource
    private XbzProductMapper productMapper;
    @Resource
    private XbzProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;
    @Resource
    private XbzProductCategoryAttributeRelationMapper productCategoryAttributeRelationMapper;
    @Resource
    private XbzProductCategoryDao productCategoryDao;


    @Override
    public int create(XbzProductCategoryParam productCategoryParam) {
        XbzProductCategory productCategory = new XbzProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        int count = productCategoryMapper.insertSelective(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return count;
    }

    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<XbzProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            XbzProductCategoryAttributeRelation relation = new XbzProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationDao.insertList(relationList);
    }
    @Override
    public int update(Long id, XbzProductCategoryParam productCategoryParam) {
        XbzProductCategory productCategory = new XbzProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        XbzProduct product = new XbzProduct();
        product.setProductCategoryName(productCategory.getName());
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andProductCategoryIdEqualTo(id);
        productMapper.updateByExampleSelective(product,example);
        //同时更新筛选属性的信息
        if(!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())){
            XbzProductCategoryAttributeRelationExample relationExample = new XbzProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
            insertRelationList(id,productCategoryParam.getProductAttributeIdList());
        }else{
            XbzProductCategoryAttributeRelationExample relationExample = new XbzProductCategoryAttributeRelationExample();
            relationExample.createCriteria().andProductCategoryIdEqualTo(id);
            productCategoryAttributeRelationMapper.deleteByExample(relationExample);
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public List<XbzProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzProductCategoryExample example = new XbzProductCategoryExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return productCategoryMapper.selectByExample(example);
    }

    @Override
    public XbzProductCategory getItem(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        XbzProductCategory productCategory = new XbzProductCategory();
        productCategory.setNavStatus(navStatus);
        XbzProductCategoryExample example = new XbzProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        XbzProductCategory productCategory = new XbzProductCategory();
        productCategory.setShowStatus(showStatus);
        XbzProductCategoryExample example = new XbzProductCategoryExample();
        example.createCriteria().andIdIn(ids);
        return productCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public List<XbzProductCategoryWithChildrenItem> listWithChildren() {
        return productCategoryDao.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(XbzProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            XbzProductCategory parentCategory = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
