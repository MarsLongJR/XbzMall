package com.macro.mall.service.impl.OrderServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.order.XbzOrderReturnApplyDao;
import com.macro.mall.dto.order.XbzOrderReturnApplyResult;
import com.macro.mall.dto.order.XbzReturnApplyQueryParam;
import com.macro.mall.dto.order.XbzUpdateStatusParam;
import com.macro.mall.mapper.XbzOrderReturnApplyMapper;
import com.macro.mall.model.XbzOrderReturnApply;
import com.macro.mall.model.XbzOrderReturnApplyExample;
import com.macro.mall.service.OrderService.XbzOrderReturnApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 订单退货管理Service
 * Created by macro on 2018/10/18.
 */
@Service
public class XbzOrderReturnApplyServiceImpl implements XbzOrderReturnApplyService {
    @Resource
    private XbzOrderReturnApplyDao returnApplyDao;
    @Resource
    private XbzOrderReturnApplyMapper returnApplyMapper;
    @Override
    public List<XbzOrderReturnApply> list(XbzReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        return returnApplyDao.getList(queryParam);
    }

    @Override
    public int delete(List<Long> ids) {
        XbzOrderReturnApplyExample example = new XbzOrderReturnApplyExample();
        example.createCriteria().andIdIn(ids).andStatusEqualTo(3);
        return returnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, XbzUpdateStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        XbzOrderReturnApply returnApply = new XbzOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        }else{
            return 0;
        }
        return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public XbzOrderReturnApplyResult getItem(Long id) {
        return returnApplyDao.getDetail(id);
    }
}
