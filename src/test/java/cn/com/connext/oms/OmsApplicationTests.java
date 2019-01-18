package cn.com.connext.oms;

import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbRefundMapper;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbReturnService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsApplicationTests {

    @Autowired
    TbExchangeMapper tbExchangeMapper;

    @Autowired
    TbExchangeService tbExchangeService;

    @Autowired
    TbReturnService tbReturnService;

    @Autowired
    TbRefundMapper tbRefundMapper;

    @Test
    public void contextLoads() {
        int a=1901060001;
        int b[]={16011353,16011522};
        int c[]={1,2};
        tbExchangeService.toGenerateExchangeOrderGoods(a,b,c);
    }

    @Test
    public void test(){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string=format.format(date);
        System.out.println(string);
    }


    @Test
    public void test1(){
        TbReturn tbReturn = tbReturnService.getTbReturnById(5);
        List<TbRefund> refundList = new ArrayList<>();
        int orderId = tbReturn.getOrderId();
        TbRefund tbRefund =new TbRefund();
        String refundCode = CodeGenerateUtils.creatUUID();
        double refundPrice = tbReturn.getReturnPrice();
        String refundState = "待退款";
        int returnId = tbReturn.getReturnId();
        String modifiedUser = "oms";
        Date time = new Date();
        tbRefund.setRefundCode(refundCode);
        tbRefund.setRefundPrice(refundPrice);
        tbRefund.setRefundState(refundState);
        tbRefund.setReturnId(returnId+"");
        tbRefund.setCreatetd(time);
        tbRefund.setModifiedUser(modifiedUser);
        tbRefund.setUpdated(time);
        tbRefund.setOrderId(orderId);
        refundList.add(tbRefund);
        tbRefundMapper.batchAddRefund(refundList);

    }
}

