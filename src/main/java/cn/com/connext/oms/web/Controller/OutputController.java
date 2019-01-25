package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.service.OutputService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private static String STATE1 = "haveShipped";
    private static String STATE2 = "waittingChecked";
    private static String STATE3 = "waittingPackaged";
    private static String STATE4 = "waittingShipped";

    /**
     * 功能描述:根据传入的数组id修改订单的状态
     *
     * @param: 数组id
     * @return: 返回修改状态码，成功或失败
     * @auther: Jay
     * @date: 2019/1/7
     */
    @PostMapping("/UpdateOrderIntoWaitOutPut")
    public BaseResult UpdateOrderIntoWaitOutPut(@RequestParam(value = "id") int id[]) {
        return outputService.UpdateOrderIntoWaitOutPut(id);
    }

    /**
     * 功能描述: 根据传入的数组id生成出库单，并根据WMS返回值修改订单状态为已出库或者出库异常
     *
     * @param: 订单idOutputDetails
     * @return: 返回修改状态码，成功或失败
     * @auther: Jay
     * @date: 2019/1/7
     */
    @GetMapping("/Output")
    public BaseResult Output(int[] id) throws Exception {
        return outputService.Output(id);
    }

    /**
     * 功能描述: 点击出库单列表，显示所有已出库的订单,以及模糊查询选择符合条件的订单,默认显示所有已出库的订单
     *
     * @return: 返回所有状态是已出库的订单，以及模糊查询选择符合条件的订单
     * @param: currentPage: 当前页， pageSize： 总页数, orderId： 订单id，outputCode ：出库单号, deliveryCode ：快递单号
     * @auther: Jay
     * @date: 2019/1/8
     */
    @GetMapping("/OutputDetails")
    public BaseResult OutputDetails(int currentPage,int pageSize, String orderId, String outputCode, String deliveryCode) {
        PageInfo<TbOrderDetails> allOrderByStatus = outputService.getAllOrderByStatusAndSeacrch(currentPage, pageSize, orderId, outputCode, deliveryCode);
        return BaseResult.success("成功", allOrderByStatus);
    }

    /**
     * 功能描述: 确认收货后将订单状态修改为已完成
     *
     * @param: 订单id
     * @return: 修改成功或者修改失败
     * @auther: Jay
     * @date: 2019/1/21
     */
    @GetMapping("/confirmReceiptUpdateOrderState")
    public BaseResult confirmReceiptUpdateOrderState(int orderId){
        return outputService.confirmReceiptUpdateOrderState(orderId);
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
        try {
            String status = (String) map.get("status");
            String STATUS = this.changeStatusIntoChinese(status);
            List<String> orderList = (List<String>) map.get("orderIdList");
            List<String> receivcerDetails = (List<String>) map.get("shippingInfo");
            // 遍历订单id集合，批量修改状态
            for (String orderIds : orderList) {
                 // 获取遍历的集合并转换为Int类型
                 int orderId = Integer.valueOf(orderIds).intValue();
                 TbOutput tbOutput = outputService.getOutputOrder(orderId);
                 // 同步状态
                 tbOutput.setOutputState(STATUS);
                 tbOutput.setUpdated(new Date());
                 String s = outputService.updateOutput(tbOutput);
                 // 判断是否是发货状态，如果是则会携带数据
                 if (receivcerDetails != null && "200".equals(s)) {
                     // 设置订单的信息
                     TbOrder tbOrder = outputService.getOrderById(orderId);
                     tbOrder.setDeliveryCompany(receivcerDetails.get(0));
                     tbOrder.setDeliveryCode(receivcerDetails.get(1));
                     tbOrder.setOrderState(STATUS);
                     Date deliveryTime = new Date(receivcerDetails.get(2));
                     tbOrder.setDeliveryTime(deliveryTime);
                     tbOrder.setUpdated(new Date());
                     return outputService.updateOrder(tbOrder);
                 }
                 // 判断是否发货状态，状态没有更改成功
                 if (receivcerDetails != null && s!="200"){
                 return "error";}
             }
        } catch (NumberFormatException e) {
            return "201";
        }
        return "200";
    }

    /**
     *
     * 功能描述: 转换 WMS 传过来的状态为中文
     *
     * @param: WMS状态
     * @return: 返回中文状态
     * @auther: Jay
     * @date: 2019/1/18
     */
    public String changeStatusIntoChinese(String status){
        if (status.equals(STATE2)){return "待检货";}
        if (status.equals(STATE3)){return "待包装";}
        if (status.equals(STATE4)){return "待发货";}
        if (status.equals(STATE1)){return "已发货";}
        return null;
    }
}
