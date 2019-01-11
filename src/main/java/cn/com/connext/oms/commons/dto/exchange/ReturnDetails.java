package cn.com.connext.oms.commons.dto.exchange;

import cn.com.connext.oms.entity.TbReturnGoods;

import java.util.Date;
import java.util.List;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/8
 * @time: 23:52
 **/
public class ReturnDetails {
    private Integer returnId;

    /**
     * 退货单号
     */
    private String returnCode;

    /**
     * 退货的状态
     */
    private String returnState;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 退货金额
     */
    private Double returnPrice;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改人
     */
    private String modifiedUser;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 单号类型（退货单/换货单）
     */
    private String returnType;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 渠道订单号
     */
    private String channelCode;

    /**
     * 退货商品列表
     */
    private List<TbReturnGoods> tbReturnGoods;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public List<TbReturnGoods> getTbReturnGoods() {
        return tbReturnGoods;
    }

    public void setTbReturnGoods(List<TbReturnGoods> tbReturnGoods) {
        this.tbReturnGoods = tbReturnGoods;
    }
}
