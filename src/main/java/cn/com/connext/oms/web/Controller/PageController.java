package cn.com.connext.oms.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: PageController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/8 15:23
 */
@Controller
public class PageController {

    /**
    * @Author: caps
    * @Description:异常订单列表
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/8 15:37
    */
    @RequestMapping("/abnormal")
    public String abnormal(){
        return "pages/details/orders/error-order-list";
    }
    /**
    * @Author: caps
    * @Description:首页
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/9 9:41
    */
    @RequestMapping("/index")
    public String indexPage(){
        return "pages/index/index";
    }

}
