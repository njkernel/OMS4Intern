package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.service.TbReceiverService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: TbUpdateReceiverController</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/9
 */
@RestController
public class TbUpdateReceiverController {
    @Autowired
    private TbReceiverService tbReceiverService;
    /**
     * 功能描述:
     * @param: [tbReceiver]
     * @return: cn.com.connext.oms.commons.dto.BaseResult
     * @auther: Jun.Zhao
     * @date: 2019/1/9 19:37
     */
    @GetMapping("updateReceiver")
    @ApiOperation(value = "收货地址变更接口")
    public BaseResult updateReceiver(TbReceiver tbReceiver){

        try {
            this.tbReceiverService.updateReceiver(tbReceiver);
            return BaseResult.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部异常");
        }

    }
}
