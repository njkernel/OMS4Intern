package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.service.TbRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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

    @Autowired
    private TbRefundService tbRefundService;

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


    /** 
    * @Description: 查看退款单页面
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2019/1/10 
    */
    @RequestMapping("/refund")
    public String refundPage(Model model){
        Map<String,Object> map=tbRefundService.getAllRefundIndex(1,4);
        model.addAttribute("map",map);
        return "pages/details/orders/refund-list";
    }

}
