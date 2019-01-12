package cn.com.connext.oms.entity;

import javax.persistence.*;

@Table(name = "oms.tb_return_goods")
public class TbReturnGoods {
    /**
     * 退货商品id
     */
    @Id
    @Column(name = "return_goods_id")
    private Integer returnGoodsId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 数量
     */
    @Column(name = "number")
    private Integer number;

    /**
     * 获取退货商品id
     *
     * @return return_goods_id - 退货商品id
     */
    public Integer getReturnGoodsId() {
        return returnGoodsId;
    }

    /**
     * 设置退货商品id
     *
     * @param returnGoodsId 退货商品id
     */
    public void setReturnGoodsId(Integer returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}