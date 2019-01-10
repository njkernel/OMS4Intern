package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.mapper.TbOutputMapper;
import cn.com.connext.oms.service.TbOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: TbOrderController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/6 10:19
 */
@RestController

public class TbOrderController {
    @Autowired
    private TbOrderService tbOrderService;


    /**
    * @Author: caps
    * @Description: 获取所有订单信息
    * @Param: []
    * @Return: cn.com.connext.oms.commons.dto.BaseResult
    * @Create: 2019/1/6 10:24
    */

    @GetMapping("/getAllOrder")
    @ApiOperation(value = "订单数据接口")
    public BaseResult getAllOrder(){
        try {
            List<TbOrder> allOrder = tbOrderService.getAllOrder();
            return BaseResult.success("成功",allOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }

    @PostMapping(value = "/cancelOrderOfWms",produces = "text/json;charset=UTF-8")
    @ApiOperation(value = "wms取消订单的接口")
    public String  cancelOrderOfWms(@RequestBody @RequestParam(required = true) String outputCodeList){
        System.out.println(outputCodeList);
        boolean b=tbOrderService.cancelOrderOfWms(outputCodeList);
         if(b){
             return "200";
         }
         return "201";
    }

    @PostMapping("/cancelOrder")
    @ApiOperation(value = "主动取消订单接口")
    public String cancelOrder(List<TbOrder> tbOrderList){
        boolean b=tbOrderService.cancelOrder(tbOrderList);
        if(b){
            return "success";
        }else{
            return "fail";
        }
    }

}
