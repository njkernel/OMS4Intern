package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbReturnGoods;

import java.util.List;

public interface TbReturnGoodsService {

    /**
    * @Description: 根据订单id查看退货商品表
    * @Param: [orderId]
    * @return: java.util.List<cn.com.connext.oms.entity.TbReturnGoods>
    * @Author: Lili Chen
    * @Date: 2019/1/23
    */
    List<TbReturnGoods> getListReturnGoodsByOrderId(Integer orderId);
}
