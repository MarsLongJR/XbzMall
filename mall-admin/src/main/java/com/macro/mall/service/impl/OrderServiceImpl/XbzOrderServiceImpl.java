package com.macro.mall.service.impl.OrderServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.order.XbzOrderDao;
import com.macro.mall.dao.order.XbzOrderOperateHistoryDao;
import com.macro.mall.dto.order.XbzMoneyInfoParam;
import com.macro.mall.dto.order.XbzOrderDetail;
import com.macro.mall.dto.order.XbzOrderQueryParam;
import com.macro.mall.dto.order.XbzReceiverInfoParam;
import com.macro.mall.mapper.XbzOrderMapper;
import com.macro.mall.mapper.XbzOrderOperateHistoryMapper;
import com.macro.mall.model.XbzOrder;
import com.macro.mall.model.XbzOrderOperateHistory;
import com.macro.mall.service.OrderService.XbzOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzOrderServiceImpl implements XbzOrderService {
    @Resource
    private XbzOrderMapper orderMapper;

    @Resource
    private XbzOrderDao orderDao;
    @Resource
    private XbzOrderOperateHistoryDao orderOperateHistoryDao;
    @Resource
    private XbzOrderOperateHistoryMapper orderOperateHistoryMapper;

    @Override
    public List<XbzOrder> list(XbzOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderDao.getList(queryParam);
    }

    @Override
    public XbzOrderDetail detail(Long id) {
        return orderDao.getDetail(id);
    }

    @Override
    public int updateMoneyInfo(XbzMoneyInfoParam moneyInfoParam) {
        XbzOrder order = new XbzOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        XbzOrderOperateHistory history = new XbzOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }
    @Override
    public int updateNote(Long id, String note, Integer status) {
        XbzOrder order = new XbzOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        XbzOrderOperateHistory history = new XbzOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateReceiverInfo(XbzReceiverInfoParam receiverInfoParam) {
        XbzOrder order=new XbzOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        XbzOrderOperateHistory history = new XbzOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}
