package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.service.MyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class MyTestImplTest {
@Autowired
private MyTest myTest;

    @Test
    public void a() {
        String b= null;
        try {
            b = myTest.a();
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        System.out.println(b);
    }
}