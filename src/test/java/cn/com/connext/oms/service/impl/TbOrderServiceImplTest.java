package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbOutputMapper;
import cn.com.connext.oms.service.TbOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbOrderServiceImplTest {

    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOutputMapper tbOutputMapper;

    @Test
    public void cancelOrder() {
       /* List<TbOrder> orderList=new ArrayList<TbOrder>();
        TbOrder order=tbOrderMapper.getOrderById(1901060001);
        TbOrder order2=tbOrderMapper.getOrderById(1901060002);
        orderList.add(order);
        orderList.add(order2);*/
       Integer[] orderIdList={1901060003,1901060004};
       boolean b= tbOrderService.cancelOrder(orderIdList);
        System.out.println(b);
    }

   /* @Test
    public void cancelOrderOfWms() {
        List<TbOutput> outputList=new ArrayList<TbOutput>();
        TbOutput output=tbOutputMapper.getOutputByOrderId(1901060001);
        TbOutput output2=tbOutputMapper.getOutputByOrderId(1901060002);
        outputList.add(output);
        outputList.add(output2);
        boolean a= tbOrderService.cancelOrderOfWms(outputList);
        System.out.println(a);

    }*/
}