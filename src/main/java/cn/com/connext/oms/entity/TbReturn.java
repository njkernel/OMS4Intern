package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_return")
public class TbReturn {
    /**
     * 退货id
     */
    @Id
    @Column(name = "return_id")
    private Integer returnId;

    /**
     * 退货单号
     */
    @Column(name = "return_code")
    private String returnCode;

    /**
     * 退货的状态
     */
    @Column(name = "return_state")
    private String returnState;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 退货金额
     */
    @Column(name = "return_price")
    private Double returnPrice;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改人
     */
    @Column(name = "modified_user")
    private String modifiedUser;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 单号类型（退货单/换货单）
     */
    @Column(name = "return_type")
    private String returnType;

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * 获取退货id
     *
     * @return return_id - 退货id
     */
    public Integer getReturnId() {
        return returnId;
    }

    /**
     * 设置退货id
     *
     * @param returnId 退货id
     */
    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    /**
     * 获取退货单号
     *
     * @return return_code - 退货单号
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设置退货单号
     *
     * @param returnCode 退货单号
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 获取退货的状态
     *
     * @return return_state - 退货的状态
     */
    public String getReturnState() {
        return returnState;
    }

    /**
     * 设置退货的状态
     *
     * @param returnState 退货的状态
     */
    public void setReturnState(String returnState) {
        this.returnState = returnState;
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
     * 获取退货金额
     *
     * @return return_price - 退货金额
     */
    public Double getReturnPrice() {
        return returnPrice;
    }

    /**
     * 设置退货金额
     *
     * @param returnPrice 退货金额
     */
    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
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
     * 获取修改人
     *
     * @return modified_user - 修改人
     */
    public String getModifiedUser() {
        return modifiedUser;
    }

    /**
     * 设置修改人
     *
     * @param modifiedUser 修改人
     */
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    /**
     * 获取修改时间
     *
     * @return updated - 修改时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置修改时间
     *
     * @param updated 修改时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}