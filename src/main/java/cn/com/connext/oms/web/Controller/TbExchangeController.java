package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.GoodsGoodsOrderDto;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.commons.utils.ExchangeUtils;
import cn.com.connext.oms.commons.utils.ListToArray;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbGoodsListService;
import cn.com.connext.oms.service.TbOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 10:08
 **/

@RequestMapping("/exchange")
@RestController
public class TbExchangeController {

    @Autowired
    private TbExchangeService tbExchangeService;

    @Autowired
     TbOrderService tbOrderService;

    @Autowired
    TbGoodsListService tbGoodsListService;

    @Autowired
    private ExchangeUtils exchangeUtils;

    /**
     * create by: yonyong
     * description: 显示所有换货单
     * create time: 2019/1/9 00:16
     *
     *  * @Param: currentPage
      * @Param: pageSize
      * @Param: returnId
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
    @RequestMapping("/showAllExchanges")
    @ApiOperation(value = "显示所有换货单接口")
    public  BaseResult showAllReturns(Integer currentPage,Integer pageSize,TbReturn tbReturn){
        try {
            PageInfo<TbReturn> pageInfo=tbExchangeService.showAllReturns(currentPage,pageSize,tbReturn);
            return BaseResult.success("查询成功！",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误！");
        }
    }

    /**
     * create by: yonyong
     * description: 点击换货获取订单所有信息，开放商品加减按钮
     * create time: 2019/1/7 11:22
     *
     *  * @Param: order_id
     * @return java.lang.String
     */
    @RequestMapping("/toRequest")
    @ApiOperation(value = "获取订单所有信息接口")
    public BaseResult toRequest(@RequestParam("orderId")int orderId){
        try {
            List<TbOrder> tbOrder = tbOrderService.getOrderByOrderId(orderId);
            if (null != tbOrder){
                return BaseResult.success("查询成功",tbOrder);
            }
            return BaseResult.fail(500,"换货订单状态必须为已完成！");
        }
        catch (Exception e){
            return BaseResult.fail("查询失败");
        }
    }


    /**
     * create by: yonyong
     * description: 生成换货单 需要加库存校验 ps:另需要在前端判断orderId，goodId和num三个数组必须一致长，相对应
     * create time: 2019/1/7 15:40
     *
     *  * @Param: orderId
     * @Param: goodCode
     * @Param: num
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
    @RequestMapping("/toGenerateExchangeOrder")
    @ApiOperation(value = "生成换货单接口")
    public BaseResult toGenerateExchangeOrder(@RequestParam("orderId")int orderId,
                                              @RequestParam("goodId")List<Integer> goodIds,
                                              @RequestParam("num")List<Integer> nums){
        //判断是否为换货单
        if (exchangeUtils.checkOrderIsReturn(orderId)){
            return BaseResult.fail(401,"订单为换货单!");
        }
        //判断订单状态是否为已完成
        if (!exchangeUtils.checkOrderStatus(orderId)){
            return BaseResult.fail(402,"只能对已完成的订单操作!");
        }
        TbGoods tbGoods=new TbGoods();
        int []goodId = ListToArray.ListFormat(ListToArray.listToArray(goodIds));
        int []num = ListToArray.ListFormat(ListToArray.listToArray(nums));
        try {
            tbExchangeService.toGenerateExchangeOrderGoods(orderId,goodId,num);
            TbReturn tbReturn=tbExchangeService.setTbReturn(orderId,goodId,num);
            tbExchangeService.toGenerateExchangeOrder(tbReturn);
            return BaseResult.success("成功生成换货单！");
        }catch (Exception e){
            return BaseResult.fail("生成换货单失败！");
        }
    }

    /**
     * create by: yonyong
     * description: 查看换货单详情
     * create time: 2019/1/9 00:16
     *
     *  * @Param: orderId
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
    @RequestMapping("/exchangeDetails")
    @ApiOperation(value = "换货单详情接口")
    public BaseResult exchangeDetails(@RequestParam("orderId")int orderId){
        try {
            ReturnDetails returnDetails=tbExchangeService.selectReturnDetailsByOrderId(orderId);
            return BaseResult.success("查询成功！",returnDetails);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.fail("服务器内部错误！");
        }
    }

    /**
     * create by: yonyong
     * description: 取消换货
     * create time: 2019/1/7 14:55
     *
     *  * @Param: ids
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
    @RequestMapping("/toCancel")
    @ApiOperation(value = "取消换货接口")
    public BaseResult toCancel(@RequestParam("ids")int [] ids){
        int t=tbExchangeService.updateTbReturn(ids,"换货取消","yonyong",new Date());
        if (t==-1){
            return BaseResult.fail("系统错误！");
        }
        else if(t==-2){
            return BaseResult.fail(500,"换货单只有在待审核状态才能取消！");
        }
        else{
            return BaseResult.success("您已成功取消换货！");
        }
    }

     /**
     * create by: yonyong
     * description: 审核换货单，订单超过15天，非可换商品
     * create time: 2019/1/7 14:53
     *
     *  * @Param: ids
     * @return cn.com.connext.oms.commons.dto.BaseResult
     */
     @RequestMapping("/toAudit ")
     @ApiOperation(value = "审核换货单接口")
     public BaseResult toAudit(@RequestParam("ids")int [] ids){
         Date date=new Date();
         int rs=tbExchangeService.AuditTbReturn(ids,"yonyong",date);
         if (rs!=1){
             return BaseResult.fail("操作失败！");
         }
         int rt=tbExchangeService.generateInput(ids);
         return BaseResult.success("完成");

    }

    @RequestMapping("/getOrderGoodsNumsDetails")
    public BaseResult getOrderGoodsNum(@Param("orderId") int orderId){
         try {
             List<GoodsGoodsOrderDto> goodsGoodsOrderDtos=tbGoodsListService.goodsListFromOrder(orderId);
             return BaseResult.success("success",goodsGoodsOrderDtos);
         }catch (Exception e){
             return BaseResult.fail("failed!");
         }
    }

}
