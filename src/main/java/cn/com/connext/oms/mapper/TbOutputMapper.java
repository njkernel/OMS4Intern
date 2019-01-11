package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.OutRepoOrderDetailDto;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbOutputMapper extends MyMapper<TbOutput> {
    /**
     *
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
}