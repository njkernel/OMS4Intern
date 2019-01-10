package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_output")
public class TbOutput {
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
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

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
     * 更新时间
     */
    private Date updated;

    /**
     * 同步状态
     */
    @Column(name = "synchronize_state")
    private String synchronizeState;

    /**
     * 备注
     */
    @Column(name = "` remark`")
    private String remark;

    /**
     * 修改人
     */
    @Column(name = "modified_user")
    private String modifiedUser;

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