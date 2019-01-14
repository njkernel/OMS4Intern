package cn.com.connext.oms.service;

import cn.com.connext.oms.entity.TbStock;

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
    public void updateStock(int goodsId,int totalStock);
    /**
     * @Author: zhaojun
     * @Description: 更新商品的可用锁定
     * @Param: []
     * @Return:
     * @Create: 2019/1/8 14:39
     */
    public void updateLockdAndAvailable(int goodsId,int num);
    /**
     * 功能描述: 根据商品的总库存和锁定库存 修改商品的可用库存
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/11 10:17
     */
    public void updateAvailable(int goodsId,int availableStock);
    /**
     * 功能描述:根据商品id查询商品的库存信息
     * @param:
     * @return:
     * @auther: Jun.Zhao
     * @date: 2019/1/11 10:20
     */
    public TbStock getLocked(int goodId);
}
