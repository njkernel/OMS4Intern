package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.InputDTO;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.entity.TbReturnGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
import java.util.List;

/**
 * create by: Aaron
 * description: 退货相关的dao
 * create time: 2019/1/8 13:47
 *
 *
 * @return  * @Param: null
 */

@Repository
public interface TbReturnMapper extends MyMapper<TbReturn> {
    /**
     * 根据退货单增加行营的商品列表
     * @param orderId
     * @param goodsId
     * @param number
     * @return boolean
     */
    boolean addReturnOrderGoods(@Param("orderId") int orderId,@Param("goodsId") int goodsId, @Param("number") int number);

    /**
     * 退货单取消
     * @param returnId
     * @param modifiedUser
     * @param updated
     * @return
     */
    boolean returnOrderCancel(@Param("returnId") int returnId, @Param("modifiedUser") String modifiedUser, @Param("updated") Date updated);

    /**
     * 根据退货单id查找退货单
     * @param returnId
     * @return
     */
    String selectReturnOrderStateById(@Param("returnId") int returnId);

    /**
     * 更具退货单id查找退货单状态
     * @param returnId
     * @param state
     * @param user
     * @param updated
     * @return
     */
    boolean updateReturnOrderStateById(@Param("returnId") int returnId,@Param("state") String state,@Param("user") String user, @Param("updated") Date updated);

    /**
     * 更具退货单查找订单完成时间
     * @param goodId
     * @return
     */
    double getPriceByGoodId(@Param("goodId") int goodId);

    /**
     * 添加退货单
     * @param returnCode
     * @param returnState
     * @param orderId
     * @param returnPrice
     * @param created
     * @param modifiedUser
     * @param updated
     * @param returnType
     * @return
     */
    boolean addReturnOrder( @Param("returnCode") String returnCode,@Param("returnState") String returnState,@Param("orderId") int orderId,@Param("returnPrice") double returnPrice,@Param("created") Date created,@Param("modifiedUser") String modifiedUser,@Param("updated") Date updated,@Param("returnType") String returnType);

    /**
     * 根据订单id查找退货单id
     * @param returnId
     * @return
     */
    int selectOrderIdByReturnId(@Param("returnId") int returnId);

    /**
     * 生成入库单
     * @param inputCode
     * @param orderId
     * @param inputState
     * @param created
     * @param updated
     * @param synchronizeState
     * @return
     */
    boolean createInputOrder(@Param("inputCode") String inputCode,@Param("orderId") int orderId,@Param("inputState") String inputState,@Param("created") Date created,@Param("updated") Date updated,@Param("synchronizeState") String synchronizeState );

    /**
     * 根据退货单id1取退货单
     * @param returnId
     * @return
     */
    TbReturn getTbReturnById(@Param("returnId") int returnId);

    /**
     * 根据订单id查找退货/换货
     * @param orderId
     * @return
     */
    String getTypeByorderId (@Param("orderId") int orderId);

    /**
     * 查找所有的入库单详情
     * @return
     */
    List<InputDTO> getAllInputOrders();

    /**
     * 根据订单id获取入库单
     * @param orderId
     * @return
     */
    TbInput getInputByOrderId(@Param("orderId") int orderId);

    /**
     * 根据订单id查找退货单商品信息
     * @param orderId
     * @return
     */
    List<TbReturnGoods> getTbReturnGoodsById(@Param("orderId")int orderId);

    /**
     * 根据商品id查询商品信息
     * @param goodsId
     * @return
     */
    TbGoods getGoodsById(@Param("goodsId") int goodsId);
}