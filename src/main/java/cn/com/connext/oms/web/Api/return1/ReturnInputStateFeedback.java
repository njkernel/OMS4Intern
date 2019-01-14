package cn.com.connext.oms.web.Api.return1;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.exchange.OMS.GoodDetails;
import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.service.TbReturnService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 21:24
 **/

@RestController
@RequestMapping("/Api")
public class ReturnInputStateFeedback {
    @Autowired
    private TbReturnService tbReturnService;

    @Autowired
    private TbExchangeService tbExchangeService;

    @Autowired
    private TbOrderService tbOrderService;

    @Autowired
    ObjectMapper objectMapper;
    String RETURN_TYPE = "退货";
    String EXCHANGE_TYPE = "换货";


    /**
     * 获取wms的反馈更改相应订单状态
     *
     * @param tokens
     * @param orderId
     * @param inputState
     * @param goodDetails
     * @return BaseResult
     */
    @RequestMapping("/getReturnInputFeedback")
    public BaseResult getReturnInputFeedback(@RequestParam String tokens,
                                             @RequestParam Integer orderId,
                                             @RequestParam String inputState,
                                             @RequestParam String goodDetails) {
        List<GoodDetails> details = null;

        String type = tbExchangeService.selectReturnDetailsByOrderId(orderId).getReturnType();

        //对订单id加密
        if (0 == tbExchangeService.checkToken(tokens, String.valueOf(orderId))) {
            return BaseResult.fail("tokens口令错误！");
        }


        //退货部分的状态反馈及更新状态 created by Aaron
        if (RETURN_TYPE == type) {
            try {
                details = objectMapper.readValue(goodDetails, new TypeReference<List<GoodDetails>>() {
                });
            } catch (IOException e) {
                details = new ArrayList<>();
            }
            InputFeedback inputFeedback = new InputFeedback(tokens, orderId, inputState, "oms", details);

            int result = tbReturnService.updateStateByFeedback(inputFeedback);
            if (0 == result) {
                return BaseResult.success("退货单与入库单的状态已经更新完毕");
            } else if (1 == result) {
                return BaseResult.fail("内部数据获取失败");
            } else if (2 == result) {
                return BaseResult.fail("收货失败");
            } else if (3 == result) {
                return BaseResult.fail("超15天未收货");
            }
        }

        //换货部分的状态反馈及更新  crated by yonyong
        if (EXCHANGE_TYPE == type) {
            try {
                details = objectMapper.readValue(goodDetails, new TypeReference<List<GoodDetails>>() {
                });
            } catch (IOException e) {
                details = new ArrayList<>();
            }
            InputFeedback inputFeedback = new InputFeedback(tokens, orderId, inputState, "yonyong", details);
            int rs = tbExchangeService.generateOutput(inputFeedback);
            if (0 == rs) {
                return BaseResult.success("操作成功！已生成新的出库单！");
            } else if (1 == rs) {
                return BaseResult.fail("操作错误或服务器内部错误！");
            } else if (2 == rs) {
                return BaseResult.success("收货失败!");
            } else {
                return BaseResult.success("超15天未收货!");
            }


        }

        return BaseResult.fail("请检查订单");
    }
}






