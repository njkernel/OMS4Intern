package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.web.Api.OrderAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/test")
    public Map<String, Object> test() throws HttpProcessException {
        try {
            Map<String, Object> allOrder = OrderAPI.getAllOrder();
            System.out.println(allOrder);
            return allOrder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
