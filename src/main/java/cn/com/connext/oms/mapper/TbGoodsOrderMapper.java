package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbGoodsOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbGoodsOrderMapper extends MyMapper<TbGoodsOrder> {
    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询该订单下所有的商品编号
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 11:42
     */
    public List<Integer> getGoodOrderById(int orderId);


}