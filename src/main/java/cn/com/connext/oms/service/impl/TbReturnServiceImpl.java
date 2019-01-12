package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.commons.dto.BaseResult;
import cn.com.connext.oms.commons.utils.CodeGenerateUtils;
import cn.com.connext.oms.entity.TbInput;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.mapper.TbOrderMapper;
import cn.com.connext.oms.mapper.TbReturnMapper;
import cn.com.connext.oms.service.TbReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private static long MISTIMING;
    @Autowired
    private TbReturnMapper tbReturnMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    /**
     * 生成退货单对应的商品表
     * @param orderId
     * @param goodsIdList
     * @param numberList
     * @return boolean
     */
    @Override
    public boolean addReturnOrderGoods(int orderId, List<Integer> goodsIdList, List<Integer> numberList) {
       boolean flag = false;
    for(int i = 0;i < goodsIdList.size();i++) {
        flag = tbReturnMapper.addReturnOrderGoods(orderId, goodsIdList.get(i), numberList.get(i));

    }
       return flag;
    }





    /**
     * 退货单取消
     * @param returnId
     * @return boolean
     */
    @Override
    public boolean returnOrderCancel(int returnId,String modifiedUser,Date updated) {
        String state = tbReturnMapper.selectReturnOrderStateById(returnId);
        updated = new Date();
        String oms = "oms";
        if ("待审核".equals(state)) {
            boolean flag = tbReturnMapper.returnOrderCancel(returnId,oms,updated);
            return flag;
        } else {
            BaseResult.fail("入库单已经生成，取消失败");
            return false;
        }
    }


    /**
     * 退货单单审核是否有退货资
     * @param returnIds
     * @return boolean
     */
    @Override
    public boolean returnOrdersAudit(List<Integer> returnIds){
        for(int i = 0;i<returnIds.size();i++){
        int orderId = tbReturnMapper.selectOrderIdByReturnId(returnIds.get(i));
//        Date created = tbOrderMapper.selectCreatedById(orderId);
        Date created = tbReturnMapper.getTbReturnByiId(returnIds.get(i)).getCreated();

        Date now = new Date();
        Long time = now.getTime()-created.getTime();
        String user = "oms";
        Date updated = new Date();
        if ( MISTIMING > time){
           boolean flag = tbReturnMapper.updateReturnOrderStateById(returnIds.get(i),"等待收货",user,updated);
            return flag;
        }
        tbReturnMapper.updateReturnOrderStateById(returnIds.get(i),"审核失败",user,updated);
        BaseResult.fail("超出退货期限，退货单状态已变为审核失败");}
        return false;
    }

    /**
     * 生成退货单
     * @param tbReturn
     * @return boolean
     */
    @Override
    public boolean addReturnOrder(TbReturn tbReturn) {
        boolean flag = tbReturnMapper.addReturnOrder(tbReturn.getReturnCode(),tbReturn.getReturnState(),tbReturn.getOrderId(),tbReturn.getReturnPrice(),tbReturn.getCreated(),tbReturn.getModifiedUser(),tbReturn.getUpdated());
        if (flag){
            BaseResult.success("添加退货单成功");
            return flag;
        }
        BaseResult.fail(500,"添加退货单失败");
        return false;
    }


    /**
     * 生成入库单
     * @param returnIdsList
     * @return TbInput
     */
    @Override
    public TbInput createInputOrder(List<Integer> returnIdsList) {
        Date time = new Date();
        TbInput tbInput = new TbInput();
        for (int i = 0;i < returnIdsList.size();i++) {
            int orderId = tbReturnMapper.selectOrderIdByReturnId(returnIdsList.get(i));


            String synchronizeState = "传输中";
            String inputState = "传输中";
            String inputCode = CodeGenerateUtils.creatUUID();
            tbInput.setOrderId(orderId);
            tbInput.setCreated(time);
            tbInput.setUpdated(time);
            tbInput.setInputCode(inputCode);
            tbInput.setSynchronizeState(synchronizeState);
            tbInput.setInputState(inputState);
            boolean flag = tbReturnMapper.createInputOrder(tbInput.getInputCode(), tbInput.getOrderId(), tbInput.getInputState(), tbInput.getCreated(), tbInput.getUpdated(), tbInput.getSynchronizeState());
        }
        return tbInput;

    }

    /**
     * 根据id查找退货/换货单
     * @param returnId
     * @return
     */
    @Override
    public TbReturn getTbReturnById(int returnId) {
        return tbReturnMapper.getTbReturnByiId(returnId);
    }


    /**
     * 生成退货单
     * @param orderId
     * @param goodsIdList
     * @param numberList
     * @return TbReturn
     */
    @Override
    public TbReturn createReturnOrder(int orderId, List<Integer> goodsIdList, List<Integer> numberList ){
        double sum = 0;
        for (int i = 0;i < goodsIdList.size();i++){
            double price = tbReturnMapper.getPriceByGoodId(goodsIdList.get(i));
            sum +=price*numberList.get(i);
        }

        TbReturn tbReturn = new TbReturn();
        Date time = new Date();
        tbReturn.setCreated(time);
        tbReturn.setModifiedUser("oms");
        tbReturn.setOrderId(orderId);
        tbReturn.setReturnPrice(sum);
        tbReturn.setUpdated(time);
        tbReturn.setReturnCode(CodeGenerateUtils.creatUUID());//调用UUID工具类生成退货单号
        tbReturn.setReturnState("待审核");

        return tbReturn;
    }





}
