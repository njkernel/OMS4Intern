package cn.com.connext.oms.commons.utils;

import cn.com.connext.oms.commons.dto.exchange.OMS.GoodDetails;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
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
    public int newOrder(List<GoodDetails> goodDetails, int oldOrderId){
        int orderId=1901060001+tbExchangeMapper.selectCountOfOrder();
        List<TbGoodsOrder> tbGoodsOrders=new ArrayList<>();

        String orderCode="lw"+String.valueOf(orderId);
        String channelCode="qd"+String.valueOf(orderId);
        TbReturn tbReturn=new TbReturn();
        Date date=new Date();
        TbExchangeOrderRelations tbExchangeOrderRelations=new TbExchangeOrderRelations();

        double sum=0;

        //如果订单属于WMS重复发送，则以最新的为准,删掉数据库里已存在的，插入新的数据信息
        boolean status=this.checkOrderIsExchange(oldOrderId);
        if (status){
            orderId=orderId-1;
            try {
                tbExchangeMapper.deleteOrder(orderId);
                tbExchangeMapper.deleteGoodsOrders(orderId);
                tbExchangeMapper.deleteExchangeOrderRelations(oldOrderId);
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }

        for (GoodDetails goodDetails1:goodDetails){
            TbGoodsOrder tbGoodsOrder=new TbGoodsOrder();
            TbGoods tbGoods=new TbGoods();
            try {
                tbGoods=tbExchangeMapper.toSelectGoodByCode(goodDetails1.getGoodsSku());
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
        tbOrder.setOrderState("待预检");
        tbOrder.setDeliveryTime(date);
        tbOrder.setRemark("换货");
        tbOrder.setSumPrice(sum);

        try {
            tbReturn=tbExchangeMapper.selectTbReturnByOrderId(oldOrderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        int exchangeId=tbReturn.getReturnId();
        tbExchangeOrderRelations.setExchangeId(exchangeId);
        tbExchangeOrderRelations.setNewOrderId(orderId);
        tbExchangeOrderRelations.setOldOrderId(oldOrderId);



        try {
            tbExchangeMapper.insertGoodsOrders(tbGoodsOrders);
            tbExchangeMapper.insertOrder(tbOrder);
            tbExchangeMapper.insertExchangeOrderRelations(tbExchangeOrderRelations);
        }catch (Exception e){
            return 0;
        }
        orderId ++;
        return 1;
    }

    /**
     * create by: yonyong
     * description: 删除上一次生成的信息，用于在WMS操作失误情况下，事务回滚，如果有其他变动，及时更新
     * create time: 2019/1/12 19:36
     *
     *  * @Param: goodDetails
      * @Param: oldOrderId
     * @return int
     */
    public int delOrder(List<GoodDetails> goodDetails, int oldOrderId) {
        int orderId = 1901060000 + tbExchangeMapper.selectCountOfOrder();
        List<TbGoodsOrder> tbGoodsOrders = new ArrayList<>();

        try {
            tbExchangeMapper.deleteOrder(orderId);
            tbExchangeMapper.deleteGoodsOrders(orderId);
            tbExchangeMapper.deleteExchangeOrderRelations(oldOrderId);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * create by: yonyong
     * description: 通过查询订单id可判断:1,换货模块:WMS反馈是否正确,2,其他模块:当前订单是否是换货单
     * create time: 2019/1/11 12:20
     *
     *  * @Param: orderId
     * @return boolean
     */
    public boolean checkOrderIsExchange(int orderId){
        try {
            TbExchangeOrderRelations tbExchangeOrderRelations=
                    tbExchangeMapper.selectExchangeOrderRelationsByOldOrderId(orderId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
