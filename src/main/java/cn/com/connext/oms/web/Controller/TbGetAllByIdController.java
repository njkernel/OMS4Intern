package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.service.TbOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: TbGetAllByIdController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/7
 */
@RestController
public class TbGetAllByIdController {
    @Autowired
    private TbOrderService tbOrderService;
    /**
        * @Author: zhaojun
        * @Description:
        * @Param: []
        * @Create: 2019/1/7 19:27
        */
    @GetMapping("getAllById")
    @ApiOperation(value = "订单详情接口")
  public BaseResult getAllById(int orderId){
        try {
            OrderGoodsReceiverDto orderGoodsReceiverDto=this.tbOrderService.getAllById(orderId);
            return BaseResult.success("成功",orderGoodsReceiverDto);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }
}