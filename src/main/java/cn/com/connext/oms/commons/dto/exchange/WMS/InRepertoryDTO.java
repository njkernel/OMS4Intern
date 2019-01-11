package cn.com.connext.oms.commons.dto.exchange.WMS;

import cn.com.connext.oms.commons.utils.ToMap;

import java.util.List;

/**
 * @Author: yonyong
 * @Date: 2019/1/8 9:41
 * @Version 1.0
 */

public class InRepertoryDTO extends ToMap {
    private String tokens;
    private String inRepoId;
    private String orderId;
    private String channelId;
    private String expressCompany;
    private String expressId;
    private List<InRepertoryDetailDTO> detailDTOS;

    public InRepertoryDTO(String tokens, String inRepoId, String orderId, String channelId, String expressCompany, String expressId, List<InRepertoryDetailDTO> detailDTOS) {
        this.tokens = tokens;
        this.inRepoId = inRepoId;
        this.orderId = orderId;
        this.channelId = channelId;
        this.expressCompany = expressCompany;
        this.expressId = expressId;
        this.detailDTOS = detailDTOS;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getInRepoId() {
        return inRepoId;
    }

    public void setInRepoId(String inRepoId) {
        this.inRepoId = inRepoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public List<InRepertoryDetailDTO> getDetailDTOS() {
        return detailDTOS;
    }

    public void setDetailDTOS(List<InRepertoryDetailDTO> detailDTOS) {
        this.detailDTOS = detailDTOS;
    }
}

