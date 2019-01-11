package cn.com.connext.oms.commons.dto;

import cn.com.connext.oms.commons.utils.ToMap;

import java.util.List;

/**
 * <p>Title: OutputDTO</p>
 * <p>Description: </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/10
 */
public class OutputDTO extends ToMap {
    private  String outRepoId;
    private  Integer orderId;
    private String channelId;
    private String receiverName;
    private String receiverAddress;
    private String expressCompany;
    private List<OutRepoOrderDetailDto> outRepoOrderDetailDto;

    public OutputDTO(String outRepoId, Integer orderId, String channelId, String receiverName, String receiverAddress, String expressCompany, List<OutRepoOrderDetailDto> outRepoOrderDetailDto) {
        this.outRepoId = outRepoId;
        this.orderId = orderId;
        this.channelId = channelId;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.expressCompany = expressCompany;
        this.outRepoOrderDetailDto = outRepoOrderDetailDto;
    }

    public String getOutRepoId() {
        return outRepoId;
    }

    public void setOutRepoId(String outRepoId) {
        this.outRepoId = outRepoId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public List<OutRepoOrderDetailDto> getOutRepoOrderDetailDto() {
        return outRepoOrderDetailDto;
    }

    public void setOutRepoOrderDetailDto(List<OutRepoOrderDetailDto> outRepoOrderDetailDto) {
        this.outRepoOrderDetailDto = outRepoOrderDetailDto;
    }
}
