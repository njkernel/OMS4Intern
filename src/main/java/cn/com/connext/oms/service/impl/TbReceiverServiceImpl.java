package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbReceiver;
import cn.com.connext.oms.mapper.TbReceiverMapper;
import cn.com.connext.oms.service.TbReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: OMS4Intern
 * @description: 有关收货人信息的操作
 * @author: Lili.Chen
 * @create: 2019-01-09 10:25
 **/
@Service
public class TbReceiverServiceImpl implements TbReceiverService {

    @Autowired
    private TbReceiverMapper tbReceiverMapper;


    @Override
    public boolean updateReceiver(TbReceiver tbReceiver) {
        if(tbReceiver!=null){
            TbReceiver receiver=tbReceiverMapper.getReceiverById(tbReceiver.getReceiverId());
            if(receiver!=null){//确保数据库中有这条数据
                int result=0;//更改数据条数
                result=tbReceiverMapper.updateReceiver(tbReceiver);
                if(result==1){//表示已经更改成功
                    return true;
                }
            }
        }
        return false;
    }
}
