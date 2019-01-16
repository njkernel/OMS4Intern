package cn.com.connext.oms.service.impl;

import cn.com.connext.oms.entity.TbStock;
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
    public void updateLockdAndAvailable(int goodsId,int num) {
        this.tbStockMapper.updateLockdAndAvailable(goodsId,num);
    }


    /**
     * 功能描述:根据商品总库存和锁定库存修改商品的可用库存
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/11 10:17
     */
    @Override
    public void updateAvailable(int goodsId, int availableStock) {

        this.tbStockMapper.updateAvailable(goodsId,availableStock);

    }
    /**
     * 功能描述:根据商品id查询商品的库存信息
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/11 10:20
     */
    @Override
    public TbStock getLocked(int goodId) {
        return this.tbStockMapper.getLocked(goodId);
    }
}
