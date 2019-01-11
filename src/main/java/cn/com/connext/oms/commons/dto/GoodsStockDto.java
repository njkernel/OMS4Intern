package cn.com.connext.oms.commons.dto;

import cn.com.connext.oms.entity.TbGoods;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <p>Title: GoodsStockDto</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/7
 */
public class GoodsStockDto extends TbGoods {
    /**
     * 库存id
     */
    @Id
    @Column(name = "stock_id")
    private Integer stockId;

    /**
     * 可用库存
     */
    @Column(name = "available_stock")
    private Integer availableStock;

    /**
     * 锁定库存
     */
    @Column(name = "locked_stock")
    private Integer lockedStock;

    /**
     * 总库存
     */
    @Column(name = "total_stock")
    private Integer totalStock;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 获取库存id
     *
     * @return stock_id - 库存id
     */
    public Integer getStockId() {
        return stockId;
    }

    /**
     * 设置库存id
     *
     * @param stockId 库存id
     */
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取可用库存
     *
     * @return available_stock - 可用库存
     */
    public Integer getAvailableStock() {
        return availableStock;
    }

    /**
     * 设置可用库存
     *
     * @param availableStock 可用库存
     */
    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    /**
     * 获取锁定库存
     *
     * @return locked_stock - 锁定库存
     */
    public Integer getLockedStock() {
        return lockedStock;
    }

    /**
     * 设置锁定库存
     *
     * @param lockedStock 锁定库存
     */
    public void setLockedStock(Integer lockedStock) {
        this.lockedStock = lockedStock;
    }

    /**
     * 获取总库存
     *
     * @return total_stock - 总库存
     */
    public Integer getTotalStock() {
        return totalStock;
    }

    /**
     * 设置总库存
     *
     * @param totalStock 总库存
     */
    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    @Override
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    @Override
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
