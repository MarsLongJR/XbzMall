package com.macro.mall.service.productService;

import com.macro.mall.dto.product.ProductAttrInfo;
import com.macro.mall.dto.product.XbzProductAttributeParam;
import com.macro.mall.model.XbzProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName 商品属性Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzProductAttributeService {
    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->属性；2->参数
     * @return
     */
    List<XbzProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(XbzProductAttributeParam productAttributeParam);

    /**
     * 修改商品属性
     */
    int update(Long id, XbzProductAttributeParam productAttributeParam);

    /**
     * 获取单个商品属性信息
     */
    XbzProductAttribute getItem(Long id);

    @Transactional
    int delete(List<Long> ids);

    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
