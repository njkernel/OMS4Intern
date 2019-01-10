package cn.com.connext.oms;

import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.service.TbExchangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsApplicationTests {

    @Autowired
    TbExchangeMapper tbExchangeMapper;

    @Autowired
    TbExchangeService tbExchangeService;

    @Test
    public void contextLoads() {
        int a=1901060001;
        int b[]={16011353,16011522};
        int c[]={1,2};
        tbExchangeService.toGenerateExchangeOrderGoods(a,b,c);
    }

}

