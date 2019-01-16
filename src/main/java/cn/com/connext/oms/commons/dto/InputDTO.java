package cn.com.connext.oms.commons.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 退货单的相应DTO
 * @created with IDEA
 * @author: Aaron
 * @version: 1.0.0
 * @date: 2019/1/15
 * @time: 13:32
 **/


public class InputDTO {

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
     * 修改人
     */
    @Column(name = "modified_user")
    private String modifiedUser;


    /**
     * 退货单号
     */
    @Column(name = "return_code")
    private String returnCode;

    public InputDTO(String inputCode, Integer orderId, String inputState, Date created, Date updated, String synchronizeState, String modifiedUser, String returnCode) {
        this.inputCode = inputCode;
        this.orderId = orderId;
        this.inputState = inputState;
        this.created = created;
        this.updated = updated;
        this.synchronizeState = synchronizeState;
        this.modifiedUser = modifiedUser;
        this.returnCode = returnCode;
    }

    public InputDTO() {
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getInputState() {
        return inputState;
    }

    public void setInputState(String inputState) {
        this.inputState = inputState;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getSynchronizeState() {
        return synchronizeState;
    }

    public void setSynchronizeState(String synchronizeState) {
        this.synchronizeState = synchronizeState;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
