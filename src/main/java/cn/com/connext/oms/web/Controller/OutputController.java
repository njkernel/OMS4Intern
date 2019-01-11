package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.service.OutputService;
import cn.com.connext.oms.service.TbOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: OutputController</p>
 * <p>Description:出库模块，包含查询订单详情，出库单详情，更改状态等 </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/7
 */
@RestController
public class OutputController {
    @Autowired
    private OutputService outputService;
    @Autowired
    private TbOrderService tbOrderService;
    private static String STATUS4 = "已出库";

    /**
     * 功能描述:根据传入的数组id修改订单的状态
     *
     * @param: 数组id
     * @return: 返回修改状态码，成功或失败
     * @auther: Jay
     * @date: 2019/1/7
     */
    @GetMapping("/UpdateOrderIntoWaitOutPut")
    public BaseResult UpdateOrderIntoWaitOutPut(@RequestParam(value = "id") int[] id) {
        return outputService.UpdateOrderIntoWaitOutPut(id);
    }

    /**
     * 功能描述: 根据传入的数组id生成出库单，并根据WMS返回值修改订单状态为已出库或者出库异常
     *
     * @param: 订单id
     * @return: 返回修改状态码，成功或失败
     * @auther: Jay
     * @date: 2019/1/7
     */
    @GetMapping("/Output")
    public BaseResult Output(int id) throws Exception {
        return outputService.Output(id);
    }

    /**
     * 功能描述: 点击出库单列表，显示所有已出库的订单
     *
     * @return: 返回所有状态是已出库的订单
     * @auther: Jay
     * @date: 2019/1/8
     */
    @GetMapping("OutputDetails")
    public BaseResult OutputDetails() {
        PageInfo<TbOrder> allOrder = (PageInfo<TbOrder>) tbOrderService.getAllOrder(STATUS4);
        // 需要和前端页面绑定
        return BaseResult.success("成功", allOrder);
    }

    /**
     * 功能描述: 根据订单id查询出所有出库单的详情
     *
     * @param: 订单id
     * @return: 返回出库单的所有需要详情，包含商品信息
     * @auther: Jay
     * @date: 2019/1/8
     */
    @GetMapping("orderDetails")
    public BaseResult orderDetails(int orderId) {
        List<TbOrderDetails> orderDetails = outputService.orderDetails(orderId);
        // 需要和前端页面绑定
        return BaseResult.success("成功", orderDetails);
    }

    /**
     * 功能描述: 根据订单id查询出所有出库单的详情
     *
     * @param: 订单id
     * @return: 返回订单所有详情消息，包含订单基本信息以及出库单信息，商品信息
     * @auther: Jay
     * @date: 2019/1/8
     */
    @GetMapping("orderDetailsAll")
    public BaseResult orderDetailsAll(Integer orderId) {
        TbOrder tbOrder = outputService.getOrderById(orderId);
        List<TbOrderDetails> orderDetails = outputService.orderDetails(orderId);
        // 需要和前端页面绑定
        return BaseResult.success("成功", orderDetails);
    }

    /**
     * 功能描述: WMS调用此接口同步状态
     *
     * @param: 待打包，待发货,发货状态同步并更新出库单信息
     * @return: 200 表示接收状态成功
     * @auther: Jay
     * @date: 2019/1/9
     */
    @PostMapping(value = "/synchronizeState")
    @ResponseBody
    public String synchronizeState(@RequestBody Map map) {
        String STATUS = (String) map.get("status");
        List<String> orderList = (List<String>) map.get("orderIdList");
        List<String> receivcerDetails = (List<String>) map.get("shippingInfo");
        // 遍历订单id集合，批量修改状态
       for (String orderIds : orderList) {
            // 获取遍历的集合并转换为Int类型
            int orderId = Integer.valueOf(orderIds).intValue();
            TbOutput tbOutput = outputService.getOutputOrder(orderId);
            tbOutput.setOutputState(STATUS);
            String s = outputService.updateOutput(tbOutput);
            // 判断是否是发货状态，如果是则会携带数据
            if (receivcerDetails != null && "200".equals(s)) {
                TbOrder tbOrder = outputService.getOrderById(orderId);
                tbOrder.setDeliveryWarehouse("南京仓");
                tbOrder.setDeliveryCompany(receivcerDetails.get(0));
                tbOrder.setChannelCode(receivcerDetails.get(1));
                tbOrder.setOrderState(STATUS);
                Date deliveryTime = new Date(receivcerDetails.get(2));
                tbOrder.setDeliveryTime(deliveryTime);
                Date updated = new Date(receivcerDetails.get(3));
                tbOrder.setUpdated(updated);
                return outputService.updateOrder(tbOrder);
            }
            // 判断是否发货状态，状态没有更改成功
            if (receivcerDetails != null && s!="200"){
            return "error";}
        }
        return "200";
    }
}
