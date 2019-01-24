package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.GoodsGoodsOrderDto;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbGoodsOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TbGoodsOrderMapper extends MyMapper<TbGoodsOrder> {
    /**
    * @Description: 根据订单id查看相应的关系表
    * @Param: [orderId]
    * @return: java.util.List<cn.com.connext.oms.entity.TbGoodsOrder>
    * @Author: Lili Chen
    * @Date: 2019/1/11
    */
   List<TbGoodsOrder> getStockByOrderId(Integer orderId);
    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询该订单下所有的商品编号
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2019/1/7 11:42
     */
    public List<Integer> getGoodsOrderById(int orderId);
    /**
     * @Author: zhaojun
     * @Description: 根据订单编号查询当前订单的商品信息
     * @Param: []
     * @Return: cn.com.connext.oms.commons.dto.BaseResult
     * @Create: 2018/1/8 16:03
     */
    public List<GoodsGoodsOrderDto> goodsListFromOrder(int orderId);
    
    
    /** 
    * @Description: 根据订单id查看到订单商品列表 
    * @Param: [orderId] 
    * @return: java.util.List<cn.com.connext.oms.entity.TbGoodsOrder> 
    * @Author: Lili Chen 
    * @Date: 2019/1/15 
    */
    public List<TbGoodsOrder> getListGoodsOrderById(Integer orderId);
    /**
     * 功能描述:根据order_id查询goods_order_id
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/24 11:44
     */
    public List<TbGoodsOrder> getGoodsDetailByOrderId (Integer orderId);


}