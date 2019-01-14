package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.entity.TbOrder;
import io.swagger.models.auth.In;
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
    List<TbOrder> getAllOrder(String state);


    /**
    * @Description: 根据id获取订单信息
    * @Param: [orderId]
    * @return: cn.com.connext.oms.entity.TbOrder
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    TbOrder getOrderById(Integer orderId);


    /**
    * @Description: 更改订单状态
    * @Param: [tbOrder]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    int updateOrderStatue(TbOrder tbOrder);


    /** 
    * @Description: 批量修改订单状态
    * @Param: [tbOrderList] 
    * @return: int 
    * @Author: Lili Chen 
    * @Date: 2019/1/7 
    */
    int updateOrderListStatue(List<TbOrder> tbOrderList);

    /**
    * @Description:  批量修改订单“取消备注”
    * @Param: [tbOrderList]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/8
    */
    int updateOrderListBasicRemark(List<TbOrder> tbOrderList);

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
    /**
     * @Author: zhaojun
     * @Description:
     * @Param: []
     * @Return:
     * @Create: 2019/1/7 19:16z
     */
    public  OrderGoodsReceiverDto getAllById(int orderId);
}