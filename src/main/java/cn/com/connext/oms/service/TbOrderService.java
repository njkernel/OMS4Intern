package cn.com.connext.oms.service;

import cn.com.connext.oms.commons.dto.OrderGoodsReceiverDto;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOrderDetails;
import com.github.pagehelper.PageInfo;import cn.com.connext.oms.entity.TbOutput;
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
     * create by: Aaron
     * description: 根据订单id查询订单详情
     * create time: 2019/1/7 15:31
     *
     *
     * @return  * @Param: null
     */

    List<TbOrder> getOrderByOrderId(int orderId);

   /** 
   * @Description: 主动批量取消订单 
   * @Param: [orderList] 
   * @return: boolean 
   * @Author: Lili Chen 
   * @Date: 2019/1/8 
   */
    boolean cancelOrder(Integer[] orderIdList,String userName);
    
    
    /** 
    * @Description: WMS取消订单 
    * @Param: [outputList] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2019/1/8 
    */
    boolean cancelOrderOfWms(String outputs);
    /**
     * @Author: zhaojun
     * @Description: 根据订单ID查询订单所有信息
     * @Param: []
     * @Return:
     * @Create: 2019/1/7 19:19
     */
    public OrderGoodsReceiverDto getAllById(int orderId);
    /**
     * 功能描述:查看所有订单 和模糊查询订单
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/18 14:22
     */
    public PageInfo<OrderGoodsReceiverDto> selectAllOrders(Integer currentPage, Integer pageSize, OrderGoodsReceiverDto orderGoodsReceiverDto);
    /**
     * 功能描述: 计算单价
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/24 16:36
     */
     Double getPrice(int num, double totalPrice);
}
