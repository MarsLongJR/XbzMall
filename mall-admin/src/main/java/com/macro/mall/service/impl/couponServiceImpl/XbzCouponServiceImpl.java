package com.macro.mall.service.impl.couponServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.couponDao.XbzCouponDao;
import com.macro.mall.dao.couponDao.XbzCouponProductCategoryRelationDao;
import com.macro.mall.dao.couponDao.XbzCouponProductRelationDao;
import com.macro.mall.dto.coupon.XbzCouponParam;
import com.macro.mall.mapper.XbzCouponMapper;
import com.macro.mall.mapper.XbzCouponProductCategoryRelationMapper;
import com.macro.mall.mapper.XbzCouponProductRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.couponService.XbzCouponService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName 优惠券管理Service实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzCouponServiceImpl implements XbzCouponService {
    @Resource
    private XbzCouponMapper couponMapper;
    @Resource
    private XbzCouponProductRelationMapper productRelationMapper;
    @Resource
    private XbzCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Resource
    private XbzCouponProductRelationDao productRelationDao;
    @Resource
    private XbzCouponProductCategoryRelationDao productCategoryRelationDao;
    @Resource
    private XbzCouponDao couponDao;
    @Override
    public int create(XbzCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        int count = couponMapper.insert(couponParam);
        //插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(XbzCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (XbzCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteByPrimaryKey(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    private void deleteProductCategoryRelation(Long id) {
        XbzCouponProductCategoryRelationExample productCategoryRelationExample = new XbzCouponProductCategoryRelationExample();
        productCategoryRelationExample.createCriteria().andCouponIdEqualTo(id);
        productCategoryRelationMapper.deleteByExample(productCategoryRelationExample);
    }

    private void deleteProductRelation(Long id) {
        XbzCouponProductRelationExample productRelationExample = new XbzCouponProductRelationExample();
        productRelationExample.createCriteria().andCouponIdEqualTo(id);
        productRelationMapper.deleteByExample(productRelationExample);
    }

    @Override
    public int update(Long id, XbzCouponParam couponParam) {
        couponParam.setId(id);
        int count =couponMapper.updateByPrimaryKey(couponParam);
        //删除后插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(XbzCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (XbzCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(id);
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelationList());
        }
        return count;
    }

    @Override
    public List<XbzCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        XbzCouponExample example = new XbzCouponExample();
        XbzCouponExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if(type!=null){
            criteria.andTypeEqualTo(type);
        }
        PageHelper.startPage(pageNum,pageSize);
        return couponMapper.selectByExample(example);
    }

    @Override
    public XbzCouponParam getItem(Long id) {
        return couponDao.getItem(id);
    }
}
