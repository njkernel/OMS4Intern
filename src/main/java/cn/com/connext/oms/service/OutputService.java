package cn.com.connext.oms.service;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbReceiver;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>Title: OutputService</p>
 * <p>Description: 出库模块，包含查询订单详情，出库单详情，更改状态等</p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/7
 */
public interface OutputService {
    /**
     *
     * 功能描述: 根据传入的数组id修改订单状态为待出库
     *
     * @param: 订单id
     * @auther: Jay
     * @date: 2019/1/7
     */
    BaseResult UpdateOrderIntoWaitOutPut(int[] id);
    /**
     *
     * 功能描述: 根据传入的数组id修改订单状态为已出库
     *
     * @param: 订单id
     * @auther: Jay
     * @date: 2019/1/7
     */
    BaseResult Output(int id) throws HttpProcessException;
    /**
     *
     * 功能描述: 根据订单id查询出所有出库单的详情
     *
     * @param: 订单id
     * @auther: Jay
     * @date: 2019/1/8
     */
    List<TbOrderDetails> orderDetails(int orderId);
    /**
     * 功能描述: 点击出库单列表，显示所有已出库的订单,以及模糊查询选择符合条件的订单,默认显示所有已出库的订单
     *
     * @return: 返回所有状态是已出库的订单，以及模糊查询选择符合条件的订单
     * @param: currentPage: 当前页， pageSize： 总页数, orderId： 订单id，outputCode ：出库单号, deliveryCode ：快递单号
     * @auther: Jay
     * @date: 2019/1/13
     */
    PageInfo<TbOrderDetails> getAllOrderByStatusAndSeacrch(int currentPage,int pageSize, String orderId, String outputCode, String deliveryCode);

    /**
     *
     * 功能描述: 根据订单id查询所有的订单详情
     *
     * @param: 订单id
     * @auther: Jay
     * @date: 2019/1/8
     */
    TbOrder getOrderById(Integer orderId);
    /**
     *
     * 功能描述: 根据传过来的发货信息更新订单数据
     *
     * @param: 订单发货信息
     * @auther: Jay
     * @date: 2019/1/9
     */
    String updateOrder(TbOrder tbOrder);
    /**
     *
     * 功能描述: 根据出库单号查询出库单信息
     *
     * @param:  出库单单号
     * @return: 返回出库单
     * @auther: Jay
     * @date: 2019/1/9
     */
    TbOutput getOutputOrder(int orderId);
    /**
     *
     * 功能描述:  更改出库单的状态
     *
     * @param:  出库订单
     * @auther: Jay
     * @date: 2019/1/9
     */
    String updateOutput(TbOutput tbOutput);
}
