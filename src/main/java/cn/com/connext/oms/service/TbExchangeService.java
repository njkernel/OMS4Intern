package cn.com.connext.oms.service;

import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbReturn;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 10:49
 * @describe: 换货service层接口
 **/
public interface TbExchangeService {

    /**
     * create by: yonyong
     * description: 分页查询所有退换货信息列表
     * create time: 2019/1/8 23:14
     *
     *  * @Param: currentPage
      * @Param: pageSize
      * @Param: retuenId
     * @return com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbReturn>
     */
    public PageInfo<TbReturn> showAllReturns(Integer currentPage,Integer pageSize,String returnType);

    /**
     * create by: yonyong
     * description: 查询退换货单详情信息
     * create time: 2019/1/9 0:04
     *
     *  * @Param: OrderId
     * @return cn.com.connext.oms.commons.dto.exchange.ReturnDetails
     */
    public ReturnDetails selectReturnDetailsByOrderId(int orderId);

    /**
     * create by: yonyong
     * description: 生成换货单对应的换货商品表tb_return_goods表
     * create time: 2019/1/7 17:24
     *
     * @Param: orderId
     * @Param: goodsId
     * @Param: num
     * @return
     */
    public void toGenerateExchangeOrderGoods(int orderId,
                                             int[] goodsId,
                                             int[] number);

    /**
     * create by: yonyong
     * description: 生成换货单对应的tb_return表
     * create time: 2019/1/7 17:48
     *
     *  * @Param: tbReturn
     * @return void
     */
    public void toGenerateExchangeOrder(TbReturn tbReturn);

    /**
     * create by: yonyong
     * description: 根据商品id查询商品信息
     * create time: 2019/1/7 21:07
     *
     *  * @Param: goodId
     * @return cn.com.connext.oms.entity.TbGoods
     */
    public TbGoods toSelectGoodById(int goodId);

    /**
     * create by: yonyong
     * description: TbReturn 设置换货单接口
     * create time: 2019/1/7 21:41
     *
     *  * @Param: orderId
      * @Param: goodId
      * @Param: num
      * @Param: tbGoods
     * @return cn.com.connext.oms.entity.TbReturn
     */
    public TbReturn setTbReturn(int orderId, int[] goodId, int[] num);


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
    public int updateTbReturn(Integer[] ids,String state, String modifiedUser,Date update);

    /**
     * create by: yonyong
     * description: 审核换货单
     * create time: 2019/1/7 22:39
     *
     *  * @Param: ids
      * @Param: state
      * @Param: modifiedUser
      * @Param: update
     * @return int
     */
    public int AuditTbReturn(int[] ids,String modifiedUser, Date update);

    /**
     * create by: yonyong
     * description: 生成入库单
     * create time: 2019/1/7 23:52
     *
     *  * @Param: orderId
     * @return int
     */
    public int generateInput(int[] ids);

    /**
     * create by: yonyong
     * description: 生成出库单
     * create time: 2019/1/7 01：38
     *
     *  * @Param: inputFeedback
      * @Param: goodCode
      * @Param: num
     * @return int
     */
    public int generateOutput(InputFeedback inputFeedback);

    /**
     * create by: yonyong
     * description: 根据Tbreturn_id查询对应的信息
     * create time: 2019/1/9 11:34
     *
     *  * @Param: returnId
     * @return cn.com.connext.oms.entity.TbReturn
     */
    public TbReturn selectTbReturnByReturnId(int returnId);
}
