package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.output.OutRepoOrderDetailDto;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbReceiver;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

@Repository
public interface TbOutputMapper extends MyMapper<TbOutput> {
/**
    * @Description: 批量修改出库单的状态
    * @Param: [outputList]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    int updateOutputListStatue(List<TbOutput> outputList);

    /**
    * @Description: 根据订单id查看出库单
    * @Param: [orderId]
    * @return: cn.com.connext.oms.entity.TbOutput
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    TbOutput getOutputByOrderId(Integer orderId);
    
    /** 
    * @Description: 根据出库单id查看出库单
    * @Param: [outputId] 
    * @return: cn.com.connext.oms.entity.TbOutput 
    * @Author: Lili Chen 
    * @Date: 2019/1/8 
    */
    TbOutput getOutputByOutputId(Integer outputId);

    
    /** 
    * @Description: 根据出库单编号查看出库单 
    * @Param: [outputCode] 
    * @return: cn.com.connext.oms.entity.TbOutput 
    * @Author: Lili Chen 
    * @Date: 2019/1/13 
    */
    TbOutput getOutputByOutputCode(String outputCode);
    /*
     * 功能描述: 根据订单id查询出所有出库单的详情
     *
     * @param: 订单id
     * @return: 订单的详情集合
     * @auther: Jay
     * @date: 2019/1/8
     */
    List<TbOrderDetails> orderDetails(int orderId);
    /**
     *
     * 功能描述: 根据订单id查询基本出库单状态
     *
     * @param:
     * @return:
     * @auther: Jay
     * @date: 2019/1/9
     */
    TbOutput getOutputOrderById(int orderId);
    /**
     *
     * 功能描述:  根据订单id查询出库单的数据
     *
     * @param:
     * @return:
     * @auther: Jay
     * @date: ${DATE}
     */
    List<OutRepoOrderDetailDto> getOutRepoOrderDetailDto(int id);

    /**
     * 功能描述: 点击出库单列表，显示所有已出库的订单,以及模糊查询选择符合条件的订单,默认显示所有已出库的订单
     *
     * @return: 返回所有状态是已出库的订单，以及模糊查询选择符合条件的订单
     * @param: currentPage: 当前页， pageSize： 总页数, orderId： 订单id，outputCode ：出库单号, deliveryCode ：快递单号
     * @auther: Jay
     * @date: 2019/1/13
     */
    List<TbOrderDetails> getOutputOrdersBySearch(@Param("state") String state, @Param("state2") String state2,@Param("state3") String state3,@Param("orderId") String orderId, @Param("outputCode") String outputCode, @Param("deliveryCode") String deliveryCode);
    /**
     * 功能描述: 更改出库单的状态，最新时间
     *
     * @param: 状态、时间、订单id
     * @auther: Jay
     * @date: 2019/1/23
     */
    void updateOutput(@Param("state") String state, @Param("date") Date date, @Param("orderId") int orderId);
}