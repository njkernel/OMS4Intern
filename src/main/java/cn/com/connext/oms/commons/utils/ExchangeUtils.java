package cn.com.connext.oms.commons.utils;

import cn.com.connext.oms.commons.dto.exchange.OMS.GoodDetails;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/10
 * @time: 17:51
 * @describe:
 **/
public class ExchangeUtils {

    @Autowired
    TbExchangeMapper tbExchangeMapper;

    @Autowired
    TbOrderMapper tbOrderMapper;

    /**
     * create by: yonyong
     * description: 入库回馈操作
     * create time: 2019/1/11 11:42
     *
     *  * @Param: goodDetails
     * @return java.util.List<cn.com.connext.oms.entity.TbGoodsOrder>
     */
    public int newOrder(List<GoodDetails> goodDetails,int oldOrderId){
        List<TbGoodsOrder> tbGoodsOrders=new ArrayList<>();
        int orderId=CodeGenerateUtils.creatId();
        String baseCode=CodeGenerateUtils.creatUUID();
        String orderCode="lw"+baseCode;
        String channelCode="qd"+baseCode;
        Date date=new Date();
        TbExchangeOrderRelations tbExchangeOrderRelations=new TbExchangeOrderRelations();

        double sum=0;
        for (GoodDetails goodDetails1:goodDetails){
            TbGoodsOrder tbGoodsOrder=new TbGoodsOrder();
            TbGoods tbGoods=new TbGoods();
            try {
                tbGoods=tbExchangeMapper.toSelectGoodByCode(Integer.valueOf(goodDetails1.getGoodsSku()));
            }catch (Exception e){
                return 0;
            }
            tbGoodsOrder.setGoodsId(tbGoods.getGoodsId());
            tbGoodsOrder.setOrderId(orderId);
            tbGoodsOrder.setNum(goodDetails1.getGoodsNum());
            tbGoodsOrder.setTotalPrice(goodDetails1.getGoodsNum()*tbGoods.getGoodsPrice());

            sum=sum+goodDetails1.getGoodsNum()*tbGoods.getGoodsPrice();
            tbGoodsOrders.add(tbGoodsOrder);
        }
        TbOrder tbOrder=new TbOrder();
        try {
            tbOrder=tbOrderMapper.getOrderById(oldOrderId);
        }catch (Exception e){
            return 0;
        }
        tbOrder.setOrderId(orderId);
        tbOrder.setOrderCode(orderCode);
        tbOrder.setChannelCode(channelCode);
        tbOrder.setBasicState("待预检");
        tbOrder.setDeliveryTime(date);
        tbOrder.setRemark("换货");
        tbOrder.setSumPrice(sum);

        TbReturn tbReturn=tbExchangeMapper.selectTbReturnByOrderId(orderId);
        int exchangeId=tbReturn.getReturnId();
        tbExchangeOrderRelations.setExchangeId(exchangeId);
        tbExchangeOrderRelations.setNewOrderId(orderId);
        tbExchangeOrderRelations.setNewOrderId(orderId);

        try {
            tbExchangeMapper.insertGoodsOrders(tbGoodsOrders);
            tbExchangeMapper.insertOrder(tbOrder);
            tbExchangeMapper.insertExchangeOrderRelations(tbExchangeOrderRelations);
        }catch (Exception e){
            return 0;
        }

        return 1;
    }

    /**
     * create by: yonyong
     * description: 判断订单是否是换货单
     * create time: 2019/1/11 12:20
     *
     *  * @Param: orderId
     * @return boolean
     */
    public boolean checkOrderIsExchange(int orderId){
        TbExchangeOrderRelations tbExchangeOrderRelations=
                tbExchangeMapper.selectExchangeOrderRelationsByOldOrderId(orderId);
        if (tbExchangeOrderRelations==null){
            return false;
        }
        else {
            return true;
        }
    }
}
