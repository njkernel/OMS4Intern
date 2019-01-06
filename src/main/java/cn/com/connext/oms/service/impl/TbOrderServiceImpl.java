package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: TbOrderService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/6 10:15
 */
@Transactional(readOnly = true)
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;
    /**
    * @Author: caps
    * @Description: 获取所有订单
    * @Param: []
    * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
    * @Create: 2019/1/6 10:16
    */
    @Override
    public List<TbOrder> getAllOrder() {
        return tbOrderMapper.getAllOrder();
    }
}
