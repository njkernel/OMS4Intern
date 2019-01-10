package cn.com.connext.oms.service;

import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.entity.TbOrder;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>Title: TbOrderService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/6 10:15
 */
public interface TbOrderService {
    /**
     * @Author: caps
     * @Description:
     * @Param: []
     * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
     * @Create: 2019/1/6 10:14
     */
    List<TbOrder> getAllOrder(String orderState);

    /**
     * create by: yonyong
     * description: 根据订单id查询订单所有信息
     * create time: 2019/1/7 13:52
     *
     *  * @Param: id
     * @return cn.com.connext.oms.entity.TbOrder
     */
    List<TbOrder> getOrderDetailsByOrderId(int id);

    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询订单的详情
     * @Param: []
     * @Create: 2019/1/7 10:59
     */
    public TbOrder getOrderById(int orderId);
    /**
     * @Author: zhaojun
     * @Description: 根据订单ID查询订单所有信息
     * @Param: []
     * @Return:
     * @Create: 2019/1/7 19:19
     */
    public OrderGoodsReceiverDto getAllById(int orderId);


    /**
     * create by: Aaron
     * description: 根据订单id查询订单详情
     * create time: 2019/1/7 15:31
     *
     *
     * @return  * @Param: null
     */

    List<TbOrder> getOrderByOrderId(int orderId);
}
