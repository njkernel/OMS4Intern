package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.service.TbOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResult getAllOrder(String orderState){
        try {
            List<TbOrder> allOrder = tbOrderService.getAllOrder(orderState);
            return BaseResult.success("成功",allOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }
}
