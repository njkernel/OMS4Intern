/*
package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.service.TbAbnormalService;
import cn.com.connext.oms.web.Api.OrderAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private TbAbnormalService tbAbnormalService;
    @GetMapping("/test")
    public Map<String, Object> test() throws HttpProcessException {
        try {
            Map<String, Object> allOrder = OrderAPI.getAllOrder();
            System.out.println(allOrder);
            return allOrder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    */
/**
     * @Author: caps
     * @Description:异常订单列表详情页面
     * @Param: []
     * @Return: java.lang.String
     * @Create: 2019/1/12 16:01
     *//*

    @RequestMapping("/abnormalModel")
    public String abnormalDetail(){
        return "pages/specific/abnormal-order";
    }

    @GetMapping("/abnormalDetail")
    @ApiOperation(value = "异常订单列表接口")
    public String abnormalDetail(int abnormalId,Model model){
        try {
            Map<String, Object> map = tbAbnormalService.abnormalDetail(abnormalId);
            model.addAttribute("map",map);
            return "pages/specific/abnormal-order";
        } catch (Exception e) {
            return null;
        }
    }
}
*/
