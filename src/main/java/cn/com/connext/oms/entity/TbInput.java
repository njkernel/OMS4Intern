package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_input")
public class TbInput {
    /**
     * 入库id
     */
    @Id
    @Column(name = "input_id")
    private Integer inputId;

    /**
     * 入库单号
     */
    @Column(name = "input_code")
    private String inputCode;

    /**
     * 订单号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 入库状态
     */
    @Column(name = "input_state")
    private String inputState;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 同步状态
     */
    @Column(name = "synchronize_state")
    private String synchronizeState;

    /**
     * 获取入库id
     *
     * @return input_id - 入库id
     */
    public Integer getInputId() {
        return inputId;
    }

    /**
     * 设置入库id
     *
     * @param inputId 入库id
     */
    public void setInputId(Integer inputId) {
        this.inputId = inputId;
    }

    /**
     * 获取入库单号
     *
     * @return input_code - 入库单号
     */
    public String getInputCode() {
        return inputCode;
    }

    /**
     * 设置入库单号
     *
     * @param inputCode 入库单号
     */
    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    /**
     * 获取订单号
     *
     * @return order_id - 订单号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单号
     *
     * @param orderId 订单号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取入库状态
     *
     * @return input_state - 入库状态
     */
    public String getInputState() {
        return inputState;
    }

    /**
     * 设置入库状态
     *
     * @param inputState 入库状态
     */
    public void setInputState(String inputState) {
        this.inputState = inputState;
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
}