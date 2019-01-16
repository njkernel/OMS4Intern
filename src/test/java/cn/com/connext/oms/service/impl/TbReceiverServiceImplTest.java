package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.service.TbReceiverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbReceiverServiceImplTest {
    @Autowired
    private TbReceiverService tbReceiverService;

    @Test
    public void updateReceiver() {
        TbReceiver tbReceiver=new TbReceiver();
      tbReceiver.setReceiverState("江西省");
      tbReceiver.setReceiverCity("九江市");
      tbReceiver.setReceiverDistrict("浔阳区");
      tbReceiver.setReceiverAddress("九瑞大道碧桂园");
      tbReceiver.setReceiverMobile("18303938394");
     /* tbReceiver.setReceiverPhone("0792-4647474");*/
      tbReceiver.setReceiverName("张四");
     /* tbReceiver.setReceiverZip("332200");*/
     /* tbReceiver.setUpdated(new Date());*/
      tbReceiver.setReceiverId(1005);
        boolean a=tbReceiverService.updateReceiver(tbReceiver);
        System.out.println(a);
    }
}