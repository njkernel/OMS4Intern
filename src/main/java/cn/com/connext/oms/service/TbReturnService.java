package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbReturn;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 15:46
 **/


public interface TbReturnService {
    /**
     *生成退货单对应的商品表
     * @param orderId
     * @param goodsId
     * @param number
     * @return boolean
     */
    boolean addReturnOrderGoods(int orderId,List<Integer>  goodsId,List<Integer> number);

    /**
     * 生成退货单
     * @param orderId
     * @param goodsIdList
     * @param numberList
     * @return TbRetur
     */
    TbReturn createReturnOrder(int orderId, List<Integer> goodsIdList,List<Integer> numberList);

    /**
     * 退货单取消
     * @param returnId
     * @param modifiedUser
     * @param updated
     * @return boolean
     */
    boolean returnOrderCancel( int returnId, String modifiedUser, Date updated);

    /**
     * 退货单审核
     * @param returnIds
     * @return
     */
    boolean returnOrdersAudit (List<Integer> returnIds);

    /**
     *添加退货单
     * @param tbReturn
     * @return boolean
     */
    boolean addReturnOrder(TbReturn tbReturn);

    /**
     * 生成出库单
     * @param returnId
     * @return TbInput
     */
    TbInput createInputOrder(List<Integer> returnId);

    /**
     * 根据id查找相对应的退货/换货单
     * @param returnId
     * @return
     */
    TbReturn getTbReturnById(int returnId);
}
