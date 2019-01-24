package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.AbnormalGoodsOrderDTO;
import cn.com.connext.oms.entity.TbAbnormal;
import cn.com.connext.oms.entity.TbPermission;
import cn.com.connext.oms.entity.TbStock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbAbnormalMapper extends MyMapper<TbAbnormal> {
    /**
    * @Author: caps
    * @Description:根据商品id查看库存
    * @Param: [goodsId]
    * @Return: cn.com.connext.oms.entity.TbStock
    * @Create: 2019/1/15 1:59
    */

    TbStock selectStockByGoodsId(@Param("goodsId") int goodsId);

    /**
    * @Author: caps
    * @Description:根据订单id查看商品信息
    * @Param: [orderId]
    * @Return: java.util.List<cn.com.connext.oms.commons.dto.AbnormalGoodsOrderDTO>
    * @Create: 2019/1/15 1:59
    */

    @Select({ "select `to`.total_price,`to`.num,g.goods_price,g.goods_name,g.goods_code from tb_goods_order AS `to`,tb_goods AS g where `to`.goods_id = g.goods_id and `to`.order_id =#{orderId}" })
    List<AbnormalGoodsOrderDTO> getAbnormalGoodsOrderDTOByOrderId(Integer orderId);
}