package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.service.TbOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: TbOrderById</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/7
 */
@RestController
public class TbGetOrderByIdController {
    @Autowired
    private TbOrderService tbOrderService;
    /**
        * @Author: zhaojun
        * @Description: 根据订单号查询订单的详情
        * @Param: []
        * @Create: 2019/1/7 11:03
        */
    @GetMapping("getOrderById")
    @ApiOperation(value = "订单详情接口")
    public BaseResult getOrderById(int orderId){
        try {
            TbOrder tbOrder = tbOrderService.getOrderById(orderId);
            return BaseResult.success("成功",tbOrder);
        } catch (Exception e) {
            return BaseResult.success("失败");
        }
    }


}
