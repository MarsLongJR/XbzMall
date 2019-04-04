package com.macro.mall.service;

import com.macro.mall.dto.brand.XbzBrandParam;

import java.util.List;

/**
 * @ClassName 商品品牌Service
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzBrandService {

    Object listAllBrand();

    int createBrand(XbzBrandParam pmsBrand);

    int updateBrand(Long id, XbzBrandParam pmsBrandParam);

    int deleteBrand(Long id);

    List listBrand(String keyword, Integer pageNum, Integer pageSize);

    int deleteBrand(List<Long> ids);


    int updateShowStatus(List<Long> ids, Integer showStatus);

    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);

    Object getBrand(Long id);
}
