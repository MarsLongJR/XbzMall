package com.macro.mall.controller.product;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.XbzSkuStock;
import com.macro.mall.service.productService.XbzSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sku库存Controller
 * Created by macro on 2018/4/27.
 */
@Controller
@RequestMapping("/sku")
public class XbzSkuStockController {
    @Autowired
    private XbzSkuStockService skuStockService;

    /**
     * 根据商品编号及编号模糊搜索sku库存
     */
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<XbzSkuStock> skuStockList = skuStockService.getList(pid, keyword);
        return new CommonResult().success(skuStockList);
    }

    /**
     * 批量更新库存信息
     */
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long pid,@RequestBody List<XbzSkuStock> skuStockList){
        int count = skuStockService.update(pid,skuStockList);
        if(count>0){
            return new CommonResult().success(count);
        }else{
            return new CommonResult().failed();
        }
    }
}
