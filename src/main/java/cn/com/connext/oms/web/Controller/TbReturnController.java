package cn.com.connext.oms.web.Controller;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.InputDTO;
import cn.com.connext.oms.commons.utils.ExchangeUtils;
import cn.com.connext.oms.commons.utils.ListToArray;
import cn.com.connext.oms.commons.utils.StringUtils;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.service.TbReturnService;
import cn.com.connext.oms.service.impl.TbReturnServiceImpl;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 15:42
 **/

@RestController
@RequestMapping("/return")
public class TbReturnController {
    @Autowired
    private TbReturnService tbReturnService;
    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbExchangeService tbExchangeService;
    @Autowired
    private ExchangeUtils exchangeUtils;

    private static final Logger log = LoggerFactory.getLogger(TbReturnServiceImpl.class);
    private static final String RETURN_TYPE = "退货";
    private static final String EXCHANGE_TYPE = "换货";
    private static final String COMPLETED = "已完成";
    private static final int ZERO = 0;



        /**
         *入库单页面
         * @param currentPage
         * @param pageSize
         * @return BaseResult
         */
        @GetMapping("/toInput")
        public BaseResult allInputOrders(Integer currentPage, Integer pageSize , TbInput tbInput) {

            PageInfo<InputDTO> tbInputList = tbReturnService.getAllInputOrders(currentPage, pageSize,tbInput);
            if (null != tbInputList) {
                return BaseResult.success("查询成功", tbInputList);
            }
            return BaseResult.fail(500, "后台数据获取失败");
        }


        /**
         * 根据订单id查询订单详情
         *
         * @param orderId
         * @return BaseResult
         * @author: Aaron
         */
        @GetMapping("/getOrderByOrderId")
        @ApiOperation(value = "订单数据接口")
        public BaseResult getOrderByOrderId(int orderId) {
            try {
                List<TbOrder> tbOrderList = tbOrderService.getOrderByOrderId(orderId);
                if (null != tbOrderList.get(0)) {
                    return BaseResult.success("查询成功", tbOrderList.get(0));
                }
                return BaseResult.fail(500, "请检查订单状态！");
            } catch (Exception e) {
                log.error(e.getMessage());
                return BaseResult.fail("查询失败");
            }

        }

        /**
         * 将前台的数据整合生成退货单
         *
         * @param orderId
         * @param goodsIdsList
         * @param numberList
         * @return BaseResult
         * @author: Aaron
         */
        @GetMapping("/addReturnOrder")
        @ApiOperation(value = "生成退货单数据接口")
        public BaseResult addReturnOrder(@RequestParam("orderId") int orderId, @RequestParam("goodsId") List<Integer> goodsIdsList, @RequestParam("number") List<Integer> numberList) {
            boolean flag = false;
            boolean flag1 = false;
            goodsIdsList = ListToArray.ListFormat(goodsIdsList);
            numberList = ListToArray.ListFormat(numberList);
            //判断是否为换货单
            if (exchangeUtils.checkOrderIsReturn(orderId)){
                return BaseResult.fail(401,"订单为退货单!");
            }
            //判断订单状态是否为已完成
            if (!exchangeUtils.checkOrderStatus(orderId)){
                return BaseResult.fail(402,"只能对已完成的订单操作!");
            }

            List<TbOrder> orderList = tbOrderService.getOrderByOrderId(orderId);
            if (null != orderList) {

                try {
                    if (COMPLETED.equals(orderList.get(0).getOrderState())) {

                        flag = tbReturnService.addReturnOrderGoods(orderId, goodsIdsList, numberList);
                        TbReturn tbReturn = tbReturnService.createReturnOrder(orderId, goodsIdsList, numberList);
                        flag1 = tbReturnService.addReturnOrder(tbReturn);

                        if (flag && flag1) {
                            return BaseResult.success("添加成功");
                        }
                    }

                    return BaseResult.fail(500, "添加退货信息失败");
                } catch (Exception e) {
                    log.error(e.getMessage());
                    return BaseResult.fail("添加失败");
                }
            }

            return BaseResult.fail(500, "并未查到相对应的订单");
        }


        /**
         * 退货单的取消
         *
         * @param returnIdsList
         * @return BaseResult
         * @author: Aaron
         */
        @GetMapping("/returnOrderCancel")
        @ApiOperation(value = "退货取消数据接口")
        public BaseResult returnOrderCancel (@RequestParam("returnId") List<Integer> returnIdsList){
            Date updated = new Date();
            String oms = "oms";
            List<Integer> returnList = new ArrayList<>();
            List<Integer> exchangeList = new ArrayList<>();

                for (int i = 0; i < returnIdsList.size(); i++) {
                    TbReturn tbReturn = tbReturnService.getTbReturnById(returnIdsList.get(i));
                    if (null != tbReturn) {
                        if (StringUtils.equals(RETURN_TYPE, tbReturn.getReturnType())) {
                            returnList.add(returnIdsList.get(i));

                        }

                        if (EXCHANGE_TYPE.equals(tbReturn.getReturnType())) {
                            exchangeList.add(returnIdsList.get(i));

                        }
                    }
                }

                //换货部分的取消 Update BY yonyong
               if (exchangeList.size()!=0) {
                   int[] ids = ListToArray.listToArray(exchangeList);
                   int t = tbExchangeService.updateTbReturn(ids, "换货取消", "yonyong", new Date());
                   if (-1 == t) {
                       BaseResult.fail("系统错误！");
                   } else if (-2 == t) {
                       BaseResult.fail(500, "换货单只有在待审核状态才能取消！");
                   } else {
                       BaseResult.success("您已成功取消换货！");
                   }

               }

               //退货部分的取消
               try {
                   boolean flag = tbReturnService.returnOrderCancel(returnList, oms, updated);
                   if (flag) {
                       return BaseResult.success("退货取消");
                   }

               } catch (Exception e) {
                  log.error(e.getMessage());
                  return BaseResult.fail("内部数据操作时出现异常");
               }

            return BaseResult.fail(500,"取消失败");
        }

        /**
         * @author: Aaron and yonyong
         * description: 退货/换货审核分流接口
         * create time: 2019/1/9 17:43
         *  * @Param: returnIds
         * @return cn.com.connext.oms.commons.dto.BaseResult
         */
        @GetMapping("/checkReturnOrExchange")
        @ApiOperation(value = "退货/换货审核分流接口")
        public BaseResult checkReturnOrExchange (@RequestParam("returnId") List<Integer> returnIdsList) {


            List<Integer> tbReturnList = new ArrayList<>();
            List<Integer> tbExchangeList = new ArrayList<>();
            Date date = new Date();

            if (ZERO != returnIdsList.size()) {
                for (int i = 0; i < returnIdsList.size(); i++) {
                    TbReturn tbReturn = tbReturnService.getTbReturnById(returnIdsList.get(i));
                    if (null != tbReturn) {

                        if (RETURN_TYPE.equals(tbReturn.getReturnType())) {
                            //将退货单生成单独的list交给退货部分处理
                            tbReturnList.add(tbReturn.getReturnId());
                        } else if (EXCHANGE_TYPE.equals(tbReturn.getReturnType())) {

                            //将换货单生成单独的list交给换货处理
                            tbExchangeList.add(tbReturn.getReturnId());
                        }
                    }
                }
            }

            try {
                //获取通过审核的订单，进行处理
                List<Integer> returnOrdersList = tbReturnService.returnOrdersAudit(tbReturnList);

                if (ZERO != returnOrdersList.size()) {
                    tbReturnService.createInputOrder(returnOrdersList);
                    BaseResult.success("生成入库单成功并成功发送");
                } else {
                 BaseResult.fail(500,"请检查退货单的状态");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                BaseResult.fail("跟wms的连接中断");
            }


            //updated by yonyong
            //TODO
            if (ZERO == tbExchangeList.size()) {
                log.error("tbExchangeList的size为"+tbExchangeList.size());
                return BaseResult.fail("tbExchangeList的size为0!");
            }
            int[] tids = ListToArray.listToArray(tbExchangeList);
            try {

                int rs = tbExchangeService.AuditTbReturn(tids, "oms", date);
                if (1 != rs) {
                    return BaseResult.fail("操作失败！");
                }
                int rt = tbExchangeService.generateInput(tids);
                return BaseResult.success("入库单生成成功");

            } catch (Exception e) {
                log.error(e.getMessage());
                return BaseResult.fail("服务器内部错误！");
            }

        }

    }



