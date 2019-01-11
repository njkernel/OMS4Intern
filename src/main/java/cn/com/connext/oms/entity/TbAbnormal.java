package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_abnormal")
public class TbAbnormal {
    /**
     * 异常id
     */
    @Id
    @Column(name = "abnormal_id")
    private Integer abnormalId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 异常类型
     */
    @Column(name = "abnormal_type")
    private String abnormalType;

    /**
     * 异常原因
     */
    @Column(name = "abnormal_reason")
    private String abnormalReason;

    /**
     * 异常状态
     */
    @Column(name = "abnormal_state")
    private String abnormalState;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 获取异常id
     *
     * @return abnormal_id - 异常id
     */
    public Integer getAbnormalId() {
        return abnormalId;
    }

    /**
     * 设置异常id
     *
     * @param abnormalId 异常id
     */
    public void setAbnormalId(Integer abnormalId) {
        this.abnormalId = abnormalId;
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
     * 获取异常类型
     *
     * @return abnormal_type - 异常类型
     */
    public String getAbnormalType() {
        return abnormalType;
    }

    /**
     * 设置异常类型
     *
     * @param abnormalType 异常类型
     */
    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    /**
     * 获取异常原因
     *
     * @return abnormal_reason - 异常原因
     */
    public String getAbnormalReason() {
        return abnormalReason;
    }

    /**
     * 设置异常原因
     *
     * @param abnormalReason 异常原因
     */
    public void setAbnormalReason(String abnormalReason) {
        this.abnormalReason = abnormalReason;
    }

    /**
     * 获取异常状态
     *
     * @return abnormal_state - 异常状态
     */
    public String getAbnormalState() {
        return abnormalState;
    }

    /**
     * 设置异常状态
     *
     * @param abnormalState 异常状态
     */
    public void setAbnormalState(String abnormalState) {
        this.abnormalState = abnormalState;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}