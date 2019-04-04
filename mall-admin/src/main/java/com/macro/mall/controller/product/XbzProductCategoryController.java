package com.macro.mall.controller.product;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.product.XbzProductCategoryParam;
import com.macro.mall.dto.product.XbzProductCategoryWithChildrenItem;
import com.macro.mall.model.XbzProductCategory;
import com.macro.mall.service.productService.XbzProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类模块Controller
 */
@Controller
@RequestMapping("/productCategory")
public class XbzProductCategoryController {
    @Autowired
    private XbzProductCategoryService productCategoryService;

    /**
     * 添加产品分类
     * @param productCategoryParam
     * @param result
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@Validated @RequestBody XbzProductCategoryParam productCategoryParam,
                         BindingResult result) {
        int count = productCategoryService.create(productCategoryParam);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改商品分类
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id,
                         @Validated
                         @RequestBody XbzProductCategoryParam productCategoryParam,
                         BindingResult result) {
        int count = productCategoryService.update(id, productCategoryParam);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 分页查询商品分类
     */
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@PathVariable Long parentId,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return new CommonResult().pageSuccess(productCategoryList);
    }

    /**
     * 根据id获取商品分类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzProductCategory productCategory = productCategoryService.getItem(id);
        return new CommonResult().success(productCategory);
    }

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改导航栏显示状态
     * @param ids
     * @param navStatus
     * @return
     */
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 修改显示状态
     */
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    /**
     * 查询所有一级分类及子分类
     */
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public Object listWithChildren() {
        List<XbzProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return new CommonResult().success(list);
    }
}
