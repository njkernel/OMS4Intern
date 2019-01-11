package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_refund")
public class TbRefund {
    /**
     * 退款id
     */
    @Id
    @Column(name = "refund_id")
    private Integer refundId;

    /**
     * 退款单号
     */
    @Column(name = "refund_code")
    private String refundCode;

    /**
     * 退款金额
     */
    @Column(name = "refund_price")
    private Double refundPrice;

    /**
     * 退款状态
     */
    @Column(name = "refund_state")
    private String refundState;

    /**
     * 退货id
     */
    @Column(name = "return_id")
    private String returnId;

    /**
     * 创建时间
     */
    private Date createtd;

    /**
     * 更改人
     */
    @Column(name = "modified_user")
    private String modifiedUser;

    /**
     * 更改时间
     */
    private Date updated;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 获取退款id
     *
     * @return refund_id - 退款id
     */
    public Integer getRefundId() {
        return refundId;
    }

    /**
     * 设置退款id
     *
     * @param refundId 退款id
     */
    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    /**
     * 获取退款单号
     *
     * @return refund_code - 退款单号
     */
    public String getRefundCode() {
        return refundCode;
    }

    /**
     * 设置退款单号
     *
     * @param refundCode 退款单号
     */
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    /**
     * 获取退款金额
     *
     * @return refund_price - 退款金额
     */
    public Double getRefundPrice() {
        return refundPrice;
    }

    /**
     * 设置退款金额
     *
     * @param refundPrice 退款金额
     */
    public void setRefundPrice(Double refundPrice) {
        this.refundPrice = refundPrice;
    }

    /**
     * 获取退款状态
     *
     * @return refund_state - 退款状态
     */
    public String getRefundState() {
        return refundState;
    }

    /**
     * 设置退款状态
     *
     * @param refundState 退款状态
     */
    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    /**
     * 获取退货id
     *
     * @return return_id - 退货id
     */
    public String getReturnId() {
        return returnId;
    }

    /**
     * 设置退货id
     *
     * @param returnId 退货id
     */
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    /**
     * 获取创建时间
     *
     * @return createtd - 创建时间
     */
    public Date getCreatetd() {
        return createtd;
    }

    /**
     * 设置创建时间
     *
     * @param createtd 创建时间
     */
    public void setCreatetd(Date createtd) {
        this.createtd = createtd;
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
}