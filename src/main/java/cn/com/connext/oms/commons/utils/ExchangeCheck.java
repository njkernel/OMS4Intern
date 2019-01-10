package cn.com.connext.oms.commons.utils;

import cn.com.connext.oms.commons.dto.exchange.OMS.GoodDetails;
import cn.com.connext.oms.entity.TbReturn;
import cn.com.connext.oms.entity.TbReturnGoods;
import cn.com.connext.oms.entity.TbStock;
import cn.com.connext.oms.mapper.TbExchangeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/10
 * @time: 17:51
 * @describe:
 **/
public class ExchangeCheck {

    @Autowired
    TbExchangeMapper tbExchangeMapper;

    public int checkExchangeCheckStockByReturnId(int returnId){
        int orderId=tbExchangeMapper.selectTbReturnById(returnId).getOrderId();
        List<TbReturnGoods> tbReturnGoods=tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
        for (TbReturnGoods tbReturnGoods1:tbReturnGoods){
            boolean rs= tbExchangeMapper.selectStockByGoodId(tbReturnGoods1.
                    getGoodsId()).getAvailableStock()<tbReturnGoods1.getNumber().intValue()?false:true;
            if (!rs){
                return 0;
            }
        }
        return 1;
    }

    /**
     * create by: yonyong
     * description: 锁定加，可用库存减
     * create time: 2019/1/10 18:26
     *
     *  * @Param: returnId
     * @return boolean
     */
    public boolean updateExchangeCheckStockBefore(int returnId){
        int orderId=tbExchangeMapper.selectTbReturnById(returnId).getOrderId();
        List<TbReturnGoods> tbReturnGoods=tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);
        for (TbReturnGoods tbReturnGoods1 : tbReturnGoods) {
            TbStock tbStock = new TbStock();
            int lockStock=tbExchangeMapper.selectStockByGoodId(tbReturnGoods1.
                    getGoodsId()).getLockedStock();
            int availableStock=tbExchangeMapper.selectStockByGoodId(tbReturnGoods1.
                    getGoodsId()).getAvailableStock();
            int exchangeNum=tbReturnGoods1.getNumber();
            tbStock.setAvailableStock(availableStock-exchangeNum);
            tbStock.setLockedStock(lockStock+exchangeNum);
            tbStock.setGoodsId(tbReturnGoods1.getGoodsId());
            try{
                tbExchangeMapper.updateStock(tbStock);
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

    /**
     * create by: yonyong
     * description: 锁定减掉，总库存减掉
     * create time: 2019/1/10 18:27
     *
     *  * @Param: returnId
     * @return boolean
     */
    public boolean updateExchangeCheckStockAfter(int orderId, List<GoodDetails> goodDetails){
        List<TbReturnGoods> tbReturnGoods=tbExchangeMapper.selectTbReturnGoodsByOrderId(orderId);

        for (GoodDetails goodDetails1:goodDetails){
            TbStock tbStock=tbExchangeMapper.selectStockByGoodCode(Integer.valueOf(goodDetails1.getGoodsSku()));
            int oldTotalStock=tbStock.getTotalStock();
            int oldLockedStock=tbStock.getLockedStock();
            int exchangeNum=goodDetails1.getGoodsNum();

            tbStock.setStockId(oldLockedStock-exchangeNum);
            tbStock.setTotalStock(oldTotalStock-exchangeNum);
            tbExchangeMapper.updateStock(tbStock);
        }
        for (TbReturnGoods tbReturnGoods1 : tbReturnGoods) {
            TbStock tbStock = new TbStock();
            int lockStock=tbExchangeMapper.selectStockByGoodId(tbReturnGoods1.
                    getGoodsId()).getLockedStock();
            int totalStock=tbExchangeMapper.selectStockByGoodId(tbReturnGoods1.
                    getGoodsId()).getTotalStock();
            int exchangeNum=tbReturnGoods1.getNumber();
            tbStock.setLockedStock(lockStock+exchangeNum);
            tbStock.setTotalStock(totalStock-exchangeNum);
            tbStock.setGoodsId(tbReturnGoods1.getGoodsId());
            try{
                tbExchangeMapper.updateStock(tbStock);
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

}
