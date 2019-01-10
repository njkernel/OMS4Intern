package cn.com.connext.oms.mapper;

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
    List<TbOrder> getAllOrder();


    /**
     * create by: Aaron
     * description: 根据id查询订单详情
     * create time: 2019/1/7 15:30
     *
     *
     * @return  * @Param: null
     */
    List<TbOrder> getOrderByOrderId( int orderId);

    /**
     * create by: Aaron
     * description: TODO
     * create time: 2019/1/8 11:09
     * @return  * @Param: null
     */
    Date selectCreatedById(int orderId);



}