package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.mapper.TbStockMapper;
import cn.com.connext.oms.service.TbUpdateStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: TbUpdateStockServiceImpl</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/8
 */
@Service
public class TbUpdateStockServiceImpl implements TbUpdateStockService {
    @Autowired
    private TbStockMapper tbStockMapper;
    /**
        * @Author: zhaojun
        * @Description: 更新商品库存
        * @Param: []
        * @Return:
        * @Create: 2019/1/8 11:48
        */
    @Override
    public void updateStock(int goodsId,int totalStock) {
       this.tbStockMapper.updateStock(goodsId,totalStock);
    }

    /**
        * @Author: zhaojun
        * @Description: 更新商品的可用库存与锁定库存
        * @Param: []
        * @Return:
        * @Create: 2019/1/8 14:45
        */
    @Override
    public void updateLockdAndAvailable(int goodsId,int num,int num2) {
        this.tbStockMapper.updateLockdAndAvailable(goodsId,num,num2);
    }
}
