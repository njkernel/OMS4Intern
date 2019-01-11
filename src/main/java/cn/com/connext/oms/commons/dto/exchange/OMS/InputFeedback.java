package cn.com.connext.oms.commons.dto.exchange.OMS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 21:31
 * @describe: 退换货Dto
 **/

public class InputFeedback {
    private String tokens;
    private int orderId;
    private String inputState;
    private String ModifiedUser;
    private List<GoodDetails> goodDetails;

    public InputFeedback(String tokens, int orderId, String inputState, String modifiedUser, List<GoodDetails> goodDetails) {
        this.tokens = tokens;
        this.orderId = orderId;
        this.inputState = inputState;
        ModifiedUser = modifiedUser;
        this.goodDetails = goodDetails;
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

    public String getModifiedUser() {
        return ModifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        ModifiedUser = modifiedUser;
    }

    public List<GoodDetails> getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(List<GoodDetails> goodDetails) {
        this.goodDetails = goodDetails;
    }
}
