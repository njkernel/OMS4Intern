package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.service.TbExchangeService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Ids;

import java.util.Date;

/** 
* TbExchangeController Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 10, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TbExchangeControllerTest {
    @Autowired
    TbExchangeService tbExchangeService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: showAllReturns(Integer currentPage, Integer pageSize, String returnType) 
* 
*/ 
@Test
public void testShowAllReturns() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toRequest(@RequestParam("orderId")int orderId) 
* 
*/ 
@Test
public void testToRequest() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toGenerateExchangeOrder(@RequestParam("orderId")int orderId, @RequestParam("goodId")int[] goodId, @RequestParam("num")int[] num) 
* 
*/ 
@Test
public void testToGenerateExchangeOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: exchangeDetails(@RequestParam("orderId")int orderId) 
* 
*/ 
@Test
public void testExchangeDetails() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toCancel(@RequestParam("ids")Integer [] ids) 
* 
*/ 
@Test
public void testToCancel() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toAudit(@RequestParam("ids")int [] ids) 
* 
*/ 
@Test
//@Transactional
public void testToAudit() throws Exception {
//TODO: Test goes here...
    int ids[]={1004};
    Date date=new Date();
    int rs=tbExchangeService.AuditTbReturn(ids,"yonyong",date);
    if (rs!=1){
        System.out.println("操作失败！");
    } else System.out.println("1111111111111111111");
    try{
        int rt=tbExchangeService.generateInput(ids);
    }catch (Exception e){
        e.printStackTrace();
    }
}
} 
