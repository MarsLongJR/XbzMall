package com.macro.mall.service.impl.productServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.Member.XbzMemberPriceDao;
import com.macro.mall.dao.product.*;
import com.macro.mall.dto.product.XbzProductParam;
import com.macro.mall.dto.product.XbzProductQueryParam;
import com.macro.mall.dto.product.XbzProductResult;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.service.productService.XbzProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 商品管理Service实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzProductServiceImpl implements XbzProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XbzProductServiceImpl.class);
    @Resource
    private XbzProductMapper productMapper;
    @Resource
    private XbzMemberPriceDao memberPriceDao;
    @Resource
    private XbzMemberPriceMapper memberPriceMapper;
    @Resource
    //产品阶梯表，只针对同一个商品
    private XbzProductLadderDao productLadderDao;
    @Resource
    private XbzProductLadderMapper productLadderMapper;
    @Resource
    private XbzProductFullReductionDao productFullReductionDao;
    @Resource
    private XbzProductFullReductionMapper productFullReductionMapper;
    @Resource
    private XbzSkuStockDao skuStockDao;
    @Resource
    private XbzSkuStockMapper skuStockMapper;
    @Resource
    private XbzProductAttributeValueDao productAttributeValueDao;
    @Resource
    private XbzProductAttributeValueMapper productAttributeValueMapper;
    @Resource
    private XbzProductDao productDao;
    @Resource
    private XbzProductVertifyRecordDao productVertifyRecordDao;

    @Override
    public int create(XbzProductParam productParam) {
        int count;
        //创建商品
        XbzProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(),productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        count = 1;
        return count;
    }

    private void handleSkuStockCode(List<XbzSkuStock> skuStockList, Long productId) {
        if(CollectionUtils.isEmpty(skuStockList))return;
        for(int i=0;i<skuStockList.size();i++){
            XbzSkuStock skuStock = skuStockList.get(i);
            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    @Override
    public XbzProductResult getUpdateInfo(Long id) {
        return  productDao.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, XbzProductParam productParam) {
        int count;
        //更新商品信息
        XbzProduct product = productParam;
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //会员价格
        XbzMemberPriceExample XbzMemberPriceExample = new XbzMemberPriceExample();
        XbzMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(XbzMemberPriceExample);
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
        //阶梯价格
        XbzProductLadderExample ladderExample = new XbzProductLadderExample();
        ladderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格
        XbzProductFullReductionExample fullReductionExample = new XbzProductFullReductionExample();
        fullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(fullReductionExample);
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        XbzSkuStockExample skuStockExample = new XbzSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        skuStockMapper.deleteByExample(skuStockExample);
        handleSkuStockCode(productParam.getSkuStockList(),id);
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), id);
        //修改商品参数,添加自定义商品规格
        XbzProductAttributeValueExample productAttributeValueExample = new XbzProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        count = 1;
        return count;
    }

    @Override
    public List<XbzProduct> list(XbzProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzProductExample productExample = new XbzProductExample();
        XbzProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublishStatus() != null) {
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }
        return productMapper.selectByExample(productExample);
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        XbzProduct product = new XbzProduct();
        product.setVerifyStatus(verifyStatus);
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andIdIn(ids);
        List<XbzProductVertifyRecord> list = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product, example);
        //修改完审核状态后插入审核记录
        for (Long id : ids) {
            XbzProductVertifyRecord record = new XbzProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordDao.insertList(list);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        XbzProduct record = new XbzProduct();
        record.setPublishStatus(publishStatus);
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        XbzProduct record = new XbzProduct();
        record.setRecommandStatus(recommendStatus);
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        XbzProduct record = new XbzProduct();
        record.setNewStatus(newStatus);
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        XbzProduct record = new XbzProduct();
        record.setDeleteStatus(deleteStatus);
        XbzProductExample example = new XbzProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<XbzProduct> list(String keyword) {
        XbzProductExample productExample = new XbzProductExample();
        XbzProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if(!StringUtils.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "%");
        }
        return productMapper.selectByExample(productExample);
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
