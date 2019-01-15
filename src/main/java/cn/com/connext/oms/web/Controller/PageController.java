package cn.com.connext.oms.web.Controller;


import cn.com.connext.oms.service.TbAbnormalService;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.service.TbExchangeService;


import cn.com.connext.oms.service.TbRefundService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    * @Description:异常订单列表详情页面
    * @Param: []
    * @Return: java.lang.String
    * @Create: 2019/1/12 16:01
    */
    @RequestMapping("/abnormalModel")
    public String abnormalDetail(){
        return "pages/specific/abnormal-order";
    }

    @Autowired
    private TbAbnormalService tbAbnormalService;

    @Autowired
    private TbExchangeService tbExchangeService;



    /*@RequiresPermissions({"checked"})//没有的话 AuthorizationException*/
    @GetMapping("/abnormalDetail")
    @ApiOperation(value = "异常订单详情接口")
    public String abnormalDetail(int abnormalId,Model model){
        try {
            Map<String, Object> map = tbAbnormalService.abnormalDetail(abnormalId);
            model.addAttribute("map",map);
            return "pages/specific/abnormal-order";
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping("/abnormal")
    public String abnormal() {
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
    public String indexPage() {
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
    public String refundPage(Model model, HttpServletRequest request) {
        Map<String, Object> map = tbRefundService.getAllRefundIndex(1, 4);
        model.addAttribute("map", map);
        HttpSession session = request.getSession();
        session.setAttribute("basic", "我的");

        return "pages/details/orders/refund-list";
    }

    /**
     * @Description: 登录页面
     * @Param: []
     * @return: java.lang.String
     * @Author: Lili Chen
     * @Date: 2019/1/10
     */
    @GetMapping({"/", "/login"})
    public String login() {
        return "pages/login/loadingOrder";
    }

    /**
     * 入库单页面
     * @return
     */
    @GetMapping({"/tbInput"})
    public String toInput(){
        return "pages/details/orders/warehouse-in-list";
    }

    /**
     * 入库单详情页
     */
    @GetMapping({"/inputDetails"})
    public String inputDetails(@RequestParam("orderId") int orderId,Model model){
        try {
            System.out.println(orderId);
            ReturnDetails returnDetails=tbExchangeService.selectReturnDetailsByOrderId(orderId);
            model.addAttribute("returnDetails",returnDetails);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pages/specific/addstock";
    }



    /**
     * @Description: 退款单的分页
     * @Param: [page, model, size, request]
     * @return: java.lang.String
     * @Author: Lili Chen
     * @Date: 2019/1/14
     */
    @RequestMapping("/getRefundIndex")
    public String getAllRefundIndex(Integer page, Model model, Integer size, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String basic = session.getAttribute("basic").toString();
        String mySelect = "";
        if (session.getAttribute("basic2") != null) {
            mySelect = session.getAttribute("basic2").toString();
        }

        Map<String, Object> map = new HashMap<>();
        if (basic.equals("orderCode")) {
            map = tbRefundService.getListRefundByOrderCode(mySelect, page, 4);
        } else if (basic.equals("refundState")) {
            map = tbRefundService.getListRefundByState(mySelect, page, 4);

        } else {
            map = tbRefundService.getAllRefundIndex(page, size);
        }
        if (map == null) {
            map = new HashMap<>();
            map.put("refundList", null);
            map.put("page", 1);
            map.put("pageCount", 1);

        }

        model.addAttribute("map", map);
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
    public String getSearchRefund(Model model, String select, String mySelect, Integer page, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        session.setAttribute("basic2", mySelect);
        if (select.equals("orderCode")) {
            map = tbRefundService.getListRefundByOrderCode(mySelect, page, 4);
            session.setAttribute("basic", "orderCode");
        } else if (select.equals("refundState")) {
            session.setAttribute("basic", "refundState");
            map = tbRefundService.getListRefundByState(mySelect, page, 4);
        }
        model.addAttribute("map", map);
        return "pages/details/orders/refund-list";
    }

    /**
     * create by: yonyong
     * description: 进入退换货界面
     * create time: 2019/1/14 17:14
     * <p>
     * * @Param:
     *
     * @return java.lang.String
     */
    @RequestMapping("/index/return")
    public String returnPage() {
        return "pages/details/orders/sales-return-list";
    }

    /**
     * create by: yonyong
     * description: 退换货详情页
     * create time: 2019/1/14 23:02
     *
     *  * @Param:
     * @return java.lang.String
     */
    @RequestMapping("/index/returnDetails")
    public String returnDetails(@RequestParam("orderId")int orderId,Model model) {
        try {
            ReturnDetails returnDetails=tbExchangeService.selectReturnDetailsByOrderId(orderId);
            model.addAttribute("test",returnDetails);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pages/specific/return-goods.html";
    }

}
