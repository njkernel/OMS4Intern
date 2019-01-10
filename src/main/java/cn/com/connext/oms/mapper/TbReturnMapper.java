package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;
/**
 * create by: Aaron
 * description: 退货相关的dao
 * create time: 2019/1/8 13:47
 * @return  * @Param: null
 */

@Repository
public interface TbReturnMapper extends MyMapper<TbReturn> {
    /**
     * @author Aaron
     * @param orderId
     * @param goodsId
     * @param number
     * @return 添加信息到退货单对应的商品表
     */
    boolean addReturnOrderGoods(@Param("orderId") int orderId,@Param("goodsId") int goodsId, @Param("number") int number);

    /**
     * 退货单取消
     * @author Aaron
     * @param returnId
     * @param modifiedUser
     * @param updated
     * @return boolean
     */
    boolean returnOrderCancel(@Param("returnId") int returnId, @Param("modifiedUser") String modifiedUser, @Param("updated") Date updated);

    /**
     * 根据退货单id获取退货单状态
     * @author Aaron
     * @param returnId
     * @return String
     */
    String selectReturnOrderStateById(@Param("returnId") int returnId);

    /**
     * 根据id更新退货单状态
     * @author Aaron
     * @param returnId
     * @param state
     * @param user
     * @param updated
     * @return boolean
     */
    boolean updateReturnOrderStateById(@Param("returnId") int returnId,@Param("state") String state,@Param("user") String user, @Param("updated") Date updated);

    /**
     * 根据订单商品获取
     *
     * @param goodId
     * @return double
     */
    double getPriceByGoodId(@Param("goodId") int goodId);

    /**
     * 添加退货单
     * @author Aaron
     * @param returnCode
     * @param returnState
     * @param orderId
     * @param returnPrice
     * @param created
     * @param modifiedUser
     * @param updated
     * @return boolean
     */
    boolean addReturnOrder( @Param("returnCode") String returnCode,@Param("returnState") String returnState,@Param("orderId") int orderId,@Param("returnPrice") double returnPrice,@Param("created") Date created,@Param("modifiedUser") String modifiedUser,@Param("updated") Date updated);

    /**
     * 根据订单id查询订单
     * @author Aaron
     * @param returnId
     * @return int
     */
    int selectOrderIdByReturnId(@Param("returnId") int returnId);

    /**
     * 生成入库单
     * @author Aaron
     * @param inputCode
     * @param orderId
     * @param inputState
     * @param created
     * @param updated
     * @param synchronizeState
     * @return boolean
     */
    boolean createInputOrder(@Param("inputCode") String inputCode,@Param("orderId") int orderId,@Param("inputState") String inputState,@Param("created") Date created,@Param("updated") Date updated,@Param("synchronizeState") String synchronizeState );

    /**
     * 根据退货单id获取退货单
     * @author Aaron
     * @param returnId
     * @return TbReturn
     */
    TbReturn getTbReturnByiId(@Param("returnId") int returnId);
}