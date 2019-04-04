package com.macro.mall.controller.product;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.product.ProductAttrInfo;
import com.macro.mall.dto.product.XbzProductAttributeParam;
import com.macro.mall.model.XbzProductAttribute;
import com.macro.mall.service.productService.XbzProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@RequestMapping("/productAttribute")
public class XbzProductAttributeController {
    @Autowired
    private XbzProductAttributeService productAttributeService;

    /**
     * 根据分类查询属性列表或参数列表
     */
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@PathVariable Long cid,
                          @RequestParam(value = "type") Integer type,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return new CommonResult().pageSuccess(productAttributeList);
    }

    /**
     * 添加商品属性信息
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody XbzProductAttributeParam productAttributeParam, BindingResult bindingResult) {
        int count = productAttributeService.create(productAttributeParam);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改商品属性信息
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id,@RequestBody XbzProductAttributeParam productAttributeParam,BindingResult bindingResult){
        int count = productAttributeService.update(id,productAttributeParam);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 查询单个商品属性
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id){
        XbzProductAttribute productAttribute = productAttributeService.getItem(id);
        return new CommonResult().success(productAttribute);
    }

    /**
     * 批量删除商品属性
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids){
        int count = productAttributeService.delete(ids);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 根据商品分类的id获取商品属性及属性分类
     */
    @RequestMapping(value = "/attrInfo/{productCategoryId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getAttrInfo(@PathVariable Long productCategoryId){
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return new CommonResult().success(productAttrInfoList);
    }
}
