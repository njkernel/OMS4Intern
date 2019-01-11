package cn.com.connext.oms.web.Api.exchange.OMS;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.exchange.OMS.GoodDetails;
import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 21:24
 **/

@RestController
@RequestMapping("/Api")
public class inputStateFeedback {
    @Autowired
    private TbExchangeService tbExchangeService;

    @Autowired
    private TbOrderService tbOrderService;

    @Autowired
    ObjectMapper objectMapper;
    /**
     * @Author: caps
     * @Description: 获取所有订单信息
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/6 10:24
     */

    @GetMapping("/test")
    @ApiOperation(value = "订单数据接口")
    public BaseResult getAllOrder(String orderState){
        try {
            PageInfo<TbOrder> allOrder = tbOrderService.getAllOrder(orderState);
            return BaseResult.success("成功",allOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }

    /**
     * create by: yonyong
     * description: TODO
     * create time: 2019/1/8 01:03
     *
     *  * @Param: inputFeedback
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
    @RequestMapping("/getExchangeInputFeedback")
    public BaseResult getExchangeInputFeedback(@RequestParam String tokens,
                                               @RequestParam Integer orderId,
                                               @RequestParam String inputState,
                                               @RequestParam String goodDetails) {
        List<GoodDetails> details= null;

        //对订单id加密
        if (tbExchangeService.checkToken(tokens,String.valueOf(orderId))==0){
            return BaseResult.fail("tokens口令错误！");
        }
        try {
            details = objectMapper.readValue(goodDetails,new TypeReference<List<GoodDetails>>() {});
        } catch (IOException e) {
            details=new ArrayList<>();
        }
        InputFeedback inputFeedback=new InputFeedback(tokens,orderId,inputState,"yonyong",details);
        int rs=tbExchangeService.inputFeedback(inputFeedback);
        if (0 == rs){
            return BaseResult.success("操作成功！已生成新的出库单！");
        }
        else if (1 == rs){
            return BaseResult.fail("操作错误或服务器内部错误！");
        }
        else if (2 == rs){
            return BaseResult.success("收货失败!");
        }
        else {
            return BaseResult.success("超15天未收货!");
        }

    }
}
