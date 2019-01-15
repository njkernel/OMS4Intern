package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.entity.TbAbnormal;
import cn.com.connext.oms.service.TbAbnormalService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * <p>Title: TbAbnormalController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/7 15:44
 */
@RestController
public class TbAbnormalController {

    @Autowired
    private TbAbnormalService tbAbnormalService;

    @GetMapping("/orderCheck")
    @ApiOperation(value = "订单预检操作接口")
    public BaseResult orderCheck(int orderId){
        try {
            return tbAbnormalService.checkGoods(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }

    /**
    * @Author: caps
    * @Description: 异常订单列表加模糊查询
    * @Param: [currentPage, pageSize, tbAbnormal]
    * @Return: cn.com.connext.oms.commons.dto.BaseResult
    * @Create: 2019/1/7 17:53
    */

    @GetMapping("/abnormalList")
    @ApiOperation(value = "异常订单列表接口")
    public BaseResult abnormalList(Integer currentPage, Integer pageSize,TbAbnormal abnormal ){
        try {
            PageInfo<TbAbnormal> pageInfo = tbAbnormalService.checkList(currentPage,pageSize, abnormal);
            return BaseResult.success("成功",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误");
        }
    }


    /**
    * @Author: caps
    * @Description: 异常订单详情
    * @Param: [abnormalId]
    * @Return: cn.com.connext.oms.commons.dto.BaseResult
    * @Create: 2019/1/8 11:53
    */
    @GetMapping("/abnormalDetail")
    @ApiOperation(value = "异常订单列表接口")
    public BaseResult abnormalDetail(int abnormalId){
        try {
            Map<String, Object> map = tbAbnormalService.abnormalDetail(abnormalId);
            return BaseResult.success("成功",map);
        } catch (Exception e) {
            return BaseResult.fail("服务器内部错误");
        }
    }
    /**
    * @Author: caps
    * @Description:异常处理接口
    * @Param: [abnormalId]
    * @Return: cn.com.connext.oms.commons.dto.BaseResult
    * @Create: 2019/1/8 13:30
    */
    @GetMapping("/abnormalHandle")
    @ApiOperation(value = "异常处理接口")
    public BaseResult abnormalHandle(Integer abnormalId){
        try {
            return tbAbnormalService.abnormalHandle(abnormalId);
        } catch (Exception e) {
            return BaseResult.fail("服务器内部错误");
        }
    }
	
}
