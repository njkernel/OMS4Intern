package cn.com.connext.oms.mapper;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbGoodsOrder;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/18
 * @time: 14:02
 * @describe: ******生成数据工具类，不要删掉这个test类******
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbExchangeMapperTest {

    @Autowired
    TbExchangeMapper tbExchangeMapper;

    @Test
    public void generateOrders(){
        //插入订单时订单起始id
        int orderId=1901060132;
        //插入receiver表时的起始receiverId
        int insertReceiverId=1121;
        //插入receiver表时的起始接受人订单id
        int receiverOrderId=1234567891;
        Date date = new Date();
        for (int t=0;t<5;t++){
            for (int i=0;i<40;i++){
                double sum=0;
                int goodsId[]={16012019,16012020,16012021,16012022,16012023,16012024,16012025,16012026,16012027,16012028,
                        16012029,16012030,16012031,16012032,16012033,16012034,16012035,16012036,16012037,16012038};
                double goodPrice[]={798,2299,1998,1399,1299.01,1549,1549,1599,2599,2399,1899,849,1499,1899,3399,549,3199,1399,1799.99,799};
                int nums[]={1,2,3,4,5};
                for (int j=0;j<2;j++){
                    List<TbGoodsOrder> tbGoodss=new ArrayList<>();
                    int index=(int)(Math.random()*goodsId.length);
                    int goodId = goodsId[index];
                    int index1=(int)(Math.random()*nums.length);
                    int goodNum=nums[index1];
                    TbGoodsOrder tbGoods = new TbGoodsOrder();
                    tbGoods.setOrderId(orderId);
                    tbGoods.setGoodsId(goodId);
                    tbGoods.setNum(goodNum);
                    tbGoods.setTotalPrice((double) (goodPrice[index]*goodNum));
                    tbGoodss.add(tbGoods);
                    sum+=(double) (goodPrice[index]*goodNum);
                    tbExchangeMapper.insertGoodsOrders(tbGoodss);

                }
                TbOrder tbOrder=new TbOrder();
                tbOrder.setOrderId(orderId);
                tbOrder.setOrderCode("LW"+orderId);
                tbOrder.setChannelCode("QD"+orderId);
                tbOrder.setOrderState("待预检");
                tbOrder.setOrderSource("线上订单");
                tbOrder.setPurchaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                tbOrder.setBasicState("无");
                tbOrder.setModifiedUser("yonyong");
                tbOrder.setUpdated(date);
                tbOrder.setPaymentState("已付款");
                tbOrder.setPaymentWay("线上支付");
                tbOrder.setPaymentTime(date);
                tbOrder.setDeliveryWarehouse("南京仓");
                tbOrder.setDeliveryCompany("");
                tbOrder.setDeliveryCode("");
                tbOrder.setDeliveryTime(null);
                tbOrder.setReceiverId(insertReceiverId);
                tbOrder.setSumPrice(sum);
                tbOrder.setRemark("无");
                tbOrder.setCustomerId(insertReceiverId);
                tbExchangeMapper.insertOrder(tbOrder);
                TbReceiver tbReceiver=tbExchangeMapper.selectTbReceiverByOrderId(receiverOrderId);
                if(null==tbReceiver){
                    System.out.println("----------------receiverOrderId:"+receiverOrderId);
                }
                tbReceiver.setReceiverId(insertReceiverId);
                tbReceiver.setUpdated(date);
                tbReceiver.setCreated(date);
                tbReceiver.setOrderId(orderId);
                tbExchangeMapper.insertReciver(tbReceiver);
                orderId++;
                insertReceiverId++;
            }
            receiverOrderId++;
        }

    }
    @Test
    public void updateReceiverId(){
        int receiverId=1013;
        int orderId=1901060020;
        for (int i=0;i<100;i++){
            tbExchangeMapper.updateReceiver(receiverId,orderId);
            receiverId++;
            orderId++;
        }
    }

    /**
     * create by: yonyong
     * description: 修复订单状态中单号过长无法正常传输的bug，批量更新订单表中的渠道单号和订单号
     * create time: 2019/1/21 12:19
     *
     *  * @Param:
     * @return void
     */
    @Test
    public void updateOrders(){
        long orderId=1901060020;
        for (int i=0;i<100;i++){
            String orderCode="LW"+orderId;
            String channelCode="QD"+orderId;

            tbExchangeMapper.updateOrder(orderId,channelCode,orderCode);
            orderId++;
        }
        TbGoods tbGoods=tbExchangeMapper.toSelectGoodById(16011701);
        System.out.println(tbGoods.getGoodsId());
    }



    @Test
    public void sum(){

        Double a=5399.97;
        Double b=3996.0;

        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount=totalAmount.add(new BigDecimal(a.toString()));
        totalAmount=totalAmount.add(new BigDecimal(b.toString()));
        System.out.println(	totalAmount);
    }
}
