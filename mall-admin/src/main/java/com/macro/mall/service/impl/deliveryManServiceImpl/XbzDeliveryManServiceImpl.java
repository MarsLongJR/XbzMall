package com.macro.mall.service.impl.deliveryManServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.deliverymanDao.XbzDeliveryVertifyRecordDao;
import com.macro.mall.dao.deliverymanDao.XbzDeliverymanDao;
import com.macro.mall.dto.deliveryman.XbzDeliveryManDetail;
import com.macro.mall.dto.deliveryman.XbzDeliveryManQueryParam;
import com.macro.mall.mapper.XbzDeliverymanMapper;
import com.macro.mall.model.XbzDeliveryVertifyRecord;
import com.macro.mall.model.XbzDeliveryman;
import com.macro.mall.model.XbzDeliverymanExample;
import com.macro.mall.model.XbzProductExample;
import com.macro.mall.service.deliveryManService.XbzDeliveryManService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 配送员Service的实现类
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzDeliveryManServiceImpl implements XbzDeliveryManService {
    @Resource
    private XbzDeliverymanMapper deliverymanMapper;

    @Resource
    private XbzDeliverymanDao deliverymanDao;

    @Resource
    private XbzDeliveryVertifyRecordDao deliveryVertifyRecordDao;
    /**
     * 查找所有的配送员
     */
    @Override
    public List<XbzDeliveryman> list(XbzDeliveryManQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzDeliverymanExample deliverymanExample=new XbzDeliverymanExample();
        XbzDeliverymanExample.Criteria criteria =deliverymanExample.createCriteria();
        criteria.andStatusEqualTo(1);
        if(queryParam.getVerifyStatus()!=null){
            criteria.andVerifyStatusEqualTo(queryParam.getStatus());
        }
        if(queryParam.getMachineId()!=null){
            criteria.andMachineIdEqualTo(queryParam.getMachineId());
        }
        if(!StringUtils.isEmpty(queryParam.getAdressName())){
            criteria.andAdressNameEqualTo(queryParam.getAdressName());
        }
        if (!StringUtils.isEmpty(queryParam.getUsername())) {
            criteria.andUsernameEqualTo(queryParam.getUsername());
        }
        if (!StringUtils.isEmpty(queryParam.getPhone())) {
            criteria.andPhoneEqualTo(queryParam.getPhone());
        }
        return deliverymanMapper.selectByExample(deliverymanExample);
    }
    /**
     * 根据id查找配送员
     */
    @Override
    public XbzDeliveryManDetail detail(Long id) {
        return deliverymanDao.detail(id);
    }

    /**
     * 批量审核配送员
     */
    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus,String detail ) {
        XbzDeliveryman deliveryman = new XbzDeliveryman();
        deliveryman.setVerifyStatus(verifyStatus);
        XbzDeliverymanExample example=new XbzDeliverymanExample();
        example.createCriteria().andIdIn(ids);
        List<XbzDeliveryVertifyRecord> list=new ArrayList<>();
        int count=deliverymanMapper.updateByExampleSelective(deliveryman,example);
        //修改完审核状态后插入审核记录
        for (Long id : ids) {
            XbzDeliveryVertifyRecord record=new XbzDeliveryVertifyRecord();
            record.setDeliverymanId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }

        deliveryVertifyRecordDao.insertList(list);
        return count;
    }

    /**
     * 修改配送员配送区域
     * @param ids
     * @param adressName
     * @return
     */
    @Override
    public int updateAdressName(List<Long> ids, String adressName) {
        XbzDeliveryman record = new XbzDeliveryman();
        record.setAdressName(adressName);
        XbzDeliverymanExample example=new XbzDeliverymanExample();
        example.createCriteria().andIdIn(ids);
        return deliverymanMapper.updateByExampleSelective(record, example);
    }

    /**
     * 批量更改配送员账号的状态
     */
    @Override
    public int changeStatus(List<Long> ids, Integer status) {
        XbzDeliveryman record = new XbzDeliveryman();
        record.setStatus(status);
        XbzDeliverymanExample example=new XbzDeliverymanExample();
        example.createCriteria().andIdIn(ids);
        return deliverymanMapper.updateByExampleSelective(record, example);
    }
}
