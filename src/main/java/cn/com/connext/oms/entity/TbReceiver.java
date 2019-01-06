package cn.com.connext.oms.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "oms.tb_receiver")
public class TbReceiver {
    /**
     * 收货id
     */
    @Id
    @Column(name = "receiver_id")
    private Integer receiverId;

    /**
     * 收货名字
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人号码
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 收货人移动号码
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 省
     */
    @Column(name = "receiver_state")
    private String receiverState;

    /**
     * 市
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 区
     */
    @Column(name = "receiver_district")
    private String receiverDistrict;

    /**
     * 详细地址
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 邮编
     */
    @Column(name = "receiver_zip")
    private String receiverZip;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 获取收货id
     *
     * @return receiver_id - 收货id
     */
    public Integer getReceiverId() {
        return receiverId;
    }

    /**
     * 设置收货id
     *
     * @param receiverId 收货id
     */
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 获取收货名字
     *
     * @return receiver_name - 收货名字
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货名字
     *
     * @param receiverName 收货名字
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取收货人号码
     *
     * @return receiver_phone - 收货人号码
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置收货人号码
     *
     * @param receiverPhone 收货人号码
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取收货人移动号码
     *
     * @return receiver_mobile - 收货人移动号码
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 设置收货人移动号码
     *
     * @param receiverMobile 收货人移动号码
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * 获取省
     *
     * @return receiver_state - 省
     */
    public String getReceiverState() {
        return receiverState;
    }

    /**
     * 设置省
     *
     * @param receiverState 省
     */
    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    /**
     * 获取市
     *
     * @return receiver_city - 市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * 设置市
     *
     * @param receiverCity 市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    /**
     * 获取区
     *
     * @return receiver_district - 区
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    /**
     * 设置区
     *
     * @param receiverDistrict 区
     */
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    /**
     * 获取详细地址
     *
     * @return receiver_address - 详细地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置详细地址
     *
     * @param receiverAddress 详细地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * 获取邮编
     *
     * @return receiver_zip - 邮编
     */
    public String getReceiverZip() {
        return receiverZip;
    }

    /**
     * 设置邮编
     *
     * @param receiverZip 邮编
     */
    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
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
}