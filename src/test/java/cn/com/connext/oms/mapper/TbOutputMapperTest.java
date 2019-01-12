package cn.com.connext.oms.mapper;

import cn.com.connext.oms.OmsApplication;
import cn.com.connext.oms.entity.TbOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OmsApplication.class)
public class TbOutputMapperTest {

    @Autowired
    private TbOutputMapper tbOutputMapper;
    @Test
    public void getOutputByOrderId() {
       TbOutput tbOutput= tbOutputMapper.getOutputByOrderId(1901060001);
    }
}