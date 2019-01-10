package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.utils.HttpClientUtils.exception.HttpProcessException;
import cn.com.connext.oms.entity.TbOrder;
import cn.com.connext.oms.entity.TbOutput;
import cn.com.connext.oms.entity.TbRefund;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbOutputMapper;
import cn.com.connext.oms.mapper.TbRefundMapper;
import cn.com.connext.oms.service.TbOrderService;
import cn.com.connext.oms.web.Api.OutputAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: TbOrderService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/6 10:15
 */
@Transactional(readOnly = true)
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOutputMapper tbOutputMapper;

    @Autowired
    private TbRefundMapper tbRefundMapper;


    /**
    * @Author: caps
    * @Description: 获取所有订单
    * @Param: []
    * @Return: java.util.List<cn.com.connext.oms.entity.TbOrder>
    * @Create: 2019/1/6 10:16
    */
    @Override
    public List<TbOrder> getAllOrder() {
        return tbOrderMapper.getAllOrder();
    }

    /**
    * @Description: 主动批量取消订单
    * @Param: [tbOrderList]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2019/1/7
    */
    @Transactional(readOnly = false)
    @Override
    public boolean cancelOrder(List<TbOrder> tbOrderList) {
        Date date=new Date();
        //List<TbOutput> outputList=new ArrayList<TbOutput>();//WMS需要更新的出库单
        StringBuffer outputBuffer=new StringBuffer();
        List<TbOutput> outputs=new ArrayList<TbOutput>();//OMS需要更新的出库单
        List<TbRefund> tbRefundList=new ArrayList<TbRefund>();//存即将生成的退款单
        TbRefund refund=new TbRefund();
        int count=0;//记录需要更新的订单条数
        int orderResult=0;//订单状态更新返回结果
        int outputResult=0;//出库单更新返回结果
        int refundResult=0;//退款单更新返回结果
        String number="";//取消WMS出库单返回的状态码
        if(!tbOrderList.isEmpty()){//集合不为空
            for(TbOrder tbOrder:tbOrderList){
              if(tbOrder.getOrderState().equals("待预检")||tbOrder.getOrderState().equals("待路由")||tbOrder.getOrderState().equals("待出库")||"wms请求取消".equals(tbOrder.getBasicState())){
                    count++;//进行记录
                }else if(tbOrder.getOrderState().equals("已出库")&& !"wms请求取消".equals(tbOrder.getBasicState())){
                       TbOutput tbOutput=tbOutputMapper.getOutputByOrderId(tbOrder.getOrderId());
                      String statusNumber="";//保存从WMS获取的出库单状态
                  try {
                      statusNumber=OutputAPI.getOutPutState(tbOutput.getOutputCode()); //从调用WMS接口获取出库单的状态（根据出库单编号）
                      System.out.println(statusNumber);
                  } catch (HttpProcessException e) {
                      e.printStackTrace();
                  }

                  if("已发货".equals(statusNumber)){//WMS出库单状态
                        return false;
                    }
                    else {//出库单未发货
                        TbOutput output=tbOutputMapper.getOutputByOrderId(tbOrder.getOrderId());
                        outputBuffer.append(output.getOutputCode()+",");
                        //output.setOutputState("已取消");
                        //outputList.add(output);  //把需要在WMS取消的出库单保存起来
                        count++;
                    }
                }

                refund.setCreatetd(date);
                refund.setModifiedUser("cll");
                UUID uuid = UUID.randomUUID();
                refund.setRefundCode(uuid+"");
                refund.setRefundPrice(tbOrder.getSumPrice());
                refund.setOrderId(tbOrder.getOrderId());
                refund.setRefundState("待退款");
                refund.setReturnId(null);
                refund.setUpdated(null);
                tbRefundList.add(refund);

            }
            if(count==tbOrderList.size()){//如果要取消的订单集合中没有与之相关的已发货的出库单
                if(outputBuffer.length()!=0){//要去WMS取消的出库单不为空
                    String outputList=outputBuffer.toString();//转换成String类型
                    outputList = outputList.substring(0,outputList.length()-1);//去除逗号
                    try {
                        number=OutputAPI.updateOutputStateOfWMS(outputList);//调用WMS接口取消WMS的出库单,并从WMS获取取消出库单的状态码
                    } catch (HttpProcessException e) {
                        e.printStackTrace();
                    }
                    if(number.equals("200")) {//如果在wms取消订单成功
                        for (TbOrder order : tbOrderList) {
                            order.setBasicState("无");
                            order.setModifiedUser("cll");
                            order.setOrderState("已取消");
                            order.setUpdated(date);
                            TbOutput output = tbOutputMapper.getOutputByOrderId(order.getOrderId());
                            output.setOutputState("已取消");
                            output.setModifiedUser("cll");
                            outputs.add(output);//保存需要取消的出库单
                        }
                        orderResult = tbOrderMapper.updateOrderListStatue(tbOrderList);//订单状态设置为已取消
                        outputResult = tbOutputMapper.updateOutputListStatue(outputs);//OMS出库单取消

                    }
                }else{//要去WMS取消的内容为空
                        for(TbOrder order:tbOrderList){
                            TbOutput output=tbOutputMapper.getOutputByOrderId(order.getOrderId());
                            if(order.getBasicState().equals("wms请求取消")){
                                order.setBasicState("无");
                                order.setModifiedUser("仓库请求修改");
                                output.setModifiedUser("仓库请求修改");
                            }else{
                                order.setModifiedUser("cll");
                                output.setModifiedUser("cll");
                            }
                            order.setOrderState("已取消");
                            order.setUpdated(date);

                            output.setOutputState("已取消");
                            output.setCreated(date);
                            outputs.add(output);//保存需要取消的出库单
                        }
                        orderResult=tbOrderMapper.updateOrderListStatue(tbOrderList);
                        outputResult=tbOutputMapper.updateOutputListStatue(outputs);//OMS出库单取消
                    }

            }
        }
         if(orderResult==tbOrderList.size()&&outputResult==outputs.size()){//数据是否全部更新
               //生成相应退款单
               refundResult=tbRefundMapper.batchAddRefund(tbRefundList);
               if(refundResult==tbRefundList.size()){
                   return true;
               }
         }

        return false;
    }

    
    /** 
    * @Description: WMS批量取消订单 
    * @Param: [outputList] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2019/1/8 
    */
    @Transactional(readOnly = false)
    @Override
    public boolean cancelOrderOfWms(String outputs) {
        System.out.println(outputs);
        List<TbOutput> outputList=new ArrayList<TbOutput>();//保存需要更改的出库单
        List<TbOrder> orderList=new ArrayList<TbOrder>();//保存需要在OMS更改备注的订单
        int orderResult=0;//保存订单更改的条数
        String[] outputString=outputs.split(",");//将传过来的数据转换成字符串数组
        TbOutput myOutput=new TbOutput();
        for(String output:outputString){
            myOutput=tbOutputMapper.getOutputByOutputCode(output);//根据出库单编码获取出库单的对象
            outputList.add(myOutput);
        }
        TbOrder tbOrder=new TbOrder();
        if(!outputList.isEmpty()){//要更改的出库单不为空
           for(TbOutput tbOutput:outputList){
               tbOrder=tbOrderMapper.getOrderById(tbOutput.getOrderId());//根据订单id获取订单对象
               if("已取消".equals(tbOrder.getOrderState())){
                   return false;
               }
               else if(!"已完成".equals(tbOrder.getOrderState())){
                   tbOrder.setBasicState("wms请求取消");
                   orderList.add(tbOrder);
               }

           }
           if(!orderList.isEmpty()){
               orderResult=tbOrderMapper.updateOrderListBasicRemark(orderList);
           }
           if(orderResult==orderList.size()){
               return true;
           }
        }
        return false;
    }


}
