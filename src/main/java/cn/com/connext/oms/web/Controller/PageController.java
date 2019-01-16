package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbGoodsOrder;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.service.TbGoodsListService;
import cn.com.connext.oms.service.TbGoodsOrderService;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.service.TbRefundService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private TbOrderService tbOrderService;

    @Autowired
    private TbGoodsOrderService tbGoodsOrderService;

    /**
    * @Author: caps
    * @Description:异常订单列表详情页面
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/12 16:01
    */
    @RequestMapping("/abnormalModel")
    public String abnormalDetail(){
        return "pages/specific/abnormal-order";
    }

    /*@RequiresPermissions({"checked"})//没有的话 AuthorizationException*/
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
    @RequestMapping("/getRefund")
    public String refundPage(Model model, HttpServletRequest request){
        Map<String,Object> map=tbRefundService.getAllRefundIndex(1,4);
        model.addAttribute("map",map);
        HttpSession session=request.getSession();
        session.setAttribute("basic","我的");
        return "pages/details/orders/refund-list";
    }
    /**
     * @Description: 登录页面
     * @Param: []
     * @return: java.lang.String
     * @Author: Lili Chen
     * @Date: 2019/1/10
     */
    @GetMapping({"/","/login"})
    public String login(){
        return "pages/login/loadingOrder";
    }

    
    /** 
    * @Description: 退款单的分页 
    * @Param: [page, model, size, request] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2019/1/14 
    */
    @RequestMapping("/getRefundIndex")
    public String getAllRefundIndex(Integer page,Model model,Integer size,HttpServletRequest request){
        HttpSession session=request.getSession();
        String basic=session.getAttribute("basic").toString();
        String mySelect="";
        if(session.getAttribute("basic2")!=null){
          mySelect=session.getAttribute("basic2").toString();
        }

        Map<String,Object> map=new HashMap<>();
        if(basic.equals("orderCode")){
            map=tbRefundService.getListRefundByOrderCode(mySelect,page,4);
        }else if(basic.equals("refundState")){
           map=tbRefundService.getListRefundByState(mySelect,page,4);

        }else{
            map=tbRefundService.getAllRefundIndex(page,size);
        }
        model.addAttribute("map",map);
        return "pages/details/orders/refund-list";
    }

    
    /** 
    * @Description: 根据条件查看退款单 
    * @Param: [model, select, mySelect, page, request] 
    * @return: java.lang.String 
    * @Author: Lili Chen 
    * @Date: 2019/1/14 
    */
    @RequestMapping("/getSearchRefund")
    public String getSearchRefund(Model model,String select,String mySelect,Integer page,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        HttpSession session=request.getSession();
        session.setAttribute("basic2",mySelect);
        if(select.equals("orderCode")){
          map=tbRefundService.getListRefundByOrderCode(mySelect,page,4);
          session.setAttribute("basic","orderCode");
        }else if(select.equals("refundState")){
            session.setAttribute("basic","refundState");
            map=tbRefundService.getListRefundByState(mySelect,page,4);
        }

        model.addAttribute("map",map);
        return "pages/details/orders/refund-list";
    }

    @RequestMapping("/refundDetail")
    public String refundDetail(Integer refundId,Model model){
        TbRefund refund=tbRefundService.getRefundById(refundId);
        List<TbGoodsOrder> tbGoodsOrderList=tbGoodsOrderService.getListGoodsOrderById(refund.getOrderId());
        model.addAttribute("GoodsOrderList",tbGoodsOrderList);
        model.addAttribute("refund",refund);
        return "pages/specific/refund";
    }

    /**
     * create by: yonyong
     * description: 进入退换货界面
     * create time: 2019/1/14 17:14
     *
     *  * @Param:
     * @return java.lang.String
     */
    @RequestMapping("/index/return")
    public String returnPage(){
        return "pages/details/orders/sales-return-list";
    }


    @RequestMapping("/orderDetail")
    public String orderDetail(){
        return "pages/specific/order-detail";
    }

    @RequestMapping("/orderList")
    public String orderList(){
        return "pages/details/orders/order-list";
    }
}
