package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbOutput;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

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


    TbOutput getOutputByOutputCode(String outputCode);
}