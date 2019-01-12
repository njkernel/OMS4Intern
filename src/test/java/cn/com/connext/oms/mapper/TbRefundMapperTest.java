package cn.com.connext.oms.mapper;

import cn.com.connext.oms.OmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbRefundMapperTest {

    @Autowired private TbRefundMapper tbRefundMapper;

    @Test
    public void getAllRefundIndex() {
        Map map=new HashMap<>();//存查看退款单分页的参数
        map.put("beginIndex",0);
        map.put("size",2);
        int a=tbRefundMapper.getAllRefundIndex(map).size();
        System.out.println(a);
    }

    @Test
    public void getAllRefund() {
        int a=tbRefundMapper.getAllRefund().size();
        System.out.println(a);
    }
}