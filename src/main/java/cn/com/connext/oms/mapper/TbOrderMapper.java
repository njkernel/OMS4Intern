package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbOrder;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

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
    List<TbOrder> getAllOrder();


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

}