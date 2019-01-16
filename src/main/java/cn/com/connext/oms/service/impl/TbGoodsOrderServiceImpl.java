package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbGoodsOrder;
import cn.com.connext.oms.mapper.TbGoodsOrderMapper;
import cn.com.connext.oms.service.TbGoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: OMS4Intern
 * @description: 订单商品关系表
 * @author: Lili.Chen
 * @create: 2019-01-15 19:27
 **/

@Service
public class TbGoodsOrderServiceImpl implements TbGoodsOrderService {

    @Autowired
    private TbGoodsOrderMapper tbGoodsOrderMapper;

    /**
    * @Description: 订单商品列表
    * @Param: [orderId]
    * @return: java.util.List<cn.com.connext.oms.entity.TbGoodsOrder>
    * @Author: Lili Chen
    * @Date: 2019/1/15
    */
    @Override
    public List<TbGoodsOrder> getListGoodsOrderById(Integer orderId) {
        return tbGoodsOrderMapper.getListGoodsOrderById(orderId) ;
    }
}
