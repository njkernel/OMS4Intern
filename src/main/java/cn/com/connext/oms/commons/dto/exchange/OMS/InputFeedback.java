package cn.com.connext.oms.commons.dto.exchange.OMS;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 21:31
 **/
public class InputFeedback {
    private String tokens;
    private int orderId;
    private String inputState;
    private String remark;
    private String modifiedUser;

    public InputFeedback(String tokens, int orderId, String inputState, String remark, String modifiedUser) {
        this.tokens = tokens;
        this.orderId = orderId;
        this.inputState = inputState;
        this.remark = remark;
        this.modifiedUser = modifiedUser;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getInputState() {
        return inputState;
    }

    public void setInputState(String inputState) {
        this.inputState = inputState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }
}
