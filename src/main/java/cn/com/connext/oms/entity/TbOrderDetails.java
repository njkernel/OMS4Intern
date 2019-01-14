package cn.com.connext.oms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * <p>Title: TbOrderDetails</p>
 * <p>Description:出库单所有商品详情字段 </p>
 *
 * @author Jay
 * @version 1.0.0
 * @Date 2019/1/8
 */
public class TbOrderDetails extends TbReceiver {
    private TbGoods tbGoods;
    private TbGoodsOrder tbGoodsOrder;
    private TbOutput tbOutput;

    public TbOutput getTbOutput() {
        return tbOutput;
    }

    public void setTbOutput(TbOutput tbOutput) {
        this.tbOutput = tbOutput;
    }

    public TbGoods getTbGoods() {
        return tbGoods;
    }

    public void setTbGoods(TbGoods tbGoods) {
        this.tbGoods = tbGoods;
    }

    public TbGoodsOrder getTbGoodsOrder() {
        return tbGoodsOrder;
    }

    public void setTbGoodsOrder(TbGoodsOrder tbGoodsOrder) {
        this.tbGoodsOrder = tbGoodsOrder;
    }
    /**
     * 商品id
     */
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品编号
     */
    @Column(name = "goods_code")
    private String goodsCode;

    /**
     * 商品名字
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private Double goodsPrice;

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品编号
     *
     * @return goods_code - 商品编号
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 设置商品编号
     *
     * @param goodsCode 商品编号
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    /**
     * 获取商品名字
     *
     * @return goods_name - 商品名字
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名字
     *
     * @param goodsName 商品名字
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品单价
     *
     * @return goods_price - 商品单价
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品单价
     *
     * @param goodsPrice 商品单价
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 订单商品关系表id
     */
    @Id
    @Column(name = "goods_order_id")
    private Integer goodsOrderId;


    /**
     * 商品个数
     */
    private Integer num;

    /**
     * 下单这类产品的总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    /**
     * 获取订单商品关系表id
     *
     * @return goods_order_id - 订单商品关系表id
     */
    public Integer getGoodsOrderId() {
        return goodsOrderId;
    }

    /**
     * 设置订单商品关系表id
     *
     * @param goodsOrderId 订单商品关系表id
     */
    public void setGoodsOrderId(Integer goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }


    /**
     * 获取商品个数
     *
     * @return num - 商品个数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置商品个数
     *
     * @param num 商品个数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取下单这类产品的总价
     *
     * @return total_price - 下单这类产品的总价
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置下单这类产品的总价
     *
     * @param totalPrice 下单这类产品的总价
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

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
    @Column(name = "remark")    private String remark;


    /**
     * 备注
     */
    @Column(name = "modified_user")
    private String  modifiedUser;


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
