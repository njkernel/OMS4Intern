package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_order")
public class TbOrder {
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
     * 收货人id
     */
    @Column(name = "receiver_id")
    private Integer receiverId;

    /**
     * 备注
     */
    @Column(name="remark")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private TbReceiver tbReceiver;

    private  TbGoods tbGoods;

    private TbGoodsOrder tbGoodsOrder;

    public TbReceiver getTbReceiver() {
        return tbReceiver;
    }

    public void setTbReceiver(TbReceiver tbReceiver) {
        this.tbReceiver = tbReceiver;
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
     * 获取订单号
     *
     * @return order_code - 订单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单号
     *
     * @param orderCode 订单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取渠道单号
     *
     * @return channel_code - 渠道单号
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道单号
     *
     * @param channelCode 渠道单号
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取订单状态
     *
     * @return order_state - 订单状态
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * 设置订单状态
     *
     * @param orderState 订单状态
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取订单来源
     *
     * @return order_source - 订单来源
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置订单来源
     *
     * @param orderSource 订单来源
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * 获取下单时间
     *
     * @return purchase_time - 下单时间
     */
    public String getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * 设置下单时间
     *
     * @param purchaseTime 下单时间
     */
    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    /**
     * 获取基本状态
     *
     * @return basic_state - 基本状态
     */
    public String getBasicState() {
        return basicState;
    }

    /**
     * 设置基本状态
     *
     * @param basicState 基本状态
     */
    public void setBasicState(String basicState) {
        this.basicState = basicState;
    }

    /**
     * 获取更改人
     *
     * @return modified_user - 更改人
     */
    public String getModifiedUser() {
        return modifiedUser;
    }

    /**
     * 设置更改人
     *
     * @param modifiedUser 更改人
     */
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    /**
     * 获取买家id
     *
     * @return customer_id - 买家id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置买家id
     *
     * @param customerId 买家id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取更改时间
     *
     * @return updated - 更改时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更改时间
     *
     * @param updated 更改时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取支付状态
     *
     * @return payment_state - 支付状态
     */
    public String getPaymentState() {
        return paymentState;
    }

    /**
     * 设置支付状态
     *
     * @param paymentState 支付状态
     */
    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    /**
     * 获取支付方式
     *
     * @return payment_way - 支付方式
     */
    public String getPaymentWay() {
        return paymentWay;
    }

    /**
     * 设置支付方式
     *
     * @param paymentWay 支付方式
     */
    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    /**
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取发货仓库
     *
     * @return delivery_warehouse - 发货仓库
     */
    public String getDeliveryWarehouse() {
        return deliveryWarehouse;
    }

    /**
     * 设置发货仓库
     *
     * @param deliveryWarehouse 发货仓库
     */
    public void setDeliveryWarehouse(String deliveryWarehouse) {
        this.deliveryWarehouse = deliveryWarehouse;
    }

    /**
     * 获取物流公司
     *
     * @return delivery_company - 物流公司
     */
    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    /**
     * 设置物流公司
     *
     * @param deliveryCompany 物流公司
     */
    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    /**
     * 获取物流单号
     *
     * @return delivery_code - 物流单号
     */
    public String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * 设置物流单号
     *
     * @param deliveryCode 物流单号
     */
    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    /**
     * 获取配送时间
     *
     * @return delivery_time - 配送时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置配送时间
     *
     * @param deliveryTime 配送时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取订单的总价格
     *
     * @return sum_price - 订单的总价格
     */
    public Double getSumPrice() {
        return sumPrice;
    }

    /**
     * 设置订单的总价格
     *
     * @param sumPrice 订单的总价格
     */
    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    /**
     * 获取收货人id
     *
     * @return receiver_id - 收货人id
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * 设置收货人id
     *
     * @param receiverId 收货人id
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}