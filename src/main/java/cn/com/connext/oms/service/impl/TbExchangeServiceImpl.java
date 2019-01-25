package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.commons.dto.exchange.ReturnGoods;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDTO;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDetailDTO;
import cn.com.connext.oms.commons.utils.AES;
import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.commons.utils.ExchangeUtils;
import cn.com.connext.oms.commons.utils.ListToArray;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.service.TbExchangeService;
import cn.com.connext.oms.web.Api.API;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 10:50
 * @describe: 换货模块Service实现类
 */
@Service
//@Transactional
public class TbExchangeServiceImpl implements TbExchangeService {
  private final String TOKENS = "yonyong";
  private static long MISTIMING;
  private static final String GET_FAILED = "fail";
  private static final String GET_SUCCESS = "success";
  private static final String GET_FEEDBACK_OUTTIME = "over";

  private static final String RETURN_STATE_UNCHECKED = "待审核";
  private static final String RETURN_STATE_AUDIT_UNCHECKED = "审核通过";

//  public static String IP="127.0.0.1";
//  public static String URL="http://"+IP+":8080/api/inRepertoryOrder";


  @Autowired RestTemplate restTemplate;

  @Autowired private TbExchangeMapper tbExchangeMapper;

  @Autowired private TbOrderMapper tbOrderMapper;

  @Autowired private ExchangeUtils exchangeUtils;
  /**
   * create by: yonyong description: 分页查询所有退换货信息列表 create time: 2019/1/8 23:14
   *
   * <p>* @Param: currentPage  * @Param: pageSize  * @Param: retuenId
   *
   * @return com.github.pagehelper.PageInfo<cn.com.connext.oms.entity.TbReturn>
   */
  @Override
  public PageInfo<TbReturn> showAllReturns(
          Integer currentPage, Integer pageSize, TbReturn tbReturn) {
    PageHelper.startPage(currentPage, pageSize);
    List<TbReturn> tbReturns = tbExchangeMapper.selectAllExchangeOrders(tbReturn);
    PageInfo<TbReturn> pageInfo = new PageInfo<>(tbReturns);
    return pageInfo;
  }

  /**
   * create by: yonyong description: 查询退换货单详情信息 create time: 2019/1/9 0:04
   *
   * <p>* @Param: OrderId
   *
   * @return cn.com.connext.oms.commons.dto.exchange.ReturnDetails
   */
  @Override
  public ReturnDetails selectReturnDetailsByOrderId(int orderId) {
    ReturnDetails returnDetails = new ReturnDetails();
    TbReturn tbReturn = tbExchangeMapper.selectTbReturnByOrderId(orderId);
    List<TbReturnGoods> tbReturnGoods = tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
    List<TbOrder> tbOrder = tbOrderMapper.getOrderByOrderId(orderId);

    returnDetails.setReturnId(tbReturn.getReturnId());
    returnDetails.setReturnCode(tbReturn.getReturnCode());
    returnDetails.setReturnState(tbReturn.getReturnState());
    returnDetails.setOrderId(tbReturn.getOrderId());
    returnDetails.setReturnPrice(tbReturn.getReturnPrice());
    returnDetails.setCreated(tbReturn.getCreated());
    returnDetails.setModifiedUser(tbReturn.getModifiedUser());
    returnDetails.setUpdated(tbReturn.getUpdated());
    returnDetails.setReturnType(tbReturn.getReturnType());
    returnDetails.setOrderCode(tbOrder.get(0).getOrderCode());
    returnDetails.setChannelCode(tbOrder.get(0).getChannelCode());
    returnDetails.setTbReturnGoods(tbReturnGoods);

    return returnDetails;
  }

  /**
   * create by: yonyong description: 生成换货单对应的换货商品表tb_return_goods表 create time: 2019/1/8 15:31
   *
   * <p>* @Param: orderId  * @Param: goodsId  * @Param: number
   *
   * @return void
   */
  @Override
  public void toGenerateExchangeOrderGoods(int orderId, int[] goodsId, int[] number) {
    List<TbReturnGoods> tbReturnGoods = new ArrayList<>();
    for (int i = 0; i < goodsId.length; i++) {
      TbReturnGoods tbReturnGoods1 = new TbReturnGoods();
      tbReturnGoods1.setOrderId(orderId);
      tbReturnGoods1.setGoodsId(goodsId[i]);
      tbReturnGoods1.setNumber(number[i]);
      tbReturnGoods.add(tbReturnGoods1);
    }
    tbExchangeMapper.toGenerateExchangeOrderGoods(tbReturnGoods);
  }

  /**
   * create by: yonyong description: 生成换货单对应的tb_return表 create time: 2019/1/8 15:33
   *
   * <p>* @Param: tbReturn
   *
   * @return void
   */
  @Override
  public void toGenerateExchangeOrder(TbReturn tbReturn) {
    tbExchangeMapper.toGenerateExchangeOrder(tbReturn);
  }

  /**
   * create by: yonyong description: 根据商品id查询商品信息 create time: 2019/1/8 15:33
   *
   * <p>* @Param: goodId
   *
   * @return cn.com.connext.oms.entity.TbGoods
   */
  @Override
  public TbGoods toSelectGoodById(int goodId) {
    return tbExchangeMapper.toSelectGoodById(goodId);
  }

  /**
   * create by: yonyong description: TbReturn 设置换货单接口 create time: 2019/1/8 15:34
   *
   * <p>* @Param: orderId  * @Param: goodId  * @Param: num
   *
   * @return cn.com.connext.oms.entity.TbReturn
   */
  @Override
  public TbReturn setTbReturn(int orderId, int[] goodId, int[] num) {
    double price = 0;
    goodId = ListToArray.ListFormat(goodId);
    num = ListToArray.ListFormat(num);
    for (int i = 0; i < goodId.length; i++) {
      TbGoods tbGoods = tbExchangeMapper.toSelectGoodById(goodId[i]);
      price = price + num[i] * tbGoods.getGoodsPrice();
    }
    TbReturn tbReturn = new TbReturn();
    tbReturn.setOrderId(orderId);
    tbReturn.setReturnCode(CodeGenerateUtils.creatUUID());
    tbReturn.setReturnState(RETURN_STATE_UNCHECKED);
    tbReturn.setReturnPrice(price);
    tbReturn.setCreated(new Date());
    tbReturn.setModifiedUser("yonyong");
    tbReturn.setUpdated(new Date());
    tbReturn.setReturnType("换货");
    return tbReturn;
  }

  /**
   * create by: yonyong description: 批量取消换货单 create time: 2019/1/8 15:34
   *
   * <p>* @Param: ids  * @Param: state  * @Param: modifiedUser  * @Param: update
   *
   * @return int
   */
  @Override
  public int updateTbReturn(int[] ids, String state, String modifiedUser, Date update) {
    List<TbReturn> tbReturns = new ArrayList<>();
    for (int i = 0; i < ids.length; i++) {
      TbReturn tbReturn = new TbReturn();
      if (!RETURN_STATE_UNCHECKED.equals(tbExchangeMapper.selectTbReturnById(ids[0]).getReturnState())){
        continue;
      }
      tbReturn.setReturnId(ids[i]);
      tbReturn.setReturnState(state);
      tbReturn.setModifiedUser(modifiedUser);
      tbReturn.setUpdated(update);
      tbReturns.add(tbReturn);
    }
      try {
        tbExchangeMapper.updateTbReturn(tbReturns);
      } catch (Exception e) {
        return -1;
      }
      return 0;
  }

  /**
   * create by: yonyong description: 审核换货单 create time: 2019/1/8 15:34
   *
   * <p>* @Param: ids  * @Param: modifiedUser  * @Param: update
   *
   * @return int
   */
  @Override
  public int AuditTbReturn(int[] ids, String modifiedUser, Date update) {
    //        TbReturn tbReturn=tbExchangeMapper.selectByPrimaryKey(id);
    List<TbReturn> list0 = new ArrayList<TbReturn>();
    List<TbReturn> list1 = new ArrayList<TbReturn>();
    Date date = new Date();
    for (int t : ids) {
      TbReturn tbReturn = tbExchangeMapper.selectTbReturnById(t);
      // 判断数据库订单时间与当前时间相差是否超过15天，超过15天，审核不予通过
      int orderId=tbReturn.getOrderId();
      ExchangeUtils exchangeUtils =new ExchangeUtils();
      //如果状态不是待审核，说明已经有过审核记录，则当前换货单不需要进入下面的审核流程，跳过继续执行下一个换货单审核流程
      if (!RETURN_STATE_UNCHECKED.equals(tbReturn.getReturnState())){
        continue;
      }
      if (MISTIMING < date.getTime() - tbReturn.getUpdated().getTime()&&!exchangeUtils.checkOrderIsExchange(orderId)) {
        tbReturn.setReturnState("审核通过");

        tbReturn.setModifiedUser(modifiedUser);
        tbReturn.setUpdated(update);
        list0.add(tbReturn);

      } else {
        tbReturn.setReturnState("审核失败");
        tbReturn.setModifiedUser(modifiedUser);
        tbReturn.setUpdated(update);
        list1.add(tbReturn);
      }
    }
    try {
      if (list0.size()>0){
        tbExchangeMapper.updateTbReturn(list0);
      }
      if (list1.size()>0){
        tbExchangeMapper.updateTbReturn(list1);
      }
      return 1;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * create by: yonyong description: 生成入库单 并将入库单发送给WMS create time: 2019/1/8 15:35
   *
   * <p>* @Param: orderId  * @Param: goodId  * @Param: num
   *
   * @return int
   */
  @Override
  public int generateInput(int[] ids) {

    // 批量生成入库单并推送至WMS
    for (int i = 0; i < ids.length; i++) {

      TbReturn tbReturn1 = tbExchangeMapper.selectTbReturnById(ids[i]);
      int orderId = tbReturn1.getOrderId();

      if (!RETURN_STATE_AUDIT_UNCHECKED.equals(tbReturn1.getReturnState())) {
        continue;
      }
      // 生成入库单
      TbInput tbInput = new TbInput();
      tbInput.setInputCode(CodeGenerateUtils.creatUUID());
      tbInput.setOrderId(orderId);
      tbInput.setInputState("传输中");
      tbInput.setCreated(new Date());
      tbInput.setUpdated(new Date());
      tbInput.setSynchronizeState("未同步");
      try {
        tbExchangeMapper.insertInput(tbInput);
      } catch (Exception e) {
        return -1;
      }
    }
    //发送入库单
    for (int i = 0; i < ids.length; i++) {

        TbReturn tbReturn1 = tbExchangeMapper.selectTbReturnById(ids[i]);
        int orderId = tbReturn1.getOrderId();
        // 发送入库单
        TbInput tbInput1 = tbExchangeMapper.selectTbInputByOrderId(orderId);
        List<TbReturnGoods> tbReturnGoods = tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
        List<TbOrder> tbOrder = tbOrderMapper.getOrderByOrderId(orderId);

        List<InRepertoryDetailDTO> detailDTOS = new ArrayList<>();
        for (TbReturnGoods t : tbReturnGoods) {
          TbGoods tbGoods = tbExchangeMapper.toSelectGoodById(t.getGoodsId());
          detailDTOS.add(new InRepertoryDetailDTO(tbGoods.getGoodsCode(), t.getNumber()));
        }

        String token = AES.AESEncode(TOKENS, String.valueOf(tbInput1.getInputId()));//1
        InRepertoryDTO inRepertoryDTO =
                new InRepertoryDTO(
                        token,
                        String.valueOf(tbInput1.getInputId()),
                        String.valueOf(orderId),
                        tbOrder.get(0).getChannelCode(),
                        tbOrder.get(0).getDeliveryCompany(),
                        tbOrder.get(0).getDeliveryCode(),
                        detailDTOS);

  //      try {
  //        restTemplate.postForEntity(
  //            "http://10.129.100.22:8080/api/inRepertoryOrder", inRepertoryDTO.toMap(), String.class);
  //      } catch (Exception e) {
        try {
          List<TbReturn> tbReturns = new ArrayList<>();
          TbReturn tbReturn = tbExchangeMapper.selectTbReturnByOrderId(orderId);
          tbReturn.setOrderId(orderId);
          tbReturn.setReturnState("等待收货");
          tbReturn.setUpdated(new Date());
          tbReturns.add(tbReturn);
          tbInput1.setInputState("接收成功");
          tbInput1.setUpdated(new Date());
          tbInput1.setSynchronizeState("已同步");
          restTemplate.postForEntity(
                  API.API_RETURN,
                  inRepertoryDTO.toMap(),
                  String.class);
          tbExchangeMapper.updateTbInput(tbInput1);
          tbExchangeMapper.updateTbReturn(tbReturns);
        } catch (Exception e1) {
          return -1;
      }
    }
    // }
    return -1;
  }

  /**
   * create by: yonyong description: 生成出库单 create time: 2019/1/8 15:35
   *
   * <p>* @Param: orderId  * @Param: modifiedUser  * @Param: goodId  * @Param: num
   *
   * @return int
   */
  @Override
  public int inputFeedback(InputFeedback inputFeedback) {
    TbReturn tbReturn = new TbReturn();
    TbInput tbInput = new TbInput();
    List<TbReturn> tbReturns = new ArrayList<>();

    int orderId = inputFeedback.getOrderId();

    //定义一个布尔值，判断接受的订单已经存在于订单换货单关系表里，在则表明WMS可能之前发错状态或需要紧急调整状态
    //需要再次向我方系统发送回馈信息
    boolean rs=exchangeUtils.checkOrderIsExchange(inputFeedback.getOrderId());

    try {

      tbReturn = tbExchangeMapper.selectTbReturnByOrderId(inputFeedback.getOrderId());
      tbInput = tbExchangeMapper.selectTbInputByOrderId(inputFeedback.getOrderId());
    } catch (Exception e) {
      return 1;
    }

    if (GET_FAILED.equals(inputFeedback.getInputState())) {
      //如果WMS上一次操作失误，进行回滚操作并及时更新
//      if (rs){
//        exchangeUtils.delOrder(inputFeedback.getGoodDetails(),inputFeedback.getOrderId());
//      }
      tbInput.setInputState("收货失败");
      tbInput.setUpdated(new Date());
      tbInput.setSynchronizeState("已同步");

      tbReturn.setReturnState("换货失败");
      tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
      tbReturn.setUpdated(new Date());
      tbReturns.add(tbReturn);
    } else if (GET_FEEDBACK_OUTTIME.equals(inputFeedback.getInputState())) {
      //如果WMS上一次操作失误，进行回滚操作并及时更新
//      if (rs){
//        exchangeUtils.delOrder(inputFeedback.getGoodDetails(),inputFeedback.getOrderId());
//      }
      tbInput.setInputState("超15天未收货");
      tbInput.setUpdated(new Date());
      tbInput.setSynchronizeState("已同步");

      tbReturn.setReturnState("换货取消");
      tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
      tbReturn.setUpdated(new Date());
      tbReturns.add(tbReturn);
    } else if (GET_SUCCESS.equals(inputFeedback.getInputState())) {
      tbInput.setInputState("收货成功");
      tbInput.setUpdated(new Date());
      tbInput.setSynchronizeState("已同步");

      tbReturn.setReturnState("收货成功");
      tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
      tbReturn.setUpdated(new Date());
      tbReturns.add(tbReturn);
    }

    try {
      tbExchangeMapper.updateTbInput(tbInput);
      tbExchangeMapper.updateTbReturn(tbReturns);

    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }

    if (GET_FAILED.equals(inputFeedback.getInputState())) {
      return 2;
    } else if (GET_FEEDBACK_OUTTIME.equals(inputFeedback.getInputState())) {
      return 3;
    }

    try {
      exchangeUtils.newOrder(inputFeedback.getGoodDetails(),inputFeedback.getOrderId());
      return 0;
    }catch (Exception e){
      return 1;
    }

    /**
     * create by: yonyong description: 根据Tbreturn_id查询对应的信息 create time: 2019/1/9 11:34
     *
     * <p>* @Param: returnId
     *
     * @return cn.com.connext.oms.entity.TbReturn
     */
  }

  @Override
  public TbReturn selectTbReturnByReturnId(int returnId) {
    return tbExchangeMapper.selectTbReturnById(returnId);
  }

  /**
   * create by: yonyong description: 验证tokens create time: 2019/1/10 12:01
   *
   * <p>* @Param: token
   *
   * @return int
   */
  @Override
  public int checkToken(String token, String orderId) {
    String rs_token = AES.AESEncode(TOKENS, orderId);
    if (rs_token.equals(token)) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * create by: yonyong
   * description: 根据退货详情单获取换货商品详情
   * create time: 2019/1/15 12:30
   *
   *  * @Param: returnDetails
   * @return java.util.List<cn.com.connext.oms.commons.dto.exchange.ReturnDetails>
   */
  @Override
  public List<ReturnGoods> selectReturnDetails(ReturnDetails returnDetails){
      List<ReturnGoods> list=new ArrayList<>();
      List<TbReturnGoods> tbReturnGoods=returnDetails.getTbReturnGoods();
      for (TbReturnGoods t:tbReturnGoods) {
        ReturnGoods returnGoods = new ReturnGoods();
        TbGoods tbGoods = tbExchangeMapper.toSelectGoodById(t.getGoodsId());
          returnGoods.setGoodId(t.getGoodsId());
          returnGoods.setOrderId(t.getOrderId());
          returnGoods.setReturnNum(t.getNumber());
          returnGoods.setReturnPrice(t.getNumber()*tbGoods.getGoodsPrice());
          returnGoods.setGoodCode(tbGoods.getGoodsCode());
          returnGoods.setGoodName(tbGoods.getGoodsName());
          list.add(returnGoods);
      }
      return list;
  }
}
