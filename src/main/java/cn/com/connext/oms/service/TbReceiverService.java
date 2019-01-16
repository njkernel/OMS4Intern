package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbReceiver;

public interface TbReceiverService {

    /** 
    * @Description: 更改收货人信息 
    * @Param: [tbReceiver] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2019/1/14 
    */
    boolean updateReceiver(TbReceiver tbReceiver);
    
    
    /** 
    * @Description: 根据id查看收货人 
    * @Param: [receiverId] 
    * @return: cn.com.connext.oms.entity.TbReceiver 
    * @Author: Lili Chen 
    * @Date: 2019/1/14 
    */
    TbReceiver getReceiverById(Integer receiverId);
}
