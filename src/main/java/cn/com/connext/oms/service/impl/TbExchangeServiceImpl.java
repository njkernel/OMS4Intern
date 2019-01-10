package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.commons.dto.exchange.ReturnDetails;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDTO;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDetailDTO;
import cn.com.connext.oms.commons.utils.AES;
import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.service.TbExchangeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.LongFunction;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 10:50
 * @describe: 换货模块Service实现类
 **/
@Service
@Transactional
public class TbExchangeServiceImpl implements TbExchangeService {
    private final String TOKENS = "yonyog";
    private static long MISTIMING;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TbExchangeMapper tbExchangeMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

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
    @Override
    public PageInfo<TbReturn> showAllReturns(Integer currentPage, Integer pageSize, String returnType){
        PageHelper.startPage(currentPage,pageSize);
        List<TbReturn> tbReturns=tbExchangeMapper.selectAllExchangeOrders(returnType);
        PageInfo<TbReturn> pageInfo=new PageInfo<>(tbReturns);
        return pageInfo;
    }

    /**
     * create by: yonyong
     * description: 查询退换货单详情信息
     * create time: 2019/1/9 0:04
     *
     *  * @Param: OrderId
     * @return cn.com.connext.oms.commons.dto.exchange.ReturnDetails
     */
    @Override
    public ReturnDetails selectReturnDetailsByOrderId(int orderId){
        ReturnDetails returnDetails=new ReturnDetails();
        TbReturn tbReturn=tbExchangeMapper.selectTbReturnByOrderId(orderId);
        List<TbReturnGoods> tbReturnGoods=tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
        List<TbOrder> tbOrder=tbOrderMapper.getOrderByOrderId(orderId);

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
     * create by: yonyong
     * description: 生成换货单对应的换货商品表tb_return_goods表
     * create time: 2019/1/8 15:31
     *
     *  * @Param: orderId
      * @Param: goodsId
      * @Param: number
     * @return void
     */
    @Override
    public void toGenerateExchangeOrderGoods(int orderId, int[] goodsId, int[] number) {
        List<TbReturnGoods> tbReturnGoods=new ArrayList<>();
        for (int i=0;i<goodsId.length;i++){
            TbReturnGoods tbReturnGoods1=new TbReturnGoods();
            tbReturnGoods1.setOrderId(orderId);
            tbReturnGoods1.setGoodsId(goodsId[i]);
            tbReturnGoods1.setNumber(number[i]);
            tbReturnGoods.add(tbReturnGoods1);
        }
        tbExchangeMapper.toGenerateExchangeOrderGoods(tbReturnGoods);
    }

    /**
     * create by: yonyong
     * description: 生成换货单对应的tb_return表
     * create time: 2019/1/8 15:33
     *
     *  * @Param: tbReturn
     * @return void
     */
    @Override
    public void toGenerateExchangeOrder(TbReturn tbReturn) {
        tbExchangeMapper.toGenerateExchangeOrder(tbReturn);
    }

    /**
     * create by: yonyong
     * description: 根据商品id查询商品信息
     * create time: 2019/1/8 15:33
     *
     *  * @Param: goodId
     * @return cn.com.connext.oms.entity.TbGoods
     */
    @Override
    public TbGoods toSelectGoodById(int goodId) {
        return tbExchangeMapper.toSelectGoodById(goodId);
    }

    /**
     * create by: yonyong
     * description: TbReturn 设置换货单接口
     * create time: 2019/1/8 15:34
     *
     *  * @Param: orderId
      * @Param: goodId
      * @Param: num
     * @return cn.com.connext.oms.entity.TbReturn
     */
    @Override
    public TbReturn setTbReturn(int orderId, int[] goodId, int[] num){
        double price=0;
        for (int i=0;i<goodId.length;i++){
            TbGoods tbGoods=tbExchangeMapper.toSelectGoodById(goodId[i]);
            price=price+num[i]*tbGoods.getGoodsPrice();
        }
        TbReturn tbReturn=new TbReturn();
        tbReturn.setOrderId(orderId);
        tbReturn.setReturnCode(CodeGenerateUtils.creatUUID());
        tbReturn.setReturnState("待审核");
        tbReturn.setReturnPrice(price);
        tbReturn.setCreated(new Date());
        tbReturn.setModifiedUser("yonyong");
        tbReturn.setUpdated(new Date());
        tbReturn.setReturnType("换货");
        return tbReturn;
    }

    /**
     * create by: yonyong
     * description: 批量取消换货单
     * create time: 2019/1/8 15:34
     *
     *  * @Param: ids
      * @Param: state
      * @Param: modifiedUser
      * @Param: update
     * @return int
     */
    @Override
    public int updateTbReturn(Integer[] ids, String state, String modifiedUser, Date update) {
        List<TbReturn> tbReturns=new ArrayList<>();
        TbReturn tbReturn=new TbReturn();
        for (int i=0;i<ids.length;i++){
            tbReturn.setReturnId(ids[i]);
            tbReturn.setReturnState(state);
            tbReturn.setModifiedUser(modifiedUser);
            tbReturn.setUpdated(update);
            tbReturns.add(tbReturn);
        }
        if ("待审核".equals(state)) {
            try {
                return tbExchangeMapper.updateTbReturn(tbReturns);
            } catch (Exception e) {
                return -1;
            }
        }
        return -2;
    }

    /**
     * create by: yonyong
     * description: 审核换货单
     * create time: 2019/1/8 15:34
     *
     *  * @Param: ids
      * @Param: modifiedUser
      * @Param: update
     * @return int
     */
    @Override
    public int AuditTbReturn(int[] ids,String modifiedUser, Date update) {
//        TbReturn tbReturn=tbExchangeMapper.selectByPrimaryKey(id);
        List<TbReturn> list0=new ArrayList<TbReturn>();
        List<TbReturn> list1=new ArrayList<TbReturn>();
        Date date=new Date();
        for (int t:ids){
            TbReturn tbReturn=tbExchangeMapper.selectTbReturnById(t);
            //判断数据库订单时间与当前时间相差是否超过15天，超过15天，审核不予通过
            if (MISTIMING<date.getTime()-tbReturn.getCreated().getTime())
            {
                tbReturn.setReturnState("审核通过");

                //生成并发送入库单给WMS

                tbReturn.setModifiedUser(modifiedUser);
                tbReturn.setUpdated(update);
                list0.add(tbReturn);
            }
            else {
                tbReturn.setReturnState("审核失败");
                tbReturn.setModifiedUser(modifiedUser);
                tbReturn.setUpdated(update);
                list1.add(tbReturn);
            }
        }
        try {
            tbExchangeMapper.updateTbReturn(list0);
            tbExchangeMapper.updateTbReturn(list1);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * create by: yonyong
     * description: 生成入库单 并将入库单发送给WMS
     * create time: 2019/1/8 15:35
     *
     *  * @Param: orderId
      * @Param: goodId
      * @Param: num
     * @return int
     */
    @Override
    public int generateInput(int[] ids) {

        //批量生成入库单并推送至WMS
        for (int i=0;i<ids.length;i++){

            TbReturn tbReturn1=tbExchangeMapper.selectTbReturnById(ids[i]);
            int orderId=tbReturn1.getOrderId();

            if ("审核失败".equals(tbReturn1.getReturnState())){
                continue;
            }
            //生成入库单
            TbInput tbInput=new TbInput();
            tbInput.setInputCode(CodeGenerateUtils.creatUUID());
            tbInput.setOrderId(orderId);
            tbInput.setInputState("传输中");
            tbInput.setCreated(new Date());
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("未同步");
            try{
                tbExchangeMapper.insertInput(tbInput);
            }catch (Exception e){
                return -1;
            }

            //发送入库单
            TbInput tbInput1=tbExchangeMapper.selectTbInputByOrderId(orderId);
            List<TbReturnGoods> tbReturnGoods=tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
            List<TbOrder> tbOrder=tbOrderMapper.getOrderByOrderId(orderId);

            List<InRepertoryDetailDTO> detailDTOS = new ArrayList<>();
            for(TbReturnGoods t:tbReturnGoods){
                TbGoods tbGoods=tbExchangeMapper.toSelectGoodById(t.getGoodsId());
                detailDTOS.add(new InRepertoryDetailDTO(tbGoods.getGoodsCode(), t.getNumber()));

                String token=AES.AESEncode(TOKENS,String.valueOf(tbInput1.getInputId()));
                InRepertoryDTO inRepertoryDTO = new InRepertoryDTO(token,
                        String.valueOf(tbInput1.getInputId()),String.valueOf(orderId),
                        tbOrder.get(0).getChannelCode(), tbOrder.get(0).getDeliveryCompany(),
                        tbOrder.get(0).getDeliveryCode(), detailDTOS);

                try {
                    restTemplate.postForEntity("http://10.129.100.21:8080/api/inRepertoryOrder", inRepertoryDTO.toMap(), String.class);
                }catch (Exception e){
                    try {
                        restTemplate.postForEntity("http://10.129.100.21:8080/api/inRepertoryOrder", inRepertoryDTO.toMap(), String.class);
                        TbReturn tbReturn=tbExchangeMapper.selectTbReturnByOrderId(orderId);
                        List<TbReturn> tbReturns=new ArrayList<>();
                        tbReturn.setOrderId(orderId);
                        tbReturn.setReturnState("等待收货");
                        tbReturn.setUpdated(new Date());
                        tbReturns.add(tbReturn);
                        tbInput.setInputState("接收成功");
                        tbInput.setUpdated(new Date());
                        tbInput.setSynchronizeState("已同步");
                        tbExchangeMapper.updateTbInput(tbInput);
                        tbExchangeMapper.updateTbReturn(tbReturns);
                    }catch (Exception e1){
                        return -1;
                    }
                }

            }
        }
        return -1;
    }

    /**
     * create by: yonyong
     * description: 生成出库单
     * create time: 2019/1/8 15:35
     *
     *  * @Param: orderId
      * @Param: modifiedUser
      * @Param: goodId
      * @Param: num
     * @return int
     */
    @Override
    public int generateOutput(InputFeedback inputFeedback) {
        TbReturn tbReturn=new TbReturn();
        TbInput tbInput=new TbInput();
        List<TbReturn> tbReturns=new ArrayList<>();

        try {
            tbReturn=tbExchangeMapper.selectTbReturnByOrderId(inputFeedback.getOrderId());
            tbInput=tbExchangeMapper.selectTbInputByOrderId(inputFeedback.getOrderId());
        }catch (Exception e){
            return 1;
        }

        if ("收货失败".equals(inputFeedback.getInputState())){
            tbInput.setInputState("收货失败");
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("已同步");

            tbReturn.setReturnState("换货失败");
            tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
            tbReturn.setUpdated(new Date());
            tbReturns.add(tbReturn);
        }
        else if ("超15天未收货".equals(inputFeedback.getInputState())){
            tbInput.setInputState("超15天未收货");
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("已同步");

            tbReturn.setReturnState("换货取消");
            tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
            tbReturn.setUpdated(new Date());
            tbReturns.add(tbReturn);
        }else {
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

        }catch (Exception e){
            return 1;
        }finally {
            if ("收货失败".equals(inputFeedback.getInputState())){
                return 2;
            }
            else if ("超15天未收货".equals(inputFeedback.getInputState())){
                return 3;
            }
        }

        //生成出库单
        TbOutput tbOutput=new TbOutput();
        tbOutput.setOutputCode(CodeGenerateUtils.creatUUID());
        tbOutput.setOrderId(inputFeedback.getOrderId());
        tbOutput.setOutputState("处理中");
        tbOutput.setCreated(new Date());
        tbOutput.setUpdated(new Date());
        tbOutput.setSynchronizeState("未同步");
        tbOutput.setModifiedUser(inputFeedback.getModifiedUser());
        try{
            tbExchangeMapper.insertOutput(tbOutput);
            return 0;
        }catch (Exception e){
            return 1;
        }

        /**
         * create by: yonyong
         * description: 根据Tbreturn_id查询对应的信息
         * create time: 2019/1/9 11:34
         *
         *  * @Param: returnId
         * @return cn.com.connext.oms.entity.TbReturn
         */

    }

    @Override
    public TbReturn selectTbReturnByReturnId(int returnId) {
        return tbExchangeMapper.selectTbReturnById(returnId);
    }
}
