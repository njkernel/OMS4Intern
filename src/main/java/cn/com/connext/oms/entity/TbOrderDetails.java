package cn.com.connext.oms.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <p>Title: TbOrderDetails</p>
 * <p>Description:出库单所有商品详情字段 </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/8
 */
public class TbOrderDetails extends TbOutput {
    private TbGoods tbGoods;
    private TbGoodsOrder tbGoodsOrder;

    public TbGoods getTbGoods() {
        return tbGoods;
    }

    public void setTbGoods(TbGoods tbGoods) {
        this.tbGoods = tbGoods;
    }

    public TbGoodsOrder getTbGoodsOrder() {
        return tbGoodsOrder;
    }

    public void setTbGoodsOrder(TbGoodsOrder tbGoodsOrder) {
        this.tbGoodsOrder = tbGoodsOrder;
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
