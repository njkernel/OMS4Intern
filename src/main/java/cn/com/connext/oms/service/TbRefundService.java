package cn.com.connext.oms.service;


import cn.com.connext.oms.entity.TbRefund;
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
   boolean updateRefundListStatue(List<TbRefund> tbRefundList);
   
   /** 
   * @Description: 查看所有的退款单（分页操作）
   * @Param: [beginIndex, size] 
   * @return: java.util.List<cn.com.connext.oms.entity.TbRefund> 
   * @Author: Lili Chen 
   * @Date: 2019/1/8 
   */
   Map<String,Object> getAllRefundIndex(Integer page, Integer size);
}
