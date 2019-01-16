package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbGoodsOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbGoodsOrderService {
    /** 
    * @Description: 根据订单id查看订单商品列表 
    * @Param: [orderId] 
    * @return: java.util.List<cn.com.connext.oms.entity.TbGoodsOrder> 
    * @Author: Lili Chen 
    * @Date: 2019/1/15 
    */
    List<TbGoodsOrder> getListGoodsOrderById(Integer orderId); 
}
