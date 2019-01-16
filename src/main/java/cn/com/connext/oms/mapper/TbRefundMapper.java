package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbRefund;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface TbRefundMapper extends MyMapper<TbRefund> {
    /**
    * @Description: 批量添加退款单
    * @Param: [tbRefundList]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    int batchAddRefund(List<TbRefund> tbRefundList);

    /**
    * @Description: 批量更改退款单状态
    * @Param: [tbRefundList]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/8
    */
    int updateRefundListStatue(List<TbRefund> tbRefundList);


     /**
     * @Description: 查看所有的退款单
     * @Param: []
     * @return: java.util.List<cn.com.connext.oms.entity.TbRefund>
     * @Author: Lili Chen
     * @Date: 2019/1/8
     */
    List<TbRefund> getAllRefund();


    /**
    * @Description: 进行分页查看所有的退款单
    * @Param: [map]
    * @return: java.util.List<cn.com.connext.oms.entity.TbRefund>
    * @Author: Lili Chen
    * @Date: 2019/1/8
    */
    List<TbRefund> getAllRefundIndex(Map map);


    /** 
    * @Description: 根据id查看退款单 
    * @Param: [refundId] 
    * @return: cn.com.connext.oms.entity.TbRefund 
    * @Author: Lili Chen 
    * @Date: 2019/1/10 
    */
    TbRefund getRefundById(Integer refundId);


    /**
    * @Description: 根据退款单状态查看退款单
    * @Param: [refundState]
    * @return: com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbRefund>
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    List<TbRefund> getListRefundByState(String refundState);




    /**
     * @Description: 根据订单编码查看退款单（并分页）
     * @Param: [map]
     * @return: java.util.List<cn.com.connext.oms.entity.TbRefund>
     * @Author: Lili Chen
     * @Date: 2019/1/15
     */
    List<TbRefund> getListRefundByStateIndex(Map map);


    /**
    * @Description: 根据订单id查看退款单
    * @Param: [orderId]
    * @return: com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbRefund>
    * @Author: Lili Chen
    * @Date: 2019/1/13
    */
    List<TbRefund> getListRefundByOrderId(Integer orderId);



    /**
    * @Description: 根据订单id查看订单
    * @Param: [map]
    * @return: java.util.List<cn.com.connext.oms.entity.TbRefund>
    * @Author: Lili Chen
    * @Date: 2019/1/15
    */
    List<TbRefund> getListRefundByOrderIdIndex(Map map);


}