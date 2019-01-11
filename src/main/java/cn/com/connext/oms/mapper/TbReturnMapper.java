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
 *
 *
 * @return  * @Param: null
 */

@Repository
public interface TbReturnMapper extends MyMapper<TbReturn> {

    boolean addReturnOrderGoods(@Param("orderId") int orderId,@Param("goodsId") int goodsId, @Param("number") int number);

    boolean returnOrderCancel(@Param("returnId") int returnId, @Param("modifiedUser") String modifiedUser, @Param("updated") Date updated);

    String selectReturnOrderStateById(@Param("returnId") int returnId);

    boolean updateReturnOrderStateById(@Param("returnId") int returnId,@Param("state") String state,@Param("user") String user, @Param("updated") Date updated);

    double getPriceByGoodId(@Param("goodId") int goodId);

    boolean addReturnOrder( @Param("returnCode") String returnCode,@Param("returnState") String returnState,@Param("orderId") int orderId,@Param("returnPrice") double returnPrice,@Param("created") Date created,@Param("modifiedUser") String modifiedUser,@Param("updated") Date updated);

    int selectOrderIdByReturnId(@Param("returnId") int returnId);

    boolean createInputOrder(@Param("inputCode") String inputCode,@Param("orderId") int orderId,@Param("inputState") String inputState,@Param("created") Date created,@Param("updated") Date updated,@Param("synchronizeState") String synchronizeState );

    TbReturn getTbReturnByiId(@Param("returnId") int returnId);
}