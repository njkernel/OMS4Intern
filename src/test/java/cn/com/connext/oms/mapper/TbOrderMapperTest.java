package cn.com.connext.oms.mapper;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.entity.TbOrder;
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
public class TbOrderMapperTest {

    @Autowired
    private TbOrderMapper tbOrderMapper;


  /*  @Test
    public void getOrderById() {
        System.out.println(tbOrderMapper.getOrderById(1901060001));
    }
*/
    @Test
    public void updateOrderListStatue() {
        List<TbOrder> orderList=new ArrayList<TbOrder>();
        TbOrder order=tbOrderMapper.getOrderById(1901060001);
        TbOrder order2=tbOrderMapper.getOrderById(1901060002);
        order.setOrderState("待预检");
        order.setModifiedUser("cll");
        order2.setOrderState("待预检");
        order2.setModifiedUser("cll");
        orderList.add(order);
        orderList.add(order2);
        tbOrderMapper.updateOrderListStatue(orderList);
    }


}