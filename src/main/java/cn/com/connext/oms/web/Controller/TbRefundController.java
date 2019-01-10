package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.service.TbRefundService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 退款操作与页面的交互
 * @author: Lili.Chen
 * @create: 2019-01-08 16:56
 **/
@RestController
public class TbRefundController {

    @Autowired
    private TbRefundService tbRefundService;



    @PostMapping("/getAllRefundIndex")
    @ApiOperation(value = "分页操作查看退款单")
    public Map<String,Object> getAllRefundIndex(Integer page,Integer size){
        Map<String,Object> map=tbRefundService.getAllRefundIndex(page,size);
        return map;
    }

    @PostMapping("/refund")
    @ApiOperation(value = "进行退款操作")
    public String refund(Integer[] refundIdList){
        boolean b=tbRefundService.updateRefundListStatue(refundIdList);
        if(b){
            return "success";
        }
        return "fail";
    }


}
