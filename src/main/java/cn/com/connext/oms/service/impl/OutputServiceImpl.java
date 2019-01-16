package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.output.OutRepoOrderDetailDto;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOrderDetails;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbOutputMapper;
import cn.com.connext.oms.mapper.TbReceiverMapper;
import cn.com.connext.oms.service.OutputService;
import cn.com.connext.oms.web.Api.output.OutputApi;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    // 定义状态
    private static String STATUS1="待预检";
    private static String STATUS2="待路由";
    private static String STATUS3="待出库";
    private static String STATUS4="已出库";
    private static String STATUS6="处理中";
    private static String STATUS5="出库异常";

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
        // 遍历数组 id
        for (int id1:id){
            // 根据订单id查询整个订单数据
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id1);
             // 判断订单状态是否是待路由
        if(tbOrder.getOrderState().equals(STATUS2)){
            // 设置订单状态为待出库
            tbOrder.setOrderState(STATUS3);
            //更改订单状态
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            return BaseResult.success("成功！");
          } else {
            return BaseResult.fail("请选择状态为带路由的订单！");
             }
        }
        return BaseResult.fail("异常！");
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
    public BaseResult Output(int id) {
        //根据id查询出所有的订单信息
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(id);
        List<OutRepoOrderDetailDto> repoOrderDetailDto = tbOutputMapper.getOutRepoOrderDetailDto(id);
        if (tbOrder.getOrderState().equals(STATUS3)||tbOrder.getOrderState().equals(STATUS5)){
        //根据收获人信息查询收获人信息
        TbReceiver tbReceiver = tbReceiverMapper.selectByPrimaryKey(tbOrder.getReceiverId());
        // 生成出库单，设置出库单属性
        TbOutput tbOutput = new TbOutput();
        String outputCode = id+""+UUID.randomUUID().toString().substring(1,8);
        Date date = new Date();
        //生成出库单
        tbOutput.setOutputCode(outputCode);
        tbOutput.setOrderId(id);
        tbOutput.setOutputState(STATUS3);
        tbOutput.setCreated(date);
        tbOutput.setUpdated(date);
        tbOutputMapper.insertSelective(tbOutput);
        //传送出库单并接受返回值类型判断是否接收成功
        String s = null;
            try {
                s = OutputApi.post(tbOutput,tbOrder,tbReceiver,repoOrderDetailDto);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //判断接收的结果
        if ("200".equals(s)){
            tbOutput.setOutputState(STATUS6);
            tbOutputMapper.updateByPrimaryKeySelective(tbOutput);
            tbOrder.setOrderState(STATUS4);
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
            return BaseResult.success("出库成功！");
        } else {
            tbOutput.setOutputState(STATUS5);
            tbOutputMapper.updateByPrimaryKeySelective(tbOutput);
            tbOrder.setOrderState(STATUS5);
            tbOrderMapper.updateByPrimaryKeySelective(tbOrder);
         }
        }else {
        return BaseResult.fail("请选择待出库的订单！");
        }
        return BaseResult.fail("出库异常！");
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
     *
     * 功能描述: 根据订单id查询所有的订单详情
     *
     * @param: 订单id
     * @return: 返回选择的订单
     * @auther: Jay
     * @date: 2019/1/8
     */
    /**
     *
     * 功能描述: 根据订单状态查询所需要的订单
     *
     * @param: 订单状态
     * @return: 返回根据订单状态查询的订单
     * @auther: Jay
     * @date: 2019/1/13
     */
    @Override
    public PageInfo<TbOrderDetails> getAllOrderByStatus(String status,int currentPage,int pageSize, TbOrderDetails tbOrderDetails) {
        Integer orderId = tbOrderDetails.getOrderId();
        String outputCode = tbOrderDetails.getOutputCode();
        String deliveryCode = tbOrderDetails.getDeliveryCode();

        // 从第一页开始，每一页显示5条数据
        PageHelper.startPage(currentPage,pageSize);
//        Example example=new Example(TbOrderDetails.class);
//        example.createCriteria()
//                .andLike("orderId",orderId!=null?"%"+orderId+"%":null)
//                .andLike("outputCode",outputCode!=null?"%"+outputCode+"%":null)
//                .andLike("deliveryCode",deliveryCode!=null?"%"+deliveryCode+"%":null);
//        List<TbOrderDetails> tbOrderAll= tbOutputMapper.selectByExample(example);

        List<TbOrderDetails> allOrder = tbOutputMapper.getAllOrderByStatus(status);
        PageInfo<TbOrderDetails> pageInfo =new PageInfo<>(allOrder);
        return pageInfo;
    }
    @Override
    public TbOrder getOrderById(Integer orderId) {
        return tbOrderMapper.selectByPrimaryKey(orderId);
    }
    /**
     *
     * 功能描述: 根据传过来的状态更改状态
     *
     * @param: 出库状态
     * @return: 返回200表示修改成功
     * @auther: Jay
     * @date: 2019/1/9
     */
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
     * @param:
     * @return:
     * @auther: Jay
     * @date: 2019/1/9
     */
    @Override
    public String updateOutput(TbOutput tbOutput) {
        int t = tbOutputMapper.updateByPrimaryKeySelective(tbOutput);
        if (t==1){
            return "200";
        }
        return "error";
    }
}
