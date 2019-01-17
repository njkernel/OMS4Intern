package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 10:51
 * @describe: 换货模块Mapper层
 **/
@Repository
public interface TbExchangeMapper {

    /**
     * create by: yonyong
     * description: 分页查询加模糊查询所有换货单信息
     * create time: 2019/1/8 0:46
     *
     *  * @Param: currentPage
      * @Param: pageSize
      * @Param: returnId
     * @return com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbReturn>
     */
    public List<TbReturn> selectAllExchangeOrders(@Param("tbReturn")TbReturn tbReturn);

//    /**
//     * create by: yonyong
//     * description: 查询退换货单详情信息
//     * create time: 2019/1/9 0:01
//     *
//     *  * @Param: returnId
//     * @return cn.com.connext.oms.commons.dto.exchange.ReturnDetails
//     */
//    public ReturnDetails selectReturnDetailsByTbReturnId(@Param("returnId")int returnId);

    /**
     * create by: yonyong
     * description: 生成换货单对应的换货商品表tb_return_goods表
     * create time: 2019/1/7 17:24
     *
     * @Param: tbReturnGoods
     * @return
     */
    public void toGenerateExchangeOrderGoods(List<TbReturnGoods> tbReturnGoods);

    /**
     * create by: yonyong
     * description: 生成换货单对应的tb_return表
     * create time: 2019/1/7 17:48
     *
     *  * @Param: tbReturn
     * @return void
     */
    public void toGenerateExchangeOrder(@Param("tbReturn")TbReturn tbReturn);

    /**
     * create by: yonyong
     * description: 根据商品id查询商品信息
     * create time: 2019/1/7 21:07
     *
     *  * @Param: goodId
     * @return cn.com.connext.oms.entity.TbGoods
     */
    public TbGoods toSelectGoodById(@Param("goodId")int goodId);

    /**
     * create by: yonyong
     * description: 根据商品code查询商品信息
     * create time: 2019/1/10 17:40
     *
     *  * @Param: goodCode
     * @return cn.com.connext.oms.entity.TbGoods
     */
    public TbGoods toSelectGoodByCode(@Param("goodsCode")String goodsCode);

    /**
     * create by: yonyong
     * description: 批量取消换货单
     * create time: 2019/1/7 21:57
     *
     *  * @Param: ids
      * @Param: state
      * @Param: modifiedUser
      * @Param: update
     * @return int
     */
    public int updateTbReturn(List<TbReturn> tbReturns);

    /**
     * create by: yonyong
     * description: 根据returnId查询Tb_Return表中的相对应的信息
     * create time: 2019/1/7 22:47
     *
     *  * @Param: returnId
     * @return cn.com.connext.oms.entity.TbReturn
     */
    public TbReturn selectTbReturnById(@Param("returnId")int returnId);

    /**
     * create by: yonyong
     * description: 根据orderId查询Tb_Return表中的相对应的信息
     * create time: 2019/1/8 22:18
     *
     *  * @Param: orderId
     * @return cn.com.connext.oms.entity.TbReturn
     */
    public TbReturn selectTbReturnByOrderId(@Param("orderId")int orderId);

    /**
     * create by: yonyong
     * description: 根据orderId查询tb_return_goods表中的所有相对应的信息
     * create time: 2019/1/8 17:32
     *
     *  * @Param: orderId
     * @return java.util.List<cn.com.connext.oms.entity.TbReturnGoods>
     */
    public List<TbReturnGoods> selectTbReturnGoodsByOrderId(@Param("orderId") int orderId);

//    /**
//     * create by: yonyong
//     * description: 审核换货单
//     * create time: 2019/1/7 22:39
//     *
//     *  * @Param: ids
//      * @Param: state
//      * @Param: modifiedUser
//      * @Param: update
//     * @return int
//     */
//    public int AuditTbReturn(@Param("ids")int[] ids,@Param("state")String state,@Param("modifiedUser") String modifiedUser, @Param("update")Date update);

    /**
     * create by: yonyong
     * description: 生成入库表
     * create time: 2019/1/7 23:58
     *
     *  * @Param: tbInput
     * @return int
     */
    public int insertInput(@Param("tbInput")TbInput tbInput);

    /**
     * create by: yonyong
     * description: 生成出库表
     * create time: 2019/1/8 0:14
     *
     *  * @Param: tbOutput
     * @return int
     */
//    public int insertOutput(@Param("tbOutput")TbOutput tbOutput);

    /**
     * create by: yonyong
     * description: 修改入库单
     * create time: 2019/1/8 22:01
     *
     *  * @Param: tbInput
     * @return int
     */
    public int updateTbInput(@Param("tbInput")TbInput tbInput);

    public TbInput selectTbInputByOrderId(@Param("orderId") int orderId);


    /**
     * create by: yonyong
     * description: 给换货单生成新订单
     * create time: 2019/1/10 23:29
     *
     *  * @Param: tbOrder
     * @return int
     */
    public int insertOrder(@Param("tbOrder")TbOrder tbOrder);

    /**
     * create by: yonyong
     * description: 生成相应的订单商品表
     * create time: 2019/1/10 23:46
     *
     *  * @Param: tbGoodsOrder
     * @return int
     */
    public int insertGoodsOrders(List<TbGoodsOrder> tbGoodsOrders);

    /**
     * create by: yonyong
     * description: 建立换货单生成的新订单与原订单之间的联系
     * create time: 2019/1/10 23:42
     *
     *  * @Param: tbExchangeOrderRelations
     * @return int
     */
    public int insertExchangeOrderRelations(@Param("tbExchangeOrderRelations")TbExchangeOrderRelations tbExchangeOrderRelations);

    /**
     * create by: yonyong
     * description: 查询一个订单是否是换货单
     * create time: 2019/1/10 23:42
     *
     *  * @Param: oldOrderId
     * @return cn.com.connext.oms.entity.TbExchangeOrderRelations
     */
    public TbExchangeOrderRelations selectExchangeOrderRelationsByOldOrderId(@Param("oldOrderId")int oldOrderId);

    /**
     * create by: yonyong
     * description: 根据订单id删除订单商品表相关信息
     * create time: 2019/1/12 18:21
     *
     *  * @Param: orderId
     * @return int
     */
    public int deleteGoodsOrders(@Param("orderId") int orderId);


    /**
     * create by: yonyong
     * description: 根据订单id删除订单
     * create time: 2019/1/12 18:23
     *
     *  * @Param: orderId
     * @return int
     */
    public int deleteOrder(@Param("orderId")int orderId);

    /**
     * create by: yonyong
     * description: 根据订单id删除关联表
     * create time: 2019/1/12 18:31
     *
     *  * @Param: orderId
     * @return int
     */
    public int deleteExchangeOrderRelations(@Param("orderId")int orderId);

    public int selectCountOfOrder();

    /**
     * create by: yonyong
     * description: 根据订单id orderId查询收货人信息
     * create time: 2019/1/17 13:06
     *
     *  * @Param: orderId
     * @return int
     */
    public TbReceiver selectTbReceiverByOrderId(@Param("orderId")int orderId);

    /**
     * create by: yonyong
     * description: 添加收货人与订单关联信息记录
     * create time: 2019/1/17 13:06
     *
     *  * @Param: orderId
     * @return int
     */
    public int insertReciver(@Param("tbReceiver")TbReceiver tbReceiver);
}
