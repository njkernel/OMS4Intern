package cn.com.connext.oms.service;

import cn.com.connext.oms.OmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbRefundServiceTest {
    @Autowired
    private TbRefundService tbRefundService;

    @Test
    public void getAllRefundIndex() {
        int a=tbRefundService.getAllRefundIndex(1,2).size();
        System.out.println(a);
    }
}