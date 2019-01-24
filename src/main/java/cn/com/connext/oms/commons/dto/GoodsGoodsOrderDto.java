package cn.com.connext.oms.commons.dto;

import cn.com.connext.oms.entity.TbGoods;
import cn.com.connext.oms.entity.TbGoodsOrder;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <p>Title: GoodsGoodsOrderDto</p>
 * <p>Description: </p>
 *
 * @author zhaojun
 * @version 1.0.0
 * @Date 2019/1/9
 */
public class GoodsGoodsOrderDto extends TbGoodsOrder {
    private TbGoods tbGoods;

    public TbGoods getTbGoods() {
        return tbGoods;
    }

    public void setTbGoods(TbGoods tbGoods) {
        this.tbGoods = tbGoods;
    }

    private String unitPriceForOrder;

    public String getUnitPriceForOrder() {
        return unitPriceForOrder;
    }

    public void setUnitPriceForOrder(String unitPriceForOrder) {
        this.unitPriceForOrder = unitPriceForOrder;
    }

    /**
     * 商品id
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品编号
     */
    @Column(name = "goods_code")
    private String goodsCode;

    /**
     * 商品名字
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private Double goodsPrice;

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品编号
     *
     * @return goods_code - 商品编号
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 设置商品编号
     *
     * @param goodsCode 商品编号
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    /**
     * 获取商品名字
     *
     * @return goods_name - 商品名字
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名字
     *
     * @param goodsName 商品名字
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品单价
     *
     * @return goods_price - 商品单价
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品单价
     *
     * @param goodsPrice 商品单价
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
