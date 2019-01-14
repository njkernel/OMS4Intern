package cn.com.connext.oms.service;


import cn.com.connext.oms.entity.TbRefund;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TbRefundService {

    /**
    * @Description: 批量更改退款单的状态
    * @Param: [tbRefundList]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/8
    */
   boolean updateRefundListStatue(Integer[] refundIdList);
   
   /** 
   * @Description: 查看所有的退款单（分页操作）
   * @Param: [beginIndex, size] 
   * @return: java.util.List<cn.com.connext.oms.entity.TbRefund> 
   * @Author: Lili Chen 
   * @Date: 2019/1/8 
   */
   Map<String,Object> getAllRefundIndex(Integer page, Integer size);


   /**
   * @Description: 根据id查找退款单
   * @Param: [refundId]
   * @return: cn.com.connext.oms.entity.TbRefund
   * @Author: Lili Chen
   * @Date: 2019/1/10
   */
   TbRefund getRefundById(Integer refundId);
   
   
  
   /** 
   * @Description: 根据退款单状态查看退款单 
   * @Param: [refundState, page, size] 
   * @return: java.util.Map 
   * @Author: Lili Chen 
   * @Date: 2019/1/13 
   */
   Map getListRefundByState(String refundState,Integer page, Integer size);



   /** 
   * @Description: 根据退款单的订单id查看退款单
    * @Param: [orderId, page, size] 
   * @return: java.util.Map 
   * @Author: Lili Chen 
   * @Date: 2019/1/13 
   */
   Map getListRefundByOrderCode(String orderCode,Integer page, Integer size);
   
   
   
   
}
