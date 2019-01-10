package cn.com.connext.oms.mapper;

import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.entity.*;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    public List<TbReturn> selectAllExchangeOrders(@Param("returnType")String returnType);

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
    public int insertOutput(@Param("tbOutput")TbOutput tbOutput);

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

}
