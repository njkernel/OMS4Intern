/*
package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbGoodsOrder;
import cn.com.connext.oms.mapper.TbGoodsMapper;
import cn.com.connext.oms.mapper.TbGoodsOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.LinkedList;
import java.util.List;

*/
/**
 * <p>Title: TbAbnormalServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/7 10:58
 *//*

@Service
public class TbAbnormalServiceImpl {
    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsOrderMapper tbGoodsOrderMapper;

    */
/**
     * 订单预检
     * @param orderId
     * @return
     *//*

    public String checkGoods(int orderId){
        List list=new LinkedList();
        Example example=new Example(TbGoodsOrder.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        List<TbGoodsOrder> tbGoodsOrders = tbGoodsOrderMapper.selectByExample(example);
        for (TbGoodsOrder tbGoodsOrder:tbGoodsOrders){

        }
        tbGoodsMapper.
    }
}
*/
