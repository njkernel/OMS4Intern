package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbReceiver;

public interface TbReceiverService {
    boolean updateReceiver(TbReceiver tbReceiver);
    /**
     * 功能描述:根据订单查看收货人信息
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/15 19:58
     */
    TbReceiver getReceiverById(Integer orderId);
}
