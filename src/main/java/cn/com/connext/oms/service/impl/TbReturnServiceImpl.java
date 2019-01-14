package cn.com.connext.oms.service.impl;

import afu.org.checkerframework.checker.igj.qual.I;
import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.dto.exchange.OMS.InputFeedback;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDTO;
import cn.com.connext.oms.commons.dto.exchange.WMS.InRepertoryDetailDTO;
import cn.com.connext.oms.commons.utils.AES;
import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.entity.*;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbReturnMapper;
import cn.com.connext.oms.service.TbReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/7
 * @time: 15:49
 **/

@Transactional
@Service
public class TbReturnServiceImpl implements TbReturnService {
    private static long MISTIMING = 1296000000;
    private final String TOKENS = "yonyong";
    String unchecked = "待审核";
    String getFailed = "收货失败";
    String getSuccess = "收货成功";
    String getFeedBackOutTime = "超15天未收货";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TbReturnMapper tbReturnMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbExchangeMapper tbExchangeMapper;

    /**
     * 生成退货单对应的商品表
     *
     * @param orderId
     * @param goodsIdList
     * @param numberList
     * @return boolean
     */
    @Override
    public boolean addReturnOrderGoods(int orderId, List<Integer> goodsIdList, List<Integer> numberList) {
        boolean flag = false;
        for (int i = 0; i < goodsIdList.size(); i++) {
            flag = tbReturnMapper.addReturnOrderGoods(orderId, goodsIdList.get(i), numberList.get(i));

        }
        return flag;
    }


    /**
     * 退货单取消
     *
     * @param returnList
     * @return boolean
     */
    @Override
    public boolean returnOrderCancel(List<Integer> returnList,String modifiedUser, Date updated) {
        boolean flag = false;
        for (int i = 0;i < returnList.size();i++){

            String state = tbReturnMapper.selectReturnOrderStateById(returnList.get(i));
            updated = new Date();
            String oms = "oms";
            if (unchecked.equals(state)) {
                flag = tbReturnMapper.returnOrderCancel(returnList.get(i), oms, updated);

                BaseResult.success("取消成功");
            } else {
                BaseResult.fail("入库单已经生成，取消失败");

            }
        }
        return flag;
    }


    /**
     * 退货单单审核是否有退货资
     *
     * @param returnIdsList
     * @return boolean
     */
    @Override
    public List<Integer> returnOrdersAudit(List<Integer> returnIdsList) {

        List<Integer> returnOrderList = new ArrayList<>();
        for (int i = 0; i < returnIdsList.size(); i++) {
            int orderId = tbReturnMapper.selectOrderIdByReturnId(returnIdsList.get(i));
            String state = tbReturnMapper.selectReturnOrderStateById(returnIdsList.get(i));
            Date created = tbReturnMapper.getTbReturnById(returnIdsList.get(i)).getCreated();

            Date now = new Date();
            Long time = now.getTime() - created.getTime();
            String user = "oms";
            Date updated = new Date();
            if (unchecked.equals(state)){
                if (MISTIMING > time ) {
//                    tbReturnMapper.updateReturnOrderStateById(returnIdsList.get(i), "等待收货", user, updated);
                    returnOrderList.add(returnIdsList.get(i));
                }else {
                    tbReturnMapper.updateReturnOrderStateById(returnIdsList.get(i), "审核失败", user, updated);
                    BaseResult.fail("审核失败，退货单状态已变为审核失败");

                }
                BaseResult.fail("请检查退货单："+returnIdsList.get(i)+" 的状态");

            }

        }
        return returnOrderList;
    }

    /**
     * 生成退货单
     *
     * @param tbReturn
     * @return boolean
     */
    @Override
    public boolean addReturnOrder(TbReturn tbReturn) {
        boolean flag = tbReturnMapper.addReturnOrder(tbReturn.getReturnCode(), tbReturn.getReturnState(), tbReturn.getOrderId(), tbReturn.getReturnPrice(), tbReturn.getCreated(), tbReturn.getModifiedUser(), tbReturn.getUpdated(),tbReturn.getReturnType());
        if (flag) {
            BaseResult.success("添加退货单成功");
            return flag;
        }
        BaseResult.fail(500, "添加退货单失败");
        return false;
    }


    /**
     * 生成入库单并发送wms
     *
     * @param returnIdsList
     * @return TbInput
     */
    @Override
    public boolean createInputOrder(List<Integer> returnIdsList) {
        String synchronizeState = "未同步";
        String inputState = "传输中";
        Date time = new Date();
        TbInput tbInput = new TbInput();
        TbInput tbInput1 = new TbInput();
        boolean flag = false;
        for (int i = 0; i < returnIdsList.size(); i++) {
            tbReturnMapper.updateReturnOrderStateById(returnIdsList.get(i),getSuccess,"oms",time );
            int orderId = tbReturnMapper.selectOrderIdByReturnId(returnIdsList.get(i));

            String inputCode = CodeGenerateUtils.creatUUID();
            Date created = time;
            Date updated = time;

            boolean flag1 = tbReturnMapper.createInputOrder(inputCode, orderId,inputState,created, updated, synchronizeState);


            tbInput1 = tbExchangeMapper.selectTbInputByOrderId(orderId);
            List<TbReturnGoods> tbReturnGoodsList = tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
            List<TbOrder> tbOrder = tbOrderMapper.getOrderByOrderId(orderId);
            List<InRepertoryDetailDTO> detailDTOS = new ArrayList<>();


            for (TbReturnGoods t : tbReturnGoodsList) {
                TbGoods tbGoods = tbExchangeMapper.toSelectGoodById(t.getGoodsId());
                detailDTOS.add(new InRepertoryDetailDTO(tbGoods.getGoodsCode(), t.getNumber()));

                String token = AES.AESEncode(TOKENS, String.valueOf(tbInput1.getInputId()));
                InRepertoryDTO inRepertoryDTO = new InRepertoryDTO(token,
                        String.valueOf(tbInput1.getInputId()), String.valueOf(orderId),
                        tbOrder.get(0).getChannelCode(), tbOrder.get(0).getDeliveryCompany(),
                        tbOrder.get(0).getDeliveryCode(), detailDTOS);


                try {
                    restTemplate.postForEntity("http://10.129.100.51:8080/api/inRepertoryOrder", inRepertoryDTO.toMap(), String.class);
                    TbReturn tbReturn = tbExchangeMapper.selectTbReturnByOrderId(orderId);
                    List<TbReturn> tbReturnsList = new ArrayList<>();
                    tbReturn.setOrderId(orderId);
                    tbReturn.setReturnState("等待收货");
                    tbReturn.setUpdated(new Date());
                    tbReturnsList.add(tbReturn);

                    tbInput.setInputState("接收成功");
                    tbInput.setUpdated(new Date());
                    tbInput.setSynchronizeState("已同步");
                    tbExchangeMapper.updateTbInput(tbInput);
                    tbExchangeMapper.updateTbReturn(tbReturnsList);
                } catch (Exception e1) {
                    return false;
                }

            }
        }
        return false;

    }

    /**
     * 根据id查找退货/换货单
     *
     * @param returnId
     * @return
     */
    @Override
    public TbReturn getTbReturnById(int returnId) {
        return tbReturnMapper.getTbReturnById(returnId);
    }

    /**
     * 根据wms的反馈更新退货单的状态
     * @param inputFeedback
     * @return int
     */
    @Override
    public int updateStateByFeedback(InputFeedback inputFeedback) {
        TbReturn tbReturn = new TbReturn();
        TbInput tbInput = new TbInput();
        List<TbReturn> tbReturnsList = new ArrayList<>();

        int orderId = inputFeedback.getOrderId();

        try {
            tbReturn = tbExchangeMapper.selectTbReturnByOrderId(inputFeedback.getOrderId());
            tbInput = tbExchangeMapper.selectTbInputByOrderId(inputFeedback.getOrderId());
        } catch (Exception e) {
            return 1;
        }

        if (getFailed.equals(inputFeedback.getInputState())) {
            tbInput.setInputState("收货失败");
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("已同步");

            tbReturn.setReturnState("退货失败");
            tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
            tbReturn.setUpdated(new Date());
            tbReturnsList.add(tbReturn);
        } else if (getFeedBackOutTime.equals(inputFeedback.getInputState())) {
            tbInput.setInputState("超15天未收货");
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("已同步");

            tbReturn.setReturnState("退货取消");
            tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
            tbReturn.setUpdated(new Date());
            tbReturnsList.add(tbReturn);
        } else if (getSuccess.equals(inputFeedback.getInputState())) {
            tbInput.setInputState("收货成功");
            tbInput.setUpdated(new Date());
            tbInput.setSynchronizeState("已同步");

            tbReturn.setReturnState("退货成功");
            tbReturn.setModifiedUser(inputFeedback.getModifiedUser());
            tbReturn.setUpdated(new Date());
            tbReturnsList.add(tbReturn);
        }

        try {
            tbExchangeMapper.updateTbInput(tbInput);
            tbExchangeMapper.updateTbReturn(tbReturnsList);

        } catch (Exception e) {
            return 1;
        }

        if (getFailed.equals(inputFeedback.getInputState())) {
            return 2;
        } else if (getFeedBackOutTime.equals(inputFeedback.getInputState())) {
            return 3;
        }
        return  0;
    }


    /**
     * 生成退货单
     *
     * @param orderId
     * @param goodsIdList
     * @param numberList
     * @return TbReturn
     * @author Aaron
     */
    @Override
    public TbReturn createReturnOrder(int orderId, List<Integer> goodsIdList, List<Integer> numberList) {
        double sum = 0;
        for (int i = 0; i < goodsIdList.size(); i++) {
            double price = tbReturnMapper.getPriceByGoodId(goodsIdList.get(i));
            sum += price * numberList.get(i);
        }

        TbReturn tbReturn = new TbReturn();
        Date time = new Date();
        tbReturn.setCreated(time);
        tbReturn.setModifiedUser("oms");
        tbReturn.setOrderId(orderId);
        tbReturn.setReturnPrice(sum);
        tbReturn.setUpdated(time);
        //调用UUID工具类生成退货单号
        tbReturn.setReturnCode(CodeGenerateUtils.creatUUID());
        tbReturn.setReturnState("待审核");
        tbReturn.setReturnType("退货");

        return tbReturn;
    }


}
