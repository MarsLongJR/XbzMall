package com.macro.mall.controller.product;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.product.XbzProductAttributeCategoryItem;
import com.macro.mall.model.XbzProductAttributeCategory;
import com.macro.mall.service.productService.XbzProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName 商品属性分类Controller
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/productAttribute/category")
public class XbzProductAttributeCategoryController {
    @Autowired
    private XbzProductAttributeCategoryService productAttributeCategoryService;

    /**
     * 添加商品属性分类
     * @param name
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改商品属性分类
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 删除单个商品属性分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 获取单个商品属性分类信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return new CommonResult().success(productAttributeCategory);
    }

    /**
     * 分页获取所有商品属性分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<XbzProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize, pageNum);
        return new CommonResult().pageSuccess(productAttributeCategoryList);
    }

    /**
     * 获取所有商品属性分类及其下属性
     * @return
     */
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public Object getListWithAttr() {
        List<XbzProductAttributeCategoryItem> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return new CommonResult().success(productAttributeCategoryResultList);
    }

}
