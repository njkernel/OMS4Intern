package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.output.OutRepoOrderDetailDto;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.*;
import cn.com.connext.oms.service.OutputService;
import cn.com.connext.oms.web.Api.output.OutputApi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: OutputServiceImpl</p>
 * <p>Description: 出库模块，包含查询订单详情，出库单详情，更改状态等</p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/7
 */
@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbReceiverMapper tbReceiverMapper;
    @Autowired
    private TbOutputMapper tbOutputMapper;
    @Autowired
    private TbGoodsOrderMapper tbGoodsOrderMapper;
    @Autowired
    private TbStockMapper tbStockMapper;
    // 定义状态
    private static String STATUS0="南京仓";
    private static String STATUS1="待处理";
    private static String STATUS2="待路由";
    private static String STATUS3="待出库";
    private static String STATUS4="已出库";
    private static String STATUS5="已发货";
    private static String STATUS6="出库异常";
    private static String STATUS7="处理中";
    private static String STATUS8="已完成";
    private static String STATUS9="已取消";


    /**
     *
     * 功能描述: 根据订单id将待路由状态改为待出库
     *
     * @param: id数组
     * @return: 返回修改结果
     * @auther:Jay
     * @date:  2018/1/7
     */
    @Override
    public BaseResult UpdateOrderIntoWaitOutPut(int[] id) {
        // 定义 m 作为统计返回"路由成功"的条数
        int m = 0;
        // 定义 n 作为统计返回"路由异常"的条数
        int n = 0;
        // 定义 k 作为统计返回"不符合状态"的条数
        int k = 0;
        // 遍历数组 id
        for (int id1:id){
            // 根据订单id查询整个订单数据
            TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id1);
            // 获取订单状态
            String orderState = tbOrder.getOrderState();
            if (orderState.equals(STATUS2)){
                // 设置订单状态为待出库
                tbOrder.setOrderState(STATUS3);
                // 设置更新时间，以便于排序
                tbOrder.setUpdated(new Date());
                // 更改订单状态
                int t = tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
                if (t==1){
                    // 路由成功，设置发货仓库为南京仓
                    tbOrder.setDeliveryWarehouse(STATUS0);
                    tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
                    m++;
                } else {
                    n++;
                }
            } else {
                k++;
            }
        }
        // 创建集合存放路由成功、路由异常、不符合订单状态的数据
        ArrayList<Integer> list = new ArrayList<>();
        list.add(m);
        list.add(n);
        list.add(k);
        return BaseResult.success("mes",list);
    }

    /**
     *
     * 功能描述: 根据传入的数组id生成出库单，并根据WMS返回值修改订单状态为已出库或者出库异常
     *
     * @param: 订单id
     * @return: 返回修改结果
     * @auther: Jay
     * @date: 2019/1/7
     */
    @Override
    public BaseResult Output(int[] id1) {
        // 定义 m 作为统计返回"出库成功"的条数
        int m = 0;
        // 定义 n 作为统计返回"出库异常"的条数
        int n = 0;
        // 定义 k 作为统计返回"不符合状态"的条数
        int k = 0;
        // 遍历前台选中的id数组
        for (int id:id1) {
            // 根据id查询出所有的订单信息
            TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id);
            List<OutRepoOrderDetailDto> repoOrderDetailDto = tbOutputMapper.getOutRepoOrderDetailDto(id);
            if (tbOrder.getOrderState().equals(STATUS3) || tbOrder.getOrderState().equals(STATUS6)) {
                // 根据收获人信息查询收获人信息
                TbReceiver tbReceiver = tbReceiverMapper.selectByPrimaryKey(tbOrder.getReceiverId());
                // 生成出库单，如果是待出库生成新的出库单，如果是出库异常使用原有的出库单
                TbOutput tbOutput = this.getOneTbOutput(id);
                //传送出库单并接受返回值类型判断是否接收成功
                String s = null;
                try {
                    // 调用WMS的接口，推送出库单，成功返回 200
                    s = OutputApi.post(tbOutput, tbOrder, tbReceiver, repoOrderDetailDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 判断接收的结果 200 表示接收成功
                if ("200".equals(s)) {
                    List<TbGoodsOrder> goodsDetailByOrderId = tbGoodsOrderMapper.getGoodsDetailByOrderId(id);
                    for (int i = 0;i<goodsDetailByOrderId.size();i++){
                        int goodsId = goodsDetailByOrderId.get(i).getGoodsId();
                        int goodsNum = -goodsDetailByOrderId.get(i).getNum();
                        // 更新锁定库存
                        tbStockMapper.updateLockdAndAvailable(goodsId,goodsNum);
                        // 获取总库存
                        int totalStock = tbStockMapper.getLocked(goodsId).getTotalStock()+goodsNum;
                        // 更新总库存
                        tbStockMapper.updateStock(goodsId,totalStock);
                        // 获取锁定库存
                        int lockStock = tbStockMapper.getLocked(goodsId).getLockedStock();
                        // 计算可使用库存
                        int availabeStock = totalStock-lockStock;
                        // 更新可使用的库存
                        tbStockMapper.updateAvailable(goodsId,availabeStock);
                    }
                    // 更新订单状态为已完成，并更新时间
                    tbOrder.setOrderState(STATUS4);
                    tbOrder.setUpdated(new Date());
                    tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
                    // 根据id更新出库单为处理中，并更新时间
                    tbOutputMapper.updateOutput(STATUS7,new Date(),tbOutput.getOrderId());
                    m++;
                } else {
                    // 状态不是200的一切情况,设置订单状态为出库异常和更新最新时间
                    tbOrder.setOrderState(STATUS6);
                    tbOrder.setUpdated(new Date());
                    tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
                    // 根据订单id更新出库单状态为出库异常和更新最新时间
                    tbOutputMapper.updateOutput(STATUS6,new Date(),tbOutput.getOrderId());
                    n++;
                }
            } else {
                k++;
            }
        }
        // 创建集合存放出库成功、出库异常、不符合订单状态的数据
        ArrayList<Integer> list = new ArrayList<>();
        list.add(m);
        list.add(n);
        list.add(k);
        return BaseResult.success("mes",list);
    }
    /**
     *
     * 功能描述: 根据订单id查询出所有出库单的详情
     *
     * @param: 订单id
     * @return: 返回订单的详情
     * @auther: Jay
     * @date: 2019/1/8
     */
    @Override
    public List<TbOrderDetails> orderDetails(int orderId) {
        return tbOutputMapper.orderDetails(orderId);
    }
    /**
     * 功能描述: 点击出库单列表，显示所有已出库的订单,以及模糊查询选择符合条件的订单,默认显示所有已出库的订单
     *
     * @return: 返回所有状态是已出库的订单，以及模糊查询选择符合条件的订单
     * @param: currentPage: 当前页， pageSize： 总页数, orderId： 订单id，outputCode ：出库单号, deliveryCode ：快递单号
     * @auther: Jay
     * @date: 2019/1/13
     */
    @Override
    public PageInfo<TbOrderDetails> getAllOrderByStatusAndSeacrch(int currentPage,int pageSize,String orderId, String outputCode, String deliveryCode) {
        // 从第 1 页开始，每一页 5 条数据
        PageHelper.startPage(currentPage, pageSize);
        // 返回所有状态是已出库的订单，模糊查询选择符合条件的订单，默认显示所有已出库订单
        List<TbOrderDetails> allOrder = tbOutputMapper.getOutputOrdersBySearch(STATUS4,STATUS5,STATUS8,STATUS9,orderId,outputCode,deliveryCode);
        PageInfo<TbOrderDetails> pageInfo = new PageInfo<>(allOrder);
        return pageInfo;
    }
    @Override
    public TbOrder getOrderById(Integer orderId) {
        return tbOrderMapper.selectByPrimaryKey(orderId);
    }
    /**
     *
     * 功能描述: 根据传过来的发货信息更新订单数据
     *
     * @param: 订单发货信息
     * @return 返回200表示接收并更新成功
     * @auther: Jay
     * @date: 2019/1/9
     */
    @Override
    public String updateOrder(TbOrder tbOrder) {
        int t = tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
        // 判断是否更新成功 200表示更新成功
        if (t==1){
        return "200";
        }
        return "error";
    }

    @Override
    public TbOutput getOutputOrder(int orderId) {
        TbOutput tbOutput = tbOutputMapper.getOutputOrderById(orderId);
        return tbOutput;
    }
    /**
     *
     * 功能描述: 更改出库单的状态
     *
     * @param: 出库单
     * @return: 200 修改成功  error 修改失败
     * @auther: Jay
     * @date: 2019/1/9
     */
    @Override
    public String updateOutput(TbOutput tbOutput) {
        // 更改出库单的状态
        int t = tbOutputMapper.updateByPrimaryKeySelective(tbOutput);
        if (t==1){
            // 修改成功
            return "200";
        }
        // 修改失败
        return "error";
    }

    /**
     * 功能描述: 确认收货后将订单状态修改为已完成
     *
     * @param: 订单id
     * @return: 修改成功或者修改失败
     * @auther: Jay
     * @date: 2019/1/21
     */
    @Override
    public BaseResult confirmReceiptUpdateOrderState(int orderId) {
        // 根据订单id获取订单
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        // 获取订单状态
        String state = tbOrder.getOrderState();
        // 判断订单状态是否是已发货
        if (state.equals(STATUS5)){
            // 设置订单状态为已完成
            tbOrder.setOrderState(STATUS8);
            tbOrder.setUpdated(new Date());
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            // 设置出库单状态为已完成
            tbOutputMapper.updateOutput(STATUS8,new Date(),orderId);
            return BaseResult.success("确认收货成功！");
        }
        return BaseResult.fail("订单状态必须为已发货，请重新选择！");
    }
    /*
     *
     * 功能描述: 判断是否生成新的出库单，当订单异常时，不生成新的出库单
     *
     * @param:  订单id
     * @return:  返回出库单
     * @auther: Jay
     * @date: 2019/1/16
     * */

    public TbOutput getOneTbOutput(int id){
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id);
        // 判断订单状态是否是出库异常，如果是返回出库单，如果不是生成新的出库单
        if (tbOrder.getOrderState().equals(STATUS6)){
            TbOutput tbOutput = tbOutputMapper.getOutputByOrderId(id);
            return tbOutput;
        } else {
            // 生成出库单，设置出库单属性
            TbOutput tbOutput = new TbOutput();
            String outputCode = id+""+UUID.randomUUID().toString().substring(1,8);
            //生成出库单
            tbOutput.setOutputCode(outputCode);
            tbOutput.setOrderId(id);
            tbOutput.setOutputState(STATUS1);
            tbOutput.setCreated(new Date());
            tbOutput.setUpdated(new Date());
            tbOutputMapper.insertSelective(tbOutput);
            return tbOutput;
        }
    }
}
