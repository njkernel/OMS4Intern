package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.service.TbReturnService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 15:42
 **/

@RestController
@RequestMapping("/return")
public class TbReturnController {
    @Autowired
    private TbReturnService tbReturnService;
    @Autowired
    private TbOrderService tbOrderService;

    /**
     * 根据订单id查询订单详情
     * create by: Aaron
     *
     * @param orderId
     * @return BaseResult
     */
    @GetMapping("/getOrderByOrderId")
    @ApiOperation(value = "订单数据接口")
    public BaseResult getOrderByOrderId(@RequestParam("orderId") int orderId) {
        try {
            List<TbOrder> tbOrderList = tbOrderService.getOrderByOrderId(orderId);
            //根据订单id查订单
            if (null != tbOrderList.get(0)) {
                return BaseResult.success("查询成功", tbOrderList.get(0));
            }
            return BaseResult.fail(500, "请检查订单状态！");
        } catch (Exception e) {
            return BaseResult.fail("查询失败");
        }

    }

    /**
     * 将前台的数据整合生成退货单
     * create by: Aaron
     *
     * @param orderId
     * @param goodsIdsList
     * @param numberList
     * @return BaseResult
     */
    @GetMapping("/addReturnOrder")
    @ApiOperation(value = "生成退货单数据接口")
    public BaseResult addReturnOrder(@RequestParam("orderId") int orderId, @RequestParam("goodsId") List<Integer> goodsIdsList, @RequestParam("number") List<Integer> numberList) {
        boolean flag = false;
        boolean flag1 = false;

        try {

            flag = tbReturnService.addReturnOrderGoods(orderId, goodsIdsList, numberList);
            TbReturn tbReturn = tbReturnService.createReturnOrder(orderId, goodsIdsList, numberList);
            //生成退货单
            flag1 = tbReturnService.addReturnOrder(tbReturn);

            if (flag && flag1) {
                return BaseResult.success("添加成功");
            }

            return BaseResult.fail(500, "添加退货信息失败");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return BaseResult.fail("添加失败");
        }
    }


    /**
     * 退货单的取消
     * create by: Aaron
     *
     * @param returnId
     * @return BaseResult
     */
    @GetMapping("/returnOrderCancel")
    @ApiOperation(value = "退货取消数据接口")
    public BaseResult returnOrderCancel(@RequestParam("returnId") int returnId) {
        Date updated = new Date();
        String oms = "oms";
        try {
            boolean flag = tbReturnService.returnOrderCancel(returnId, oms, updated);
            //退货单取消
            if (flag) {
                return BaseResult.success("取消成功");
            }
            return BaseResult.fail(500, "取消失败");
        } catch (Exception e) {

            return BaseResult.fail("内部数据出现错误，请稍后重试");
        }
    }


    /**
     * 退货单审核
     * create by: Aaron
     *
     * @param returnIdsList
     * @return BaseResult
     */
    @GetMapping("/returnOrdersAudit")
    @ApiOperation(value = "退货审核数据接口")
    public BaseResult returnOrdersAudit(@RequestParam("returnId") List<Integer> returnIdsList) {
        try {
            boolean flag = tbReturnService.returnOrdersAudit(returnIdsList);
            //退货单审核
            if (flag) {
                TbInput tbInput = tbReturnService.createInputOrder(returnIdsList);
                return BaseResult.success("审核通过,已生成入库单，退货单状态变为“等待收货”。");
            }
            return BaseResult.fail(500, "审核失败,时间已超出期限");
        } catch (Exception e) {
            return BaseResult.fail("内部数据出现错误,请稍后重试");
        }

    }

    /**
     * 退货/换货审核数据接口
     * create by: Aaron
     * @param returnIdsList
     * @return BaseResult
     */
    @GetMapping("/checkReturnOrExchange")
    @ApiOperation(value = "退货/换货审核数据接口")
    public BaseResult checkReturnOrExchange(@RequestParam("returnId") List<Integer> returnIdsList) {
        boolean flag = false;
        List<Integer> tbReturnList = new ArrayList<>();
        List<Integer> tbExchangeList = new ArrayList<>();
        for (int i = 0; i < returnIdsList.size(); i++) {
            TbReturn tbReturn = tbReturnService.getTbReturnById(returnIdsList.get(i));

            if ("退货".equals(tbReturn.getReturnType())) {
                tbReturnList.add(tbReturn.getReturnId());
                //将退货单生成单独的list交给退货部分处理

            } else if ("换货".equals(tbReturn.getReturnType())) {

                tbExchangeList.add(tbReturn.getReturnId());
                //将换货单生成单独的list交给换货出来
            }
        }

        try {
            flag = tbReturnService.returnOrdersAudit(tbReturnList);
            if (flag) {
                TbInput tbInput = tbReturnService.createInputOrder(tbReturnList);
                return BaseResult.success("审核通过,已生成入库单，退货单状态变为“等待收货”。");
            }
            return BaseResult.fail(500, "审核失败,时间已超出期限");
        } catch (Exception e) {
        }
        return BaseResult.fail("内部数据出现错误,请稍后重试");

    }

}



