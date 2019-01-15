package cn.com.connext.oms.service;

import cn.com.connext.oms.commons.dto.InputDTO;
import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.entity.TbReturnGoods;
import com.github.pagehelper.PageInfo;
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
     * @param returnList
     * @param modifiedUser
     * @param updated
     * @return boolean
     */
    boolean returnOrderCancel( List<Integer> returnList, String modifiedUser, Date updated);

    /**
     * 退货单审核
     * @param returnIds
     * @return
     */
    List<Integer> returnOrdersAudit (List<Integer> returnIds);

    /**
     *添加退货单
     * @param tbReturn
     * @return boolean
     */
    boolean addReturnOrder(TbReturn tbReturn);

    /**
     * 生成出库单并发送
     * @param returnIdList
     * @return TbInput
     */
    boolean createInputOrder(List<Integer> returnIdList);

    /**
     * 根据id查找相对应的退货/换货单
     * @param returnId
     * @return
     */
    TbReturn getTbReturnById(int returnId);

    /**
     * 根据wms反馈更新入库单及退货单状态
     * @param inputFeedback
     * @return
     */
    int updateStateByFeedback(InputFeedback inputFeedback);

    /**
     * 查找所有的入库单详情
     * @return
     */
    PageInfo<InputDTO> getAllInputOrders(Integer currentPage, Integer pageSize);

    /**
     * 根据订单id查找退货单
     * @param orderId
     * @return
     */
    TbInput getInputByOrderId(int orderId);

    /**
     * 根据订单id查找退货单商品信息
     * @param orderId
     * @return
     */
    List<TbReturnGoods> getTbReturnGoodsById(int orderId);
}
