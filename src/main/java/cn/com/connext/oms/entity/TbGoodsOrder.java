package cn.com.connext.oms.entity;

import javax.persistence.*;

@Table(name = "oms.tb_goods_order")
public class TbGoodsOrder {
    /**
     * 订单商品关系表id
     */
    @Id
    @Column(name = "goods_order_id")
    private Integer goodsOrderId;

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
     * 商品个数
     */
    private Integer num;

    /**
     * 下单这类产品的总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 获取订单商品关系表id
     *
     * @return goods_order_id - 订单商品关系表id
     */
    public Integer getGoodsOrderId() {
        return goodsOrderId;
    }

    /**
     * 设置订单商品关系表id
     *
     * @param goodsOrderId 订单商品关系表id
     */
    public void setGoodsOrderId(Integer goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
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
     * 获取商品个数
     *
     * @return num - 商品个数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置商品个数
     *
     * @param num 商品个数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取下单这类产品的总价
     *
     * @return total_price - 下单这类产品的总价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置下单这类产品的总价
     *
     * @param totalPrice 下单这类产品的总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}