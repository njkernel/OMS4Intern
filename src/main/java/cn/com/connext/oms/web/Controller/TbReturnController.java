package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.utils.ListToArray;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.service.TbReturnService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TbExchangeService tbExchangeService;

    /**
     * 根据订单id查询订单详情
     * @author: Aaron
     * @param orderId
     * @return BaseResult
     */
    @GetMapping("/getOrderByOrderId")
    @ApiOperation(value = "订单数据接口")
    public BaseResult getOrderByOrderId (int orderId) {
        try {
            List<TbOrder> tbOrderList = tbOrderService.getOrderByOrderId(orderId);
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
     * @param orderId
     * @author: Aaron
     * @param goodsIdsList
     * @param numberList
     * @return BaseResult
     */
    @GetMapping("/addReturnOrder")
    @ApiOperation(value = "生成退货单数据接口")
    public BaseResult addReturnOrder(@RequestParam("orderId") int orderId, @RequestParam("goodsId") List<Integer> goodsIdsList, @RequestParam("number")List<Integer> numberList){
        boolean flag = false;
        boolean flag1 = false;

        try{

                flag = tbReturnService.addReturnOrderGoods(orderId, goodsIdsList, numberList);
                TbReturn tbReturn = tbReturnService.createReturnOrder(orderId, goodsIdsList, numberList);
                flag1 = tbReturnService.addReturnOrder(tbReturn);

                if (flag && flag1) {
                    return BaseResult.success("添加成功");
                }

                return BaseResult.fail(500, "添加退货信息失败");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return BaseResult.fail("添加失败");
        }
    }


    /**
     * 退货单的取消
     * @author: Aaron
     * @param returnId
     * @return BaseResult
     */
    @GetMapping("/returnOrderCancel")
    @ApiOperation(value = "退货取消数据接口")
    public BaseResult returnOrderCancel (@RequestParam("returnId") int returnId){
        Date updated = new Date();
        String oms = "oms";
        try{
            boolean flag  = tbReturnService.returnOrderCancel(returnId,oms,updated);
            if (flag){
                return BaseResult.success("取消成功");
            }
            return BaseResult.fail(500,"取消失败");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return BaseResult.fail("内部数据出现错误，请稍后重试");
        }
    }


    /**
     * 退货单审核
     * @author: Aaron
     * @param returnIdsList
     * @return BaseResult
     */
    @GetMapping("/returnOrdersAudit")
    @ApiOperation(value = "退货审核数据接口")
    public BaseResult returnOrdersAudit (@RequestParam("returnId") List<Integer> returnIdsList) {
        try {
                boolean flag = tbReturnService.returnOrdersAudit(returnIdsList);
                if (flag) {
                    TbInput tbInput = tbReturnService.createInputOrder(returnIdsList);
                    return BaseResult.success("审核通过,已生成入库单，退货单状态变为“等待收货”。");
                }
            return BaseResult.fail(500, "审核失败,时间已超出期限");
        } catch (Exception e) {
        }
        return BaseResult.fail("内部数据出现错误,请稍后重试");

    }

    /**
     * @author: Aaron
     * create by: yonyong
     * description: 退货/换货审核分流接口
     * create time: 2019/1/9 17:43
     *
     *  * @Param: returnIds
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */

    @GetMapping("/checkReturnOrExchange")
    @ApiOperation(value = "退货/换货审核分流接口")
    public BaseResult checkReturnOrExchange(List<Integer>  returnIds){

        Boolean rsReturn=false;
        int rsExchange=0;
        List<Integer> tbReturnList = new ArrayList<>();
        List<Integer> tbExchangeList = new ArrayList<>();
        Date date=new Date();


        for (int i = 0;i<returnIds.size();i++){
            TbReturn tbReturn = tbReturnService.getTbReturnById(returnIds.get(i));
            if ("退货".equals(tbReturn.getReturnType())){
                tbReturnList.add(tbReturn.getReturnId());//将退货单生成单独的list交给退货部分处理
            }else if ("换货".equals(tbReturn.getReturnType())){
                tbExchangeList.add(tbReturn.getReturnId());//将换货单生成单独的list交给换货出来
            }
        }

        try {
            boolean flag = tbReturnService.returnOrdersAudit(tbReturnList);
            if (flag) {
                TbInput tbInput = tbReturnService.createInputOrder(tbReturnList);
            }
        } catch (Exception e) {
            return BaseResult.fail("退货单生成入库单失败");
        }

        int [] tids= ListToArray.listToArray(tbExchangeList);
        try{
            int rs=tbExchangeService.AuditTbReturn(tids,"yonyong",date);
            if (rs!=1){
                return BaseResult.fail("操作失败！");
            }
            int rt=tbExchangeService.generateInput(tids);
            return BaseResult.success("入库单生成成功");
        }catch (Exception e){
            return BaseResult.fail("服务器内部错误！");
        }
    }

}



