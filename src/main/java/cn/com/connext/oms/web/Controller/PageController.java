package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.GoodsGoodsOrderDto;
import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.service.*;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;

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
    private TbAbnormalService tbAbnormalService;

    @Autowired
    private TbExchangeService tbExchangeService;
    @Autowired
    private TbOrderService tbOrderService;
   @Autowired
   private  TbGoodsListService tbGoodsListService;


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
    /**
     * 功能描述:跳转到商品列表
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/15 10:59
     */
    @RequestMapping("/goodsList")
    public String returnGoodsList() {
        return "pages/details/commodity/goods-list";
    }

    /**
     * 功能描述:订单列表跳转
     * @param: []
     * @return: java.lang.String
     * @auther: Jun.Zhao
     * @date: 2019/1/15 13:38
     */
    @RequestMapping("/orderList")
    public String returnOrederList() {
        return "pages/details/orders/order-list";
    }
    /**
     * 功能描述:订单详情跳转
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/15 16:21
     */
    @RequestMapping("/orderDetail")
    public String returnOrderDetail(Integer orderId, Model model){
        OrderGoodsReceiverDto orderGoodsReceiverDto =tbOrderService.getAllById(orderId);
        model.addAttribute("orderDetail",orderGoodsReceiverDto);
        List<GoodsGoodsOrderDto> goodsGoodsOrderDtos=tbGoodsListService.goodsListFromOrder(orderId);
        model.addAttribute("orderGoodsDetails",goodsGoodsOrderDtos);
        int sum1 = 0;
        double sum2 = 0;
        for (int i =0;i<goodsGoodsOrderDtos.size();i++){
            sum1+=goodsGoodsOrderDtos.get(i).getNum();
            sum2+=goodsGoodsOrderDtos.get(i).getTotalPrice();
        }
        model.addAttribute("sumNum",sum1);
        model.addAttribute("sumTotalPrice",sum2);
        return "pages/specific/order-detail";
    }


}
