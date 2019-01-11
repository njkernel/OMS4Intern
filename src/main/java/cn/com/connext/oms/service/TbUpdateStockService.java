package cn.com.connext.oms.service;

/**
 * <p>Title: TbUpdateStockService</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/8
 */
public interface TbUpdateStockService {
    /**
        * @Author: zhaojun
        * @Description: 更新商品库存
        * @Param: []
        * @Create: 2019/1/8 11:46
        */
    public void updateStock(int goodsId, int totalStock);
    /**
        * @Author: zhaojun
        * @Description: 更新商品的可用锁定
        * @Param: []
        * @Return:
        * @Create: 2019/1/8 14:39
        */
    public void updateLockdAndAvailable(int goodsId, int num, int num2);
}
