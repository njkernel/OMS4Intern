package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.entity.TbOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

@Repository
public interface TbOrderMapper extends MyMapper<TbOrder> {
    /**
    * @Author: caps
    * @Description:
    * @Param: []
    * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
    * @Create: 2019/1/6 10:14
    */
    List<TbOrder> getAllOrder(@Param("orderState") String orderState);
    List<TbOrder> getAllOrder();

    /**
     * create by: yonyong
     * description: 根据订单id查询订单所有信息
     * create time: 2019/1/7 13:52
     *
     *  * @Param: id
     * @return cn.com.connext.oms.entity.TbOrder
     */
    List<TbOrder> getOrderByOrderId(@Param("id")int id);

    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询订单详情
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 10:54
     */
    public TbOrder getOrderById(int orderId);
    /**
     * @Author: zhaojun
     * @Description:
     * @Param: []
     * @Return:
     * @Create: 2019/1/7 19:16z
     */
    public OrderGoodsReceiverDto getAllById(int orderId);

    /**
     * create by: Aaron
     * description: 根据id查询订单详情
     * create time: 2019/1/7 15:30
     *
     *
     * @return  * @Param: null
     */
    List<TbOrder> getOrderDetailsByOrderId( int orderId);

    /**
     * create by: Aaron
     * description: TODO
     * create time: 2019/1/8 11:09
     * @return  * @Param: null
     */
    Date selectCreatedById(int orderId);
}