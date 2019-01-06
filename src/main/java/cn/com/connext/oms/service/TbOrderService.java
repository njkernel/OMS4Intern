package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbOrder;

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
    List<TbOrder> getAllOrder();
}
