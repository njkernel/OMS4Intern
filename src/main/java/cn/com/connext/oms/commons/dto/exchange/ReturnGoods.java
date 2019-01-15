package cn.com.connext.oms.commons.dto.exchange;

/**
 * @created with IDEA
 * @author: yonyong
 * @version: 1.0.0
 * @date: 2019/1/15
 * @time: 12:24
 * @describe: 用于前台展示换货单商品详细信息
 **/
public class ReturnGoods {
    private int goodId;
    private int orderId;
    private String goodCode;
    private String goodName;
    private int returnNum;
    private double returnPrice;

    public ReturnGoods() {
    }

    public ReturnGoods(int goodId, int orderId, String goodCode, String goodName, int returnNum, double returnPrice) {
        this.goodId = goodId;
        this.orderId = orderId;
        this.goodCode = goodCode;
        this.goodName = goodName;
        this.returnNum = returnNum;
        this.returnPrice = returnPrice;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    public double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(double returnPrice) {
        this.returnPrice = returnPrice;
    }
}
