package cn.com.connext.oms.mapper;

import cn.com.connext.oms.entity.TbReceiver;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface TbReceiverMapper extends MyMapper<TbReceiver> {

    /**
    * @Description: 修改收货人的信息
    * @Param: [tbReceiver]
    * @return: int
    * @Author: Lili Chen
    * @Date: 2019/1/9
    */
    int updateReceiver(TbReceiver tbReceiver);


    /**
    * @Description: 根据id查看用户信息
    * @Param: [receiverId]
    * @return: cn.com.connext.oms.entity.TbReceiver
    * @Author: Lili Chen
    * @Date: 2019/1/9
    */
    TbReceiver getReceiverById(Integer receiverId);
}