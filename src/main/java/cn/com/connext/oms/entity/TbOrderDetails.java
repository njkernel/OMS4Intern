package cn.com.connext.oms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>Title: TbOrderDetails</p>
 * <p>Description:出库单所有商品详情字段 </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/8
 */
public class TbOrderDetails extends TbReceiver {
    private TbGoods tbGoods;
    private TbGoodsOrder tbGoodsOrder;
    private TbOutput tbOutput;
    private TbOrder tbOrder;
    private TbReceiver tbReceiver;

    public TbOrder getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrder tbOrder) {
        this.tbOrder = tbOrder;
    }

    public TbReceiver getTbReceiver() {
        return tbReceiver;
    }

    public void setTbReceiver(TbReceiver tbReceiver) {
        this.tbReceiver = tbReceiver;
    }

    public TbOutput getTbOutput() {
        return tbOutput;
    }

    public void setTbOutput(TbOutput tbOutput) {
        this.tbOutput = tbOutput;
    }

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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 渠道单号
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 订单状态
     */
    @Column(name = "order_state")
    private String orderState;

    /**
     * 订单来源
     */
    @Column(name = "order_source")
    private String orderSource;

    /**
     * 下单时间
     */
    @Column(name = "purchase_time")
    private String purchaseTime;

    /**
     * 基本状态
     */
    @Column(name = "basic_state")
    private String basicState;

    /**
     * 更改人
     */
    @Column(name = "modified_user")
    private String modifiedUser;

    /**
     * 买家id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 更改时间
     */
    private Date updated;

    /**
     * 支付状态
     */
    @Column(name = "payment_state")
    private String paymentState;

    /**
     * 支付方式
     */
    @Column(name = "payment_way")
    private String paymentWay;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 发货仓库
     */
    @Column(name = "delivery_warehouse")
    private String deliveryWarehouse;

    /**
     * 物流公司
     */
    @Column(name = "delivery_company")
    private String deliveryCompany;

    /**
     * 物流单号
     */
    @Column(name = "delivery_code")
    private String deliveryCode;

    /**
     * 配送时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 订单的总价格
     */
    @Column(name = "sum_price")
    private Double sumPrice;

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

    /**
     * 出库id
     */
    @Id
    @Column(name = "output_id")
    private Integer outputId;

    /**
     * 出库单号
     */
    @Column(name = "output_code")
    private String outputCode;

    /**
     * 出库状态
     */
    @Column(name = "output_state")
    private String outputState;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 同步状态
     */
    @Column(name = "synchronize_state")
    private String synchronizeState;

    /**
     * 备注
     */
    @Column(name = "remark")    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getBasicState() {
        return basicState;
    }

    public void setBasicState(String basicState) {
        this.basicState = basicState;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    /**
     * 获取出库id
     *
     * @return output_id - 出库id

     */
    public Integer getOutputId() {
        return outputId;
    }

    /**
     * 设置出库id
     *
     * @param outputId 出库id
     */
    public void setOutputId(Integer outputId) {
        this.outputId = outputId;
    }

    /**
     * 获取出库单号
     *
     * @return output_code - 出库单号
     */
    public String getOutputCode() {
        return outputCode;
    }

    /**
     * 设置出库单号
     *
     * @param outputCode 出库单号
     */
    public void setOutputCode(String outputCode) {
        this.outputCode = outputCode;
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
     * 获取出库状态
     *
     * @return output_state - 出库状态
     */
    public String getOutputState() {
        return outputState;
    }

    /**
     * 设置出库状态
     *
     * @param outputState 出库状态
     */
    public void setOutputState(String outputState) {
        this.outputState = outputState;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取同步状态
     *
     * @return synchronize_state - 同步状态
     */
    public String getSynchronizeState() {
        return synchronizeState;
    }

    /**
     * 设置同步状态
     *
     * @param synchronizeState 同步状态
     */
    public void setSynchronizeState(String synchronizeState) {
        this.synchronizeState = synchronizeState;
    }

    /**
     * 获取备注
     *
     * @return  remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
