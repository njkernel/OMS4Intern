package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.service.TbReceiverService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: OMS4Intern
 * @description: 收货人信息与web的交互
 * @author: Lili.Chen
 * @create: 2019-01-09 10:33
 **/
@RestController
public class TbReceiverController {
    @Autowired
    private TbReceiverService tbReceiverService;

    @PostMapping("/editReceiverInformation")
    @ApiOperation(value = "编辑收货人信息")
    public String editReceiverInformation(TbReceiver tbReceiver){
        boolean b=tbReceiverService.updateReceiver(tbReceiver);
        if(b){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/cancelEdit")
    @ApiOperation(value = "编辑收货人信息")
    public Map cancelEdit(Integer receiverId){
        TbReceiver receiver=tbReceiverService.getReceiverById(receiverId);
        Map map=new HashMap();
        map.put("receiver",receiver);
        return map;
    }
}
