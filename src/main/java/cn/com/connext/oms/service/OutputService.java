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
     * @Author: Jay
     * @Param: 订单状态
     * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
     * @Create: 2019/1/13
     */
    PageInfo<TbOrderDetails> getAllOrderByStatus(String state,int currentPage,int pageSize);

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
